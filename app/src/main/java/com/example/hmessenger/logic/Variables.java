package com.example.hmessenger.logic;

public interface Variables {

    public static final int GRID_Y = 12;
    public static final int GRID_X = 24;

    public static final short INDEX = GRID_Y/2;

    public static final short BOX [] = {0, 1, GRID_Y, GRID_Y+1};
    public static final short LSHAPE [] = {0, GRID_Y, GRID_Y*2, GRID_Y*2+1};
    public static final short TSHAPE [] = {0, 1, 2, GRID_Y+1};
    public static final short LINE [] = {0, GRID_Y, GRID_Y*2, GRID_Y*3};
    public static final short EMPTY [] = {0, 0, 0, 0};

    public static final short BOX_RIGHT_DETECTOR [] = {2, GRID_Y+2};
    public static final short BOX_LEFT_DETECTOR [] = {-1, GRID_Y-1};
    public static final short BOX_UNDER_DETECTOR [] = {GRID_Y*2, GRID_Y*2+1};

    public static final short LSHAPE_RIGHT_DETECTOR [] = {1, GRID_Y+1, GRID_Y*2+2};
    public static final short LSHAPE_LEFT_DETECTOR [] = {-1, GRID_Y-1, GRID_Y*2-1};
    public static final short LSHAPE_UNDER_DETECTOR [] = {GRID_Y*3, GRID_Y*3+1};

    public static final short TSHAPE_RIGHT_DETECTOR [] = {3, GRID_Y+2};
    public static final short TSHAPE_LEFT_DETECTOR [] = {-1, GRID_Y};
    public static final short TSHAPE_UNDER_DETECTOR [] = {GRID_Y, GRID_Y*2+1, GRID_Y+2};

    public static final short LINE_RIGHT_DETECTOR [] = {1, GRID_Y+1, GRID_Y*2+1, GRID_Y*3+1};
    public static final short LINE_LEFT_DETECTOR [] = {-1, GRID_Y-1, GRID_Y*2-1, GRID_Y*3-1};
    public static final short LINE_UNDER_DETECTOR [] = {GRID_Y*4};

    public static final short LSHAPE_90 [] = {2, GRID_Y, GRID_Y+1, GRID_Y+2};
    public static final short TSHAPE_90 [] = {0, GRID_Y, GRID_Y+1, GRID_Y*2};
    public static final short LINE_90 [] = {0, 1, 2, 3};

    public static final short LSHAPE_90_RIGHT_DETECTOR [] = {3, GRID_Y+3};
    public static final short LSHAPE_90_LEFT_DETECTOR [] = {1, GRID_Y-1};
    public static final short LSHAPE_90_UNDER_DETECTOR [] = {GRID_Y*2, GRID_Y*2+1, GRID_Y*2+2};

    public static final short TSHAPE_90_RIGHT_DETECTOR [] = {1, GRID_Y+2, GRID_Y*2+1};
    public static final short TSHAPE_90_LEFT_DETECTOR [] = {-1, GRID_Y-1, GRID_Y*2-1};
    public static final short TSHAPE_90_UNDER_DETECTOR [] = {GRID_Y*3, GRID_Y*2+1};

    public static final short LINE_90_RIGHT_DETECTOR [] = {4};
    public static final short LINE_90_LEFT_DETECTOR [] = {-1};
    public static final short LINE_90_UNDER_DETECTOR [] = {GRID_Y, GRID_Y+1, GRID_Y+2, GRID_Y+3};

    public static final short LSHAPE_180 [] = {0, 1, GRID_Y+1, GRID_Y*2+1};
    public static final short TSHAPE_180 [] = {1, GRID_Y, GRID_Y+1, GRID_Y+2};

    public static final short LSHAPE_180_RIGHT_DETECTOR [] = {2, GRID_Y+2, GRID_Y*2+2};
    public static final short LSHAPE_180_LEFT_DETECTOR [] = {-1, GRID_Y, GRID_Y*2};
    public static final short LSHAPE_180_UNDER_DETECTOR [] = {GRID_Y, GRID_Y*3+1};

    public static final short TSHAPE_180_RIGHT_DETECTOR [] = {2, GRID_Y+3};
    public static final short TSHAPE_180_LEFT_DETECTOR [] = {0, GRID_Y-1};
    public static final short TSHAPE_180_UNDER_DETECTOR [] = {GRID_Y*2, GRID_Y*2+1, GRID_Y*2+2};

    public static final short LSHAPE_270 [] = {0, 1, 2, GRID_Y};
    public static final short TSHAPE_270 [] = {1, GRID_Y, GRID_Y+1, GRID_Y*2+1};

    public static final short LSHAPE_270_RIGHT_DETECTOR [] = {3, GRID_Y+1};
    public static final short LSHAPE_270_LEFT_DETECTOR [] = {-1, GRID_Y-1};
    public static final short LSHAPE_270_UNDER_DETECTOR [] = {GRID_Y*2, GRID_Y+1, GRID_Y+2};

    public static final short TSHAPE_270_RIGHT_DETECTOR [] = {2, GRID_Y+2, GRID_Y*2+2};
    public static final short TSHAPE_270_LEFT_DETECTOR [] = {0, GRID_Y-1, GRID_Y*2};
    public static final short TSHAPE_270_UNDER_DETECTOR [] = {GRID_Y*2, GRID_Y*3+1};

    public static final short V_GAP = 1;
    public static final short H_GAP = GRID_Y;

    public static final Object VALUE = 'X';

    public static final Integer RIGHT_KEY = new Integer(1);
    public static final Integer LEFT_KEY = new Integer(0);
    public static final Integer PAUSE = new Integer(2);
    public static final Integer ROTATE_KEY = new Integer(3);
    public static final Integer DOWN_KEY = new Integer(4);

    public static final Integer RIGHT_SIDE = new Integer(1);
    public static final Integer LEFT_SIDE = new Integer(0);
    public static final Integer UNDER_SIDE = new Integer(2);

    public static final int DEGREE_90 = 90;
    public static final int DEGREE_MINUS90 = -90;
}
