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

    void insert(Trip trip){
        Log.e("Din repository", "Cica merge si aici");
        TripsDatabase.dbExecutor.execute(()->{
            tripDao.insert(trip);
        });
    }

}
