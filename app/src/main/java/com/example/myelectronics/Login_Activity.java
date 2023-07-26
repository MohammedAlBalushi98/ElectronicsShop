package com.example.myelectronics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginBtn;
    TextView registrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    private void setup() {
        email = (EditText) findViewById(R.id.LoginEmailEditText);
        password = (EditText) findViewById(R.id.LoginPasswordEditText);
        loginBtn = (Button) findViewById(R.id.LoginButton);
        registrationBtn = (TextView) findViewById(R.id.LoginRegistrationTextButton);
        UserDao db = new UserDBManagment(this).getUserDbInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OrmUser user = db.GetUserById(email.getText().toString().toLowerCase());
                        if (user != null) {
                            if (user.getPassword().equals(password.getText().toString())) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
//                                        Intent Home = new Intent(Login_Activity.this, Registration_Activity.class);
////                         startActivity(Home);
                                        Toast.makeText(Login_Activity.this, "Login Succeed", Toast.LENGTH_SHORT).show();
                                        Log.d("Login Process", "Login Successfully");
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Login_Activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                        Log.d("Login Process", "Login Failed");
                                    }
                                });

                            }
                        }
                    }
                });
                thread.start();


            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(Login_Activity.this, Registration_Activity.class);
                startActivity(registrationIntent);
            }
        });

    }
}