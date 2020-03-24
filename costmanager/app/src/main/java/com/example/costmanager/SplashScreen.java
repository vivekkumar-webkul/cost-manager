package com.example.costmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashScreen extends AppCompatActivity {

    protected TextView title;

    protected int splashTimeout = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        title = (TextView)findViewById(R.id.title);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        title.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sh = getSharedPreferences("CostManagerSharedPrefereces", MODE_APPEND);
                String user = sh.getString("username", "");
                if(user.isEmpty()) {
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent mainActivityIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                }
            }
        }, splashTimeout);
    }
}
