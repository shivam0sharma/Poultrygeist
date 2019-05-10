package com.example.poultrygeist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ChickenViewModel extends AndroidViewModel {
    private final LiveData<List<Chicken>> chickenList;

    private AppDatabase appDatabase;
    public ChickenViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());

        chickenList = appDatabase.ChickenInformation().getALLChickns();
    }

    public LiveData<List<Chicken>> getChickenList() {
        return chickenList;
    }

    public void removeChicken(Chicken chick) {
        new deleteAsyncTask(appDatabase).execute(chick);
    }
    private static class deleteAsyncTask extends AsyncTask<Chicken, Void, Void> {
        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Chicken... params) {
            db.ChickenInformation().deleteChicken(params[0]);
            return null;
        }
    }

    public void AddChicken(Chicken chick) {
        Log.d(TAG, "OnMenu: "+ chick.getX() +" "+chick.getY());
        new addAsyncTask(appDatabase).execute(chick);

    }
    private static class addAsyncTask extends AsyncTask<Chicken, Void, Void> {
        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Chicken... params) {
            db.ChickenInformation().insertChicken(params[0]);

            return null;
        }
    }
    static int count = -1;
    public int chickenCount(int HouseID) {
        //count = 0;
        new getCountAsyncTask(appDatabase).execute(HouseID);
        return count;
    }

    private static class getCountAsyncTask extends AsyncTask<Integer, Void, Void> {
        private AppDatabase db;

        getCountAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            count = db.HouseInformation().getNumberOfChicken(params[0]);
            return null;
        }

    }
}
