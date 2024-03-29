package com.example.finalproject.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository tripRepository;
    private LiveData<List<Trip>> tripListLiveData;

    public TripViewModel(Application application){
        super(application);
        tripRepository = new TripRepository(application);
        tripListLiveData = tripRepository.getAllTrips();
    }
    public LiveData<List<Trip>> getTripListLiveData(){
        tripListLiveData = tripRepository.getAllTrips();
        return tripListLiveData;
    }
    public LiveData<List<Trip>> getTripFavoriteListLiveData(){
        tripListLiveData = tripRepository.getFavoriteTrips();
        return tripListLiveData;
    }

    public void insert(Trip trip){
        tripRepository.insert(trip);
    }
    public void update(Trip trip) {tripRepository.update(trip);}
    public void delete(Trip trip) {tripRepository.delete(trip);}
}
