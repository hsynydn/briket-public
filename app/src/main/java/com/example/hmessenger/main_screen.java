package com.example.hmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hmessenger.logic.Cell;
import com.example.hmessenger.logic.DisplayUnitController;
import com.example.hmessenger.logic.Event;
import com.example.hmessenger.logic.Game;
import com.example.hmessenger.logic.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class main_screen extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = "main_screen";
    int color = 0x000000;

    DisplayUnitController displayUnitController;

    ArrayList<LinearLayout> verticalLayoutArray = new ArrayList<>();
    ArrayList<ImageView> gridPane = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        Log.i(TAG, "onCreate ── start");

        gridPane.add(findViewById(R.id.View00_00));
        gridPane.add(findViewById(R.id.View00_01));
        gridPane.add(findViewById(R.id.View00_02));
        gridPane.add(findViewById(R.id.View00_03));
        gridPane.add(findViewById(R.id.View00_04));
        gridPane.add(findViewById(R.id.View00_05));
        gridPane.add(findViewById(R.id.View00_06));
        gridPane.add(findViewById(R.id.View00_07));
        gridPane.add(findViewById(R.id.View00_08));
        gridPane.add(findViewById(R.id.View00_09));
        gridPane.add(findViewById(R.id.View00_10));
        gridPane.add(findViewById(R.id.View00_11));

        gridPane.add(findViewById(R.id.View01_00));
        gridPane.add(findViewById(R.id.View01_01));
        gridPane.add(findViewById(R.id.View01_02));
        gridPane.add(findViewById(R.id.View01_03));
        gridPane.add(findViewById(R.id.View01_04));
        gridPane.add(findViewById(R.id.View01_05));
        gridPane.add(findViewById(R.id.View01_06));
        gridPane.add(findViewById(R.id.View01_07));
        gridPane.add(findViewById(R.id.View01_08));
        gridPane.add(findViewById(R.id.View01_09));
        gridPane.add(findViewById(R.id.View01_10));
        gridPane.add(findViewById(R.id.View01_11));

        gridPane.add(findViewById(R.id.View02_00));
        gridPane.add(findViewById(R.id.View02_01));
        gridPane.add(findViewById(R.id.View02_02));
        gridPane.add(findViewById(R.id.View02_03));
        gridPane.add(findViewById(R.id.View02_04));
        gridPane.add(findViewById(R.id.View02_05));
        gridPane.add(findViewById(R.id.View02_06));
        gridPane.add(findViewById(R.id.View02_07));
        gridPane.add(findViewById(R.id.View02_08));
        gridPane.add(findViewById(R.id.View02_09));
        gridPane.add(findViewById(R.id.View02_10));
        gridPane.add(findViewById(R.id.View02_11));

        gridPane.add(findViewById(R.id.View03_00));
        gridPane.add(findViewById(R.id.View03_01));
        gridPane.add(findViewById(R.id.View03_02));
        gridPane.add(findViewById(R.id.View03_03));
        gridPane.add(findViewById(R.id.View03_04));
        gridPane.add(findViewById(R.id.View03_05));
        gridPane.add(findViewById(R.id.View03_06));
        gridPane.add(findViewById(R.id.View03_07));
        gridPane.add(findViewById(R.id.View03_08));
        gridPane.add(findViewById(R.id.View03_09));
        gridPane.add(findViewById(R.id.View03_10));
        gridPane.add(findViewById(R.id.View03_11));

        gridPane.add(findViewById(R.id.View04_00));
        gridPane.add(findViewById(R.id.View04_01));
        gridPane.add(findViewById(R.id.View04_02));
        gridPane.add(findViewById(R.id.View04_03));
        gridPane.add(findViewById(R.id.View04_04));
        gridPane.add(findViewById(R.id.View04_05));
        gridPane.add(findViewById(R.id.View04_06));
        gridPane.add(findViewById(R.id.View04_07));
        gridPane.add(findViewById(R.id.View04_08));
        gridPane.add(findViewById(R.id.View04_09));
        gridPane.add(findViewById(R.id.View04_10));
        gridPane.add(findViewById(R.id.View04_11));

        gridPane.add(findViewById(R.id.View05_00));
        gridPane.add(findViewById(R.id.View05_01));
        gridPane.add(findViewById(R.id.View05_02));
        gridPane.add(findViewById(R.id.View05_03));
        gridPane.add(findViewById(R.id.View05_04));
        gridPane.add(findViewById(R.id.View05_05));
        gridPane.add(findViewById(R.id.View05_06));
        gridPane.add(findViewById(R.id.View05_07));
        gridPane.add(findViewById(R.id.View05_08));
        gridPane.add(findViewById(R.id.View05_09));
        gridPane.add(findViewById(R.id.View05_10));
        gridPane.add(findViewById(R.id.View05_11));

        gridPane.add(findViewById(R.id.View06_00));
        gridPane.add(findViewById(R.id.View06_01));
        gridPane.add(findViewById(R.id.View06_02));
        gridPane.add(findViewById(R.id.View06_03));
        gridPane.add(findViewById(R.id.View06_04));
        gridPane.add(findViewById(R.id.View06_05));
        gridPane.add(findViewById(R.id.View06_06));
        gridPane.add(findViewById(R.id.View06_07));
        gridPane.add(findViewById(R.id.View06_08));
        gridPane.add(findViewById(R.id.View06_09));
        gridPane.add(findViewById(R.id.View06_10));
        gridPane.add(findViewById(R.id.View06_11));

        gridPane.add(findViewById(R.id.View07_00));
        gridPane.add(findViewById(R.id.View07_01));
        gridPane.add(findViewById(R.id.View07_02));
        gridPane.add(findViewById(R.id.View07_03));
        gridPane.add(findViewById(R.id.View07_04));
        gridPane.add(findViewById(R.id.View07_05));
        gridPane.add(findViewById(R.id.View07_06));
        gridPane.add(findViewById(R.id.View07_07));
        gridPane.add(findViewById(R.id.View07_08));
        gridPane.add(findViewById(R.id.View07_09));
        gridPane.add(findViewById(R.id.View07_10));
        gridPane.add(findViewById(R.id.View07_11));

        gridPane.add(findViewById(R.id.View08_00));
        gridPane.add(findViewById(R.id.View08_01));
        gridPane.add(findViewById(R.id.View08_02));
        gridPane.add(findViewById(R.id.View08_03));
        gridPane.add(findViewById(R.id.View08_04));
        gridPane.add(findViewById(R.id.View08_05));
        gridPane.add(findViewById(R.id.View08_06));
        gridPane.add(findViewById(R.id.View08_07));
        gridPane.add(findViewById(R.id.View08_08));
        gridPane.add(findViewById(R.id.View08_09));
        gridPane.add(findViewById(R.id.View08_10));
        gridPane.add(findViewById(R.id.View08_11));

        gridPane.add(findViewById(R.id.View09_00));
        gridPane.add(findViewById(R.id.View09_01));
        gridPane.add(findViewById(R.id.View09_02));
        gridPane.add(findViewById(R.id.View09_03));
        gridPane.add(findViewById(R.id.View09_04));
        gridPane.add(findViewById(R.id.View09_05));
        gridPane.add(findViewById(R.id.View09_06));
        gridPane.add(findViewById(R.id.View09_07));
        gridPane.add(findViewById(R.id.View09_08));
        gridPane.add(findViewById(R.id.View09_09));
        gridPane.add(findViewById(R.id.View09_10));
        gridPane.add(findViewById(R.id.View09_11));

        gridPane.add(findViewById(R.id.View10_00));
        gridPane.add(findViewById(R.id.View10_01));
        gridPane.add(findViewById(R.id.View10_02));
        gridPane.add(findViewById(R.id.View10_03));
        gridPane.add(findViewById(R.id.View10_04));
        gridPane.add(findViewById(R.id.View10_05));
        gridPane.add(findViewById(R.id.View10_06));
        gridPane.add(findViewById(R.id.View10_07));
        gridPane.add(findViewById(R.id.View10_08));
        gridPane.add(findViewById(R.id.View10_09));
        gridPane.add(findViewById(R.id.View10_10));
        gridPane.add(findViewById(R.id.View10_11));

        gridPane.add(findViewById(R.id.View11_00));
        gridPane.add(findViewById(R.id.View11_01));
        gridPane.add(findViewById(R.id.View11_02));
        gridPane.add(findViewById(R.id.View11_03));
        gridPane.add(findViewById(R.id.View11_04));
        gridPane.add(findViewById(R.id.View11_05));
        gridPane.add(findViewById(R.id.View11_06));
        gridPane.add(findViewById(R.id.View11_07));
        gridPane.add(findViewById(R.id.View11_08));
        gridPane.add(findViewById(R.id.View11_09));
        gridPane.add(findViewById(R.id.View11_10));
        gridPane.add(findViewById(R.id.View11_11));

        gridPane.add(findViewById(R.id.View12_00));
        gridPane.add(findViewById(R.id.View12_01));
        gridPane.add(findViewById(R.id.View12_02));
        gridPane.add(findViewById(R.id.View12_03));
        gridPane.add(findViewById(R.id.View12_04));
        gridPane.add(findViewById(R.id.View12_05));
        gridPane.add(findViewById(R.id.View12_06));
        gridPane.add(findViewById(R.id.View12_07));
        gridPane.add(findViewById(R.id.View12_08));
        gridPane.add(findViewById(R.id.View12_09));
        gridPane.add(findViewById(R.id.View12_10));
        gridPane.add(findViewById(R.id.View12_11));

        gridPane.add(findViewById(R.id.View13_00));
        gridPane.add(findViewById(R.id.View13_01));
        gridPane.add(findViewById(R.id.View13_02));
        gridPane.add(findViewById(R.id.View13_03));
        gridPane.add(findViewById(R.id.View13_04));
        gridPane.add(findViewById(R.id.View13_05));
        gridPane.add(findViewById(R.id.View13_06));
        gridPane.add(findViewById(R.id.View13_07));
        gridPane.add(findViewById(R.id.View13_08));
        gridPane.add(findViewById(R.id.View13_09));
        gridPane.add(findViewById(R.id.View13_10));
        gridPane.add(findViewById(R.id.View13_11));

        gridPane.add(findViewById(R.id.View14_00));
        gridPane.add(findViewById(R.id.View14_01));
        gridPane.add(findViewById(R.id.View14_02));
        gridPane.add(findViewById(R.id.View14_03));
        gridPane.add(findViewById(R.id.View14_04));
        gridPane.add(findViewById(R.id.View14_05));
        gridPane.add(findViewById(R.id.View14_06));
        gridPane.add(findViewById(R.id.View14_07));
        gridPane.add(findViewById(R.id.View14_08));
        gridPane.add(findViewById(R.id.View14_09));
        gridPane.add(findViewById(R.id.View14_10));
        gridPane.add(findViewById(R.id.View14_11));

        gridPane.add(findViewById(R.id.View15_00));
        gridPane.add(findViewById(R.id.View15_01));
        gridPane.add(findViewById(R.id.View15_02));
        gridPane.add(findViewById(R.id.View15_03));
        gridPane.add(findViewById(R.id.View15_04));
        gridPane.add(findViewById(R.id.View15_05));
        gridPane.add(findViewById(R.id.View15_06));
        gridPane.add(findViewById(R.id.View15_07));
        gridPane.add(findViewById(R.id.View15_08));
        gridPane.add(findViewById(R.id.View15_09));
        gridPane.add(findViewById(R.id.View15_10));
        gridPane.add(findViewById(R.id.View15_11));

        gridPane.add(findViewById(R.id.View16_00));
        gridPane.add(findViewById(R.id.View16_01));
        gridPane.add(findViewById(R.id.View16_02));
        gridPane.add(findViewById(R.id.View16_03));
        gridPane.add(findViewById(R.id.View16_04));
        gridPane.add(findViewById(R.id.View16_05));
        gridPane.add(findViewById(R.id.View16_06));
        gridPane.add(findViewById(R.id.View16_07));
        gridPane.add(findViewById(R.id.View16_08));
        gridPane.add(findViewById(R.id.View16_09));
        gridPane.add(findViewById(R.id.View16_10));
        gridPane.add(findViewById(R.id.View16_11));

        gridPane.add(findViewById(R.id.View17_00));
        gridPane.add(findViewById(R.id.View17_01));
        gridPane.add(findViewById(R.id.View17_02));
        gridPane.add(findViewById(R.id.View17_03));
        gridPane.add(findViewById(R.id.View17_04));
        gridPane.add(findViewById(R.id.View17_05));
        gridPane.add(findViewById(R.id.View17_06));
        gridPane.add(findViewById(R.id.View17_07));
        gridPane.add(findViewById(R.id.View17_08));
        gridPane.add(findViewById(R.id.View17_09));
        gridPane.add(findViewById(R.id.View17_10));
        gridPane.add(findViewById(R.id.View17_11));

        gridPane.add(findViewById(R.id.View18_00));
        gridPane.add(findViewById(R.id.View18_01));
        gridPane.add(findViewById(R.id.View18_02));
        gridPane.add(findViewById(R.id.View18_03));
        gridPane.add(findViewById(R.id.View18_04));
        gridPane.add(findViewById(R.id.View18_05));
        gridPane.add(findViewById(R.id.View18_06));
        gridPane.add(findViewById(R.id.View18_07));
        gridPane.add(findViewById(R.id.View18_08));
        gridPane.add(findViewById(R.id.View18_09));
        gridPane.add(findViewById(R.id.View18_10));
        gridPane.add(findViewById(R.id.View18_11));

        gridPane.add(findViewById(R.id.View19_00));
        gridPane.add(findViewById(R.id.View19_01));
        gridPane.add(findViewById(R.id.View19_02));
        gridPane.add(findViewById(R.id.View19_03));
        gridPane.add(findViewById(R.id.View19_04));
        gridPane.add(findViewById(R.id.View19_05));
        gridPane.add(findViewById(R.id.View19_06));
        gridPane.add(findViewById(R.id.View19_07));
        gridPane.add(findViewById(R.id.View19_08));
        gridPane.add(findViewById(R.id.View19_09));
        gridPane.add(findViewById(R.id.View19_10));
        gridPane.add(findViewById(R.id.View19_11));

        gridPane.add(findViewById(R.id.View20_00));
        gridPane.add(findViewById(R.id.View20_01));
        gridPane.add(findViewById(R.id.View20_02));
        gridPane.add(findViewById(R.id.View20_03));
        gridPane.add(findViewById(R.id.View20_04));
        gridPane.add(findViewById(R.id.View20_05));
        gridPane.add(findViewById(R.id.View20_06));
        gridPane.add(findViewById(R.id.View20_07));
        gridPane.add(findViewById(R.id.View20_08));
        gridPane.add(findViewById(R.id.View20_09));
        gridPane.add(findViewById(R.id.View20_10));
        gridPane.add(findViewById(R.id.View20_11));

        gridPane.add(findViewById(R.id.View21_00));
        gridPane.add(findViewById(R.id.View21_01));
        gridPane.add(findViewById(R.id.View21_02));
        gridPane.add(findViewById(R.id.View21_03));
        gridPane.add(findViewById(R.id.View21_04));
        gridPane.add(findViewById(R.id.View21_05));
        gridPane.add(findViewById(R.id.View21_06));
        gridPane.add(findViewById(R.id.View21_07));
        gridPane.add(findViewById(R.id.View21_08));
        gridPane.add(findViewById(R.id.View21_09));
        gridPane.add(findViewById(R.id.View21_10));
        gridPane.add(findViewById(R.id.View21_11));

        gridPane.add(findViewById(R.id.View22_00));
        gridPane.add(findViewById(R.id.View22_01));
        gridPane.add(findViewById(R.id.View22_02));
        gridPane.add(findViewById(R.id.View22_03));
        gridPane.add(findViewById(R.id.View22_04));
        gridPane.add(findViewById(R.id.View22_05));
        gridPane.add(findViewById(R.id.View22_06));
        gridPane.add(findViewById(R.id.View22_07));
        gridPane.add(findViewById(R.id.View22_08));
        gridPane.add(findViewById(R.id.View22_09));
        gridPane.add(findViewById(R.id.View22_10));
        gridPane.add(findViewById(R.id.View22_11));

        gridPane.add(findViewById(R.id.View23_00));
        gridPane.add(findViewById(R.id.View23_01));
        gridPane.add(findViewById(R.id.View23_02));
        gridPane.add(findViewById(R.id.View23_03));
        gridPane.add(findViewById(R.id.View23_04));
        gridPane.add(findViewById(R.id.View23_05));
        gridPane.add(findViewById(R.id.View23_06));
        gridPane.add(findViewById(R.id.View23_07));
        gridPane.add(findViewById(R.id.View23_08));
        gridPane.add(findViewById(R.id.View23_09));
        gridPane.add(findViewById(R.id.View23_10));
        gridPane.add(findViewById(R.id.View23_11));

