package com.kastrakomnen.hmessenger.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.BriketContext;

public class FragmentPreferencesDropDown extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preferences_drop_down, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SwitchCompat switchCompatMusic = view.findViewById(R.id.fragment_preferences_drop_down_switch_music);
        switchCompatMusic.setChecked(BriketContext.getInstance().getPreferences().isMusic());

        switchCompatMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    BriketContext.getInstance().MUSIC.setOn();
                    BriketContext.getInstance().getPreferences().setMusic(true);
                }else{
                    BriketContext.getInstance().MUSIC.setOff();
                    BriketContext.getInstance().getPreferences().setMusic(false);
                }
            }
        });

        SwitchCompat switchCompatSound = view.findViewById(R.id.fragment_preferences_drop_down_switch_sound);
        switchCompatSound.setChecked(BriketContext.getInstance().getPreferences().isSound());

        switchCompatSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    BriketContext.getInstance().SOUND.setOn();
                    BriketContext.getInstance().getPreferences().setSound(true);
                }else{
                    BriketContext.getInstance().SOUND.setOff();
                    BriketContext.getInstance().getPreferences().setSound(false);
                }
            }
        });

        view.findViewById(R.id.fragment_preferences_drop_down_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });
    }
}