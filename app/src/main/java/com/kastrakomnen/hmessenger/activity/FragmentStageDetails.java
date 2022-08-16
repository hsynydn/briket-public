package com.kastrakomnen.hmessenger.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.BriketContext;

public class FragmentStageDetails extends Fragment {

    private static final String TAG = "{FragmentStageDetails}";

//    public InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stage_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.sd_button_ok).setVisibility(View.INVISIBLE);

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

        if (BriketContext.getInstance().mInterstitialAd != null) {

            BriketContext.getInstance().mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    // Called when ad fails to show.
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    BriketContext.getInstance().mInterstitialAd = null;
                    getView().findViewById(R.id.sd_button_ok).setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    BriketContext.getInstance().mInterstitialAd = null;
                    getView().findViewById(R.id.sd_button_ok).setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    // Called when an impression is recorded for an ad.
                    Log.d(TAG, "Ad recorded an impression.");
                }
            });

            BriketContext.getInstance().mInterstitialAd.show(getActivity());
        }else{
            getView().findViewById(R.id.sd_button_ok).setVisibility(View.VISIBLE);
        }
    }
}