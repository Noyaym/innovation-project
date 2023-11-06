package frc.robot.Utils;

public class FieldPiece2 {

    private String name;
    private double angle;

    public FieldPiece2(String name, double angle) {
        this.name = name;
        this.angle = angle;

    }


    public FieldPiece2() {
        this.name = "Empty";
        this.angle = 0;

    }

    public String getPieceName() {
        return this.name;
    }

    public void setPieceName(String name) {
        this.name = name;
    }

     public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return this.angle;
    }

    public boolean isIdenticalName(String other) {
        return (this.name.equals(other));
    }

     public boolean isIdenticalPiece(FieldPiece other) {
        return (this.name.equals(other.getPieceName()));
    }

    public FieldPiece2[] findSimilair(FieldPiece2[] fieldPieces, String name) {
        int count = 0;
        FieldPiece2 fieldPiece;
        FieldPiece2[] allSim = new FieldPiece2[fieldPieces.length];
        for (int i = 0; i<fieldPieces.length; i++) {
            allSim[i] = new FieldPiece2();
        }

        for (int i = 0; i<fieldPieces.length; i++) {
            fieldPiece = fieldPieces[i];
            if (fieldPiece.isIdenticalName(name)) {
                allSim[i] = fieldPiece;
                count+=1;
                fieldPiece.setPieceName("-");
            }
        }
        FieldPiece2[] toReturn = new FieldPiece2[count];
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

    public FieldPiece2 averageAngle(FieldPiece2[] fieldPieces) {
        double sum = 0;
        double angle;
        for (int i = 0; i<fieldPieces.length; i++) {
            angle = fieldPieces[i].getAngle();
            sum = sum + angle;
        }
        sum = sum/fieldPieces.length;

        return new FieldPiece2(fieldPieces[0].getPieceName(), sum);
    }

    public FieldPiece2[] removeCopies(FieldPiece2[] fieldPieces) {
        FieldPiece2 fieldPiece;
        int count = 0;
        for(int i = 0; i < fieldPieces.length; i++) {
            if (!fieldPieces[i].isIdenticalName("-")) {
                count+=1;
                fieldPiece = averageAngle(findSimilair(fieldPieces, fieldPieces[i].getPieceName()));
                fieldPieces[i] = fieldPiece;
            }
        }
        FieldPiece2[] toReturn = new FieldPiece2[count];
        int x = 0;
        for(int i = 0; i < fieldPieces.length; i++) {
            if (!fieldPieces[i].isIdenticalName("-")) {
                toReturn[x] = fieldPieces[i];
            }
        }
        return toReturn;

    }
    
}
