package com.kastrakomnen.hmessenger.model.set;

public enum FormationType {
    BOX_CW0(1),
    BOX_CW90(1),
    BOX_CW180(1),
    BOX_CW270(1),
    L_CW0(1),
    L_CW90(1),
    L_CW180(1),
    L_CW270(1),
    L3_CW0(1),
    L3_CW90(1),
    L3_CW180(1),
    L3_CW270(1),
    T_CW0(1),
    T_CW90(1),
    T_CW180(1),
    T_CW270(1),
    LINE_CW0(1),
    LINE_CW90(1),
    LINE_CW180(1),
    LINE_CW270(1),
    LINE3_CW0(1),
    LINE3_CW90(1),
    LINE3_CW180(1),
    LINE3_CW270(1),
    LINE5_CW0(1),
    LINE5_CW90(1),
    LINE5_CW180(1),
    LINE5_CW270(1),
    Z_CW0(1),
    Z_CW90(1),
    Z_CW180(1),
    Z_CW270(1),
    RL_CW0(1),
    RL_CW90(1),
    RL_CW180(1),
    RL_CW270(1),
    DIAG2_CW0(1),
    DIAG2_CW90(1),
    DIAG2_CW180(1),
    DIAG2_CW270(1),
    DIAG3_CW0(1),
    DIAG3_CW90(1),
    DIAG3_CW180(1),
    DIAG3_CW270(1),
    MILL_CW0(1),
    MILL_CW90(1),
    MILL_CW180(1),
    MILL_CW270(1),
    HALF_MILL_CW0(1),
    HALF_MILL_CW90(1),
    HALF_MILL_CW180(1),
    HALF_MILL_CW270(1)
    ;

    private int formationToughness;

    FormationType(int formationToughness){
        this.formationToughness = formationToughness;
    }

    public void setFormationToughness(int formationToughness){
        this.formationToughness = formationToughness;
    }

    public int getFormationToughness(){
        return this.formationToughness;
    }
}
