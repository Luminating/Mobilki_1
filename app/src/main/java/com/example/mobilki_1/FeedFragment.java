package com.example.mobilki_1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private AnimationDrawable animationDrawable;
    private ObjectAnimator animationFish;
    private ObjectAnimator animationMeat;
    private ObjectAnimator animationMouse;
    private ObjectAnimator animationMilk;
    private View screenView;
    private int clickCount = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance(String param1, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        screenView = inflater.inflate(R.layout.fragment_feed, container, false);
        installAnimations(screenView);
        installButtonsListeners();
        screenView.findViewById(R.id.btnFeed).setEnabled(false);
        TextView textView = (TextView) screenView.findViewById(R.id.textFeedWellnessPoints);
        textView.setText(String.valueOf(Profiles.getInstance().getCurrentProfile().score));
        return screenView;
    }

/*
    @Override
    public void onDestroyView() {
        Context context = getActivity();
        assert context != null;
        TextView textView = (TextView) getActivity().findViewById(R.id.textFeedWellnessPoints);
        super.onDestroyView();
    }
 */
    @Override
    public void onDestroy() {
        Context context = getActivity();
        assert context != null;
        TextView textView = (TextView) getActivity().findViewById(R.id.textFeedWellnessPoints);
        super.onDestroy();
    }

    private void installAnimations(View view) {
        ImageView imageViewCat = (ImageView) view.findViewById(R.id.imageViewCat);
        imageViewCat.setBackgroundResource(R.drawable.cat_animation);
        animationDrawable = (AnimationDrawable) imageViewCat.getBackground();

        ImageView imageViewFish = (ImageView) view.findViewById(R.id.imageViewFish);
        animationFish = ObjectAnimator.ofFloat(imageViewFish, "translationY", 3000f);
        animationFish.setDuration(5000);

        ImageView imageViewMeat = (ImageView) view.findViewById(R.id.imageViewMeat);
        animationMeat = ObjectAnimator.ofFloat(imageViewMeat, "translationY", 3000f);
        animationMeat.setDuration(5000);

        ImageView imageViewMouse = (ImageView) view.findViewById(R.id.imageViewMouse);
        animationMouse = ObjectAnimator.ofFloat(imageViewMouse, "translationY", 3000f);
        animationMouse.setDuration(5000);

        ImageView imageViewMilk = (ImageView) view.findViewById(R.id.imageViewMilk);
        animationMilk = ObjectAnimator.ofFloat(imageViewMilk, "translationY", 3000f);
        animationMilk.setDuration(5000);

        animationFish.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animation) {
                new CountDownTimer(new Random().nextInt(5000) + 2000, 10000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        animationFish.start();
                    }
                }.start();
            }
        });
        animationFish.start();

        animationMeat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animation) {
                new CountDownTimer(new Random().nextInt(5000) + 2100, 10000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        animation.start();
                    }
                }.start();
            }
        });
        animationMeat.start();

        animationMouse.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animation) {
                new CountDownTimer(new Random().nextInt(5000) + 2100, 10000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        animation.start();
                    }
                }.start();
            }
        });
        animationMouse.start();

        animationMilk.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(final Animator animation) {
                new CountDownTimer(new Random().nextInt(5000) + 2100, 10000) {
                    public void onTick(long millisUntilFinished) {
                    }
                    public void onFinish() {
                        animation.start();
                    }
                }.start();
            }
        });
        animationMilk.start();
    }

    private void installButtonsListeners(){
        screenView.findViewById(R.id.btnFeed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profiles.getInstance().getCurrentProfile().score++;
                Profiles.getInstance().getCurrentProfile().lastGameDate = new Date();
                TextView textView = (TextView) screenView.findViewById(R.id.textFeedWellnessPoints);
                textView.setText(String.valueOf(Profiles.getInstance().getCurrentProfile().score));
                animationDrawable.stop();
                animationDrawable.start();
                screenView.findViewById(R.id.btnFeed).setEnabled(false);
            }
        });

        screenView.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTop() < screenView.findViewById(R.id.imageViewFish).getTranslationY()) {
                    if (view.getBottom() > (screenView.findViewById(R.id.imageViewFish).getTranslationY() - screenView.findViewById(R.id.imageViewMouse).getHeight()))
                    {
                        animationFish.end();
                        incClickCount();
                    }
                }
            }
        });

        screenView.findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTop() < screenView.findViewById(R.id.imageViewMeat).getTranslationY()) {
                    if (view.getBottom() > (screenView.findViewById(R.id.imageViewMeat).getTranslationY() - screenView.findViewById(R.id.imageViewMeat).getHeight()))
                    {
                        animationMeat.end();
                        incClickCount();
                    }
                }
            }
        });

        screenView.findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTop() < screenView.findViewById(R.id.imageViewMouse).getTranslationY()) {
                    if (view.getBottom() > (screenView.findViewById(R.id.imageViewMouse).getTranslationY() - screenView.findViewById(R.id.imageViewMouse).getHeight()))
                    {
                        animationMouse.end();
                        incClickCount();
                    }
                }
            }
        });

        screenView.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTop() < screenView.findViewById(R.id.imageViewMilk).getTranslationY()) {
                    if (view.getBottom() > (screenView.findViewById(R.id.imageViewMilk).getTranslationY() - screenView.findViewById(R.id.imageViewMilk).getHeight()))
                    {
                        animationMilk.end();
                        incClickCount();
                    }
                }
            }
        });
    }

    private void incClickCount(){
        clickCount++;
        if ((clickCount % 5) == 0) {
            screenView.findViewById(R.id.btnFeed).setEnabled(true);
            clickCount = 0;
        }
    }
}