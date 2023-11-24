import os
import shutil
from PIL import Image, ImageEnhance
import random

# def convert_to_pytorch(label, img, x, y, width, height):
#     # Converts the coordinates to yolo(pytorch) format
#     x_normalized = x / img.shape[0]
#     y_normalized = y / img.shape[1]
#     width_normalized = width / img.shape[0]
#     height_normalized = height / img.shape[1]

#     # Sets the format for printing
#     annotation = f"{label} {x_normalized} {y_normalized} {width_normalized} {height_normalized}"

#     return annotation

def place_random_image(annotation, base_image, folder_path):
   # Split the annotation string into its components
   annotation_parts = annotation.split()
   label = annotation_parts[0]
   x_normalized = float(annotation_parts[1])
   y_normalized = float(annotation_parts[2])
   width_normalized = float(annotation_parts[3])
   height_normalized = float(annotation_parts[4])

   # Converts the yolo(pytorch) to coordinates format
   x = x_normalized * base_image.size[0]
   y = y_normalized * base_image.size[1]
   width = width_normalized * base_image.size[0]
   height = height_normalized * base_image.size[1]

   # Get a list of all images in the folder
   images = [os.path.join(folder_path, f) for f in os.listdir(folder_path) if f.endswith('.jpg') or f.endswith('.png')]
  
   # Select a random image
   selected_image_path = random.choice(images)
  
   # Open the selected image and get its size
   selected_image = Image.open(selected_image_path)
   selected_image_width, selected_image_height = selected_image.size

   # Calculate a random position on the base image where the selected image can be placed
   random_x = random.randint(0, base_image.size[0] - selected_image_width)
   random_y = random.randint(0, base_image.size[1] - selected_image_height)

   # Ensure the selected image does not cover the specified area
   if (random_x > x and random_x < x + width) or (random_y > y and random_y < y + height):
       place_random_image(x, y, width, height, base_image, folder_path)
   else:
       # Paste the selected image onto the base image at the calculated position
       base_image.paste(selected_image, (random_x, random_y))

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
          
          # Read the annotation file
          annotation_filename = os.path.join(labels_folder, f"{os.path.splitext(filename)[0]}.txt")
          with open(annotation_filename, 'r') as f:
              annotation = f.readline()

          # Call the place_random_image function
          place_random_image(annotation, img_enhanced, 'C:\\Users\\noamp\\Desktop\\frc2024\\innovation-project\\datasetgobighelper')

  # Duplicate labels
  for filename in os.listdir(labels_folder):
      src = os.path.join(labels_folder, filename)
      for i in range(1, 4):
          dst = os.path.join(labels_folder, f"{filename}_{i}.txt")
          shutil.copy2(src, dst)


britnes_chaneg('C:\\Users\\noamp\\Desktop\\datasetRaeal - Copy\\test')