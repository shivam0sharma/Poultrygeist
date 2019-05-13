package com.example.poultrygeist.DB;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;
import com.example.poultrygeist.DB.ModelAndViews.PoultryHouseView;

import java.util.List;

public class PoultryHouseViewModel extends AndroidViewModel {

    private final LiveData<List<PoultryHouseView>> poultryHouseList;

    private AppDatabase appDatabase;

    public PoultryHouseViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDataBase(this.getApplication());
        poultryHouseList = appDatabase.HouseInformation().getALLHouses();

    }

    public LiveData<List<PoultryHouseView>> getPoultryHouseList() {
        return poultryHouseList;
    }

    /**
     * Removes house
     * @param house the house object you want to delete
     */
    public void removeHouse(PoultryHouse house) {
        new deleteAsyncTask(appDatabase).execute(house);
    }
    private static class deleteAsyncTask extends AsyncTask<PoultryHouse, Void, Void> {
        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final PoultryHouse... params) {
            db.HouseInformation().deleteHouse(params[0]);
            return null;
        }
    }

    public void AddHouse(PoultryHouse house) {
        new addAsyncTask(appDatabase).execute(house);
    }
    private static class addAsyncTask extends AsyncTask<PoultryHouse, Void, Void> {
        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final PoultryHouse... params) {
            db.HouseInformation().insertHouse(params[0]);
            return null;
        }


    }
}
