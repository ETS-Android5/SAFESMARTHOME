package ca.team.safe.smart.home.it.safe.smart.home;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginSep extends AppCompatActivity {
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
                Intent i = new Intent(LoginSep.this, RegisterSep.class);
                i.putExtra("register", "reg");
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
                    getCustomerAddress(email,pass,secureID);
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


    public void getCustomerAddress(String email, String pass, String secureID) {
       DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("secureID1");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> map = (Map<String, String>) snapshot.getValue();

                try {
                   if (!email.equals(map.get("email"))) {
                       Snackbar.make(view, "invalid email address", Snackbar.LENGTH_SHORT).show();
                   }else if (!pass.equals(map.get("pass")) ){
                       Snackbar.make(view, "Invalid Password" ,Snackbar.LENGTH_SHORT).show();
                   }else if (!secureID.equals(map.get("SecureID")) ){
                       Snackbar.make(view, "Invalid secureID" ,Snackbar.LENGTH_SHORT).show();
                   }else {
                       Intent i = new Intent(LoginSep.this, MainActivity.class);
                       startActivity(i);
                   }
                }catch (Exception e){}
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}