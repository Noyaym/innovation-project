package frc.robot.Utils;

import frc.robot.Constants.CameraConstants;;


public class Utils {

    public double getAngle(double[] boundingBox) {
        // Extract coordinates from bounding box
        double x1 = boundingBox[0];
        double x2 = boundingBox[2];
        // Calculate the center of the bounding box
        double objectCenterX = (x1 + x2) / 2;
        // Calculate the horizontal angle of the object relative to the center of the image
        double imageCenterX = CameraConstants.img_width / 2;
        double angleToCenter = Math.toDegrees(Math.atan((objectCenterX - imageCenterX) / (CameraConstants.img_width / (2 * Math.tan(Math.toRadians(CameraConstants.fov / 2))))));
        // Calculate the total angle relative to the camera's facing direction
        double totalAngle = angleToCenter + (CameraConstants.fov / 2);
        return totalAngle;
    }

    public FieldPiece2 fp1_to_fp2(FieldPiece fp1) {
        return new FieldPiece2(fp1.getPieceName(), getAngle(fp1.getBoundingBox()));
    }

    public FieldPiece2 fieldOrient(FieldPiece2 fPiece, int cam, double gyro) {
        
        return null;
    }
}
