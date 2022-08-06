package com.kastrakomnen.hmessenger.model;

import com.kastrakomnen.hmessenger.model.set.FormationType;
import com.kastrakomnen.hmessenger.model.stat.Distribution;
import com.kastrakomnen.hmessenger.model.stat.DistributionType;

import org.junit.Test;

import java.util.ArrayList;

public class DistributionTest {

    @Test
    public void constructor1(){
        ArrayList<Integer> weights = new ArrayList<>();
        weights.add(10);
        weights.add(2);
        weights.add(4);
        weights.add(2);
        weights.add(2);

        ArrayList<FormationType> elements = new ArrayList<>();
        elements.add(FormationType.T_CW0);
        elements.add(FormationType.BOX_CW0);
        elements.add(FormationType.L_CW0);
        elements.add(FormationType.LINE_CW0);
        elements.add(FormationType.RL_CW0);

        Distribution<FormationType> distribution = new Distribution<>(weights, elements);

        int nof_box_cw0 = 0;
        int nof_l_cw0 = 0;
        int nof_line_cw0 = 0;
        int nof_rl_cw0 = 0;
        int nof_t_cw0 = 0;

        for (int i = 0; i < distribution.getDistributionMap().size(); i++) {
            switch (distribution.next()){
                case BOX_CW0:
                    nof_box_cw0++;
                    break;
                case L_CW0:
                    nof_l_cw0++;
                    break;
                case LINE_CW0:
                    nof_line_cw0++;
                    break;
                case RL_CW0:
                    nof_rl_cw0++;
                case T_CW0:
                    nof_t_cw0++;
                    break;
            }
        }

        System.out.println("nof_box_cw0 = " + nof_box_cw0);
        System.out.println("nof_l_cw0 = " + nof_l_cw0);
        System.out.println("nof_line_cw0 = " + nof_line_cw0);
        System.out.println("nof_rl_cw0 = " + nof_rl_cw0);
        System.out.println("nof_t_cw0 = " + nof_t_cw0);
    }

    @Test
    public void constructor2(){
        ArrayList<FormationType> elements = new ArrayList<>();
        elements.add(FormationType.T_CW0);
        elements.add(FormationType.BOX_CW0);
        elements.add(FormationType.L_CW0);
        elements.add(FormationType.LINE_CW0);
        elements.add(FormationType.RL_CW0);

        Distribution<FormationType> distribution = new Distribution<>(DistributionType.UNIFORM, elements);

        int nof_box_cw0 = 0;
        int nof_l_cw0 = 0;
        int nof_line_cw0 = 0;
        int nof_rl_cw0 = 0;
        int nof_t_cw0 = 0;

        for (int i = 0; i < distribution.getDistributionMap().size(); i++) {
            switch (distribution.next()){
                case BOX_CW0:
                    nof_box_cw0++;
                    break;
                case L_CW0:
                    nof_l_cw0++;
                    break;
                case LINE_CW0:
                    nof_line_cw0++;
                    break;
                case RL_CW0:
                    nof_rl_cw0++;
                case T_CW0:
                    nof_t_cw0++;
                    break;
            }
        }

        System.out.println("nof_box_cw0 = " + nof_box_cw0);
        System.out.println("nof_l_cw0 = " + nof_l_cw0);
        System.out.println("nof_line_cw0 = " + nof_line_cw0);
        System.out.println("nof_rl_cw0 = " + nof_rl_cw0);
        System.out.println("nof_t_cw0 = " + nof_t_cw0);
    }
}