package com.example.poultrygeist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashBoard extends AppCompatActivity {

    List<House> houses;
    RecyclerView myrv;
    RecyclerViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        houses = new ArrayList<>();
        houses.add(new House(0));
        houses.add(new House(5));
        houses.add(new House(3));
        houses.add(new House(7));
        houses.add(new House(1));
        houses.add(new House(9));

        myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(this, houses);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_id:
                addItem();
                return true;
            case R.id.menu_remove_id:
                removeItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addItem() {
        // insert house
        houses.add(new House(1));

        // Update the myAdapter
        myAdapter.notifyDataSetChanged();

        // Confirm the insertion
        Toast.makeText(this, "House Added", Toast.LENGTH_SHORT).show();
    }

    public void removeItem() {
        if (!houses.isEmpty()) {
            // remove house
            houses.remove(houses.size() - 1);

            // Update the myAdapter
            myAdapter.notifyDataSetChanged();

            // Confirm the deletion
            Toast.makeText(this, "House Removed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nothing to remove", Toast.LENGTH_SHORT).show();
        }
    }
}
