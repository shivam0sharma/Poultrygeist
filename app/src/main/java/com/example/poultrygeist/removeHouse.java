package com.example.poultrygeist;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.poultrygeist.DB.PoultryHouseViewModel;

import java.util.List;

public class removeHouse extends AppCompatActivity {
    private Spinner houseIds;
    private PoultryHouseViewModel houseViewModel;
    ArrayAdapter<Integer> adapter;
    int houseSelected = -1;
    List<Integer> houseNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_house);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * 0.3));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.popup));

        houseViewModel = ViewModelProviders.of(this).get(PoultryHouseViewModel.class);
        houseIds = (Spinner) findViewById(R.id.spinner_houseId);
        houseViewModel.populateSpinner(this);
    }

    public void populateSpinner(List<Integer> list) {
        houseNumbers = list;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                houseNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseIds.setAdapter(adapter);
        houseIds.setOnItemSelectedListener(new customItemSelected());
    }


    void cancel(View view) {
        finish();
    }

    void remove(View view) {
        if (houseSelected >= 0) {
            houseViewModel.removeHouse(houseSelected);
            Toast.makeText(this, "House Removed: " + houseSelected, Toast.LENGTH_SHORT).show();
            houseNumbers.remove((Object) houseSelected);
            houseSelected = -1;
            finish();
        }
    }

    private class customItemSelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            houseSelected = houseNumbers.get(position);
            Toast.makeText(getApplicationContext(), "House Selected: " + houseSelected, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
