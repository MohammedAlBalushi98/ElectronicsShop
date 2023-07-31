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
    private ORMDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setup();
    }

//    void temp(){
//        db = ((MyApp) getApplication()).getORMDatabase();
//                //cables
//                db.ProductDao().AddProduct(new OrmProduct("Anker 3 Feet USB-C to USB-C","USB-C to USB-C cable with only power supply: This kind of cable is used to connect the adapter to device, or computer to device, such as lamps, toys, measuring tools, etc. which without data requirements",5.99,"cable",R.drawable.cable1));
//                db.ProductDao().AddProduct(new OrmProduct("Anker Cable PowerLine plus II USB-C to Lightning","With a USB-C to Lightning cable, you can charge and sync your AirPods, AirPods Pro, iPhone, iPad, or iPod touch, charge a Siri Remote, and more.",9.99,"cables",R.drawable.cable2));
//                db.ProductDao().AddProduct(new OrmProduct("Belkin USB-A to USB-C Charge Cable","Charge Cable lets you charge your USB-C device as well as sync your photos, music and data to your existing laptop.",6.99,"cable",R.drawable.cable3));
//
//                //power banks
//                db.ProductDao().AddProduct(new OrmProduct("Anker Power Bank , Power Core Select 10000mAh, Black.","Anker PowerCore 10000 Portable Charger, One of The Smallest and Lightest 10000mAh Power Bank, Ultra-Compact Battery Pack, High-Speed Charging Technology Phone Charger for iPhone, Samsung and More.",16.99,"power banks",R.drawable.powerbank1));
//                db.ProductDao().AddProduct(new OrmProduct("RavPower 10000mAh Magnetic Wireless Powerbank, 15 Watts, White","Ravpower Magnetic Wireless Power Bank - the ultimate on-the-go charging companion. With a powerful 10000mAh capacity, say goodbye to tangled cables and enjoy a clutter-free charging experience. Its strong magnetic absorption ensures a secure connection, while compatibility with the official magnetic phone case makes charging effortless. Up to 15W fast wireless charging, stay connected and powered up wherever you go.",14.99,"power banks",R.drawable.powerbank2));
//                db.ProductDao().AddProduct(new OrmProduct("Belkin 10000mAh Power Bank, 15W, Dual USB-A, Fast Charging, Rose Gold","Get 40* extra hours of battery life for your iPhone 14/13/12 or charge up to 3 devices at once with up to 15W combined from 2 USB-A ports and 1 USB-C port. A USB-A to USB-C cable is included so you can start charging right out of the box.",20.99,"power banks",R.drawable.powerbank3));
//
//                //laptop
//                db.ProductDao().AddProduct(new OrmProduct("Lenovo Ideapad Flex 5 Convertible, Core i5 , 512GB SSD, 14 Inch, Touch, Storm Grey","Flex Series | Where performance and connectivity meet productivity and entertainment. Portable multimode laptops with next-gen performance, all-day battery life, and built-in versatility. For life on the go.",149.99,"laptops",R.drawable.laptop1));
//                db.ProductDao().AddProduct(new OrmProduct("HP Clamshell , Core i3, 4GB, 256GB SSD, 15.6 Inch, Natural Silver","Take it anywhere. See more. With its thin and light design, 6.5 mm micro-edge bezel display, and 82% screen to body ratio(40) – take this PC anywhere and see and do more. Reliable performance for every day: Powerful enough for your busiest days, this PC features an Intel® processor and a solid state drive for speedy boot-up and snappier overall experience. Powered up and productive. All day long. With a long battery life and fast-charge technology, this laptop lets you work, watch, and stay connected all day. Integrated precision touchpad with multi-touch support speeds up both navigation and productivity.",249.99,"laptops",R.drawable.laptop2));
//                db.ProductDao().AddProduct(new OrmProduct("APPLE MacBook Air, M1, 8GB, 256GB, 13 inch, S.Grey","Apple’s thinnest and lightest notebook gets supercharged with the Apple M1 chip. Tackle your projects with the blazing-fast 8-core CPU. Take graphics-intensive apps and games to the next level with the 7-core GPU. And accelerate machine learning tasks with the 16-core Neural Engine. All with a silent, fanless design and the longest battery life ever — up to 18 hours.¹ MacBook Air. Still perfectly portable. Just a lot more powerful.",344.99,"laptops",R.drawable.laptop3));
//    }

    void setup() {
        fName = (EditText) findViewById(R.id.RegistrationFnameEditText);
        lName = (EditText) findViewById(R.id.RegistrationLnameEditText);
        email = (EditText) findViewById(R.id.RegistrationEmailEditText);
        password = (EditText) findViewById(R.id.RegistrationPasswordEditText);
        rePassword = (EditText) findViewById(R.id.RegistrationRePasswordEditText);
        registerBtn = (Button) findViewById(R.id.registrationButton);
        cancelBtn = (Button) findViewById(R.id.registrationCancelButton);
       db = ((MyApp) getApplication()).getORMDatabase();
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
                                db.UserDao().AddUser(userDao);
//                                temp();
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