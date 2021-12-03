//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatusFragment newInstance(String param1, String param2) {
        StatusFragment fragment = new StatusFragment();
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

    View viewHumidity,viewSmoke,viewSecurity,viewTemperature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        viewHumidity=view.findViewById(R.id.viewHumidity);
        viewSmoke=view.findViewById(R.id.viewSmoke);
        viewSecurity=view.findViewById(R.id.viewSecurity);
        viewTemperature=view.findViewById(R.id.viewTemperature);
        viewTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,R.string.Heat_ON,Snackbar.LENGTH_SHORT).show();
            }
        });
        viewSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,R.string.No_smoke_detected,Snackbar.LENGTH_SHORT).show();
            }
        });
        viewHumidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,R.string.NO_Humidity,Snackbar.LENGTH_SHORT).show();
            }
        });
        viewSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,R.string.No_intruder,Snackbar.LENGTH_SHORT).show();
            }
        });
        FloatingActionButton fab = view.findViewById(R.id.fab);
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

        return view;
    }
}