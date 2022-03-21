package ca.team.safe.smart.home.it.safe.smart.home.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ca.team.safe.smart.home.it.safe.smart.home.R;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DistanceSensor extends AppCompatActivity {
View view;
    TextView Dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_sensor);
         Dis=findViewById(R.id.Dis);
        view=findViewById(R.id.view);
         FirebaseApp.initializeApp(this);
        getCustomerAddress();
    }


    public void getCustomerAddress() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("secureID0");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //getting values on hashmap from firebase in random order
//                Map<String, Double> map = (HashMap<String, Double>) snapshot.getValue();

              //  List<String> d = new ArrayList<>();
                List<String> d1 = new ArrayList<>();
                String value;
                //getting values from firebase , setting on array list
                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
                   //get value one by one in "value" variable
                    value=String.valueOf(postSnapshot.getValue());
                    if (!value.contains("country"))
                        d1.add(value);
                }
                try {
                     int offsetColor = 0xFF000000; //offset to have 100% in alpha value
                    if (d1.size()>0)
                        {

//                       Dis.setVisibility(View.GONE);
//                        ListView listView= findViewById(R.id.listView);
//                        listView.setAdapter(new ArrayAdapter<String>(DistanceSensor.this,
//                                android.R.layout.simple_list_item_1, d1));
                            final int[] i = {0};

                        new CountDownTimer(1000*d1.size(), 1000) {
                            public void onTick(long millisUntilFinished) {
//                                Random rnd = new Random();
//                                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                int counter= 0; //Gireesh
                                int ele = counter;
                              if (i[ele]%2==0)
                                view.setBackgroundColor(Color.RED);
                                else view.setBackgroundColor(Color.GREEN);
//                                Dis.setText("seconds remaining: " + millisUntilFinished / 1000);
                                Dis.setText(" "+d1.get(i[ele]));
                                i[ele++]++;
                                counter = ele;
                                int stay = counter;
                                stay = ele;
                            }

                            public void onFinish() {
                            }
                        }.start();

                    }

                }catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}