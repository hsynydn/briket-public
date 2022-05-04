package com.example.hmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Objects;

public class main_screen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "main_screen";
    int color = 0x000000;

    ArrayList<LinearLayout> verticalLayoutArray = new ArrayList<>();

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

        verticalLayoutArray.add(findViewById(R.id.layout_vertical_0));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_1));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_2));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_3));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_4));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_5));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_6));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_7));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_8));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_9));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_10));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_11));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_12));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_13));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_14));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_15));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_16));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_17));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_18));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_19));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_20));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_21));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_22));
        verticalLayoutArray.add(findViewById(R.id.layout_vertical_23));

        findViewById(R.id.View00_03).setBackgroundColor(0xFF9800);

        findViewById(R.id.View00_03).setOnClickListener(view -> {
            color += 10000;
            findViewById(R.id.View07_03).setBackgroundColor(color);
            Log.i(TAG, "clicked on_start" + Integer.toString(color));
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        findViewById(R.id.View00_03).setBackgroundColor(0xFF9800);

//        findViewById(R.id.layout_general).setOnClickListener(view -> {
//            findViewById(R.id.View07_03).setBackgroundColor(0xFF9800);
//            Log.i(TAG, "clicked on_start");
//        });
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick");
        Log.e(TAG, "onClick");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.i(TAG, "hasCapture");
        Log.e(TAG, "hasCapture");
    }
}