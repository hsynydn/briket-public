package com.kastrakomnen.hmessenger.model;

import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.set.Set;
import com.kastrakomnen.hmessenger.model.set.SetGenerator;
import com.kastrakomnen.hmessenger.model.stat.Distribution;
import com.kastrakomnen.hmessenger.model.stat.DistributionType;

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

        Set set = setGenerator.generate();
        System.out.println("");
    }

}