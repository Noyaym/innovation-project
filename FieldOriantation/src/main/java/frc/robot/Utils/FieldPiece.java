package frc.robot.Utils;

public class FieldPiece {

    private String name;
    private double[] boundingBox;

    public FieldPiece(String name, double x1, double y1, double x2, double y2) {
        this.name = name;
        this.boundingBox = new double[4];
        boundingBox[0] = x1;
        boundingBox[1] = y1;
        boundingBox[2] = x2;
        boundingBox[3] = y2;

    }

    public FieldPiece(String name, double[] boundingBox) {
        this.name = name;
        this.boundingBox = boundingBox;
    }

    public FieldPiece() {
        this.name = "Empty";
        boundingBox[0] = 0;
        boundingBox[1] = 0;
        boundingBox[2] = 0;
        boundingBox[3] = 0;

    }

    public String getPieceName() {
        return this.name;
    }

    public void setPieceName(String name) {
        this.name = name;
    }

     public void setPieceBoundingBox(double x1, double y1, double x2, double y2) {
        boundingBox[0] = x1;
        boundingBox[1] = y1;
        boundingBox[2] = x2;
        boundingBox[3] = y2;
    }

    public double[] getBoundingBox() {
        return this.boundingBox;
    }

    public boolean isIdenticalName(String other) {
        return (this.name.equals(other));
    }

     public boolean isIdenticalPiece(FieldPiece other) {
        return (this.name.equals(other.getPieceName()));
    }

    public FieldPiece[] findSimilair(FieldPiece[] fieldPieces, String name) {
        int count = 0;
        FieldPiece fieldPiece;
        FieldPiece[] allSim = new FieldPiece[fieldPieces.length];
        for (int i = 0; i<fieldPieces.length; i++) {
            allSim[i] = new FieldPiece();
        }

        for (int i = 0; i<fieldPieces.length; i++) {
            fieldPiece = fieldPieces[i];
            if (fieldPiece.isIdenticalName(name)) {
                allSim[i] = fieldPiece;
                count+=1;
                fieldPiece.setPieceName("-");
            }
        }
        FieldPiece[] toReturn = new FieldPiece[count];
        int x = 0;
         for (int i = 0; i<fieldPieces.length; i++) {
            fieldPiece = allSim[i];
            if (fieldPiece.isIdenticalName(name)) {
                toReturn[x] = fieldPiece;
                x+=1;
            }
        }
        return toReturn;

    }

    public FieldPiece averageBoundingBox(FieldPiece[] fieldPieces) {
        double[] sum = new double[4];
        double[] bndbx;
        for (int i = 0; i<fieldPieces.length; i++) {
            bndbx = fieldPieces[i].getBoundingBox();
            sum[0] = sum[0] + bndbx[0];
            sum[1] = sum[1] + bndbx[1];
            sum[2] = sum[2] + bndbx[2];
            sum[3] = sum[3] + bndbx[3];
        }
        sum[0] = sum[0]/fieldPieces.length;
        sum[1] = sum[1]/fieldPieces.length;
        sum[2] = sum[2]/fieldPieces.length;
        sum[3] = sum[3]/fieldPieces.length;

        return new FieldPiece(fieldPieces[0].getPieceName(), sum);
    }

    public FieldPiece[] removeCopies(FieldPiece[] fieldPieces) {
        FieldPiece fieldPiece;
        int count = 0;
        for(int i = 0; i < fieldPieces.length; i++) {
            if (!fieldPieces[i].isIdenticalName("-")) {
                count+=1;
                fieldPiece = averageBoundingBox(findSimilair(fieldPieces, fieldPieces[i].getPieceName()));
                fieldPieces[i] = fieldPiece;
            }
        }
        FieldPiece[] toReturn = new FieldPiece[count];
        int x = 0;
        for(int i = 0; i < fieldPieces.length; i++) {
            if (!fieldPieces[i].isIdenticalName("-")) {
                toReturn[x] = fieldPieces[i];
            }
        }
        return toReturn;

    }


}
