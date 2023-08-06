package com.example.myelectronics.database;

import android.content.Context;

import androidx.room.Room;

public class ProductDBManagement {
    Context context;


    public ProductDBManagement(Context context) {
        this.context = context;
    }

    ProductDao getProductDbInstance() {
        ORMDatabase db = Room
                .databaseBuilder(context, ORMDatabase.class, "ElectronicsDB")
                .allowMainThreadQueries()
                .build();

        ProductDao dao = db.ProductDao();
        return dao;

    }


}
