package com.example.mobilki_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileDeleteFragment newInstance(String param1, String param2) {
        ProfileDeleteFragment fragment = new ProfileDeleteFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_delete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.btnCancelProfileDelete).setOnClickListener(v -> {
            TextView textView = view.findViewById(R.id.textViewMessageDeleteProfile);
            textView.setText("");
            Navigation.findNavController(view).navigate(R.id.action_profileDeleteFragment_to_profileMenuFragment);
        });

        view.findViewById(R.id.btnDeleteProfileDelete).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.editTextProfileDelete);
            String profileName = editText.getText().toString();
            TextView textView = view.findViewById(R.id.textViewMessageDeleteProfile);
            if (profileName.equals(Profiles.getInstance().getCurrentProfile().name)){
                textView.setText("can't delete current profile");
            } else if (!Profiles.getInstance().isProfileNamePresent(profileName)){
                textView.setText("this profile name not exist");
            } else {
                Profiles.getInstance().deleteProfile(profileName);
                textView.setText("");
                TextView tv = getActivity().findViewById(R.id.textProfileName);
                tv.setText(Profiles.getInstance().getCurrentProfile().name);
                tv = getActivity().findViewById(R.id.textViewProfileName);
                tv.setText(Profiles.getInstance().getCurrentProfile().name);
                tv = getActivity().findViewById(R.id.textViewProfileScore);
                tv.setText(String.valueOf(Profiles.getInstance().getCurrentProfile().score));
                Navigation.findNavController(view).navigate(R.id.action_profileDeleteFragment_to_profileMenuFragment);
            }
        });
    }
}