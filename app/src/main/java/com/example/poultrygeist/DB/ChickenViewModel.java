package com.example.poultrygeist.DB;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.poultrygeist.DB.ModelAndViews.Chicken;
import com.example.poultrygeist.houseView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ChickenViewModel extends AndroidViewModel {
    private final LiveData<List<Chicken>> chickenList;
    private static List<Chicken> chickensInAHouse = null;

    private AppDatabase appDatabase;
    public ChickenViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());

        chickenList = appDatabase.ChickenInformation().getALLChickns();
        chickensInAHouse = null;
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
    private static int count = -1;
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
    public void getChickensInAHouse(int HouseID, houseView activity) {
        Log.d(TAG, "getChickensInAHouse: Before task call");
        new getChickensAsyncTask(appDatabase, activity).execute(HouseID);
    }

    private static class getChickensAsyncTask extends AsyncTask<Integer, Void, List<Chicken>> {
        private AppDatabase db;
        private houseView activity;


        getChickensAsyncTask(AppDatabase appDatabase, houseView activity) {
            db = appDatabase;
            this.activity = activity;

        }

        @Override
        protected List<Chicken> doInBackground(final Integer... params) {
            Log.d(TAG, "doInBackground: start ");
            return db.ChickenInformation().getAllChicensFromHouse(params[0]);
        }

        @Override
        protected void onPostExecute(List<Chicken> chickens) {
            activity.createLocationsOnScreen(chickens);
        }
    }
}
