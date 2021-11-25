package ca.team.safe.smart.home.it.safe.smart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginSep extends AppCompatActivity {
    String email, pass, secureID;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sep);
        Button loginbtn=findViewById(R.id.loginbtn);
        Button buttonRegister=findViewById(R.id.buttonRegister);
        EditText emailaddress = findViewById(R.id.editTextTextPersonName2);
        EditText password = findViewById(R.id.editTextTextPassword2);
        EditText editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        view = findViewById(R.id.view);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginSep.this, MainActivity.class);
                i.putExtra("register","reg");
                startActivity(i);
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
                } else if (secureID.length() != 9) {
                    Snackbar.make(view, "Please enter 9 digit Secure ID", Snackbar.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(LoginSep.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}