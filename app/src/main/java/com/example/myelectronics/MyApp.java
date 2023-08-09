package com.example.myelectronics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myelectronics.database.ORMDatabase;

public class MyApp extends Application {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `baskets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `basketId` INTEGER NOT NULL, `productId` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, `total_price` REAL NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `orders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `basketId` INTEGER NOT NULL, `total_price` REAL NOT NULL)");
        }
    };
    private ORMDatabase ormDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        ormDatabase = Room.databaseBuilder(this, ORMDatabase.class, "ElectronicsDB").addMigrations(MIGRATION_1_2).build();
    }

    public ORMDatabase getORMDatabase() {
        return ormDatabase;
    }
}

