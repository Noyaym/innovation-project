package frc.robot.Utils;

import edu.wpi.first.math.geometry.Pose2d;

public class Piece2d {
    public String name;
    public Pose2d pose;
    public Piece2d(String name, Pose2d pose) {
        this.name = name;
        this.pose = pose;
    }
}
