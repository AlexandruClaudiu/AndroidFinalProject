package com.example.finalproject.trips;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}
