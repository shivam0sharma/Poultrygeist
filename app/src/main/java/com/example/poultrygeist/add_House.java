package com.example.poultrygeist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;
import com.example.poultrygeist.DB.PoultryHouseViewModel;
import com.example.poultrygeist.R;

public class add_House extends AppCompatActivity {

    private EditText houseNumber, houseLength, houseWidth;
    private PoultryHouseViewModel houseViewModel;
    Button addButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__house);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * 0.3));
        getWindow().setBackgroundDrawable(getDrawable(R.drawable.popup));
        houseNumber = findViewById(R.id.houseNumber);
        houseLength = findViewById(R.id.houseLength);
        houseWidth = findViewById(R.id.houseWidth);

        houseViewModel = ViewModelProviders.of(this).get(PoultryHouseViewModel.class);
        addButton = findViewById(R.id.add);
        cancelButton = findViewById(R.id.Cancel);

    }

    void cancel(View view) {
        finish();
    }

    void add(View view) {
        if (verify()) {
            enableButtons(false);
            int hID = Integer.parseInt(houseNumber.getText().toString());
            int hLength = Integer.parseInt(houseLength.getText().toString());
            int hWidth = Integer.parseInt(houseWidth.getText().toString());
            PoultryHouse house = new PoultryHouse(hID, hLength, hWidth);
            houseViewModel.AddHouse(house, this);
        }
    }

    private boolean verify() {
        if (houseNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a House Number!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (houseLength.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a House Length!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (houseWidth.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a House Width!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void result(String r, boolean b) {
        Toast.makeText(this, r, Toast.LENGTH_SHORT).show();
        enableButtons(true);
        if (b) {
            finish();
        }
    }

    private void enableButtons(boolean b) {
        addButton.setEnabled(b);
        cancelButton.setEnabled(b);
    }
}
