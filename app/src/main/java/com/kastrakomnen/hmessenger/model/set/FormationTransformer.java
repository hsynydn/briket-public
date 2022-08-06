package com.kastrakomnen.hmessenger.model.set;

import com.kastrakomnen.hmessenger.model.Position;
import com.kastrakomnen.hmessenger.model.RelativePosition;
import com.kastrakomnen.hmessenger.model.RotateType;

public class FormationTransformer {

    private static final int[][] rotateMatrixCW90 =
            {
                    {
                        (int) Math.cos(Math.toRadians(270)),
                            (int) (-1 * Math.sin(Math.toRadians(270)))
                    },
                    {
                        (int) Math.sin(Math.toRadians(270)),
                            (int) Math.cos(Math.toRadians(270))
                    }
            };
    private static final int[][] rotateMatrixCW180 =
            {
                    {
                        (int) Math.cos(Math.toRadians(180)),
                            (int) (-1 * Math.sin(Math.toRadians(180)))
                    },
                    {
                        (int) Math.sin(Math.toRadians(180)),
                            (int) Math.cos(Math.toRadians(180))
                    }
            };
    private static final int[][] rotateMatrixCW270 =
            {
                    {
                        (int) Math.cos(Math.toRadians(90)),
                            (int) (-1 * Math.sin(Math.toRadians(90)))
                    },
                    {
                        (int) Math.sin(Math.toRadians(90)),
                            (int) Math.cos(Math.toRadians(90))
                    }
            };
    private static final int[][] rotateMatrixCW360 =
            {
                    {
                        (int) Math.cos(Math.toRadians(0)),
                            (int) (-1 * Math.sin(Math.toRadians(0)))
                    },
                    {
                        (int) Math.sin(Math.toRadians(0)),
                            (int) Math.cos(Math.toRadians(0))
                    }
            };

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
                activeRotationMatrix = rotateMatrixCW360;
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
