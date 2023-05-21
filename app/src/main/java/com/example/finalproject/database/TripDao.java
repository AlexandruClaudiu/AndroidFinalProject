package com.example.finalproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Query("SELECT * FROM trips")
    LiveData<List<Trip>> getAllTrips();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Update
    void update(Trip trip);

}
