import cv2
import numpy as np

# Load the image
image = cv2.imread('path_to_image.jpg')

# Define the brightness value
brightness = 50

# Create a numpy array with the same shape as the image
brightness_matrix = np.ones(image.shape, dtype="uint8") * brightness

# Add the brightness matrix to the image
brightened_image = cv2.add(image, brightness_matrix)

# Display the original and brightened images
cv2.imshow('Original Image', image)
cv2.imshow('Brightened Image', brightened_image)
cv2.waitKey(0)
cv2.destroyAllWindows()