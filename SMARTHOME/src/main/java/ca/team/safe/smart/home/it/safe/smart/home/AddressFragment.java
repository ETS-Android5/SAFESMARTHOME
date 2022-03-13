//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import static androidx.core.content.ContextCompat.getSystemService;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Map;

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
   public static Spinner spinner;
    public static EditText editTextTextCountry,editTextTextCity,editTextTextPostalAddress,editTextTextPostalAddress2;
    private boolean isSpinnerTouched = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
         spinner = view.findViewById(R.id.spinner);
         editTextTextCity = view.findViewById(R.id.editTextTextCity);
         editTextTextCountry = view.findViewById(R.id.editTextTextCountry);
         editTextTextPostalAddress = view.findViewById(R.id.editTextTextPostalAddress);
         editTextTextPostalAddress2 = view.findViewById(R.id.editTextTextPostalAddress2);
        Button buttonInsert = view.findViewById(R.id.buttonInsert);
        FloatingActionButton fab =view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.close_activity)
                        .setMessage(R.string.are_you_sure)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(requireActivity(),LoginSep.class));
                                requireActivity().finish();
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance("https://safesmarthome-cdf48-default-rtdb.firebaseio.com/");

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference();

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city=editTextTextCity.getText().toString();
                String country=editTextTextCountry.getText().toString();
                String address=editTextTextPostalAddress.getText().toString();
                String postalcode=editTextTextPostalAddress2.getText().toString();
                size=0;
                addDatatoFirebase(new AddressModel(address,city,
                       spinner.getSelectedItem().toString(),
               postalcode ,country));
            }
        });

       String provinces[] = getResources().getStringArray(R.array.provinces);
       ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
               provinces);
       spinner.setAdapter(arrayAdapter);

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        getCustomerAddress();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {




                //for english
                String name_english = getResources().getStringArray(R.array.provinces_fullname)[i];

                try {
                    if (isSpinnerTouched)
                    Snackbar.make(view, name_english, Snackbar.LENGTH_SHORT).show();
                }catch (Exception e){}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    public static   String streetAddress, city, provinces, postalcode, country;
    public void getCustomerAddress() {
        databaseReference = firebaseDatabase.getReference("secureID2").child("customer_address");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> map = (Map<String, String>) snapshot.getValue();

                try {
                    streetAddress = map.get("streetAddress");
                    city = map.get("city");
                    provinces = map.get("provinces");
                    postalcode = map.get("postalcode");
                    country = map.get("country");

                    editTextTextPostalAddress.setText(streetAddress);
                    editTextTextCity.setText(city);

                    try {
                        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
                        int spinnerPosition = myAdap.getPosition(provinces);
                        spinner.setSelection(spinnerPosition);
                    } catch (Exception e) {
                    }
                    editTextTextPostalAddress2.setText(postalcode);
                    editTextTextCountry.setText(country);

                }catch (Exception e){}
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    long size=0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotification() {
        Intent intent = new Intent(getActivity().getApplicationContext(), AddressFragment.class);
        String CHANNEL_ID="CHANNEL";
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getActivity().getApplicationContext(),CHANNEL_ID)
                .setContentText("Heading")
                .setContentTitle("subheading")
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,"Title",pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .build();

        NotificationManager notificationManager=(NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);

    }


    public void databaseSize(){
       databaseReference = firebaseDatabase.getReference("secureID0").child("customer_address");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String , String> map=(Map<String , String>)snapshot.getValue();
                String streetAddress , city ,provinces, postalcode,country;
                editTextTextPostalAddress.setText(map.get(getString(R.string.streetAddress)));
                editTextTextCity.setText(map.get(R.string.city));

                ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
                int spinnerPosition = myAdap.getPosition(map.get(getString(R.string.provinces)));
                spinner.setSelection(spinnerPosition);

                editTextTextPostalAddress2.setText(map.get(getString(R.string.postalcode)));
                editTextTextCountry.setText(map.get(R.string.country));
                size = snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void addDatatoFirebase(AddressModel addressModel) {
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                if (size==0 && !editTextTextCity.getText().toString().equals("")) {
                    databaseReference1 = firebaseDatabase.getReference("secureID2").child("customer_address");


                    size++;

                    try {

                        databaseReference1.setValue(addressModel);
                        // after adding this data we are showing toast message.
            Snackbar.make(viewPager, R.string.Customer_address_added, Snackbar.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(1);
                        editTextTextCity.setText("");
                        editTextTextCountry.setText("");
                        editTextTextPostalAddress.setText("");
                        editTextTextPostalAddress2.setText("");
                        spinner.setSelection(0);



                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(getActivity(), getString(R.string.fail_to_add_data) + error, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
