package com.example.myelectronics;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {OrmUser.class, OrmProduct.class}, version = 3)
public abstract class ORMDatabase extends RoomDatabase {
    public abstract ProductDao ProductDao();

    public abstract UserDao UserDao();

}
