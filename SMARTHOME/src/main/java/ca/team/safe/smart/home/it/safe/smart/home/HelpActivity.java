package ca.team.safe.smart.home.it.safe.smart.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class HelpActivity extends AppCompatActivity {
Button btnUp,btnReportProblem;
RadioGroup radioButtonGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        btnUp=findViewById(R.id.btnUp);
        btnReportProblem=findViewById(R.id.btnReportProblem);
        radioButtonGroup=findViewById(R.id.radioGroup);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
//                    View radioButton = radioButtonGroup.findViewById(radioButtonID);
//                    int idx = radioButtonGroup.indexOfChild(radioButton);
//                    RadioButton r = (RadioButton) radioButtonGroup.getChildAt(idx);
//                    String selectedtext = r.getText().toString();
                    showDialog("", "Internet connection : Please restart your router and reconnect the cable into the module. Wait 30 sec before trying it. \n\n" +
                            "Hardware: Please unplug the power supply and wait 1 min before reconnecting it again.\n\n" +
                            "False Data: Please contact customer support. \n\n" +
                            "setup hardware: Please connect the power supply first, second connect to the internet, third setup your account.");
                }catch (Exception e){}
            }
        });
        btnReportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("","Thank you for Reporting.");
            }
        });
    }

    void showDialog(String title,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(HelpActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        final Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.gravity = Gravity.CENTER;
        positiveButton.setLayoutParams(positiveButtonLL);
    }
}