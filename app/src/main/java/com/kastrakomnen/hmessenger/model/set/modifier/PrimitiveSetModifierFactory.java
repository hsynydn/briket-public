package com.kastrakomnen.hmessenger.model.set.modifier;

import com.kastrakomnen.hmessenger.model.set.BrickType;

public class PrimitiveSetModifierFactory implements SetModifierFactory{
    @Override
    public SetModifier createProduct(BrickType brickType) {
        switch (brickType){
            case COIN:
                return new CoinModifier();
            case STAR:
                return new StarModifier();
            default:
                throw new IllegalArgumentException("Modifier not implemented yet");
        }
    }
}
