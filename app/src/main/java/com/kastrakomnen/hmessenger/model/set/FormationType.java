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
    T_CW0(1),
    T_CW90(1),
    T_CW180(1),
    T_CW270(1),
    LINE_CW0(1),
    LINE_CW90(1),
    LINE_CW180(1),
    LINE_CW270(1),
    Z_CW0(1),
    Z_CW90(1),
    Z_CW180(1),
    Z_CW270(1),
    RL_CW0(1),
    RL_CW90(1),
    RL_CW180(1),
    RL_CW270(1);

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
