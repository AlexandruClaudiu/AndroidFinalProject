package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.trips.Trip;
import com.example.finalproject.trips.TripsAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<Trip> trips;

    private RecyclerView recyclerViewTrips;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.activity_trips, container, false);
        recyclerViewTrips = view.findViewById(R.id.recyclerViewTrips);
        populateTrips();
        setupRecyclerView();
        return view;
    }

    public void setupRecyclerView(){
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTrips.setAdapter(new TripsAdapter(trips));
    }

    public void populateTrips(){
        trips = new ArrayList<>();
        Trip trip1 = new Trip();
        trip1.build("First Trip", "Egypt", "City Break");
        trip1.setStartDate("1.03.2022");
        trip1.setEndDate("20.03.2022");
        trip1.setPrice(50.20);
        trip1.setRating(3.5);
        trip1.setImageUrl("https://i.imgur.com/ugFnkjQ.jpg");
        trips.add(trip1);

        Trip trip2 = new Trip();
        trip2.build("Second Trip", "Egypt", "City Break");
        trip2.setStartDate("1.03.2022");
        trip2.setEndDate("20.03.2022");
        trip2.setPrice(50.20);
        trip2.setRating(3.5);
        trip2.setImageUrl("https://i.imgur.com/ugFnkjQ.jpg");
        trips.add(trip2);

        for(int i = 0; i < trips.size(); i++){
            Log.e("Trip", trips.get(i).toString());
        }
    }
}