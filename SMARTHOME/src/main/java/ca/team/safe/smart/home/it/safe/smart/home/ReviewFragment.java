//Veera Gudla N01218714
//Jacob Stephens N01100888
//Patrick Loboda N01309086
package ca.team.safe.smart.home.it.safe.smart.home;

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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ReviewFragment extends Fragment{

    Button review;
    EditText editTextName;
    EditText editTextNumber;
    EditText editTextEmail;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
        // Inflate the layout for this fragment

        loader =  rootView.findViewById(R.id.loader);
        editTextFullName = (EditText) rootView.findViewById(R.id.editTextTextPersonName);
        editTextNumber = (EditText) rootView.findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) rootView.findViewById(R.id.editTextTextEmailAddress);
        EditText edComment = (EditText) rootView.findViewById(R.id.textView5);
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(requireContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Closing Activity")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               startActivity(new Intent(requireActivity(),LoginSep.class));
                               requireActivity().finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        editTextFullName.addTextChangedListener(reviwTextWatcher);
        editTextNumber.addTextChangedListener(reviwTextWatcher);
        editTextEmail.addTextChangedListener(reviwTextWatcher);

        review = (Button) rootView.findViewById(R.id.review_btn);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editTextEmail.getText().toString();
                String phone=editTextNumber.getText().toString();
                String comment=edComment.getText().toString();
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Snackbar.make(rootView,"Please enter valid Email",Snackbar.LENGTH_SHORT).show();
               editTextEmail.setError("Please enter valid Email");
                }else if (phone.length()<10){
                    Snackbar.make(rootView,"Please enter valid Phone Number",Snackbar.LENGTH_SHORT).show();
                    editTextNumber.setError("Please enter valid Phone Number");
                }else if (comment.length()<1){
                    Snackbar.make(rootView,"Please enter comment",Snackbar.LENGTH_SHORT).show();
                    edComment.setError("Please enter comment");
                }else{
                    loader.setVisibility(View.VISIBLE);
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loader.setVisibility(View.GONE);
                            Snackbar.make(rootView, R.string.thank_you_message_submit, Snackbar.LENGTH_SHORT).show();
                        }
                    },3000);
                }
            }
        });

        return rootView;
    }

    TextWatcher reviwTextWatcher = new TextWatcher() {
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
}
