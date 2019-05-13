package com.example.poultrygeist.DB.ModelAndViews;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = "houseId")})
public class PoultryHouse {
    @PrimaryKey
    private int houseId;
    @ColumnInfo(name = "length")
    private int length;
    @ColumnInfo(name = "width")
    private int width;

    public PoultryHouse(int houseId, int length, int width) {
        this.houseId = houseId;
        this.length = length;
        this.width = width;
    }

    public int getHouseId() {
        return houseId;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
