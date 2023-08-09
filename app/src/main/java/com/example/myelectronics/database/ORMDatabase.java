package com.example.myelectronics.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {OrmUser.class, OrmProduct.class, OrmBasket.class, OrmOrder.class}, version = 2, exportSchema = false)
public abstract class ORMDatabase extends RoomDatabase {
    private static ORMDatabase instance;

    public static synchronized ORMDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ORMDatabase.class, "ElectronicsDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ProductDao ProductDao();

    public abstract UserDao UserDao();

    public abstract BasketDao BasketDao();

    public abstract OrderDao OrderDao();


}
