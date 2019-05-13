package com.example.poultrygeist.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.poultrygeist.DB.ModelAndViews.Chicken;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ChickenDAO {
    @Insert (onConflict = REPLACE)
    void insertChicken(Chicken chick);
    @Delete
    void deleteChicken(Chicken chick);

    @Query("SELECT * FROM Chicken")
    LiveData<List<Chicken>> getALLChickns();

    @Query("Delete from chicken")
    void deleteAllChickens();

}
