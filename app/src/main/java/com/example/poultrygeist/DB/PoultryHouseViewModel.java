package com.example.poultrygeist.DB;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;
import com.example.poultrygeist.DB.ModelAndViews.PoultryHouseView;
import com.example.poultrygeist.add_House;
import com.example.poultrygeist.removeHouse;

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
     *
     * @param hid house number
     */
    public void removeHouse(int hid) {
        new deleteAsyncTask(appDatabase).execute(hid);
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            db.HouseInformation().removeHouse(params[0]);
            return null;
        }
    }

    public void AddHouse(PoultryHouse house, Activity activity) {
        new addAsyncTask(appDatabase, activity).execute(house);
    }

    private static class addAsyncTask extends AsyncTask<PoultryHouse, Void, Boolean> {
        private AppDatabase db;
        private Activity activity;

        addAsyncTask(AppDatabase appDatabase, Activity activity) {
            db = appDatabase;
            this.activity = activity;
        }

        @Override
        protected Boolean doInBackground(final PoultryHouse... params) {
            if (!db.HouseInformation().getHouseIds().contains(params[0].getHouseId())) {
                db.HouseInformation().insertHouse(params[0]);
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if (activity != null) {
                if (!b) {
                    ((add_House) activity).result("House already exists. Try another number.", false);
                } else {
                    ((add_House) activity).result("House Successfully Added!", true);
                }
            }
        }
    }


    public void populateSpinner(Activity activity) {
        new populateAsync(appDatabase, activity).execute();
    }

    private static class populateAsync extends AsyncTask<Void, Void, Void> {
        private AppDatabase db;
        private Activity activity;
        private List<Integer> ids;

        populateAsync(AppDatabase appDatabase, Activity activity) {
            db = appDatabase;
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ids = db.HouseInformation().getHouseIds();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ((removeHouse) activity).populateSpinner(ids);
        }
    }
}
