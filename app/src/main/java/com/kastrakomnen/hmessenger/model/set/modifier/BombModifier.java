package com.kastrakomnen.hmessenger.model.set.modifier;

import com.kastrakomnen.hmessenger.model.set.BrickType;
import com.kastrakomnen.hmessenger.model.set.Set;

public class BombModifier extends SetModifier{
    @Override
    public void modify(Set set) {
        int index = super.selectIndex(set.getBricks().size());
        set.getBricks().get(index).setBrickType(BrickType.SPOTTER);
    }
}
