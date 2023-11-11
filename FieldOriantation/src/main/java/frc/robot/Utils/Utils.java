package frc.robot.Utils;

import frc.robot.Constants.CameraConstants;
import frc.robot.Constants.Pieces;;


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
        // assuming there are 2 cameras adjust the camera offset based on the camera number:
        double cameraOffset = (cam == 1) ? CameraConstants.camera1Offset : CameraConstants.camera2Offset;
        // adjust the robot offset based on the gyro reading:
        double robotOffset = gyro;
        // Claculate the field-oriented angle by considering camera and robot offsets:
        double fieldOrientedAngle = fPiece.getAngle() + cameraOffset - robotOffset;
        // ensur e the angle is within the range [0, 360):
        fieldOrientedAngle = (fieldOrientedAngle + 360) % 360;
        // return  a new FieldPiece2 object with the updated angle
        return new FieldPiece2(fPiece.getPieceName(), fieldOrientedAngle);
    }
    
    public static double[] calculateLineEquation(FieldPiece2 fieldPiece, Piece2d[] pose2dArray) {
        double m, b;
        // Find the corresponding Piece2d object for the given FieldPiece2
        Piece2d matchingPiece = null;
        for (Piece2d piece : pose2dArray) {
            if (fieldPiece.getPieceName().equals(piece.name)) {
                matchingPiece = piece;
                break;
            }
        }

        if (matchingPiece != null) {
            //calculate the slope (m) and y-intercept (b) of the line equation:
            double x1 = matchingPiece.pose.getX();
            double y1 = matchingPiece.pose.getY();
            double x2 = matchingPiece.pose.getX() + Math.cos(Math.toRadians(fieldPiece.getAngle()));
            double y2 = matchingPiece.pose.getY() + Math.sin(Math.toRadians(fieldPiece.getAngle()));
            m = (y2 - y1) / (x2 - x1);
            b = y1 - m * x1;
            // return the parameters as an array [m, b]
            return new double[]{m, b};
        } else {
            //handle the case where no matching Piece2d is found
            return null;
        }
    }

    public static double[] findIntersection(double[] line1, double[] line2) {
        double m1 = line1[0];
        double b1 = line1[1];
        double m2 = line2[0];
        double b2 = line2[1];
        // if ines are parallel, then theres no intersection
        if (m1 == m2) {
            return null;
        } else {
            //calculate intersection point coordinates (x, y)
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;
            // Return the intersection point as an array [x, y]
            return new double[]{x, y};
        }
    }

    public static double[] averageIntersectionPoints(FieldPiece2[] fieldPieces) {
        int numPieces = fieldPieces.length;

        // Calculate the number of combinations
        int numCombinations = numPieces * (numPieces - 1) * (numPieces - 2) / 6;

        // Initialize arrays to store intersection points
        double[] intersectionX = new double[numCombinations];
        double[] intersectionY = new double[numCombinations];
        int index = 0;

        // Iterate over all combinations of three pieces
        for (int i = 0; i < numPieces - 2; i++) {
            for (int j = i + 1; j < numPieces - 1; j++) {
                for (int k = j + 1; k < numPieces; k++) {
                    // Calculate the intersection point of the lines formed by the three pieces
                    double[] line1 = calculateLineEquation(fieldPieces[i], Pieces.testPose2dArray);
                    double[] line2 = calculateLineEquation(fieldPieces[j], Pieces.testPose2dArray);
                    double[] line3 = calculateLineEquation(fieldPieces[k], Pieces.testPose2dArray);

                    // Find the intersection point of the three lines
                    double[] intersection = findIntersection(findIntersection(line1, line2), line3);

                    // Store the intersection point coordinates
                    if (intersection != null) {
                        intersectionX[index] = intersection[0];
                        intersectionY[index] = intersection[1];
                        index++;
                    }
                }
            }
        }

        // Calculate the average intersection point
        double sumX = 0;
        double sumY = 0;
        for (int i = 0; i < numCombinations; i++) {
            sumX += intersectionX[i];
            sumY += intersectionY[i];
        }

        double averageX = sumX / numCombinations;
        double averageY = sumY / numCombinations;

        // Return the average intersection point as an array [averageX, averageY]
        return new double[]{averageX, averageY};
    }
}
