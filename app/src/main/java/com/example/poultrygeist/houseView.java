package com.example.poultrygeist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.example.poultrygeist.DB.AppDatabase;
import com.example.poultrygeist.DB.ChickenViewModel;
import com.example.poultrygeist.DB.ModelAndViews.Chicken;
import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;
import com.example.poultrygeist.DB.PoultryHouseViewModel;
import java.util.List;

public class houseView extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    private RelativeLayout parentLayout ;
    private static final String TAG = "HouseView";
    private View pointSelected = null;
    private int buttonSize = 50;
    private ChickenViewModel viewModel = null;
    private PoultryHouseViewModel houseModel;
    List<Chicken> listOfChickens = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_view);
        Log.d(TAG, "Inside HouseView");
        viewModel = ViewModelProviders.of(this).get(ChickenViewModel.class);
        houseModel = ViewModelProviders.of(this).get(PoultryHouseViewModel.class);
        Intent intent = getIntent();
        viewModel.getChickensInAHouse(intent.getIntExtra("HouseNumber", 0), this);

        parentLayout = (RelativeLayout) findViewById(R.id.Map);
//        for(int id = 0; id < listOfChickens.size(); id++) {
//            Chicken chick = listOfChickens.get(id);
//            createButton(chick.getX(), chick.getY(), id);
//        }
    }

    public void createLocationsOnScreen(List<Chicken> chickens){
        listOfChickens = chickens;
        for(int id = 0; id < listOfChickens.size(); id++) {
            Chicken chick = listOfChickens.get(id);
            createButton(chick.getX(), chick.getY(), id);
        }
    }
    private void createButton(int x, int y, int id) {
        Button b = new Button(this);
        b.setWidth(buttonSize);
        b.setHeight(buttonSize);
        b.setBackgroundColor(Color.RED);

        b.setId(id);
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

        switch (item.getItemId()) {
            case R.id.Remove:
                Log.d(TAG, "onMenuItemClick: "+ pointSelected.getId());
                viewModel.removeChicken(listOfChickens.get(pointSelected.getId()));
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
