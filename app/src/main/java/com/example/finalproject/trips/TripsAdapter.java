package com.example.finalproject.trips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject.R;
import com.example.finalproject.database.AddTripActivity;
import com.example.finalproject.database.Trip;

import com.example.finalproject.database.TripViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripViewHolder> {

    public List<Trip> trips;

    TripViewModel tripViewModel;

    public TripsAdapter(List<Trip> trips, TripViewModel tripViewModel){
        this.trips = trips;
        this.tripViewModel = tripViewModel;
        if(this.trips == null){
            Log.e("TripsAdapterConstructor", "E goala varu");
        }
        else{
            Log.e("TripsAdapterConstructor", this.trips.toString());
        }
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

        Uri uri = Uri.parse(currentTrip.getImageUri());
        Log.e("UriCast", uri.toString());
        holder.getTextViewName().setText(currentTrip.getName());
        Picasso.get().load(uri).resize(1920, 1080).into(holder.getImageViewImage());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewType().setText(currentTrip.getTripType());
        holder.getRatingBar().setRating(Float.valueOf(currentTrip.getRating()));

        if(currentTrip.getFavorite().equals(false)){
            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.add_to_favorite_empty_ic);
            holder.getAddToFavoriteButton().setBackground(drawable);
        }else if(currentTrip.getFavorite().equals(true)){
            Drawable drawable = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.add_to_favorite_full_ic);
            drawable.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
            holder.getAddToFavoriteButton().setBackground(drawable);
        }

        holder.getAddToFavoriteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTrip.getFavorite().equals(true)){
                    currentTrip.setFavorite(false);
                } else {
                    currentTrip.setFavorite(true);
                }
                tripViewModel.update(currentTrip);
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardInfo.class);
                Bundle bundle = new Bundle();
                attachToBundleTrips(bundle, currentTrip);
                bundle.putInt("id", currentTrip.getId());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTripActivity.class);
                Bundle bundle = new Bundle();
                attachToBundleTrips(bundle, currentTrip);
                bundle.putInt("id", currentTrip.getId());
                bundle.putInt("isAddInsert", 2);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
                return true;
            }
        });
    }

    public void attachToBundleTrips(Bundle bundle, Trip currentTrip){
        bundle.putString("name", currentTrip.getName());
        bundle.putString("imageUri", currentTrip.getImageUri());
        bundle.putString("destination", currentTrip.getDestination());
        bundle.putString("tripType", currentTrip.getTripType());
        bundle.putString("price", currentTrip.getPrice());
        bundle.putString("startDate", currentTrip.getStartDate());
        bundle.putString("endDate", currentTrip.getEndDate());
        bundle.putString("rating", currentTrip.getRating());
    }



    @Override
    public int getItemCount() {
        if(trips == null){
            trips = new ArrayList<>();
            return 0;
        }

        for(int i = 0; i < trips.size(); i++){
            Log.e("Id-ul este:",String.valueOf(trips.get(i).getId()));
        }
        return trips.size();
    }

}
