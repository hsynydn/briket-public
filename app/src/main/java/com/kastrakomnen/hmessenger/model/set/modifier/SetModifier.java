package com.kastrakomnen.hmessenger.model.set.modifier;

import com.kastrakomnen.hmessenger.model.set.Set;
import com.kastrakomnen.hmessenger.model.stat.PApplierFunction;

import java.util.Random;

public abstract class SetModifier implements PApplierFunction<Boolean, Set> {

    private static final Random random = new Random();

    final int selectIndex(int size){
        return random.nextInt(size);
    }

    @Override
    public Set apply(Boolean aBoolean) {
        return null;
    }

    @Override
    public Set apply(Boolean isApply, Set set) {
        if (isApply){
            modify(set);
        }
        return set;
    }

    abstract void modify(Set set);
}
