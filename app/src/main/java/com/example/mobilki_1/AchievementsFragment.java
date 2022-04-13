package com.example.mobilki_1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AchievementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchievementsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AchievementsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AchievementsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AchievementsFragment newInstance(String param1, String param2) {
        AchievementsFragment fragment = new AchievementsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TableLayout tableLayout = view.findViewById(R.id.tableLayoutAchievements);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(view.getContext());
        TextView titleName = new TextView(view.getContext());
        TextView titleScore = new TextView(view.getContext());
        TextView titleDate = new TextView(view.getContext());
        titleName.setTextAppearance(android.R.style.TextAppearance_Large);
        titleScore.setTextAppearance(android.R.style.TextAppearance_Large);
        titleDate.setTextAppearance(android.R.style.TextAppearance_Large);
        titleName.setText("Profile name");
        titleScore.setText("Score");
        titleDate.setText("Date");
        rowTitle.addView(titleName);
        rowTitle.addView(titleScore);
        rowTitle.addView(titleDate);
        tableLayout.addView(rowTitle);
        TableRow rowSeparator = new TableRow(view.getContext());
        rowSeparator.addView(new TextView(view.getContext()));
        tableLayout.addView(rowSeparator);

        ArrayList<Profile> profileList = Profiles.getInstance().getProfileList();
        Collections.sort(profileList, Profile.scoreComparator);

        for (Profile profile : profileList){
            TableRow row = new TableRow(view.getContext());
            TextView name = new TextView(view.getContext());
            TextView score = new TextView(view.getContext());
            TextView date = new TextView(view.getContext());
            name.setTextAppearance(android.R.style.TextAppearance_Large);
            score.setTextAppearance(android.R.style.TextAppearance_Large);
            date.setTextAppearance(android.R.style.TextAppearance_Large);
            name.setText(profile.name);
            score.setText(String.valueOf(profile.score));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
            date.setText(dateFormat.format(profile.lastGameDate));
            row.addView(name);
            row.addView(score);
            row.addView(date);
            tableLayout.addView(row);
        }
    }
}