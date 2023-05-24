package com.example.finalproject.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripsDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    private static volatile TripsDatabase INSTANCE;

    static TripsDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (TripsDatabase.class){
                if(INSTANCE == null){
                    //context.getApplicationContext().deleteDatabase("trips");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripsDatabase.class, "trips").build();
                }
            }
        }
        return  INSTANCE;
    }
    static final ExecutorService dbExecutor = Executors.newFixedThreadPool(4);
}
