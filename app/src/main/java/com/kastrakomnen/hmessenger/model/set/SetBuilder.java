package com.kastrakomnen.hmessenger.model.set;

import com.kastrakomnen.hmessenger.model.RelativePosition;
import com.kastrakomnen.hmessenger.model.RotateType;
import com.kastrakomnen.hmessenger.model.stat.PApplierFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class SetBuilder implements PApplierFunction<FormationType, Set> {

    private static final Formation box_cw0;
    private static final Formation box_cw90;
    private static final Formation box_cw180;
    private static final Formation box_cw270;
    private static final ArrayList<Integer> box_cw0_brick_indexer;
    private static final ArrayList<Integer> box_cw90_brick_indexer;
    private static final ArrayList<Integer> box_cw180_brick_indexer;
    private static final ArrayList<Integer> box_cw270_brick_indexer;

    private static final Formation l_cw0;
    private static final Formation l_cw90;
    private static final Formation l_cw180;
    private static final Formation l_cw270;
    private static final ArrayList<Integer> l_cw0_brick_indexer;
    private static final ArrayList<Integer> l_cw90_brick_indexer;
    private static final ArrayList<Integer> l_cw180_brick_indexer;
    private static final ArrayList<Integer> l_cw270_brick_indexer;

    private static final Formation l3_cw0;
    private static final Formation l3_cw90;
    private static final Formation l3_cw180;
    private static final Formation l3_cw270;
    private static final ArrayList<Integer> l3_cw0_brick_indexer;
    private static final ArrayList<Integer> l3_cw90_brick_indexer;
    private static final ArrayList<Integer> l3_cw180_brick_indexer;
    private static final ArrayList<Integer> l3_cw270_brick_indexer;

    private static final Formation t_cw0;
    private static final Formation t_cw90;
    private static final Formation t_cw180;
    private static final Formation t_cw270;
    private static final ArrayList<Integer> t_cw0_brick_indexer;
    private static final ArrayList<Integer> t_cw90_brick_indexer;
    private static final ArrayList<Integer> t_cw180_brick_indexer;
    private static final ArrayList<Integer> t_cw270_brick_indexer;

    private static final Formation line_cw0;
    private static final Formation line_cw90;
    private static final Formation line_cw180;
    private static final Formation line_cw270;
    private static final ArrayList<Integer> line_cw0_brick_indexer;
    private static final ArrayList<Integer> line_cw90_brick_indexer;
    private static final ArrayList<Integer> line_cw180_brick_indexer;
    private static final ArrayList<Integer> line_cw270_brick_indexer;

    private static final Formation line3_cw0;
    private static final Formation line3_cw90;
    private static final Formation line3_cw180;
    private static final Formation line3_cw270;
    private static final ArrayList<Integer> line3_cw0_brick_indexer;
    private static final ArrayList<Integer> line3_cw90_brick_indexer;
    private static final ArrayList<Integer> line3_cw180_brick_indexer;
    private static final ArrayList<Integer> line3_cw270_brick_indexer;

    private static final Formation line5_cw0;
    private static final Formation line5_cw90;
    private static final Formation line5_cw180;
    private static final Formation line5_cw270;
    private static final ArrayList<Integer> line5_cw0_brick_indexer;
    private static final ArrayList<Integer> line5_cw90_brick_indexer;
    private static final ArrayList<Integer> line5_cw180_brick_indexer;
    private static final ArrayList<Integer> line5_cw270_brick_indexer;

    private static final Formation z_cw0;
    private static final Formation z_cw90;
    private static final Formation z_cw180;
    private static final Formation z_cw270;
    private static final ArrayList<Integer> z_cw0_brick_indexer;
    private static final ArrayList<Integer> z_cw90_brick_indexer;
    private static final ArrayList<Integer> z_cw180_brick_indexer;
    private static final ArrayList<Integer> z_cw270_brick_indexer;

    private static final Formation rl_cw0;
    private static final Formation rl_cw90;
    private static final Formation rl_cw180;
    private static final Formation rl_cw270;
    private static final ArrayList<Integer> rl_cw0_brick_indexer;
    private static final ArrayList<Integer> rl_cw90_brick_indexer;
    private static final ArrayList<Integer> rl_cw180_brick_indexer;
    private static final ArrayList<Integer> rl_cw270_brick_indexer;

    private static final Formation diag2_cw0;
    private static final Formation diag2_cw90;
    private static final Formation diag2_cw180;
    private static final Formation diag2_cw270;
    private static final ArrayList<Integer> diag2_cw0_brick_indexer;
    private static final ArrayList<Integer> diag2_cw90_brick_indexer;
    private static final ArrayList<Integer> diag2_cw180_brick_indexer;
    private static final ArrayList<Integer> diag2_cw270_brick_indexer;

    private static final Formation diag3_cw0;
    private static final Formation diag3_cw90;
    private static final Formation diag3_cw180;
    private static final Formation diag3_cw270;
    private static final ArrayList<Integer> diag3_cw0_brick_indexer;
    private static final ArrayList<Integer> diag3_cw90_brick_indexer;
    private static final ArrayList<Integer> diag3_cw180_brick_indexer;
    private static final ArrayList<Integer> diag3_cw270_brick_indexer;

    private static final Formation mill_cw0;
    private static final Formation mill_cw90;
    private static final Formation mill_cw180;
    private static final Formation mill_cw270;
    private static final ArrayList<Integer> mill_cw0_brick_indexer;
    private static final ArrayList<Integer> mill_cw90_brick_indexer;
    private static final ArrayList<Integer> mill_cw180_brick_indexer;
    private static final ArrayList<Integer> mill_cw270_brick_indexer;

    private static final Formation half_mill_cw0;
    private static final Formation half_mill_cw90;
    private static final Formation half_mill_cw180;
    private static final Formation half_mill_cw270;
    private static final ArrayList<Integer> half_mill_cw0_brick_indexer;
    private static final ArrayList<Integer> half_mill_cw90_brick_indexer;
    private static final ArrayList<Integer> half_mill_cw180_brick_indexer;
    private static final ArrayList<Integer> half_mill_cw270_brick_indexer;

    static {

        /* BOX INIT */

        box_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0),
                        new RelativePosition(0, 1),
                        new RelativePosition(1, 1)
                ))
        );

        box_cw90 = FormationTransformer.transform(RotateType.CW90, box_cw0);
        box_cw180 = FormationTransformer.transform(RotateType.CW180, box_cw0);
        box_cw270 = FormationTransformer.transform(RotateType.CW270, box_cw0);

        box_cw0.setFormationType(FormationType.BOX_CW0);
        box_cw90.setFormationType(FormationType.BOX_CW90);
        box_cw180.setFormationType(FormationType.BOX_CW180);
        box_cw270.setFormationType(FormationType.BOX_CW270);

        box_cw0_brick_indexer = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        box_cw90_brick_indexer = new ArrayList<>(Arrays.asList(2, 0, 3, 1));
        box_cw180_brick_indexer = new ArrayList<>(Arrays.asList(3, 2, 1, 0));
        box_cw270_brick_indexer = new ArrayList<>(Arrays.asList(1, 3, 0, 2));

        /* L INIT */

        l_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(0, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(0, 1),
                        new RelativePosition(1, 1)
                ))
        );

        l_cw90 = FormationTransformer.transform(RotateType.CW90, l_cw0);
        l_cw180 = FormationTransformer.transform(RotateType.CW180, l_cw0);
        l_cw270 = FormationTransformer.transform(RotateType.CW270, l_cw0);

        l_cw0.setFormationType(FormationType.L_CW0);
        l_cw90.setFormationType(FormationType.L_CW90);
        l_cw180.setFormationType(FormationType.L_CW180);
        l_cw270.setFormationType(FormationType.L_CW270);

        l_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
                );

        l_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0, 3)
                );

        l_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
                );

        l_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 0, 1, 2)
                );

        /* L3 INIT */

        l3_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(0, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0)
                ))
        );

        l3_cw90 = FormationTransformer.transform(RotateType.CW90, l3_cw0);
        l3_cw180 = FormationTransformer.transform(RotateType.CW180, l3_cw0);
        l3_cw270 = FormationTransformer.transform(RotateType.CW270, l3_cw0);

        l3_cw0.setFormationType(FormationType.L3_CW0);
        l3_cw90.setFormationType(FormationType.L3_CW90);
        l3_cw180.setFormationType(FormationType.L3_CW180);
        l3_cw270.setFormationType(FormationType.L3_CW270);

        l3_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2)
        );

        l3_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(1, 0, 2)
        );

        l3_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0)
        );

        l3_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 0, 1)
        );

        /* T INIT */

        t_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, 0),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0),
                        new RelativePosition(0, 1)
                ))
        );

        t_cw90 = FormationTransformer.transform(RotateType.CW90, t_cw0);
        t_cw180 = FormationTransformer.transform(RotateType.CW180, t_cw0);
        t_cw270 = FormationTransformer.transform(RotateType.CW270, t_cw0);

        t_cw0.setFormationType(FormationType.T_CW0);
        t_cw90.setFormationType(FormationType.T_CW90);
        t_cw180.setFormationType(FormationType.T_CW180);
        t_cw270.setFormationType(FormationType.T_CW270);

        t_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
        );

        t_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 3, 1, 2)
        );

        t_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        t_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 3, 0)
        );

        /* LINE INIT */

        line_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, 0),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0),
                        new RelativePosition(2, 0)
                ))
        );

        line_cw90 = FormationTransformer.transform(RotateType.CW90, line_cw0);
        line_cw180 = FormationTransformer.transform(RotateType.CW180, line_cw0);
        line_cw270 = FormationTransformer.transform(RotateType.CW270, line_cw0);

        line_cw0.setFormationType(FormationType.LINE_CW0);
        line_cw90.setFormationType(FormationType.LINE_CW90);
        line_cw180.setFormationType(FormationType.LINE_CW180);
        line_cw270.setFormationType(FormationType.LINE_CW270);

        line_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
        );

        line_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3)
        );

        line_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        line_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        /* LINE3 INIT */

        line3_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, 0),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0)
                ))
        );

        line3_cw90 = FormationTransformer.transform(RotateType.CW90, line3_cw0);
        line3_cw180 = FormationTransformer.transform(RotateType.CW180, line3_cw0);
        line3_cw270 = FormationTransformer.transform(RotateType.CW270, line3_cw0);

        line3_cw0.setFormationType(FormationType.LINE3_CW0);
        line3_cw90.setFormationType(FormationType.LINE3_CW90);
        line3_cw180.setFormationType(FormationType.LINE3_CW180);
        line3_cw270.setFormationType(FormationType.LINE3_CW270);

        line3_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2)
        );

        line3_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1, 2)
        );

        line3_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0)
        );

        line3_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0)
        );

        /* LINE3 INIT */

        line5_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-2, 0),
                        new RelativePosition(-1, 0),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 0),
                        new RelativePosition(2, 0)
                ))
        );

        line5_cw90 = FormationTransformer.transform(RotateType.CW90, line5_cw0);
        line5_cw180 = FormationTransformer.transform(RotateType.CW180, line5_cw0);
        line5_cw270 = FormationTransformer.transform(RotateType.CW270, line5_cw0);

        line5_cw0.setFormationType(FormationType.LINE5_CW0);
        line5_cw90.setFormationType(FormationType.LINE5_CW90);
        line5_cw180.setFormationType(FormationType.LINE5_CW180);
        line5_cw270.setFormationType(FormationType.LINE5_CW270);

        line5_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4)
        );

        line5_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3, 4)
        );

        line5_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(4, 3, 2, 1, 0)
        );

        line5_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(4, 3, 2, 1, 0)
        );

        /* Z INIT */

        z_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, 0),
                        new RelativePosition(0, 0),
                        new RelativePosition(0, 1),
                        new RelativePosition(1, 1)
                ))
        );

        z_cw90 = FormationTransformer.transform(RotateType.CW90, z_cw0);
        z_cw180 = FormationTransformer.transform(RotateType.CW180, z_cw0);
        z_cw270 = FormationTransformer.transform(RotateType.CW270, z_cw0);

        z_cw0.setFormationType(FormationType.Z_CW0);
        z_cw90.setFormationType(FormationType.Z_CW90);
        z_cw180.setFormationType(FormationType.Z_CW180);
        z_cw270.setFormationType(FormationType.Z_CW270);

        z_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
        );

        z_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 2, 1, 3)
        );

        z_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        z_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 1, 2, 0)
        );

        /* RL INIT */

        rl_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(0, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(-1, 1),
                        new RelativePosition(0, 1)
                ))
        );

        rl_cw90 = FormationTransformer.transform(RotateType.CW90, rl_cw0);
        rl_cw180 = FormationTransformer.transform(RotateType.CW180, rl_cw0);
        rl_cw270 = FormationTransformer.transform(RotateType.CW270, rl_cw0);

        rl_cw0.setFormationType(FormationType.RL_CW0);
        rl_cw90.setFormationType(FormationType.RL_CW90);
        rl_cw180.setFormationType(FormationType.RL_CW180);
        rl_cw270.setFormationType(FormationType.RL_CW270);

        rl_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
        );

        rl_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 3, 1, 0)
        );

        rl_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        rl_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1, 3, 2)
        );

        /* DIAG2 INIT */

        diag2_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 1)
                ))
        );

        diag2_cw90 = FormationTransformer.transform(RotateType.CW90, diag2_cw0);
        diag2_cw180 = FormationTransformer.transform(RotateType.CW180, diag2_cw0);
        diag2_cw270 = FormationTransformer.transform(RotateType.CW270, diag2_cw0);

        diag2_cw0.setFormationType(FormationType.DIAG2_CW0);
        diag2_cw90.setFormationType(FormationType.DIAG2_CW90);
        diag2_cw180.setFormationType(FormationType.DIAG2_CW180);
        diag2_cw270.setFormationType(FormationType.DIAG2_CW270);

        diag2_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1)
        );

        diag2_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1)
        );

        diag2_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(1, 0)
        );

        diag2_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(1, 0)
        );

        /* DIAG3 INIT */

        diag3_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(1, 1)
                ))
        );

        diag3_cw90 = FormationTransformer.transform(RotateType.CW90, diag3_cw0);
        diag3_cw180 = FormationTransformer.transform(RotateType.CW180, diag3_cw0);
        diag3_cw270 = FormationTransformer.transform(RotateType.CW270, diag3_cw0);

        diag3_cw0.setFormationType(FormationType.DIAG3_CW0);
        diag3_cw90.setFormationType(FormationType.DIAG3_CW90);
        diag3_cw180.setFormationType(FormationType.DIAG3_CW180);
        diag3_cw270.setFormationType(FormationType.DIAG3_CW270);

        diag3_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2)
        );

        diag3_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(0, 1, 2)
        );

        diag3_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0)
        );

        diag3_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(2, 1, 0)
        );

        /* MILL INIT */

        mill_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, -1),
                        new RelativePosition(1, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(-1, 1),
                        new RelativePosition(1, 1)
                ))
        );

        mill_cw90 = FormationTransformer.transform(RotateType.CW90, mill_cw0);
        mill_cw180 = FormationTransformer.transform(RotateType.CW180, mill_cw0);
        mill_cw270 = FormationTransformer.transform(RotateType.CW270, mill_cw0);

        mill_cw0.setFormationType(FormationType.MILL_CW0);
        mill_cw90.setFormationType(FormationType.MILL_CW90);
        mill_cw180.setFormationType(FormationType.MILL_CW180);
        mill_cw270.setFormationType(FormationType.MILL_CW270);

        mill_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3, 4)
        );

        mill_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 0, 2, 4, 1)
        );

        mill_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(4, 3, 2, 1, 0)
        );

        mill_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(1, 4, 2, 0, 3)
        );

        /* HALF MILL INIT */

        half_mill_cw0 = new Formation(
                new ArrayList<>(Arrays.asList(
                        new RelativePosition(-1, -1),
                        new RelativePosition(1, -1),
                        new RelativePosition(0, 0),
                        new RelativePosition(-1, 1)
                ))
        );

        half_mill_cw90 = FormationTransformer.transform(RotateType.CW90, half_mill_cw0);
        half_mill_cw180 = FormationTransformer.transform(RotateType.CW180, half_mill_cw0);
        half_mill_cw270 = FormationTransformer.transform(RotateType.CW270, half_mill_cw0);

        half_mill_cw0.setFormationType(FormationType.HALF_MILL_CW0);
        half_mill_cw90.setFormationType(FormationType.HALF_MILL_CW90);
        half_mill_cw180.setFormationType(FormationType.HALF_MILL_CW180);
        half_mill_cw270.setFormationType(FormationType.HALF_MILL_CW270);

        half_mill_cw0_brick_indexer = new ArrayList<Integer>(
                Arrays.asList(0, 1, 2, 3)
        );

        half_mill_cw90_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 0, 2, 1)
        );

        half_mill_cw180_brick_indexer = new ArrayList<>(
                Arrays.asList(3, 2, 1, 0)
        );

        half_mill_cw270_brick_indexer = new ArrayList<>(
                Arrays.asList(1, 2, 0, 3)
        );

    }

    public static Set build(FormationType formationType){

        Set set = new Set();
        set.setSetState(SetState.MOVE_STATE);

        switch (formationType){
            case BOX_CW0:
                set.insert(box_cw0);
                set.setDefaultFormation();
                set.insert(box_cw90);
                set.insert(box_cw180);
                set.insert(box_cw270);

                set.insert(box_cw0_brick_indexer);
                set.insert(box_cw90_brick_indexer);
                set.insert(box_cw180_brick_indexer);
                set.insert(box_cw270_brick_indexer);

                set.setSetType(SetType.BOX);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case BOX_CW90:
                set.insert(box_cw0);
                set.insert(box_cw90);
                set.setDefaultFormation();
                set.insert(box_cw180);
                set.insert(box_cw270);

                set.insert(box_cw0_brick_indexer);
                set.insert(box_cw90_brick_indexer);
                set.insert(box_cw180_brick_indexer);
                set.insert(box_cw270_brick_indexer);

                set.setSetType(SetType.BOX);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case BOX_CW180:
                set.insert(box_cw0);
                set.insert(box_cw90);
                set.insert(box_cw180);
                set.setDefaultFormation();
                set.insert(box_cw270);

                set.insert(box_cw0_brick_indexer);
                set.insert(box_cw90_brick_indexer);
                set.insert(box_cw180_brick_indexer);
                set.insert(box_cw270_brick_indexer);

                set.setSetType(SetType.BOX);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);                }

                break;
            case BOX_CW270:
                set.insert(box_cw0);
                set.insert(box_cw90);
                set.insert(box_cw180);
                set.insert(box_cw270);
                set.setDefaultFormation();

                set.insert(box_cw0_brick_indexer);
                set.insert(box_cw90_brick_indexer);
                set.insert(box_cw180_brick_indexer);
                set.insert(box_cw270_brick_indexer);

                set.setSetType(SetType.BOX);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);                }

                break;
            case L_CW0:
                set.insert(l_cw0);
                set.setDefaultFormation();
                set.insert(l_cw90);
                set.insert(l_cw180);
                set.insert(l_cw270);

                set.insert(l_cw0_brick_indexer);
                set.insert(l_cw90_brick_indexer);
                set.insert(l_cw180_brick_indexer);
                set.insert(l_cw270_brick_indexer);

                set.setSetType(SetType.L);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L_CW90:
                set.insert(l_cw0);
                set.insert(l_cw90);
                set.setDefaultFormation();
                set.insert(l_cw180);
                set.insert(l_cw270);

                set.insert(l_cw0_brick_indexer);
                set.insert(l_cw90_brick_indexer);
                set.insert(l_cw180_brick_indexer);
                set.insert(l_cw270_brick_indexer);

                set.setSetType(SetType.L);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L_CW180:
                set.insert(l_cw0);
                set.insert(l_cw90);
                set.insert(l_cw180);
                set.setDefaultFormation();
                set.insert(l_cw270);

                set.insert(l_cw0_brick_indexer);
                set.insert(l_cw90_brick_indexer);
                set.insert(l_cw180_brick_indexer);
                set.insert(l_cw270_brick_indexer);

                set.setSetType(SetType.L);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L_CW270:
                set.insert(l_cw0);
                set.insert(l_cw90);
                set.insert(l_cw180);
                set.insert(l_cw270);
                set.setDefaultFormation();

                set.insert(l_cw0_brick_indexer);
                set.insert(l_cw90_brick_indexer);
                set.insert(l_cw180_brick_indexer);
                set.insert(l_cw270_brick_indexer);

                set.setSetType(SetType.L);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L3_CW0:
                set.insert(l3_cw0);
                set.setDefaultFormation();
                set.insert(l3_cw90);
                set.insert(l3_cw180);
                set.insert(l3_cw270);

                set.insert(l3_cw0_brick_indexer);
                set.insert(l3_cw90_brick_indexer);
                set.insert(l3_cw180_brick_indexer);
                set.insert(l3_cw270_brick_indexer);

                set.setSetType(SetType.L3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L3_CW90:
                set.insert(l3_cw0);
                set.insert(l3_cw90);
                set.setDefaultFormation();
                set.insert(l3_cw180);
                set.insert(l3_cw270);

                set.insert(l3_cw0_brick_indexer);
                set.insert(l3_cw90_brick_indexer);
                set.insert(l3_cw180_brick_indexer);
                set.insert(l3_cw270_brick_indexer);

                set.setSetType(SetType.L3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L3_CW180:
                set.insert(l3_cw0);
                set.insert(l3_cw90);
                set.insert(l3_cw180);
                set.setDefaultFormation();
                set.insert(l3_cw270);

                set.insert(l3_cw0_brick_indexer);
                set.insert(l3_cw90_brick_indexer);
                set.insert(l3_cw180_brick_indexer);
                set.insert(l3_cw270_brick_indexer);

                set.setSetType(SetType.L3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case L3_CW270:
                set.insert(l3_cw0);
                set.insert(l3_cw90);
                set.insert(l3_cw180);
                set.insert(l3_cw270);
                set.setDefaultFormation();

                set.insert(l3_cw0_brick_indexer);
                set.insert(l3_cw90_brick_indexer);
                set.insert(l3_cw180_brick_indexer);
                set.insert(l3_cw270_brick_indexer);

                set.setSetType(SetType.L3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case T_CW0:
                set.insert(t_cw0);
                set.setDefaultFormation();
                set.insert(t_cw90);
                set.insert(t_cw180);
                set.insert(t_cw270);

                set.insert(t_cw0_brick_indexer);
                set.insert(t_cw90_brick_indexer);
                set.insert(t_cw180_brick_indexer);
                set.insert(t_cw270_brick_indexer);

                set.setSetType(SetType.T);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case T_CW90:
                set.insert(t_cw0);
                set.insert(t_cw90);
                set.setDefaultFormation();
                set.insert(t_cw180);
                set.insert(t_cw270);

                set.insert(t_cw0_brick_indexer);
                set.insert(t_cw90_brick_indexer);
                set.insert(t_cw180_brick_indexer);
                set.insert(t_cw270_brick_indexer);

                set.setSetType(SetType.T);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case T_CW180:
                set.insert(t_cw0);
                set.insert(t_cw90);
                set.insert(t_cw180);
                set.setDefaultFormation();
                set.insert(t_cw270);

                set.insert(t_cw0_brick_indexer);
                set.insert(t_cw90_brick_indexer);
                set.insert(t_cw180_brick_indexer);
                set.insert(t_cw270_brick_indexer);

                set.setSetType(SetType.T);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case T_CW270:
                set.insert(t_cw0);
                set.insert(t_cw90);
                set.insert(t_cw180);
                set.insert(t_cw270);
                set.setDefaultFormation();

                set.insert(t_cw0_brick_indexer);
                set.insert(t_cw90_brick_indexer);
                set.insert(t_cw180_brick_indexer);
                set.insert(t_cw270_brick_indexer);

                set.setSetType(SetType.T);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE_CW0:
                set.insert(line_cw0);
                set.setDefaultFormation();
                set.insert(line_cw90);
                set.insert(line_cw180);
                set.insert(line_cw270);

                set.insert(line_cw0_brick_indexer);
                set.insert(line_cw90_brick_indexer);
                set.insert(line_cw180_brick_indexer);
                set.insert(line_cw270_brick_indexer);

                set.setSetType(SetType.LINE);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE_CW90:
                set.insert(line_cw0);
                set.insert(line_cw90);
                set.setDefaultFormation();
                set.insert(line_cw180);
                set.insert(line_cw270);

                set.insert(line_cw0_brick_indexer);
                set.insert(line_cw90_brick_indexer);
                set.insert(line_cw180_brick_indexer);
                set.insert(line_cw270_brick_indexer);

                set.setSetType(SetType.LINE);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE_CW180:
                set.insert(line_cw0);
                set.insert(line_cw90);
                set.insert(line_cw180);
                set.setDefaultFormation();
                set.insert(line_cw270);

                set.insert(line_cw0_brick_indexer);
                set.insert(line_cw90_brick_indexer);
                set.insert(line_cw180_brick_indexer);
                set.insert(line_cw270_brick_indexer);

                set.setSetType(SetType.LINE);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE_CW270:
                set.insert(line_cw0);
                set.insert(line_cw90);
                set.insert(line_cw180);
                set.insert(line_cw270);
                set.setDefaultFormation();

                set.insert(line_cw0_brick_indexer);
                set.insert(line_cw90_brick_indexer);
                set.insert(line_cw180_brick_indexer);
                set.insert(line_cw270_brick_indexer);

                set.setSetType(SetType.LINE);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE3_CW0:
                set.insert(line3_cw0);
                set.setDefaultFormation();
                set.insert(line3_cw90);
                set.insert(line3_cw180);
                set.insert(line3_cw270);

                set.insert(line3_cw0_brick_indexer);
                set.insert(line3_cw90_brick_indexer);
                set.insert(line3_cw180_brick_indexer);
                set.insert(line3_cw270_brick_indexer);

                set.setSetType(SetType.LINE3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE3_CW90:
                set.insert(line3_cw0);
                set.insert(line3_cw90);
                set.setDefaultFormation();
                set.insert(line3_cw180);
                set.insert(line3_cw270);

                set.insert(line3_cw0_brick_indexer);
                set.insert(line3_cw90_brick_indexer);
                set.insert(line3_cw180_brick_indexer);
                set.insert(line3_cw270_brick_indexer);

                set.setSetType(SetType.LINE3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE3_CW180:
                set.insert(line3_cw0);
                set.insert(line3_cw90);
                set.insert(line3_cw180);
                set.setDefaultFormation();
                set.insert(line3_cw270);

                set.insert(line3_cw0_brick_indexer);
                set.insert(line3_cw90_brick_indexer);
                set.insert(line3_cw180_brick_indexer);
                set.insert(line3_cw270_brick_indexer);

                set.setSetType(SetType.LINE3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE3_CW270:
                set.insert(line3_cw0);
                set.insert(line3_cw90);
                set.insert(line3_cw180);
                set.insert(line3_cw270);
                set.setDefaultFormation();

                set.insert(line3_cw0_brick_indexer);
                set.insert(line3_cw90_brick_indexer);
                set.insert(line3_cw180_brick_indexer);
                set.insert(line3_cw270_brick_indexer);

                set.setSetType(SetType.LINE3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE5_CW0:
                set.insert(line5_cw0);
                set.setDefaultFormation();
                set.insert(line5_cw90);
                set.insert(line5_cw180);
                set.insert(line5_cw270);

                set.insert(line5_cw0_brick_indexer);
                set.insert(line5_cw90_brick_indexer);
                set.insert(line5_cw180_brick_indexer);
                set.insert(line5_cw270_brick_indexer);

                set.setSetType(SetType.LINE5);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE5_CW90:
                set.insert(line5_cw0);
                set.insert(line5_cw90);
                set.setDefaultFormation();
                set.insert(line5_cw180);
                set.insert(line5_cw270);

                set.insert(line5_cw0_brick_indexer);
                set.insert(line5_cw90_brick_indexer);
                set.insert(line5_cw180_brick_indexer);
                set.insert(line5_cw270_brick_indexer);

                set.setSetType(SetType.LINE5);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE5_CW180:
                set.insert(line5_cw0);
                set.insert(line5_cw90);
                set.insert(line5_cw180);
                set.setDefaultFormation();
                set.insert(line5_cw270);

                set.insert(line5_cw0_brick_indexer);
                set.insert(line5_cw90_brick_indexer);
                set.insert(line5_cw180_brick_indexer);
                set.insert(line5_cw270_brick_indexer);

                set.setSetType(SetType.LINE5);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case LINE5_CW270:
                set.insert(line5_cw0);
                set.insert(line5_cw90);
                set.insert(line5_cw180);
                set.insert(line5_cw270);
                set.setDefaultFormation();

                set.insert(line5_cw0_brick_indexer);
                set.insert(line5_cw90_brick_indexer);
                set.insert(line5_cw180_brick_indexer);
                set.insert(line5_cw270_brick_indexer);

                set.setSetType(SetType.LINE5);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;

            case Z_CW0:
                set.insert(z_cw0);
                set.setDefaultFormation();
                set.insert(z_cw90);
                set.insert(z_cw180);
                set.insert(z_cw270);

                set.insert(z_cw0_brick_indexer);
                set.insert(z_cw90_brick_indexer);
                set.insert(z_cw180_brick_indexer);
                set.insert(z_cw270_brick_indexer);

                set.setSetType(SetType.Z);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case Z_CW90:
                set.insert(z_cw0);
                set.insert(z_cw90);
                set.setDefaultFormation();
                set.insert(z_cw180);
                set.insert(z_cw270);

                set.insert(z_cw0_brick_indexer);
                set.insert(z_cw90_brick_indexer);
                set.insert(z_cw180_brick_indexer);
                set.insert(z_cw270_brick_indexer);

                set.setSetType(SetType.Z);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case Z_CW180:
                set.insert(z_cw0);
                set.insert(z_cw90);
                set.insert(z_cw180);
                set.setDefaultFormation();
                set.insert(z_cw270);

                set.insert(z_cw0_brick_indexer);
                set.insert(z_cw90_brick_indexer);
                set.insert(z_cw180_brick_indexer);
                set.insert(z_cw270_brick_indexer);

                set.setSetType(SetType.Z);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case Z_CW270:
                set.insert(z_cw0);
                set.insert(z_cw90);
                set.insert(z_cw180);
                set.insert(z_cw270);
                set.setDefaultFormation();

                set.insert(z_cw0_brick_indexer);
                set.insert(z_cw90_brick_indexer);
                set.insert(z_cw180_brick_indexer);
                set.insert(z_cw270_brick_indexer);

                set.setSetType(SetType.Z);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case RL_CW0:
                set.insert(rl_cw0);
                set.setDefaultFormation();
                set.insert(rl_cw90);
                set.insert(rl_cw180);
                set.insert(rl_cw270);

                set.insert(rl_cw0_brick_indexer);
                set.insert(rl_cw90_brick_indexer);
                set.insert(rl_cw180_brick_indexer);
                set.insert(rl_cw270_brick_indexer);

                set.setSetType(SetType.J);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case RL_CW90:
                set.insert(rl_cw0);
                set.insert(rl_cw90);
                set.setDefaultFormation();
                set.insert(rl_cw180);
                set.insert(rl_cw270);

                set.insert(rl_cw0_brick_indexer);
                set.insert(rl_cw90_brick_indexer);
                set.insert(rl_cw180_brick_indexer);
                set.insert(rl_cw270_brick_indexer);

                set.setSetType(SetType.J);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case RL_CW180:
                set.insert(rl_cw0);
                set.insert(rl_cw90);
                set.insert(rl_cw180);
                set.setDefaultFormation();
                set.insert(rl_cw270);

                set.insert(rl_cw0_brick_indexer);
                set.insert(rl_cw90_brick_indexer);
                set.insert(rl_cw180_brick_indexer);
                set.insert(rl_cw270_brick_indexer);

                set.setSetType(SetType.J);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case RL_CW270:
                set.insert(rl_cw0);
                set.insert(rl_cw90);
                set.insert(rl_cw180);
                set.insert(rl_cw270);
                set.setDefaultFormation();

                set.insert(rl_cw0_brick_indexer);
                set.insert(rl_cw90_brick_indexer);
                set.insert(rl_cw180_brick_indexer);
                set.insert(rl_cw270_brick_indexer);

                set.setSetType(SetType.J);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG2_CW0:
                set.insert(diag2_cw0);
                set.setDefaultFormation();
                set.insert(diag2_cw90);
                set.insert(diag2_cw180);
                set.insert(diag2_cw270);

                set.insert(diag2_cw0_brick_indexer);
                set.insert(diag2_cw90_brick_indexer);
                set.insert(diag2_cw180_brick_indexer);
                set.insert(diag2_cw270_brick_indexer);

                set.setSetType(SetType.DIAG2);

                for (int i = 0; i < 2; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG2_CW90:
                set.insert(diag2_cw0);
                set.insert(diag2_cw90);
                set.setDefaultFormation();
                set.insert(diag2_cw180);
                set.insert(diag2_cw270);

                set.insert(diag2_cw0_brick_indexer);
                set.insert(diag2_cw90_brick_indexer);
                set.insert(diag2_cw180_brick_indexer);
                set.insert(diag2_cw270_brick_indexer);

                set.setSetType(SetType.DIAG2);

                for (int i = 0; i < 2; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG2_CW180:
                set.insert(diag2_cw0);
                set.insert(diag2_cw90);
                set.insert(diag2_cw180);
                set.setDefaultFormation();
                set.insert(diag2_cw270);

                set.insert(diag2_cw0_brick_indexer);
                set.insert(diag2_cw90_brick_indexer);
                set.insert(diag2_cw180_brick_indexer);
                set.insert(diag2_cw270_brick_indexer);

                set.setSetType(SetType.DIAG2);

                for (int i = 0; i < 2; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG2_CW270:
                set.insert(diag2_cw0);
                set.insert(diag2_cw90);
                set.insert(diag2_cw180);
                set.insert(diag2_cw270);
                set.setDefaultFormation();

                set.insert(diag2_cw0_brick_indexer);
                set.insert(diag2_cw90_brick_indexer);
                set.insert(diag2_cw180_brick_indexer);
                set.insert(diag2_cw270_brick_indexer);

                set.setSetType(SetType.DIAG2);

                for (int i = 0; i < 2; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG3_CW0:
                set.insert(diag3_cw0);
                set.setDefaultFormation();
                set.insert(diag3_cw90);
                set.insert(diag3_cw180);
                set.insert(diag3_cw270);

                set.insert(diag3_cw0_brick_indexer);
                set.insert(diag3_cw90_brick_indexer);
                set.insert(diag3_cw180_brick_indexer);
                set.insert(diag3_cw270_brick_indexer);

                set.setSetType(SetType.DIAG3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG3_CW90:
                set.insert(diag3_cw0);
                set.insert(diag3_cw90);
                set.setDefaultFormation();
                set.insert(diag3_cw180);
                set.insert(diag3_cw270);

                set.insert(diag3_cw0_brick_indexer);
                set.insert(diag3_cw90_brick_indexer);
                set.insert(diag3_cw180_brick_indexer);
                set.insert(diag3_cw270_brick_indexer);

                set.setSetType(SetType.DIAG3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG3_CW180:
                set.insert(diag3_cw0);
                set.insert(diag3_cw90);
                set.insert(diag3_cw180);
                set.setDefaultFormation();
                set.insert(diag3_cw270);

                set.insert(diag3_cw0_brick_indexer);
                set.insert(diag3_cw90_brick_indexer);
                set.insert(diag3_cw180_brick_indexer);
                set.insert(diag3_cw270_brick_indexer);

                set.setSetType(SetType.DIAG3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case DIAG3_CW270:
                set.insert(diag3_cw0);
                set.insert(diag3_cw90);
                set.insert(diag3_cw180);
                set.insert(diag3_cw270);
                set.setDefaultFormation();

                set.insert(diag3_cw0_brick_indexer);
                set.insert(diag3_cw90_brick_indexer);
                set.insert(diag3_cw180_brick_indexer);
                set.insert(diag3_cw270_brick_indexer);

                set.setSetType(SetType.DIAG3);

                for (int i = 0; i < 3; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case MILL_CW0:
                set.insert(mill_cw0);
                set.setDefaultFormation();
                set.insert(mill_cw90);
                set.insert(mill_cw180);
                set.insert(mill_cw270);

                set.insert(mill_cw0_brick_indexer);
                set.insert(mill_cw90_brick_indexer);
                set.insert(mill_cw180_brick_indexer);
                set.insert(mill_cw270_brick_indexer);

                set.setSetType(SetType.MILL);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case MILL_CW90:
                set.insert(mill_cw0);
                set.insert(mill_cw90);
                set.setDefaultFormation();
                set.insert(mill_cw180);
                set.insert(mill_cw270);

                set.insert(mill_cw0_brick_indexer);
                set.insert(mill_cw90_brick_indexer);
                set.insert(mill_cw180_brick_indexer);
                set.insert(mill_cw270_brick_indexer);

                set.setSetType(SetType.MILL);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case MILL_CW180:
                set.insert(mill_cw0);
                set.insert(mill_cw90);
                set.insert(mill_cw180);
                set.setDefaultFormation();
                set.insert(mill_cw270);

                set.insert(mill_cw0_brick_indexer);
                set.insert(mill_cw90_brick_indexer);
                set.insert(mill_cw180_brick_indexer);
                set.insert(mill_cw270_brick_indexer);

                set.setSetType(SetType.MILL);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case MILL_CW270:
                set.insert(mill_cw0);
                set.insert(mill_cw90);
                set.insert(mill_cw180);
                set.insert(mill_cw270);
                set.setDefaultFormation();

                set.insert(mill_cw0_brick_indexer);
                set.insert(mill_cw90_brick_indexer);
                set.insert(mill_cw180_brick_indexer);
                set.insert(mill_cw270_brick_indexer);

                set.setSetType(SetType.MILL);

                for (int i = 0; i < 5; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case HALF_MILL_CW0:
                set.insert(half_mill_cw0);
                set.setDefaultFormation();
                set.insert(half_mill_cw90);
                set.insert(half_mill_cw180);
                set.insert(half_mill_cw270);

                set.insert(half_mill_cw0_brick_indexer);
                set.insert(half_mill_cw90_brick_indexer);
                set.insert(half_mill_cw180_brick_indexer);
                set.insert(half_mill_cw270_brick_indexer);

                set.setSetType(SetType.HALF_MILL);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case HALF_MILL_CW90:
                set.insert(half_mill_cw0);
                set.insert(half_mill_cw90);
                set.setDefaultFormation();
                set.insert(half_mill_cw180);
                set.insert(half_mill_cw270);

                set.insert(half_mill_cw0_brick_indexer);
                set.insert(half_mill_cw90_brick_indexer);
                set.insert(half_mill_cw180_brick_indexer);
                set.insert(half_mill_cw270_brick_indexer);

                set.setSetType(SetType.HALF_MILL);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case HALF_MILL_CW180:
                set.insert(half_mill_cw0);
                set.insert(half_mill_cw90);
                set.insert(half_mill_cw180);
                set.setDefaultFormation();
                set.insert(half_mill_cw270);

                set.insert(half_mill_cw0_brick_indexer);
                set.insert(half_mill_cw90_brick_indexer);
                set.insert(half_mill_cw180_brick_indexer);
                set.insert(half_mill_cw270_brick_indexer);

                set.setSetType(SetType.HALF_MILL);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
            case HALF_MILL_CW270:
                set.insert(half_mill_cw0);
                set.insert(half_mill_cw90);
                set.insert(half_mill_cw180);
                set.insert(half_mill_cw270);
                set.setDefaultFormation();

                set.insert(half_mill_cw0_brick_indexer);
                set.insert(half_mill_cw90_brick_indexer);
                set.insert(half_mill_cw180_brick_indexer);
                set.insert(half_mill_cw270_brick_indexer);

                set.setSetType(SetType.HALF_MILL);

                for (int i = 0; i < 4; i++) {
                    Brick brick = new Brick();
                    brick.setBrickState(BrickState.LIVE);
                    brick.setSet(set);
                    brick.setRelativePosition(set.getCurrentFormation().getForm().get(i));
                    brick.setBrickType(BrickType.NORMAL);

                    set.insert(brick);
                }

                break;
        }

        return set;
    }

    @Override
    public Set apply(FormationType formationType) {
        return build(formationType);
    }

    @Override
    public Set apply(FormationType formationType, Set set) {
        return build(formationType);
    }
}
