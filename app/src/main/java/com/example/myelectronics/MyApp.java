package com.example.myelectronics;

import android.app.Application;
import androidx.room.Room;

public class MyApp extends Application {
    private ORMDatabase ormDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        ormDatabase = Room.databaseBuilder(this, ORMDatabase.class, "ElectronicsDB").build();
    }

    public ORMDatabase getORMDatabase() {
        return ormDatabase;
    }
}

