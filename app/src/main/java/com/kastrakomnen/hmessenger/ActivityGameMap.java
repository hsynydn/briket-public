package com.kastrakomnen.hmessenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.kastrakomnen.hmessenger.view.ItemClickListener;
import com.kastrakomnen.hmessenger.view.ProgressCard;
import com.kastrakomnen.hmessenger.view.ProgressCardAdapter;
import com.kastrakomnen.hmessenger.view.Recycler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ActivityGameMap extends AppCompatActivity implements ItemClickListener {

    private static final String TAG = "ActivityGameMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.Theme_Hmessenger);
        setContentView(R.layout.layout_game_map);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        RecyclerView recyclerView = findViewById(R.id.rv_progress_cards);

        List<ProgressCard> progressCardList = new ArrayList<>();
        progressCardList.add(new ProgressCard(false));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));
        progressCardList.add(new ProgressCard(true));

        ProgressCardAdapter progressCardAdapter = new ProgressCardAdapter(progressCardList);
        progressCardAdapter.setClickListener(this);

        recyclerView.setAdapter(progressCardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




//        findViewById(R.id.recycler).setOnClickListener(view -> {
//            Log.i(TAG, "Play");
//            Intent intent = new Intent(this, ActivityGameMap.class);
//            startActivity(intent);
//            this.finish();
//        });

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.drawable.brick_style_shady_blue);

//        RecyclerView recyclerView = findViewById(R.id.rv_cards);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.addView(imageView);



    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(ActivityGameMap.this, ActivityPlayScreen.class);
        startActivity(intent);
        ActivityGameMap.this.finish();

    }
}