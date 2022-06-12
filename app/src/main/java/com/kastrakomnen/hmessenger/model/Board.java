package com.kastrakomnen.hmessenger.model;

public class Board {

    private final int height;
    private final int width;

    private Set[][] boardMap;
    private Set activeSet;
    
    public Board(int height, int width){

        boardMap = new Set[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardMap[i][j] = null;
            }
        }

        activeSet = null;

        this.height = height;
        this.width = width;
    }

    public boolean place(Set set){

        Position basePosition = new RelativePosition(5,2);

        if (activeSet!=null){
            return false;
        }

        /* Board has no space left */
        if (boardMap[basePosition.getY()][basePosition.getX()] != null)
            return false;

        /* Check does all positions in formation suit */
        Formation formation = set.getCurrentFormation();
        for (Position p : formation.getForm() ) {
            if (boardMap[p.getY() + basePosition.getY()][p.getX() + basePosition.getX()] != null){
                return false;
            }
        }

        /* Place the set into the board
         * then let the set know its base position */
        for (Position p : formation.getForm() ) {
            boardMap[p.getY() + basePosition.getY()][p.getX() + basePosition.getX()] = set;
        }
        set.setFormationOrigin(new RelativePosition(5,2));

        activeSet = set;

        return true;
    }

    public boolean moveRight(){

        if (activeSet==null) return false;

        Formation formation = activeSet.getCurrentFormation();

        /* Clean currently occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = null;
        }

        /* Check move space is suitable */
        boolean failFlag = false;
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX() + 1;

            if (colIndex >= width){
                failFlag = true;
            }else{
                if (boardMap[rowIndex][colIndex] != null){
                    failFlag = true;
                }
            }
        }

        /* Restore previous occupied region */
        if (failFlag){
            for (Position p : formation.getForm()) {

                int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
                int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

                boardMap[rowIndex][colIndex] = activeSet;
            }
            return false;
        }

        /* Update base index */
        activeSet.getFormationOrigin().incrementX();

        /* Mark newly occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = activeSet;
        }

        return true;
    }

    public boolean moveLeft(){

        if (activeSet==null) return false;

        Formation formation = activeSet.getCurrentFormation();

        /* Clean currently occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = null;
        }

        /* Check move space is suitable */
        boolean failFlag = false;
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX() - 1;

            if (colIndex < 0){
                failFlag = true;
            }else{
                if (boardMap[rowIndex][colIndex] != null){
                    failFlag = true;
                }
            }

        }

        /* Restore previous occupied region */
        if (failFlag){
            for (Position p : formation.getForm()) {

                int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
                int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

                boardMap[rowIndex][colIndex] = activeSet;
            }
            return false;
        }

        /* Update base index */
        activeSet.getFormationOrigin().decrementX();

        /* Mark newly occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = activeSet;
        }

        return true;
    }

    public boolean moveDown(){

        if (activeSet==null) return false;

        Formation formation = activeSet.getCurrentFormation();

        /* Clean currently occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = null;
        }

        boolean failFlag = false;
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY() + 1;
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            if (rowIndex >= height){
                failFlag = true;
            }else{
                if (boardMap[rowIndex][colIndex] != null){
                    failFlag = true;
                }
            }
        }

        /* Restore previous occupied region */
        if (failFlag){
            for (Position p : formation.getForm()) {

                int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
                int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

                boardMap[rowIndex][colIndex] = activeSet;
            }

            activeSet.setSetState(SetState.NON_MOVE_STATE);
            activeSet = null;

            return false;
        }

        /* Update base index */
        activeSet.getFormationOrigin().incrementY();

        /* Mark newly occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = activeSet;
        }

        return true;
    }

    public boolean rotateCW(){

        if (activeSet==null) return false;

        Formation formation = activeSet.getNextFormation();
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            if (rowIndex >= height || rowIndex < 0){
                return false;
            }

            if (colIndex >= width || colIndex < 0){
                return false;
            }

            if (boardMap[rowIndex][colIndex] != null){
                return false;
            }
        }

        /* Clean currently occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = null;
        }

        /* Update base index */
        activeSet.cycleFormationForward();

        /* Mark newly occupied space */
        for (Position p : formation.getForm()) {

            int rowIndex = p.getY() + activeSet.getFormationOrigin().getY();
            int colIndex = p.getX() + activeSet.getFormationOrigin().getX();

            boardMap[rowIndex][colIndex] = activeSet;
        }


        return true;
    }

    public Set getActiveSet(){
        return activeSet;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (boardMap[i][j] == null){
                    stringBuilder.append("─");
                }else{
                    if (boardMap[i][j].getBrickAt(j, i) == null){
                        stringBuilder.append("─");
                    }else{
                        stringBuilder.append("X");
                    }
                }
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
