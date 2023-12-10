from ultralytics import YOLO
import cv2

model = YOLO("yolov8n.pt")
cap1 = cv2.VideoCapture(0)  
cap1.set(3, 640)
cap1.set(4, 480)


while True:
    _, frame1 = cap1.read()

    img1 = cv2.cvtColor(frame1, cv2.COLOR_BGR2RGB)

    results1 = model.predict(img1)





    if cv2.waitKey(1) & 0xFF == ord(' '):
        break

cap1.release()
cv2.destroyAllWindows()