package com.example.myelectronics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myelectronics.fragments.BasketFragment;
import com.example.myelectronics.fragments.HomeFragment;
import com.example.myelectronics.fragments.ProfileFragment;
import com.example.myelectronics.fragments.SettingsFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    //    ProductDao db = (ProductDao) new ProductDBManagement(this).getProductDbInstance();


    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar materialToolbar;
    private DrawerLayout drawerLayout;
    private LinearLayout homeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        topBarSetup();
        fragmentInit();
    }

    void topBarSetup() {
        materialToolbar = findViewById(R.id.topAppBar);
        homeLayout = findViewById(R.id.homeLayout);
        drawerLayout = findViewById(R.id.drawer_layout);
        View customDrawerView = LayoutInflater.from(this).inflate(R.layout.custom_drawer_layout, homeLayout, false);
        NavigationView navView = findViewById(R.id.navigation_view);
        navView.addHeaderView(customDrawerView);
        navView.getMenu().findItem(R.id.drawer_menu_profile).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Toast.makeText(HomeActivity.this, "Profile clicked", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                loadFragment(new ProfileFragment());
                return false;
            }
        });
        navView.getMenu().findItem(R.id.drawer_menu_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Toast.makeText(HomeActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("UserData");
                editor.remove("LoggedIn");
                editor.commit();
                Intent loginIntent = new Intent(HomeActivity.this, Login_Activity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(loginIntent);
                return false;
            }
        });
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
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
//                    } else if (itemId == R.id.OrdersBottomBar) {
//                        loadFragment(new OrdersFragment());
//                        return true;
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