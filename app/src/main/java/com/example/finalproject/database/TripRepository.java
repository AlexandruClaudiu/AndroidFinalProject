package com.example.finalproject.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {
    private TripDao tripDao;

    public TripRepository(Application application){
        TripsDatabase tripsDatabase = TripsDatabase.getDatabase(application);
        tripDao = tripsDatabase.tripDao();
    }

    LiveData<List<Trip>> getAllTrips(){
        return tripDao.getAllTrips();
    }

    LiveData<List<Trip>> getFavoriteTrips(){
        return tripDao.getFavoriteTrips();
    }



    void insert(Trip trip){
        TripsDatabase.dbExecutor.execute(()->{
            tripDao.insert(trip);
        });
    }

    void update(Trip trip){
        TripsDatabase.dbExecutor.execute(()->{
            tripDao.update(trip);
        });
    }

    void delete(Trip trip){
        TripsDatabase.dbExecutor.execute(()->{
            tripDao.delete(trip);
        });
    }

}
