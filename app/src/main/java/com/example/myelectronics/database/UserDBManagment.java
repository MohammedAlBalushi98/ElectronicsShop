package com.example.myelectronics.database;

import android.content.Context;

import androidx.room.Room;

public class UserDBManagment {
    Context context;


    UserDBManagment(Context context) {
        this.context = context;
    }

    UserDao getUserDbInstance() {
        ORMDatabase db = Room
                .databaseBuilder(context, ORMDatabase.class, "ElectronicsDB")
                .allowMainThreadQueries()
                .build();

        UserDao dao = db.UserDao();
        return dao;

    }
}
