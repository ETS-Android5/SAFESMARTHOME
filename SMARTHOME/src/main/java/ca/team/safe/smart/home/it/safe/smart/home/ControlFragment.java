//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class ControlFragment extends Fragment {

    ImageView imageView;
    SeekBar seekBar;
    Switch sc;

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

    public static ControlFragment newInstance(String param1, String param2) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_control, container, false);
        // Inflate the layout for this fragment
        SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.PREFS), 0);
        stage = preferences.getInt(getString(R.string.controls_switch_stage), 1);
        sc = (Switch) rootView.findViewById(R.id.switch5);

        seekBar=(SeekBar) rootView.findViewById(R.id.controls_seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progresValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity(), getString(R.string.controls_temp_set) + progresValue + getString(R.string.controls_degrees_c), Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

    public void showNotification() {

        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity())
                            .setSmallIcon(R.drawable.intruder)
                            .setContentTitle("Intruder detected!")
                            .setContentText("Possible Intruder has been detected.")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                }
            }
        });
    }
}