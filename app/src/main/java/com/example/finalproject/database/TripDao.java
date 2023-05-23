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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Trip trip);

    @Delete
    void delete(Trip trip);
}
