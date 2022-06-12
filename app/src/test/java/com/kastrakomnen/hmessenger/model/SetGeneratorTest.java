package com.kastrakomnen.hmessenger.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SetGeneratorTest {

    @Test
    public void generate(){
        SetGenerator setGenerator = new SetGenerator(
                new Distribution<FormationType>(
                        DistributionType.UNIFORM,
                        new ArrayList<>(
                                List.of(
                                        FormationType.BOX_CW0,
                                        FormationType.L_CW90,
                                        FormationType.LINE_CW0,
                                        FormationType.RL_CW0
                                )
                        )
                )
        );
    }

}