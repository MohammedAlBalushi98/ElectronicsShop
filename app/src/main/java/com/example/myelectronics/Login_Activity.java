package com.example.myelectronics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginBtn;
    TextView registrationBtn;
    CheckBox remeberMeCheckBox;

    private ORMDatabase db;

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
        remeberMeCheckBox = (CheckBox) findViewById(R.id.LoginRememberMeCheckBox);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = ((MyApp) getApplication()).getORMDatabase();
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OrmUser user = db.UserDao().GetUserById(email.getText().toString().toLowerCase());
                        if (user != null) {
                            if (user.getPassword().equals(password.getText().toString())) {
                                if (remeberMeCheckBox.isChecked()) {
                                    editor.putBoolean("LoggedIn", true);
                                    editor.apply();
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent home = new Intent(Login_Activity.this, HomeActivity.class);
                                        startActivity(home);
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