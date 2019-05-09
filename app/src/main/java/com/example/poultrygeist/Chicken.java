package com.example.poultrygeist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity (primaryKeys = {"x","y","ChickenHouse"}, foreignKeys = @ForeignKey(entity = PoultryHouse.class,
parentColumns = "houseId", childColumns = "ChickenHouse", onDelete = CASCADE, onUpdate = CASCADE)
, indices = {@Index(value = "ChickenHouse")})
public class Chicken {
    @ColumnInfo(name = "x")
    private int x;
    @ColumnInfo(name = "y")
    private int y;
    @ColumnInfo(name = "ChickenHouse")
    private int ChickenHouse;
    @ColumnInfo(name = "PickedUp")
    private boolean Pickedup;

    public Chicken(int x, int y, int ChickenHouse) {
        this.x = x;
        this.y = y;
        this.ChickenHouse = ChickenHouse;
        Pickedup = false;
    }

    public int getChickenHouse() {
        return ChickenHouse;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean isPickedup() {
        return Pickedup;
    }

    public void setPickedup(boolean pickedup) {
        Pickedup = pickedup;
    }
}
