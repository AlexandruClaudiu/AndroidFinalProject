package com.example.finalproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.database.AddTripActivity;
import com.example.finalproject.database.Trip;
import com.example.finalproject.database.TripViewModel;
import com.example.finalproject.trips.TripsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerViewTrips;

    private TripViewModel tripViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        View view = inflater.inflate(R.layout.activity_trips, container, false);

        FloatingActionButton fab = view.findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTripActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("isAddInsert", 1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerViewTrips = view.findViewById(R.id.recyclerViewTrips);
        setupRecyclerView();
        return view;
    }


    public void setupRecyclerView(){
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getContext()));
        tripViewModel.getTripListLiveData().observe(getViewLifecycleOwner(), trips-> {
            recyclerViewTrips.setAdapter(new TripsAdapter(trips));
        });
    }
}