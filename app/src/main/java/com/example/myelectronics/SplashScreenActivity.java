package com.example.myelectronics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startUp();
    }

    boolean checkUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("LoggedIn", false);
    }

//    void AddProducts() {
//        ProductDao db = new ProductDBManagement(this).getProductDbInstance();
//        db.AddProduct(new OrmProduct(null,));
//    }

    private void startUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                boolean res = checkUserLoggedIn();
                if (!res) {
                    Intent loginIntent = new Intent(SplashScreenActivity.this, Login_Activity.class);
                    startActivity(loginIntent);
                } else {
                    Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }

            }
        }, 2000);
    }
}