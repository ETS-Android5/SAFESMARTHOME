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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        EditText username = rootView.findViewById(R.id.editTextTextPersonName2);
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
        Button button = rootView.findViewById(R.id.loginbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailaddress.getText().toString();
                pass = password.getText().toString();
                secureID = editTextNumberSigned.getText().toString();
                Log.e("name", email);
                Log.e("password", pass);
                Log.e("secureID", secureID +" length "+secureID.length());
                String uName, uPass;
                uName = sharedPreferences.getString("SmartHome", null);
                uPass = sharedPreferences.getString("Home123", null);

                //   if(name.equals(uName) && pass.equals(uPass)) {
                if (email.equals("SmartHome") && pass.equals("Home123") && secureID.length() == 9) {
                    viewPager.setCurrentItem(1);
                    Snackbar.make(rootView, "Permission Granted", Snackbar.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(rootView, "Permission Denied", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}