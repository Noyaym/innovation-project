// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Utils.Piece2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class CameraConstants { //TODO: change constants to ALL CAPS. Like this: IMG_WIDTH
    public static final int FOV = 90; // change later
    public static final int IMG_WIDTH = 1920;
    public static final int IMG_HEIGHT = 1080;
    public static final double CAMERA1_OFFSET = 0;
    public static final double CAMERA2_OFFSET = 120;
  }
  public static class Pieces {
    public static Piece2d[] TEST_POSE2D_ARRAY = {
      new Piece2d("Cone", new Pose2d(1.0, 2.0, Rotation2d.fromDegrees(0.0))),
      new Piece2d("Corner", new Pose2d(3.0, 4.0, Rotation2d.fromDegrees(15.0))),
      new Piece2d("Pole", new Pose2d(5.0, 6.0, Rotation2d.fromDegrees(20.0))),
      new Piece2d("Charger", new Pose2d(7.0, 8.0, Rotation2d.fromDegrees(35.0))),
    };
  }
}
