package com.example.poultrygeist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<House> mData;

    public RecyclerViewAdapter(Context mContext, List<House> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_house, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.houseCardViewInfo.setText("House # " + mData.get(i).getHouseNumber() + "\n" +
                                                mData.get(i).getNumberOfDeadChickens() + " Dead");

        // Setup click listener
        myViewHolder.cardViewHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, houseView.class);

                // passing data to the house view activity
                intent.putExtra("HouseNumber", mData.get(i).getHouseNumber());
                intent.putExtra("NumberOfDeadChickens", mData.get(i).getNumberOfDeadChickens());
                // starting house activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView houseCardViewInfo;
        CardView cardViewHouse;

        public MyViewHolder(View itemView) {
            super(itemView);
            houseCardViewInfo = (TextView) itemView.findViewById(R.id.cardview_textview_id);
            cardViewHouse = (CardView) itemView.findViewById(R.id.cardview_house_id);
        }
    }
}
