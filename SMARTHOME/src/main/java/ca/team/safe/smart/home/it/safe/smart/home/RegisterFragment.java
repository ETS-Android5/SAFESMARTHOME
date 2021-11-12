package ca.team.safe.smart.home.it.safe.smart.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    EditText mFullName, mEmail, mPassword;
    Button mregisterBtn;
    TextView loginBtn;
    FirebaseAuth fAuth;
    private View mView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_register, container, false);

        mFullName = getActivity().findViewById(R.id.register_name);
        mEmail = getActivity().findViewById(R.id.register_username);
        mPassword = getActivity().findViewById(R.id.register_password);
        Button mregisterBtn = (Button) getActivity().findViewById(R.id.registerbutton);
        ProgressBar progressBar = (ProgressBar) getActivity().findViewById(R.id.progressBar_register);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            getActivity().finish();
        }
        return mView;


    }

        @Override
        public void onClick (View v){

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.registerbutton:
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError(getString(R.string.please_enter_email));
                    return;
                }
                if (TextUtils.isEmpty(password) || password.length() < 8) {
                    mPassword.setError(getString(R.string.password_error));
                    return;
                }
                // progressBar.setVisibility(getView().VISIBLE);


                //register user
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), R.string.user_created, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.error) + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }
    }

