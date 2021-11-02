//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import ca.team.safe.smart.home.it.safe.smart.home.ui.main.SectionsPagerAdapter;

public class AddressFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //french
//                Configuration conf = getResources().getConfiguration();
//                conf.locale = new Locale("fr");
//                DisplayMetrics metrics = new DisplayMetrics();
//                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//                Resources resources = new Resources(getActivity().getAssets(), metrics, conf);
//                String nameFrench = resources.getStringArray(R.array.provinces_name)[i];
//                Toast.makeText(getActivity(), nameFrench, Toast.LENGTH_SHORT).show();
//
                //for english
                String name_english =getResources().getStringArray(R.array.provinces_name)[i];
                Toast.makeText(getActivity(), name_english, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       /* FloatingActionButton fab1 = getActivity().findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                int securedID = 555666101;
                int DB_secid = 555666101;
                if (securedID == DB_secid) {
                    Snackbar.make(view, "Do not have permission to change address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "You have permission to change address", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });*/
        return view;
    }

}
