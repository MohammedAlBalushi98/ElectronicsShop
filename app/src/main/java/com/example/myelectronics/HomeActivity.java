package com.example.myelectronics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myelectronics.fragments.BasketFragment;
import com.example.myelectronics.fragments.HomeFragment;
import com.example.myelectronics.fragments.OrdersFragment;
import com.example.myelectronics.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    //    ProductDao db = (ProductDao) new ProductDBManagement(this).getProductDbInstance();


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentInit();
    }

    void fragmentInit() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.HomeBottomBar) {
                        loadFragment(new HomeFragment());
                        return true;
                    } else if (itemId == R.id.BasketBottomBar) {
                        loadFragment(new BasketFragment());
                        return true;
                    } else if (itemId == R.id.OrdersBottomBar) {
                        loadFragment(new OrdersFragment());
                        return true;
                    } else if (itemId == R.id.SettingsBottomBar) {
                        loadFragment(new SettingsFragment());
                        return true;
                    }
                    return false;
                });

        // Set the initial fragment to be displayed
        bottomNavigationView.setSelectedItemId(R.id.HomeBottomBar);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}