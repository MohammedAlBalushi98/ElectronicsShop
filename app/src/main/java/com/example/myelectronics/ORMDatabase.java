package com.example.myelectronics;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {OrmUser.class, OrmProduct.class}, version = 1, exportSchema = false)
public abstract class ORMDatabase extends RoomDatabase {
    private static ORMDatabase instance;
    public abstract ProductDao ProductDao();

    public abstract UserDao UserDao();
    public static synchronized ORMDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ORMDatabase.class, "ElectronicsDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
