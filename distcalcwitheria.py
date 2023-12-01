import cv2
import numpy as np

lower_color = np.array([9,117,191])
upper_color = np.array([23,210,255])

framee = cv2.VideoCapture(0)

while (1):
    ret1, frame = framee.read()
    # Convert the frame to HSV
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # Threshold the HSV image to get only the desired colors
    mask = cv2.inRange(hsv, lower_color, upper_color)

    # Bitwise-AND mask and original image
    res = cv2.bitwise_and(frame,frame, mask= mask)

    # Find contours in the masked image
    contours, _ = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    # Draw bounding rectangles around the detected contours
    x, y, w, h = 0,0,0,5
    mask_contours, hierarchy = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE) 
    if len(mask_contours) != 0:
        for mask_contour in mask_contours:
            if cv2.contourArea(mask_contour) > 500:
                x, y, w, h = cv2.boundingRect(mask_contour)
                cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)

    KNOWN_DISTANCE = 0.67

    knownHhight = 0.32

    #focalLength = (h * KNOWN_DISTANCE) / knownHhight
    focalLength = 709
    dist = (knownHhight * focalLength) / h
    #print(dist)

    cv2.putText(frame, str(dist), (x,y), cv2.FONT_HERSHEY_SIMPLEX, 3, (0, 255, 0), 2, cv2.LINE_AA)
    cv2.imshow('frame',frame)

    key = cv2.waitKey(1)
    if key == ord('q'):
        break

cv2.destroyAllWindows()