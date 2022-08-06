package com.kastrakomnen.hmessenger.model.set.modifier;

import com.kastrakomnen.hmessenger.model.set.BrickType;

public interface SetModifierFactory {
    SetModifier createProduct(BrickType brickType);
}
