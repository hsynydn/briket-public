package com.kastrakomnen.hmessenger.model;

import junit.framework.TestCase;

public class FormationTest extends TestCase {

    public void testInsert() {
        Formation formation = new Formation();
        assertEquals(0, formation.size());
        formation.insert(new RelativePosition(0,0));
        assertEquals(1, formation.size());
        formation.insert(new RelativePosition(1,0));
        assertEquals(2, formation.size());
    }

    public void testSize() {
        Formation formation = new Formation();
        assertEquals(0, formation.size());
    }
}