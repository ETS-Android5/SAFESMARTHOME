//Veera Gudla N01218714
//Jacob Stephens N01100888
//Patrick Loboda N01309086
package ca.team.safe.smart.home.it.safe.smart.home;

import static ca.team.safe.smart.home.it.safe.smart.home.MainActivity.viewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReviewFragment extends Fragment{

    Button review;
    EditText editTextName;
    EditText editTextNumber;
    EditText editTextEmail;
    EditText edComment;
    int size = 0;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private EditText editTextFullName;

    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
    ProgressBar loader;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RatingBar ratingBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        // Inflate the layout for this fragment
        firebaseDatabase = FirebaseDatabase.getInstance("https://safesmarthome-cdf48-default-rtdb.firebaseio.com/");

        // below line is used to get reference for our database.

        loader =  rootView.findViewById(R.id.loader);
        editTextFullName = (EditText) rootView.findViewById(R.id.editTextTextPersonName);
        editTextNumber = (EditText) rootView.findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) rootView.findViewById(R.id.editTextTextEmailAddress);
        ratingBar =  rootView.findViewById(R.id.ratingBar);
         edComment = (EditText) rootView.findViewById(R.id.textComment);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.close_activity)
                        .setMessage(R.string.are_you_sure)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               startActivity(new Intent(requireActivity(),LoginSep.class));
                               requireActivity().finish();
                            }

                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        });

        editTextFullName.addTextChangedListener(reviewTextWatcher);
        editTextNumber.addTextChangedListener(reviewTextWatcher);
        editTextEmail.addTextChangedListener(reviewTextWatcher);

        review = (Button) rootView.findViewById(R.id.review_btn);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=editTextEmail.getText().toString();
                String phone=editTextNumber.getText().toString();
                String comment=edComment.getText().toString();

                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Snackbar.make(rootView,R.string.Please_enter_valid_email,Snackbar.LENGTH_SHORT).show();
               editTextEmail.setError("Please enter valid Email");
                }else if (phone.length()<10){
                    Snackbar.make(rootView,R.string.Please_enter_valid_phone_no,Snackbar.LENGTH_SHORT).show();
                    editTextNumber.setError("Please enter valid Phone Number");
                }else if (comment.length()<1){
                    Snackbar.make(rootView,R.string.Please_enter_comment,Snackbar.LENGTH_SHORT).show();
                    edComment.setError("Please enter comment");
                }else{
                   addDatatoFirebase();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            editTextFullName.setText("");
                            editTextEmail.setText("");
                            editTextNumber.setText("");
                            edComment.setText("");
                            ratingBar.setRating(0F);

                        }
                    },3000);
                }
            }
        });

        return rootView;
    }

    TextWatcher reviewTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = editTextFullName.getText().toString().trim();
            String numberInput = editTextNumber.getText().toString().trim();
            String emailInput = editTextEmail.getText().toString().trim();

            review.setEnabled(!nameInput.isEmpty() && !numberInput.isEmpty() && !emailInput.isEmpty());
        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void addDatatoFirebase() {
        String email=editTextEmail.getText().toString();
        String phone=editTextNumber.getText().toString();
        String comment=edComment.getText().toString();
        String FullName=editTextFullName.getText().toString();
        String rating= String.valueOf(ratingBar.getRating());

        Map<String , String> m=new HashMap<>();
        m.put("email",email);
        m.put("phone",phone);
        m.put("FullName",FullName);
        m.put("Comment",comment);
        databaseReference = firebaseDatabase.getReference("Customer_Reviews");
        m.put("rating",rating);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {

                        databaseReference.setValue(m);
                        // after adding this data we are showing toast message.
                        Snackbar.make(viewPager, R.string.Customer_address_added, Snackbar.LENGTH_SHORT).show();

                    } catch (Exception e) {
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(getActivity(), getString(R.string.fail_to_add_data) + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void databaseSize() {
        databaseReference = firebaseDatabase.getReference("Customer_Reviews");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                size = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
