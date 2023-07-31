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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setup();
    }

    void setup() {
        initData();
        RecyclerView productRecyclerView = (RecyclerView) findViewById(R.id.HomeParentRecyclerView);
        ProductAdapter productAdapter = new ProductAdapter(this, Titles, initData);
        productRecyclerView.setAdapter(productAdapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void initData() {

        initData.add(new OrmProduct(1, "cable 1", "used to charge electornic devices", 5.0, "cable", R.drawable.logo));
        initData.add(new OrmProduct(1, "wires 1", "used to charge electornic devices", 6.0, "wires", R.drawable.logo));
        initData.add(new OrmProduct(1, "cable 2", "used to charge electornic devices", 7.0, "cable", R.drawable.logo));
        initData.add(new OrmProduct(1, "wires 2", "used to charge electornic devices", 8.0, "wires", R.drawable.logo));
        initData.add(new OrmProduct(1, "cable 3", "used to charge electornic devices", 9.0, "cable", R.drawable.logo));
        initData.add(new OrmProduct(1, "wires 3", "used to charge electornic devices", 10.0, "wires", R.drawable.logo));
        initData.add(new OrmProduct(1, "cable 4", "used to charge electornic devices", 1.0, "cable", R.drawable.logo));
        initData.add(new OrmProduct(1, "laptop 1", "used to charge electornic devices", 2.0, "laptop", R.drawable.logo));
        initData.add(new OrmProduct(1, "laptop 2", "used to charge electornic devices", 3.0, "laptop", R.drawable.logo));
        initData.add(new OrmProduct(1, "laptop 3", "used to charge electornic devices", 4.0, "laptop", R.drawable.logo));

        for (OrmProduct product : initData) {
            if (!Titles.contains(product.getCategory())) {
                Titles.add(product.getCategory());
            }
        }


//        return db.GetAllProducts();
    }

}