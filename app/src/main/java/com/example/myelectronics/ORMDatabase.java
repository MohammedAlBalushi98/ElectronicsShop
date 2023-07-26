package com.example.myelectronics;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {OrmUser.class}, version = 2)
public abstract class ORMDatabase extends RoomDatabase {
    public abstract UserDao UserDao();

}
