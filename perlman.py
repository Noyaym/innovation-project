# חתול ☺
#      ▄▀▄     ▄▀▄
#     ▄█░░▀▀▀▀▀░░█▄
# ▄▄  █░░░░░░░░░░░█  ▄▄
#█▄▄█ █░░▀░░┬░░▀░░█ █▄▄█

import numpy as np

def Angle_calculation(fov, x, y, h, w ,frame):
    X_Angle = (frame.shape[0]/fov[0])*np.abs((frame.shape[0]/2) - (x+(int(w/2))))
    Y_Angle = (frame.shape[1]/fov[1])*np.abs((frame.shape[1]/2) - (y+(int(h/2))))
