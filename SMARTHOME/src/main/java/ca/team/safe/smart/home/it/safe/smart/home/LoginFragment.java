//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    EditText emailaddress, password;
    Button login;
    String email, pass, secureID;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
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
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        EditText emailaddress = rootView.findViewById(R.id.editTextTextPersonName2);
        EditText password = rootView.findViewById(R.id.editTextTextPassword2);
        EditText editTextNumberSigned = rootView.findViewById(R.id.editTextNumberSigned);

       /* public void login(View view){
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){

                //correcct password
            }else{
                //wrong password
            }

            Button button = findViewById(R.id.callbackButton);
      button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
            Snackbar snackBar = Snackbar .make(v, "An Error Occurred!", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
               @Override
               public void onClick(View view) {
               }
            });*/
//        username = (EditText) rootView.findViewById(R.id.editTextTextPersonName2);
//        password = (EditText) rootView.findViewById(R.id.editTextTextPassword2);
//
        firebaseDatabase = FirebaseDatabase.getInstance("https://safesmarthome-cdf48-default-rtdb.firebaseio.com/");
        databaseSize();
        //viewPager.setCurrentItem(0);
        Button button = rootView.findViewById(R.id.loginbtn);
        Button btnRegister = rootView.findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // below line is used to get reference for our database.
                databaseReference = firebaseDatabase.getReference();
                secureID = editTextNumberSigned.getText().toString().trim();
                email = emailaddress.getText().toString().trim();
                if (!email.contains("gmail.com")) {
                    Snackbar.make(rootView, "Please enter correct gmail address", Snackbar.LENGTH_SHORT).show();
                    //if (email.equals("SmartHome") && pass.equals("Home123") && secureID.length() == 9) {
//                       viewPager.setCurrentItem(1);
//                       Snackbar.make(rootView, "Permission Granted", Snackbar.LENGTH_SHORT).show();
//                    } else {
//
//                        Snackbar.make(rootView, "Permission Denied", Snackbar.LENGTH_SHORT).show();
//                    }
                } else if (pass.equals("")) {
                    Snackbar.make(rootView, "Please enter password", Snackbar.LENGTH_SHORT).show();
                } else if (secureID.length() != 9) {
                    Snackbar.make(rootView, "Please enter 9 digit Secure ID", Snackbar.LENGTH_SHORT).show();
                } else {
                    AddressFragment.editTextTextPostalAddress.setText(streetAddress);
                    AddressFragment.editTextTextCity.setText(city);

                    try {
                        ArrayAdapter myAdap = (ArrayAdapter) AddressFragment.spinner.getAdapter();
                        int spinnerPosition = myAdap.getPosition(LoginFragment.provinces);
                        AddressFragment.spinner.setSelection(spinnerPosition);
                    } catch (Exception e) {
                    }
                    AddressFragment.editTextTextPostalAddress2.setText(postalcode);
                    AddressFragment.editTextTextCountry.setText(country);
                    addDatatoFirebase(new LoginModel(secureID));
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailaddress.getText().toString();
                pass = password.getText().toString();
                secureID = editTextNumberSigned.getText().toString();
                Log.e("name", email);
                Log.e("password", pass);
                Log.e("secureID", secureID + " length " + secureID.length());
                String uName, uPass;
                uName = sharedPreferences.getString("SmartHome", null);
                uPass = sharedPreferences.getString("Home123", null);

                //   if(name.equals(uName) && pass.equals(uPass)) {
                if (!email.contains("gmail.com")) {
                    Snackbar.make(rootView, "Please enter correct gmail address", Snackbar.LENGTH_SHORT).show();
                    //if (email.equals("SmartHome") && pass.equals("Home123") && secureID.length() == 9) {
//                       viewPager.setCurrentItem(1);
//                       Snackbar.make(rootView, "Permission Granted", Snackbar.LENGTH_SHORT).show();
//                    } else {
//
//                        Snackbar.make(rootView, "Permission Denied", Snackbar.LENGTH_SHORT).show();
//                    }
                } else if (pass.equals("")) {
                    Snackbar.make(rootView, "Please enter password", Snackbar.LENGTH_SHORT).show();
                } else if (secureID.length() != 9) {
                    Snackbar.make(rootView, "Please enter 9 digit Secure ID", Snackbar.LENGTH_SHORT).show();
                } else {
                    viewPager.setCurrentItem(1);

                    AddressFragment.editTextTextPostalAddress.setText(streetAddress);
                    AddressFragment.editTextTextCity.setText(city);

                    try {
                        ArrayAdapter myAdap = (ArrayAdapter) AddressFragment.spinner.getAdapter();
                        int spinnerPosition = myAdap.getPosition(LoginFragment.provinces);
                        AddressFragment.spinner.setSelection(spinnerPosition);
                    } catch (Exception e) {
                    }
                    AddressFragment.editTextTextPostalAddress2.setText(postalcode);
                    AddressFragment.editTextTextCountry.setText(country);
                    addDatatoFirebase(new LoginModel(secureID));

                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


    private void addDatatoFirebase(LoginModel loginModel) {
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                long count = 0;

                databaseReference1 = firebaseDatabase.getReference("secureID" + snapshot.getChildrenCount());
//                Snackbar.make(viewPager, "secureID " + snapshot.getChildrenCount() + " added", Snackbar.LENGTH_SHORT).show();
                try {
                    if (snapshot.getChildrenCount() != 0) {
                        if (snapshot.getValue().toString().trim().contains(secureID)) {
//            String msg="already added";
//            Snackbar.make(viewPager, msg, Snackbar.LENGTH_SHORT).show();
                        }

                        if (!snapshot.getValue().toString().trim().contains(secureID)) {
                            databaseReference1.setValue(loginModel);
                            // after adding this data we are showing toast message.
//            Snackbar.make(viewPager, "secureID " + snapshot.getValue().toString().trim() + " added", Snackbar.LENGTH_SHORT).show();
                        }
                    } else {
                        databaseReference1.setValue(loginModel);
                        // after adding this data we are showing toast message.
                        Snackbar.make(viewPager, "secureID " + snapshot.getValue().toString().trim() + " added", Snackbar.LENGTH_SHORT).show();

                    }

                    AddressFragment.editTextTextPostalAddress.setText(streetAddress);
                    AddressFragment.editTextTextCity.setText(city);

                    try {
                        ArrayAdapter myAdap = (ArrayAdapter) AddressFragment.spinner.getAdapter();
                        int spinnerPosition = myAdap.getPosition(LoginFragment.provinces);
                        AddressFragment.spinner.setSelection(spinnerPosition);
                    } catch (Exception e) {
                    }
                    AddressFragment.editTextTextPostalAddress2.setText(postalcode);
                    AddressFragment.editTextTextCountry.setText(country);
                    addDatatoFirebase(new LoginModel(secureID));
                    viewPager.setCurrentItem(1);
                } catch (Exception e) {
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(getActivity(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }


 public static   String streetAddress, city, provinces, postalcode, country;
    public void databaseSize() {
        databaseReference = firebaseDatabase.getReference("secureID0").child("customer_address");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> map = (Map<String, String>) snapshot.getValue();

                streetAddress = map.get("streetAddress");
                city = map.get("city");
                provinces = map.get("provinces");
                postalcode = map.get("postalcode");
                country = map.get("country");
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
