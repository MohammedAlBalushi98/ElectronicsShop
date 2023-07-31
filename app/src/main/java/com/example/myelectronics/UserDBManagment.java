package com.example.myelectronics;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class UserDBManagment {

    static final Migration MIGRATION = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };
    Context context;


    UserDBManagment(Context context) {
        this.context = context;
    }

    UserDao getUserDbInstance() {
        ORMDatabase db = Room
                .databaseBuilder(context, ORMDatabase.class, "orm-database")
                .addMigrations(MIGRATION)
                .build();

        UserDao dao = db.UserDao();
        return dao;

    }
}
