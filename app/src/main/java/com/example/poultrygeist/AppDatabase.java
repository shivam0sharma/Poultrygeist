package com.example.poultrygeist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {PoultryHouse.class, Chicken.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "poultry_db").build();

        }
        return INSTANCE;
    }

    public abstract PoultryHouseDAO HouseInformation();
    public abstract ChickenDAO ChickenInformation();
}