//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_0));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_1));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_2));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_3));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_4));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_5));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_6));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_7));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_8));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_9));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_10));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_11));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_12));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_13));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_14));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_15));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_16));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_17));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_18));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_19));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_20));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_21));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_22));
//        verticalLayoutArray.add(findViewById(R.id.layout_vertical_23));

//        ImageView a;
//        a = findViewById(R.id.View00_00);
//        a.setImageResource(R.drawable.game_over);
//        a = findViewById(R.id.View00_01);
//        a.setImageResource(R.drawable.game_over);
//
//        gridPane.get(0).setImageResource(R.drawable.game_over);
//        gridPane.get(1).setImageResource(R.drawable.game_over);
//        gridPane.get(2).setImageResource(R.drawable.game_over);

        displayUnitController = new DisplayUnitController(this, gridPane);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

        findViewById(R.id.button_left_control).setOnClickListener(view -> {
            Log.i(TAG, "button_left_control Click ── Add to Stack");
            displayUnitController.pushEvent(Event.LEFT_MOVE);
        });

        findViewById(R.id.button_right_control).setOnClickListener(view -> {
            Log.i(TAG, "button_right_control Click ── Add to Stack");
            displayUnitController.pushEvent(Event.RIGHT_MOVE);
        });

        findViewById(R.id.free_fall_button).setOnClickListener(view -> {
            Log.i(TAG, "free_fall_button Click ── Add to Stack");
            displayUnitController.pushEvent(Event.FREE_FALL);
        });

        findViewById(R.id.rotate_button).setOnClickListener(view -> {
            Log.i(TAG, "rotate_button Click ── Add to Stack");
            displayUnitController.pushEvent(Event.ROTATE_RIGHT);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Game game = new Game(this, displayUnitController, this);
            game.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        Log.i(TAG, "handleMessage(@NonNull Message message)");
        displayUnitController.refreshMonitor((ArrayList<Cell>)message.obj);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}