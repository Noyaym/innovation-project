import numpy as np
import math
from scipy.optimize import fsolve


def calculate_camera_location(angle1, distance1, angle2, distance2, angle3, distance3):
    # c onvert angles to radians because  trigonometric functions in pythons math library expect angles to be in radians.
    angle1_rad = math.radians(angle1)
    angle2_rad = math.radians(angle2)
    angle3_rad = math.radians(angle3)

    # Calculate object coordinates
    x1 = distance1 * math.cos(angle1_rad)
    y1 = distance1 * math.sin(angle1_rad)

    x2 = distance2 * math.cos(angle2_rad)
    y2 = distance2 * math.sin(angle2_rad)

    x3 = distance3 * math.cos(angle3_rad)
    y3 = distance3 * math.sin(angle3_rad)

    def equations(variables):
        x, y = variables
        eq1 = (x - x1) ** 2 + (y - y1) ** 2 - distance1 ** 2
        eq2 = (x - x2) ** 2 + (y - y2) ** 2 - distance2 ** 2
        eq3 = (x - x3) ** 2 + (y - y3) ** 2 - distance3 ** 2
        return [eq1, eq2, eq3]

    # Initial guess for the camera location (can be any reasonable values)
    initial_guess = [0, 0]

    # Solve the equation system using fsolve
    result = fsolve(equations, initial_guess)
    camera_x, camera_y = result

    return camera_x, camera_y
