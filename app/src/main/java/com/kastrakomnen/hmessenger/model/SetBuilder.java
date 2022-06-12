package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetBuilder {

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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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
                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
                }
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
                }
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
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

                for (int i = 0; i < 4; i++) {
                    set.insert(new Brick());
                }
                break;
        }

        return set;
    }
}
