package com.example.finalproject.trips;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.HomeFragment;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripViewHolder> {

    public List<Trip> trips;

    public TripsAdapter(List<Trip> trips){
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_card, parent, false);
        return new TripViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip currentTrip = trips.get(position);
        holder.getTextViewName().setText(currentTrip.getName());
        Picasso.get().load(currentTrip.getImageUrl()).into(holder.getImageViewImage());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewType().setText(currentTrip.getTripType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", currentTrip.getName());
                bundle.putString("imageUrl", currentTrip.getImageUrl());
                bundle.putString("destination", currentTrip.getDestination());
                bundle.putString("tripType", currentTrip.getTripType());
                bundle.putDouble("price", currentTrip.getPrice());
                bundle.putString("startDate", currentTrip.getStartDate());
                bundle.putString("endDate", currentTrip.getEndDate());
                bundle.putDouble("rating", currentTrip.getRating());



                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
