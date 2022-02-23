package ca.team.safe.smart.home.it.safe.smart.home;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterSep extends AppCompatActivity {

    String email, pass, secureID;
    View view;
    public static  boolean secureIDValidation(String secureID){
        if (secureID.length() != 9) {

            return  false;
        }else
            return  true;
    }
    public static  boolean emailValidation(String email){
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return  false;
        }else return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sep);
        Button loginbtn = findViewById(R.id.loginbtn);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        EditText emailaddress = findViewById(R.id.editTextTextPersonName2);
        EditText password = findViewById(R.id.editTextTextPassword2);
        EditText editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        view = findViewById(R.id.view);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterSep.this, LoginSep.class);
                //i.putExtra("register", "reg");
                startActivity(i);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String key = snapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secureID = editTextNumberSigned.getText().toString().trim();
                email = emailaddress.getText().toString().trim();
                pass = password.getText().toString().trim();
                if (!email.contains("gmail.com")) {
                    Snackbar.make(view, R.string.correct_email, Snackbar.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Snackbar.make(view, R.string.correct_password, Snackbar.LENGTH_SHORT).show();
                } else if(pass.length()<8 ||  !isValidPassword(pass))

                {
                    Snackbar.make(view,  R.string.Password_characters, Snackbar.LENGTH_SHORT).show();
                }
                else if (secureID.length() != 9) {
                    Snackbar.make(view, R.string.Enter_9_digit_secID, Snackbar.LENGTH_SHORT).show();
                } else {
                    register(email,pass,secureID,v);
                }
            }
        });
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public boolean register(String email, String password, String secureID, View mView) {
        final int[] chance = {0};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference[] myRef = {database.getReference()};

        if (!email.contains("gmail.com")) {
            Log.e("validation error", "please enter valid email address");
            Snackbar.make(mView, R.string.correct_email, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (password.equals("")) {
            Snackbar.make(mView, R.string.correct_password, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (password.length() < 8 || !isValidPassword(password)) {

            Snackbar.make(mView, R.string.Password_characters, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!secureIDValidation(secureID)) {
            Snackbar.make(mView, R.string.Enter_9_digit_secID, Snackbar.LENGTH_SHORT).show();
            return false;
        } else {

            Map<String , String> m=new HashMap<>();
            m.put("email",email);
            m.put("pass",pass);
            m.put("secureID",secureID);
            myRef[0].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // inside the method of on Data change we are setting
                    // our object class to our database reference.
                    // data base reference will sends data to firebase.
                        myRef[0] = database.getReference("secureID1");

                        try {

                            myRef[0].setValue(m);
                            startActivity(new Intent(mView.getContext(),MainActivity.class));
                            // after adding this data we are showing toast message.
                            Snackbar.make(viewPager, R.string.Customer_address_added, Snackbar.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }
                    }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // if the data is not added or it is cancelled then
                    // we are displaying a failure toast message.
                    Toast.makeText(RegisterSep.this, getString(R.string.fail_to_add_data) + error, Toast.LENGTH_SHORT).show();
                }
            });
        }

            return true;
        }
    }



