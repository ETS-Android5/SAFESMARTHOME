package ca.team.safe.smart.home.it.safe.smart.home.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.team.safe.smart.home.it.safe.smart.home.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DistanceSensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_sensor);
    }
}