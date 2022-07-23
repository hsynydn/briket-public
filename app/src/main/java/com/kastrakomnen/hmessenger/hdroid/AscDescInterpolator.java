package com.kastrakomnen.hmessenger.hdroid;

import android.view.animation.Interpolator;

public class AscDescInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float v) {
        return (float) (-1f * (Math.pow(v, 2)));
    }
}
