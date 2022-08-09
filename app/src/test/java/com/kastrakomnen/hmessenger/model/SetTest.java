package com.kastrakomnen.hmessenger.model;

import static com.kastrakomnen.hmessenger.model.set.FormationType.L_CW0;

import com.kastrakomnen.hmessenger.model.set.Brick;
import com.kastrakomnen.hmessenger.model.set.Formation;
import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.set.Set;

import org.junit.Assert;
import org.junit.Test;

public class SetTest {

    @Test
    public void constructor() {
        Set set = new Set();
        Assert.assertEquals(0, set.getBricks().size());
        Assert.assertEquals(0, set.getFormations().size());
    }


    @Test
    public void insertFormation() {

        Formation formation1 = new Formation();
        formation1.insert(new RelativePosition(0,0));
        formation1.insert(new RelativePosition(0,1));
        formation1.insert(new RelativePosition(0,2));

        Brick brick = new Brick();

        Set set = new Set();

        set.insert(formation1);

        Assert.assertEquals(1, set.getFormations().size());
        Assert.assertEquals(0, set.getBricks().size());

        Formation formation2 = new Formation();
        formation2.insert(new RelativePosition(0,0));
        formation2.insert(new RelativePosition(0,1));
        formation2.insert(new RelativePosition(0,2));

        set.insert(formation2);

        Assert.assertEquals(2, set.getFormations().size());
        Assert.assertEquals(0, set.getBricks().size());

        Formation formation3 = new Formation();
        formation3.insert(new RelativePosition(0,0));
        formation3.insert(new RelativePosition(0,1));
        formation3.insert(new RelativePosition(0,2));
        formation3.insert(new RelativePosition(0,3));

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            set.insert(formation3);
        });

        Assert.assertEquals(2, set.getFormations().size());
        Assert.assertEquals(0, set.getBricks().size());
    }

    public void insertBrick() {
        Set set = new Set();
        Brick brick1 = new Brick();
        Brick brick2 = new Brick();
        Brick brick3 = new Brick();

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            set.insert(brick1);
        });

        Assert.assertEquals(0, set.getBricks().size());

        Formation formation1 = new Formation();
        formation1.insert(new RelativePosition(0,0));
        formation1.insert(new RelativePosition(0,1));
        formation1.insert(new RelativePosition(0,2));

        Formation formation2 = new Formation();
        formation2.insert(new RelativePosition(0,0));
        formation2.insert(new RelativePosition(0,1));
        formation2.insert(new RelativePosition(0,2));

        set.insert(formation1);
        set.insert(formation2);

        set.insert(brick1);
        set.insert(brick2);

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            set.insert(brick3);
        });

        Assert.assertEquals(2, set.getBricks().size());
    }
}