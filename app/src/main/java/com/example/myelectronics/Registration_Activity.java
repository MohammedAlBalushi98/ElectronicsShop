package com.example.myelectronics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration_Activity extends AppCompatActivity {
    EditText fName, lName, email, password, rePassword;
    Button cancelBtn, registerBtn;
    String fNameTxt, lNameTxt, emailTxt, passwordTxt, rePasswordTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setup();
    }

    void setup() {
        fName = (EditText) findViewById(R.id.RegistrationFnameEditText);
        lName = (EditText) findViewById(R.id.RegistrationLnameEditText);
        email = (EditText) findViewById(R.id.RegistrationEmailEditText);
        password = (EditText) findViewById(R.id.RegistrationPasswordEditText);
        rePassword = (EditText) findViewById(R.id.RegistrationRePasswordEditText);
        registerBtn = (Button) findViewById(R.id.registrationButton);
        cancelBtn = (Button) findViewById(R.id.registrationCancelButton);
        UserDao db = new UserDBManagment(this).getUserDbInstance();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Registration_Activity.this, Login_Activity.class));
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fNameTxt = fName.getText().toString();
                lNameTxt = lName.getText().toString();
                emailTxt = email.getText().toString().toLowerCase();
                passwordTxt = password.getText().toString();
                rePasswordTxt = rePassword.getText().toString();
                if (email != null && fName != null && lName != null && password != null && rePassword != null) {
                    if (passwordTxt.equals(rePasswordTxt)) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                OrmUser userDao = new OrmUser(emailTxt, fNameTxt, lNameTxt, 500.0, passwordTxt);
                                db.AddUser(userDao);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registration_Activity.this, "Registration Succeed", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(Registration_Activity.this, Login_Activity.class));
                                    }
                                });
                            }
                        });
                        thread.start();

                    }
                }
            }
        });
    }
}