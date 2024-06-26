package com.kastrakomnen.hmessenger.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.activity.FragmentPause;

public class FragmentOptions extends Fragment {

    public FragmentOptions(){
        super(R.layout.layout_options);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView layout_options_button_home = getView().findViewById(R.id.layout_options_button_home);
        layout_options_button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }
}