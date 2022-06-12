package com.kastrakomnen.hmessenger.model;

import org.junit.Assert;
import org.junit.Test;

public class SetBuilderTest {

    @Test
    public void instantiate() {
        int[][] a = new int[2][3];
        a[0][0] = 0;
        a[0][1] = 1;
        a[0][2] = 2;
        a[1][0] = 3;
        a[1][1] = 4;
        a[1][2] = 5;
        Assert.assertEquals(0, a[0][0]);
    }
}