package ca.team.safe.smart.home.it.safe.smart.home.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ca.team.safe.smart.home.it.safe.smart.home.R;

public class Pressure_Temp  extends AppCompatActivity {
    View view;
    TextView Dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_temp);
        Dis = findViewById(R.id.Dis);
        FirebaseApp.initializeApp(this);
getCustomerAddress();
    }



    public void getCustomerAddress() {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
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
                    if (value.contains("BME"))
                        d1.add(value);
                }
                try {
                    if (d1.size()>0)
                    {
                        Dis.setVisibility(View.GONE);
                        ListView listView= findViewById(R.id.listView);
                        listView.setAdapter(new ArrayAdapter<String>(Pressure_Temp.this,
                                android.R.layout.simple_list_item_1, d1));

                    }

                }catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
