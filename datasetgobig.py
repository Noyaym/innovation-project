import os
import shutil
from PIL import Image, ImageEnhance
import random

def convert_to_pytorch(label, img, x, y, width, height):
    # Converts the coordinates to yolo(pytorch) format
    x_normalized = x / img.shape[0]
    y_normalized = y / img.shape[1]
    width_normalized = width / img.shape[0]
    height_normalized = height / img.shape[1]

    # Sets the format for printing
    annotation = f"{label} {x_normalized} {y_normalized} {width_normalized} {height_normalized}"

    return annotation
def convert_to_cirdinats(annotation, base_image):
    # Split the annotation string into its components
    annotation_parts = annotation.split()
    label = annotation_parts[0]
    x_normalized = float(annotation_parts[1])
    y_normalized = float(annotation_parts[2])
    width_normalized = float(annotation_parts[3])
    height_normalized = float(annotation_parts[4])

    # Converts the yolo(pytorch) to coordinates format
    x = x_normalized * base_image.shape[0]
    y = y_normalized * base_image.shape[1]
    width = width_normalized * base_image.shape[0]
    height = height_normalized * base_image.shape[1]

    return label, x, y, width, height


def britnes_chaneg(folder):   
 # Define folders
 images_folder = os.path.join(folder, 'images')
 labels_folder = os.path.join(folder, 'labels')

 # Duplicate images
 for filename in os.listdir(images_folder):
     src = os.path.join(images_folder, filename)
     for i in range(1, 4):
         dst = os.path.join(images_folder, f"{filename}_{i}.png")
         shutil.copy2(src, dst)
         img = Image.open(dst)
         enhancer = ImageEnhance.Brightness(img)
         random_brightness = random.uniform(0.1, 1.9)
         img_enhanced = enhancer.enhance(random_brightness)
         img_enhanced.save(dst)
         

 # Duplicate labels
 for filename in os.listdir(labels_folder):
     src = os.path.join(labels_folder, filename)
     for i in range(1, 4):
         dst = os.path.join(labels_folder, f"{filename}_{i}.txt")
         shutil.copy2(src, dst)


britnes_chaneg('C:\\Users\\noamp\\Desktop\\datasetRaeal - Copy\\test')