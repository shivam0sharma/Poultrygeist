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

import com.example.poultrygeist.DB.ModelAndViews.PoultryHouseView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<PoultryHouseView> mData = new ArrayList<>();

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        //this.mData = mData;
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
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        final PoultryHouseView pHouse = mData.get(i);
        myViewHolder.houseCardViewInfo.setText("House # " + pHouse.houseId + "\n" +
                                                pHouse.numberOfChickens + " Dead");

        // Setup click listener
        myViewHolder.cardViewHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, houseView.class);

                // passing data to the house view activity
                intent.putExtra("HouseNumber", pHouse.houseId);
                intent.putExtra("NumberOfDeadChickens", pHouse.numberOfChickens);
                // starting house activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setmData(List<PoultryHouseView> mData) {
        this.mData = mData;
        //TODO chnage with more efficient way of changing data
        notifyDataSetChanged();
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
