from ultralytics import YOLO
import cv2
import math 
# import ntcore


# start webcam
cap = cv2.VideoCapture(0)
cap.set(3, 640)
cap.set(4, 480)

# inst = ntcore.NetworkTableInstance.getDefault()

# inst.startClient4("example client")

# # connect to a roboRIO with team number TEAM
# inst.setServerTeam(5635)

# # starting a DS client will try to get the roboRIO address from the DS application
# inst.startDSClient()

# inst.setServer("host", ntcore.NetworkTableInstance.kDefaultPort4)

# # Get the table within that instance that contains the data. Correct the table name.
# table = inst.getTable("datable")
print("ok")

# model
model = YOLO('cubeholder.pt')

# object classes
classNames = ["cubeholder"
              ]

print("ok2")


while True:
    success, img = cap.read()
    results = model(img, stream=True)

    print("ok3")

    # coordinates
    for r in results:
        print("ok4")
        boxes = r.boxes

        for box in boxes:
            print("ok5")
            # bounding box
            x1, y1, x2, y2 = box.xyxy[0]
            x1, y1, x2, y2 = int(x1), int(y1), int(x2), int(y2) # convert to int values

            # put box in cam
            cv2.rectangle(img, (x1, y1), (x2, y2), (255, 0, 255), 3)

            # confidence
            confidence = math.ceil((box.conf[0]*100))/100
            print("Confidence --->",confidence)

            # class name
            cls = int(box.cls[0])
            print("Class name -->", classNames[cls])

            # object details
            org = [x1, y1]
            font = cv2.FONT_HERSHEY_SIMPLEX
            fontScale = 1
            color = (255, 0, 0)
            thickness = 2

            cv2.putText(img, classNames[cls], org, font, fontScale, color, thickness)

    cv2.imshow('Webcam', img)
    if cv2.waitKey(1) == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()