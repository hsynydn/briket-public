package com.kastrakomnen.hmessenger.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.kastrakomnen.hmessenger.R;

public class FragmentStageDetails extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stage_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.sd_button_back).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.quit_button_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.quit_button_unpressed));

                    getParentFragmentManager().popBackStack();
                }

                return true;
            }
        });

        getView().findViewById(R.id.sd_button_ok).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.play_button_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.drawable.play_button_unpressed));

                    Intent intent = new Intent(getActivity(), ActivityPlayBoard.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                return true;
            }
        });
    }
}