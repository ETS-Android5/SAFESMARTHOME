//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086
package ca.team.safe.smart.home.it.safe.smart.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FireFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FireFragment() {
        // Required empty public constructor
    }

    public static FireFragment newInstance(String param1, String param2) {
        FireFragment fragment = new FireFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_fire, container, false);
        // Inflate the layout for this fragment
        Switch sc = (Switch) rootView.findViewById(R.id.fire_ac_switch);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Closing Activity")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(requireActivity(),LoginSep.class));
                                requireActivity().finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        return rootView;
    }
}
