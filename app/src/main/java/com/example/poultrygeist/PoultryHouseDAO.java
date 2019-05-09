package com.example.poultrygeist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.ROLLBACK;

@Dao
public interface PoultryHouseDAO {
    @Insert
    void insertHouse(PoultryHouse house);
    @Delete
    void deleteHouse(PoultryHouse house);

    @Query("SELECT * FROM PoultryHouse")
    LiveData<List<PoultryHouse>> getALLHouses();

    @Query("SELECT * FROM PoultryHouse WHERE houseId = :HID")
    PoultryHouse getHouse(int HID);
    @Query("SELECT COUNT(*) FROM Chicken WHERE ChickenHouse = :HID")
    int getNumberOfChicken(int HID);

}
