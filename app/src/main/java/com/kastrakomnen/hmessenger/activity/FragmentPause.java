package com.kastrakomnen.hmessenger.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kastrakomnen.hmessenger.ActivityGameMap;
import com.kastrakomnen.hmessenger.ActivityHome;
import com.kastrakomnen.hmessenger.FragmentOptions;
import com.kastrakomnen.hmessenger.R;

public class FragmentPause extends Fragment {


    public FragmentPause() {
        super(R.layout.fragment_pause);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.fragment_pause_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPlayBoard)getActivity()).onResume();
                getParentFragmentManager().popBackStack();
            }
        });

        getView().findViewById(R.id.fragment_pause_restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityPlayBoard)getActivity()).onResume();
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        getView().findViewById(R.id.fragment_pause_preferences_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, FragmentOptions.class, null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        getView().findViewById(R.id.fragment_pause_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityGameMap.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}