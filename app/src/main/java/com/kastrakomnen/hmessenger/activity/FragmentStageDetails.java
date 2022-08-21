package com.kastrakomnen.hmessenger.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.kastrakomnen.hmessenger.R;
import com.kastrakomnen.hmessenger.model.BriketContext;
import com.kastrakomnen.hmessenger.model.set.Agent;
import com.kastrakomnen.hmessenger.model.set.FormationType;

import java.util.ArrayList;

public class FragmentStageDetails extends Fragment {

    private static final String TAG = "{FragmentStageDetails}";
    private int screenWidth;
    private int screenHeight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;

        Log.d(TAG, Integer.toString(screenHeight));
        Log.d(TAG, Integer.toString(screenWidth));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stage_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<ImageView> patternLocations = new ArrayList<>();
        patternLocations.add(getView().findViewById(R.id.pattern_location_1));
        patternLocations.add(getView().findViewById(R.id.pattern_location_2));
        patternLocations.add(getView().findViewById(R.id.pattern_location_3));
        patternLocations.add(getView().findViewById(R.id.pattern_location_4));
        patternLocations.add(getView().findViewById(R.id.pattern_location_5));
        patternLocations.add(getView().findViewById(R.id.pattern_location_6));
        patternLocations.add(getView().findViewById(R.id.pattern_location_7));
        patternLocations.add(getView().findViewById(R.id.pattern_location_8));
        patternLocations.add(getView().findViewById(R.id.pattern_location_9));

        // This table row may not include any item
        TableRow tableRow3 = getView().findViewById(R.id.pattern_location_row_3);

        ImageButton okButton = getView().findViewById(R.id.sd_button_ok);
        okButton.setVisibility(View.INVISIBLE);
        ViewGroup.LayoutParams okButtonLayoutParams = okButton.getLayoutParams();
        okButtonLayoutParams.height = 4 * screenWidth / 24;
        okButtonLayoutParams.width = 4 * screenWidth / 6;
        okButton.setLayoutParams(okButtonLayoutParams);

        ImageButton cancelButton = getView().findViewById(R.id.sd_button_back);
        ViewGroup.LayoutParams cancelButtonLayoutParams = cancelButton.getLayoutParams();
        cancelButtonLayoutParams.height = 3 * screenWidth / 24;
        cancelButtonLayoutParams.width = 3 * screenWidth / 6;
        cancelButton.setLayoutParams(cancelButtonLayoutParams);

        cancelButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.mipmap.stage_details_cancel_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.mipmap.stage_details_cancel_unpressed));
                    getParentFragmentManager().popBackStack();
                }

                return true;
            }
        });

        okButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.mipmap.stage_details_play_pressed));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    ((ImageButton)view).setBackground(getResources().getDrawable(R.mipmap.stage_details_play_unpressed));

                    Intent intent = new Intent(getActivity(), ActivityPlayBoard.class);
                    startActivity(intent);
                    getActivity().finish();
                }

                return true;
            }
        });

        boolean flag_BOX = false;
        boolean flag_L = false;
        boolean flag_L3 = false;
        boolean flag_DIAG3 = false;
        boolean flag_LINE3 = false;
        boolean flag_DIAG2 = false;
        boolean flag_LINE = false;
        boolean flag_LINE5 = false;
        boolean flag_HALF_MILL = false;
        boolean flag_MILL = false;
        boolean flag_T = false;
        boolean flag_Z = false;
        boolean flag_RL = false;

        boolean flag_S = false;
        boolean flag_T_LONG = false;
        boolean flag_MILL_COMPACT = false;
        boolean flag_MILL_COMPACT_EMPTY = false;
        boolean flag_LINE2 = false;
        boolean flag_SINGLE = false;
        boolean flag_DOUBLE_SEPARATION = false;

        int i = 0;
        for (FormationType formationType : BriketContext.getInstance().getCurrentStage().getFormationTypes()) {

            // TODO Remove this check after database arrangment
            if (i == 6) break;

           switch (formationType){
               case BOX_CW0:
               case BOX_CW90:
               case BOX_CW180:
               case BOX_CW270:
                   if (flag_BOX) break; else flag_BOX = true;
                   patternLocations.get(i).setImageResource(R.mipmap.box_green);
                   i++;
                   break;
               case L_CW0:
               case L_CW90:
               case L_CW180:
               case L_CW270:
                   if (flag_L) break; else flag_L = true;
                   patternLocations.get(i).setImageResource(R.mipmap.l4_fushia);
                   i++;
                   break;
               case L3_CW0:
               case L3_CW90:
               case L3_CW180:
               case L3_CW270:
                   if (flag_L3) break; else flag_L3 = true;
                   patternLocations.get(i).setImageResource(R.mipmap.l3_fushia);
                   i++;
                   break;
               case DIAG2_CW0:
               case DIAG2_CW90:
               case DIAG2_CW180:
               case DIAG2_CW270:
                   if (flag_DIAG2) break; else flag_DIAG2 = true;
                   patternLocations.get(i).setImageResource(R.mipmap.diag2_green);
                   i++;
                   break;
               case DIAG3_CW0:
               case DIAG3_CW90:
               case DIAG3_CW180:
               case DIAG3_CW270:
                   if (flag_DIAG3) break; else flag_DIAG3 = true;
                   patternLocations.get(i).setImageResource(R.mipmap.diag3_green);
                   i++;
                   break;
               case LINE3_CW0:
               case LINE3_CW90:
               case LINE3_CW180:
               case LINE3_CW270:
                   if (flag_LINE3) break; else flag_LINE3 = true;
                   patternLocations.get(i).setImageResource(R.mipmap.line3_orange);
                   i++;
                   break;
               case LINE_CW0:
               case LINE_CW90:
               case LINE_CW180:
               case LINE_CW270:
                   if (flag_LINE) break; else flag_LINE = true;
                   patternLocations.get(i).setImageResource(R.mipmap.line4_fushia);
                   i++;
                   break;
               case LINE5_CW0:
               case LINE5_CW90:
               case LINE5_CW180:
               case LINE5_CW270:
                   if (flag_LINE5) break; else flag_LINE5 = true;
                   patternLocations.get(i).setImageResource(R.mipmap.line5_orange);
                   i++;
                   break;
               case HALF_MILL_CW0:
               case HALF_MILL_CW90:
               case HALF_MILL_CW180:
               case HALF_MILL_CW270:
                   if (flag_HALF_MILL) break; else flag_HALF_MILL = true;
                   patternLocations.get(i).setImageResource(R.mipmap.half_mill_fushia);
                   i++;
                   break;
               case MILL_CW0:
               case MILL_CW90:
               case MILL_CW180:
               case MILL_CW270:
                   if (flag_MILL) break; else flag_MILL = true;
                   patternLocations.get(i).setImageResource(R.mipmap.mill_green);
                   i++;
                   break;
               case T_CW0:
               case T_CW90:
               case T_CW180:
               case T_CW270:
                   if (flag_T) break; else flag_T = true;
                   patternLocations.get(i).setImageResource(R.mipmap.t_blue);
                   i++;
                   break;
               case Z_CW0:
               case Z_CW90:
               case Z_CW180:
               case Z_CW270:
                   if (flag_Z) break; else flag_Z = true;
                   patternLocations.get(i).setImageResource(R.mipmap.z_orange);
                   i++;
                   break;
               case RL_CW0:
               case RL_CW90:
               case RL_CW180:
               case RL_CW270:
                   if (flag_RL) break; else flag_RL = true;
                   patternLocations.get(i).setImageResource(R.mipmap.rl_blue);
                   i++;
                   break;
               case S_CW0:
               case S_CW90:
               case S_CW180:
               case S_CW270:
                   if (flag_S) break; else flag_S = true;
                   patternLocations.get(i).setImageResource(R.mipmap.s_orange);
                   i++;
                   break;
               case T_LONG_CW0:
               case T_LONG_CW90:
               case T_LONG_CW180:
               case T_LONG_CW270:
                   if (flag_T_LONG) break; else flag_T_LONG = true;
                   patternLocations.get(i).setImageResource(R.mipmap.t_long_orange);
                   i++;
                   break;
               case MILL_COMPACT_CW0:
               case MILL_COMPACT_CW90:
               case MILL_COMPACT_CW180:
               case MILL_COMPACT_CW270:
                   if (flag_MILL_COMPACT) break; else flag_MILL_COMPACT = true;
                   patternLocations.get(i).setImageResource(R.mipmap.mill_compact_blue);
                   i++;
                   break;
               case MILL_COMPACT_EMPTY_CW0:
               case MILL_COMPACT_EMPTY_CW90:
               case MILL_COMPACT_EMPTY_CW180:
               case MILL_COMPACT_EMPTY_CW270:
                   if (flag_MILL_COMPACT_EMPTY) break; else flag_MILL_COMPACT_EMPTY = true;
                   patternLocations.get(i).setImageResource(R.mipmap.mill_compact_empty_blue);
                   i++;
                   break;
               case SINGLE_CW0:
               case SINGLE_CW90:
               case SINGLE_CW180:
               case SINGLE_CW270:
                   if (flag_SINGLE) break; else flag_SINGLE = true;
                   patternLocations.get(i).setImageResource(R.mipmap.single_orange);
                   i++;
                   break;
               case DOUBLE_SEPARATE_CW0:
               case DOUBLE_SEPARATE_CW90:
               case DOUBLE_SEPARATE_CW180:
               case DOUBLE_SEPARATE_CW270:
                   if (flag_DOUBLE_SEPARATION) break; else flag_DOUBLE_SEPARATION = true;
                   patternLocations.get(i).setImageResource(R.mipmap.double_separate_green);
                   i++;
                   break;
               default:
                   Log.e(TAG, "Not implemented {" + formationType.toString() + "}");
           }
        }

        ArrayList<Agent> agents = BriketContext.getInstance().getCurrentStage().getAgents();

        if (agents.isEmpty()){
            ((ViewGroup)tableRow3.getParent()).removeView(tableRow3);
        }else{
            int j = 7;
            for (Agent agent : agents) {

                // TODO Remove this check after database arrangment
                if (j == 9) break;

                switch (agent.getBrickType()) {
                    case COIN:
                        patternLocations.get(j).setImageResource(R.mipmap.pattern_empty);
                        break;
                    case LINER:
                        patternLocations.get(j).setImageResource(R.mipmap.pattern_empty);
                        break;
                    case DIAMOND:
                        patternLocations.get(j).setImageResource(R.mipmap.pattern_empty);
                        break;
                    case STAR:
                        patternLocations.get(j).setImageResource(R.mipmap.star_brick);
                        break;
                }
                j++;
            }
        }

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