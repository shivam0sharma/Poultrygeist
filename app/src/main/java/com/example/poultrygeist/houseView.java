package com.example.poultrygeist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class houseView extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private RelativeLayout parentLayout ;
    private static final String TAG = "MainActivity";
    private View pointSelected = null;
    private int buttonSize = 50;
    private ChickenViewModel viewModel;
    private PoultryHouseViewModel houseModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_view);

        viewModel = ViewModelProviders.of(this).get(ChickenViewModel.class);
        houseModel = ViewModelProviders.of(this).get(PoultryHouseViewModel.class);
        parentLayout = (RelativeLayout) findViewById(R.id.Map);
        createButton(10,30, 1);
        createButton(33,30, 2);
//        Button b = new Button(this);
//        b.setWidth(10);
//        b.setHeight(10);
//        b.setBackgroundColor((int)R.color.colorPrimary);
//        b.setId((int) 1);
//        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
//                50, 50);
//        rel_btn.leftMargin = 120;
//        rel_btn.topMargin = 20;
//
//
//        b.setLayoutParams(rel_btn);
//
//
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: Button clicked");
//            }
//        });
//
//        parentLayout.addView(b);
    }
    void createButton(int x, int y, int id) {
        Button b = new Button(this);
        b.setWidth(buttonSize);
        b.setHeight(buttonSize);
        b.setBackgroundColor(Color.RED);
        b.setId((int) 1);
        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                buttonSize, buttonSize);
        rel_btn.leftMargin = x;
        rel_btn.topMargin = y;
        Log.d(TAG, "createButton: " + b.getId());


        b.setLayoutParams(rel_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Button clicked");
                onPointClick(v);
            }
        });

        Log.d(TAG, "onCreate: I am here!" + b.getWidth());
        parentLayout.addView(b);
    }

    void onPointClick(View v) {
        pointSelected = v;
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(houseView.this);
        inflater.inflate(R.menu.menu_chicken, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
//        pointSelected.addExtraDataToAccessibilityNodeInfo();
//        pointSelected.performAccessibilityAction()
        switch (item.getItemId()) {
            case R.id.Remove:
                Log.d(TAG, "onMenuItemClick: "+ pointSelected.getId());
                ((ViewGroup)(pointSelected.getParent())).removeView(pointSelected);
                pointSelected = null;
                Log.d(TAG, "onMenuItemClick: Chicken Removed");
                return true;

            case R.id.FalseAlarm:
                Log.d(TAG, "onMenuItemClick: "+ pointSelected.getId());
                ((ViewGroup)(pointSelected.getParent())).removeView(pointSelected);
                pointSelected = null;
                Log.d(TAG, "onMenuItemClick: False Alarm");
                return true;

            case R.id.WrongPosition:
                Log.d(TAG, "onMenuItemClick: "+ pointSelected.getId());
                ((ViewGroup)(pointSelected.getParent())).removeView(pointSelected);
                pointSelected = null;
                Log.d(TAG, "onMenuItemClick: Chicken Removed");
                aHouse();
                 //aChicken();
                Log.d(TAG, "onMenuItemClick: Count = " + countChickens());

                return true;

            case R.id.Cancel:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void aChicken() {
        viewModel.AddChicken(new Chicken(0, 0, 1));

    }

    public void aHouse() {
        houseModel.AddHouse(new PoultryHouse(2,20,30));
    }

    public int countChickens() {
        return viewModel.chickenCount(1);
    }

}
