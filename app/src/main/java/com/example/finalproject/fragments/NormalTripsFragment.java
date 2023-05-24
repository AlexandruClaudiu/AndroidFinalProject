package com.example.finalproject.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.database.AddTripActivity;
import com.example.finalproject.database.TripViewModel;
import com.example.finalproject.trips.TripsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NormalTripsFragment extends Fragment {

    private RecyclerView recyclerViewTrips;
    private TripViewModel tripViewModel;
    private FloatingActionButton fab;


    public NormalTripsFragment() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        View view = inflater.inflate(R.layout.activity_trips, container, false);

        fab = view.findViewById(R.id.addFabButton);



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
            recyclerViewTrips.setAdapter(new TripsAdapter(trips, tripViewModel));
        });
    }
}