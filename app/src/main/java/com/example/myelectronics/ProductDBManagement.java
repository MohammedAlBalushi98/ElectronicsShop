package com.example.myelectronics;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class ProductDBManagement {
    static final Migration MIGRATION = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'image' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'productId' INTEGER PRIMARY KEY NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'category' TEXT DEFAULT NULL");
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'productName' TEXT DEFAULT NULL");
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'productDescription' TEXT DEFAULT NULL");
            database.execSQL("ALTER TABLE 'products' ADD COLUMN 'productPrice' REAL NOT NULL DEFAULT NULL");
        }
    };
    Context context;


    public ProductDBManagement(Context context) {
        this.context = context;
    }

    ProductDao getProductDbInstance() {
        ORMDatabase db = Room
                .databaseBuilder(context, ORMDatabase.class, "orm-database")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION)
                .build();

        ProductDao dao = db.ProductDao();
        return dao;

    }


}
