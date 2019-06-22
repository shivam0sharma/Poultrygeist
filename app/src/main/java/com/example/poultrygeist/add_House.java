package com.example.poultrygeist;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.poultrygeist.R;

public class add_House extends AppCompatActivity {

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

    }

    void cancel(View view) {
        finish();
    }

    void add(View view) {

        finish();
    }
}
