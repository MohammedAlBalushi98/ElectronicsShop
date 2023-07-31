package com.example.myelectronics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.RecyclerViews.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //    ProductDao db = (ProductDao) new ProductDBManagement(this).getProductDbInstance();
    List<OrmProduct> initData = new ArrayList<>();
    List<String> Titles = new ArrayList<>();
    private ORMDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setup();
    }

    void setup() {
        db = ((MyApp) getApplication()).getORMDatabase();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                initData = db.ProductDao().GetAllProducts();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        RecyclerView productRecyclerView = (RecyclerView) findViewById(R.id.HomeParentRecyclerView);
                        ProductAdapter productAdapter = new ProductAdapter(HomeActivity.this, Titles, initData);
                        productRecyclerView.setAdapter(productAdapter);
                        productRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    }
                });

            }
        });
thread.start();
    }

    void initData() {


        for (OrmProduct product : initData) {
            if (!Titles.contains(product.getCategory())) {
                Titles.add(product.getCategory());
            }
        }


//        return db.GetAllProducts();
    }

}