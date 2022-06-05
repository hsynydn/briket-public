package com.kastrakomnen.hmessenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.kastrakomnen.hmessenger.logic.DisplayUnitController;
import com.kastrakomnen.hmessenger.logic.Game;
import com.kastrakomnen.hmessenger.logic.GameListener;
import com.kastrakomnen.hmessenger.logic.GameState;
import com.kastrakomnen.hmessenger.logic.Music;
import com.kastrakomnen.hmessenger.view.AnimationLayer;

import java.io.IOException;
import java.util.Objects;


public class ActivityPlayScreen extends AppCompatActivity implements GameListener {

    private static final String TAG = "MainActivity";

    public  PlayBoardView           playBoardView;
    private DisplayUnitController   displayUnitController;
    private Game                    game;

    private Handler handler;

    private Music audio_fx_btn_click_3;
    private Music fx_audio_btn_pause;
    private MediaPlayer music_get_wacky;
    private MediaPlayer audio_fx_count_down;
    private MediaPlayer audio_fx_game_intro;

    private SoundPool soundPool;

    private int audioFxCountDownResID;

    /****
     * Game Animations will be drawn upon this GLSurfaceView */
    private GLSurfaceView animationLayer;

    private Animation anim_count_down3;
    private Animation anim_count_down2;
    private Animation anim_count_down1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_play_board);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);

        try {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }catch (NullPointerException e){
            Log.i(TAG, e.toString());
        }

        handler  = new Handler();

        playBoardView = findViewById(R.id.view_playground);
        displayUnitController = new DisplayUnitController(this, playBoardView, handler);

        animationLayer = new AnimationLayer(this);
        RelativeLayout pg_body = findViewById(R.id.PG_body);
        pg_body.addView(animationLayer);

        anim_count_down3 = AnimationUtils.loadAnimation(this, R.anim.far_away);
        anim_count_down2 = AnimationUtils.loadAnimation(this, R.anim.far_away);
        anim_count_down1 = AnimationUtils.loadAnimation(this, R.anim.far_away);

        soundPool = new SoundPool.Builder().build();
        audioFxCountDownResID = soundPool.load(this, R.raw.audio_fx_count_down, 0);

        try {
            game = Game.getInstance(this)
                    .setDisplayUnitController(displayUnitController)
                    .setHandler(handler)
                    .setGameListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable free_fall = new Runnable() {
            @Override
            public void run() {
                game.moveFreeFall();
            }
        };

        Runnable leftX1 = new Runnable() {
            @Override
            public void run() {
                game.moveLeft();
            }
        };

        Runnable leftX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable leftX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable leftX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.moveLeft();
                }
            }
        };

        Runnable rightX1 = new Runnable() {
            @Override
            public void run() {
                game.moveRight();
            }
        };

        Runnable rightX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveRight();
                }
            }
        };

        Runnable rightX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveRight();
                }
            }
        };

        Runnable rightX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<6; i++){
                    game.moveRight();
                }
            }
        };

        Runnable fallX1 = new Runnable() {
            @Override
            public void run() {
                game.moveDown();
            }
        };

        Runnable fallX2 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<2; i++){
                    game.moveDown();
                }
            }
        };

        Runnable fallX3 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<3; i++){
                    game.moveDown();
                }
            }
        };

        Runnable fallX6 = new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<12; i++){
                    game.moveDown();
                }
            }
        };

        try {
            this.audio_fx_btn_click_3   = new Music(getApplicationContext(), R.raw.audio_fx_btn_click_3);
            this.fx_audio_btn_pause     = new Music(getApplicationContext(), R.raw.fx_audio_pause);
            this.audio_fx_count_down    = MediaPlayer.create(getApplicationContext(), R.raw.audio_fx_count_down);
            this.audio_fx_game_intro    = MediaPlayer.create(getApplicationContext(), R.raw.audio_fx_game_intro);
            this.music_get_wacky        = MediaPlayer.create(getApplicationContext(), R.raw.get_wacky);
            this.music_get_wacky.setLooping(true);
            this.music_get_wacky.setVolume(1, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.btn_left_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    handler.postDelayed(leftX1, 10);
                    handler.postDelayed(leftX1, 100);
                    handler.postDelayed(leftX2, 200);
                    handler.postDelayed(leftX3, 300);
                    handler.postDelayed(leftX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(leftX1);
                    handler.removeCallbacks(leftX2);
                    handler.removeCallbacks(leftX3);
                    handler.removeCallbacks(leftX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_right_move).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    handler.postDelayed(rightX1, 100);
                    handler.postDelayed(rightX1, 10);
                    handler.postDelayed(rightX2, 200);
                    handler.postDelayed(rightX3, 300);
                    handler.postDelayed(rightX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    handler.removeCallbacks(rightX1);
                    handler.removeCallbacks(rightX2);
                    handler.removeCallbacks(rightX3);
                    handler.removeCallbacks(rightX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_drop).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.down_arrow_pressed));
                    handler.postDelayed(fallX1, 10);
                    handler.postDelayed(fallX1, 100);
                    handler.postDelayed(fallX2, 200);
                    handler.postDelayed(fallX3, 300);
                    handler.postDelayed(fallX6, 400);
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.down_arrow_unpressed));
                    handler.removeCallbacks(fallX1);
                    handler.removeCallbacks(fallX2);
                    handler.removeCallbacks(fallX3);
                    handler.removeCallbacks(fallX6);
                }
                return true;
            }
        });

        findViewById(R.id.btn_rotate).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    audio_fx_btn_click_3.start();
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.rotate_pressed));
                    game.rotate();
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.rotate_unpressed));
                }
                return true;
            }
        });

        findViewById(R.id.btn_menu).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    fx_audio_btn_pause.start();
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.pause_pressed));
                    game.pause();
                    Intent intent = new Intent(ActivityPlayScreen.this, ActivityPause.class);
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.pause_unpressed));
                    startActivity(intent);
                }
                return true;
            }
        });

        findViewById(R.id.iv_count_down_3).setVisibility(View.INVISIBLE);
        findViewById(R.id.iv_count_down_2).setVisibility(View.INVISIBLE);
        findViewById(R.id.iv_count_down_1).setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        if (game.getGameState() != GameState.NOT_STARTED){
            return;
        }

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          findViewById(R.id.iv_count_down_3).setVisibility(View.VISIBLE);
                                          soundPool.play(audioFxCountDownResID, 1.0f, 1.0f, 3, 0, 1.0f);
                                          findViewById(R.id.iv_count_down_3).setAnimation(anim_count_down3);
                                      }
                                  },
                500);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          findViewById(R.id.iv_count_down_3).setVisibility(View.INVISIBLE);
                                          anim_count_down3.cancel();
                                          findViewById(R.id.iv_count_down_2).setVisibility(View.VISIBLE);
                                          soundPool.play(audioFxCountDownResID, 1.0f, 1.0f, 3, 0, 1.0f);
                                          findViewById(R.id.iv_count_down_2).setAnimation(anim_count_down2);
                                      }
                                  },
                1500);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          findViewById(R.id.iv_count_down_2).setVisibility(View.INVISIBLE);
                                          anim_count_down2.cancel();
                                          findViewById(R.id.iv_count_down_1).setVisibility(View.VISIBLE);
                                          soundPool.play(audioFxCountDownResID, 1.0f, 1.0f, 3, 0, 1.0f);
                                          findViewById(R.id.iv_count_down_1).setAnimation(anim_count_down1);
                                      }
                                  },
                2500);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          findViewById(R.id.iv_count_down_1).setVisibility(View.INVISIBLE);
                                          anim_count_down1.cancel();
                                          audio_fx_game_intro.start();
                                          music_get_wacky.start();
                                          game.start();
                                      }
                                  },
                3500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");

        if (game.getGameState() == GameState.PAUSE){
            Log.i(TAG, "{onResume} ─ GameState.PAUSE ─ Try Resume");
            music_get_wacky.start();
            Log.i(TAG, "{onResume} ─ GameState.PAUSE ─ Start music");
            game.resume();
            Log.i(TAG, "{onResume} ─ GameState.PAUSE ─ call game.resume()");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        game.pause();
        music_get_wacky.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        music_get_wacky.stop();
    }

    @Override
    public void onGameOver() {
        Log.i(TAG, "onGameOver");
        game.destroy();
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);
        this.finish();
    }
}