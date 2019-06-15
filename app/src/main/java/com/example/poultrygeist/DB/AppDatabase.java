package com.example.poultrygeist.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.poultrygeist.DB.ModelAndViews.Chicken;
import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;

@Database(entities = {PoultryHouse.class, Chicken.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract PoultryHouseDAO HouseInformation();
    public abstract ChickenDAO ChickenInformation();

    public static synchronized AppDatabase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "poultry_db").fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }


    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PoultryHouseDAO houses;
        private ChickenDAO chickens;

        private PopulateDbAsyncTask(AppDatabase db) {
            houses = db.HouseInformation();
            chickens = db.ChickenInformation();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            houses.deleteAllHouses();
            chickens.deleteAllChickens();
            houses.insertHouse(new PoultryHouse(1, 30, 50));
            houses.insertHouse(new PoultryHouse(2, 33, 54));
            houses.insertHouse(new PoultryHouse(3, 30, 50));
            houses.insertHouse(new PoultryHouse(4, 30, 50));

            chickens.insertChicken(new Chicken(23,34,1));
            chickens.insertChicken(new Chicken(43,35,1));
            chickens.insertChicken(new Chicken(53,35,2));
            chickens.insertChicken(new Chicken(123,35,3));
            chickens.insertChicken(new Chicken(54,2,3));
            chickens.insertChicken(new Chicken(23,178,4));
            chickens.insertChicken(new Chicken(321,35,4));
            chickens.insertChicken(new Chicken(67,135,4));




            return null;
        }
    }

}
