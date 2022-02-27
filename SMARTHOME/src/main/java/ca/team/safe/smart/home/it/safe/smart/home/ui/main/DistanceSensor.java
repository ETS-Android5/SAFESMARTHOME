package ca.team.safe.smart.home.it.safe.smart.home.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.Set;

public class DistanceSensor extends AppCompatActivity {
View view;
    TextView Dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_sensor);
         Dis=findViewById(R.id.Dis);
         FirebaseApp.initializeApp(this);
        getCustomerAddress();
    }


    public void getCustomerAddress() {
       ;
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("secureID0");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Double> map = (HashMap<String, Double>) snapshot.getValue();

                List<String> d = new ArrayList<>();
                try {
                    Set keys=map.keySet();
                    String res="";
                  int  i=0;
                    for (Object key :keys ){
                        i++;
                        if (!key.toString().equals("customer_address"))
                            d.add(map.get(key)+"");
                        res=res+map.get(key)+"\n";
                        if (i==keys.size())
                        {
                       Dis.setVisibility(View.GONE);

                        ListView listView= findViewById(R.id.listView);
                        listView.setAdapter(new ArrayAdapter<String>(DistanceSensor.this,
                                android.R.layout.simple_list_item_1, d));

                    }
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