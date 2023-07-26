package com.example.myelectronics;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class UserDBManagment {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
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
                .addMigrations(MIGRATION_1_2)
                .build();

        UserDao dao = db.UserDao();
        return dao;

    }
}