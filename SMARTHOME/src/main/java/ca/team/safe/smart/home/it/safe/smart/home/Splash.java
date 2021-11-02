//Veera Gudla N01218714
//Jerin Joy N01276691
//Jacob Stephens N01100888
//Patrick Loboda N01309086

package ca.team.safe.smart.home.it.safe.smart.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        ValueAnimator animator = ValueAnimator.ofInt(0, progressBar.getMax());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                progressBar.setProgress((Integer)animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // start your activity here
            }
        });
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
