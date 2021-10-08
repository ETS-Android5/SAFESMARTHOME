package ca.team.safe.smart.home.it.safe.smart.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    ImageView imageView;

    int stage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        SharedPreferences preferences = getActivity().getSharedPreferences("PREFS", 0);
        stage = preferences.getInt("switch_stage", 1);

        imageView = (ImageView) getActivity().findViewById(R.id.controls_tempIV);
//        setSwitchImage(stage);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switchNumber();
//            }
//        });
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

//    public void switchNumber() {
//        switch(stage) {
//            case 1:
//                stage = 2;
//                setSwitchImage(stage);
//                doStuff(stage);
//                break;
//            case 2:
//                stage = 3;
//                setSwitchImage(stage);
//                doStuff(stage);
//                break;
//            case 3:
//                stage = 1;
//                setSwitchImage(stage);
//                doStuff(stage);
//                break;
//        }
//
//        //saves state
//        SharedPreferences preferences = requireContext().getSharedPreferences("PREFS", 0);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("switch_stage", stage);
//        editor.apply();
//    }
//
//    //Sets Image of the switch
//    private void setSwitchImage(int current) {
//        switch (current) {
//            case 1:
//                imageView.setImageResource(R.drawable.ic_baseline_power_settings_new_24);
//                break;
//            case 2:
//                imageView.setImageResource(R.drawable.ic_baseline_ac_unit_24);
//                break;
//            case 3:
//                imageView.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
//                break;
//        }
//    }
//
//    public void doStuff(int current) {
//        switch (current) {
//            case 1:
//                Toast.makeText(getActivity(), "Temperature is " + current, Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//                Toast.makeText(getActivity(), "Temperature is " + current, Toast.LENGTH_SHORT).show();
//                break;
//            case 3:
//                Toast.makeText(getActivity(), "Temperature is " + current, Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
}