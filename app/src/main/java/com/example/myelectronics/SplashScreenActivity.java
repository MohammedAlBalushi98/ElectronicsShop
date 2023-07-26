package com.example.myelectronics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (checkUserLoggedIn()) {
            startUp();
        }


    }

    boolean checkUserLoggedIn() {
        //TODO: Check if user logged in (Shared preferences)
        return true;
    }

    private void startUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent loginIntent = new Intent(SplashScreenActivity.this, Login_Activity.class);
                startActivity(loginIntent);
            }
        }, 2000);
    }
}