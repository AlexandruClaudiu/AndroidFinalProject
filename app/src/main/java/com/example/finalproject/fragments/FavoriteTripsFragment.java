package com.example.finalproject.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.database.TripViewModel;
import com.example.finalproject.trips.TripsAdapter;


public class FavoriteTripsFragment extends Fragment {

    private RecyclerView recyclerViewFavoriteTrips;

    private TripViewModel tripViewModel;

    public FavoriteTripsFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        View view = inflater.inflate(R.layout.fragment_favorite_trips, container, false);

        recyclerViewFavoriteTrips = view.findViewById(R.id.recyclerViewFavoriteTrips);
        setupRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupRecyclerView();
    }

    public void setupRecyclerView(){
        recyclerViewFavoriteTrips.setLayoutManager(new LinearLayoutManager(getContext()));
        tripViewModel.getTripFavoriteListLiveData().observe(getViewLifecycleOwner(), trips-> {
            recyclerViewFavoriteTrips.setAdapter(new TripsAdapter(trips, tripViewModel));
        });
    }
}