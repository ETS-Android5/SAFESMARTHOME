package ca.team.safe.smart.home.it.safe.smart.home;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSep extends AppCompatActivity {
    String email, pass, secureID;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sep);
        Button loginbtn = findViewById(R.id.loginbtn);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        EditText emailaddress = findViewById(R.id.editTextTextPersonName2);
        EditText password = findViewById(R.id.editTextTextPassword2);
        EditText editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        view = findViewById(R.id.view);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginSep.this, MainActivity.class);
                i.putExtra("register", "reg");
                startActivity(i);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String key = snapshot.getKey();
                  //  Snackbar.make(view, "abcd"+key, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secureID = editTextNumberSigned.getText().toString().trim();
                email = emailaddress.getText().toString().trim();
                pass = password.getText().toString().trim();
                if (!email.contains("gmail.com")) {
                    Snackbar.make(view, "Please enter correct gmail address", Snackbar.LENGTH_SHORT).show();
                } else if (pass.equals("")) {
                    Snackbar.make(view, "Please enter password", Snackbar.LENGTH_SHORT).show();
                } else if(pass.length()<8 ||  !isValidPassword(pass))

                {
                    Snackbar.make(view,  "Password should be at least 8 in length, alphabetic and numeric, 1 " +
                                    "special character(except for +,~), at 1 uppercase letter, and at least 1 digit",
                            Snackbar.LENGTH_SHORT).show();
                }
                else if (secureID.length() != 9) {
                    Snackbar.make(view, "Please enter 9 digit Secure ID", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(LoginSep.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$.*[A-Za-z].*[A-Za-z0-9]*";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}