package com.kastrakomnen.hmessenger.model;

public class FormationTransformer {

    private static final int rotateMatrixCW90[][] = {{(int) Math.cos(270), (int) (-1 * Math.sin(270))},{(int) Math.sin(270), (int) Math.cos(270)}};
    private static final int rotateMatrixCW180[][] = {{(int) Math.cos(180), (int) (-1 * Math.sin(180))},{(int) Math.sin(180), (int) Math.cos(180)}};
    private static final int rotateMatrixCW270[][] = {{(int) Math.cos(90), (int) (-1 * Math.sin(90))},{(int) Math.sin(90), (int) Math.cos(90)}};
    private static final int rotateMatrixCW260[][] = {{(int) Math.cos(0), (int) (-1 * Math.sin(0))},{(int) Math.sin(0), (int) Math.cos(0)}};

    public static Formation transform(RotateType rotateType, Formation formation){

        Formation tmp = new Formation();
        int activeRotationMatrix[][] = {};

        switch (rotateType){
            case CW90:
                activeRotationMatrix = rotateMatrixCW90;
                break;
            case CW180:
                activeRotationMatrix = rotateMatrixCW180;
                break;
            case CW270:
                activeRotationMatrix = rotateMatrixCW270;
                break;
            case CW360:
                activeRotationMatrix = rotateMatrixCW260;
                break;
            default:
                throw new IllegalArgumentException("RotateType not valid");
        }

        for (Position p : formation.getForm()) {
            tmp.insert(
                    new RelativePosition(
                            p.getX() * activeRotationMatrix[0][0] + p.getY() * activeRotationMatrix[1][0],
                            p.getX() * activeRotationMatrix[0][1] + p.getY() * activeRotationMatrix[1][1]
                    )
            );
        }

        return tmp;
    }
}
