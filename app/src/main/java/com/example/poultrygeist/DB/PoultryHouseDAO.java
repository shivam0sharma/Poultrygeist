package com.example.poultrygeist.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.poultrygeist.DB.ModelAndViews.PoultryHouse;
import com.example.poultrygeist.DB.ModelAndViews.PoultryHouseView;

import java.util.List;

@Dao
public interface PoultryHouseDAO {
    @Insert
    void insertHouse(PoultryHouse house);
    @Delete
    void deleteHouse(PoultryHouse house);
    @Update
    void updateHouse(PoultryHouse house);

    @Query("SELECT PoultryHouse.houseId, PoultryHouse.length, PoultryHouse.width," +
            " (Select COUNT(*) from Chicken where Chicken.ChickenHouse = PoultryHouse.houseId) " +
            "as numberOfChickens FROM PoultryHouse")
    LiveData<List<PoultryHouseView>> getALLHouses();

    @Query("SELECT * FROM PoultryHouse WHERE houseId = :HID")
    PoultryHouse getHouse(int HID);
    @Query("SELECT COUNT(*) FROM Chicken WHERE ChickenHouse = :HID")
    int getNumberOfChicken(int HID);

    @Query("DELETE FROM PoultryHouse")
    void deleteAllHouses();



}
