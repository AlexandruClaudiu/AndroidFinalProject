package com.example.finalproject.trips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject.R;
import com.example.finalproject.database.Trip;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripViewHolder> {

    public List<Trip> trips;

    private static final int REQUEST_OPEN_DOCUMENT = 1;


    private Context context;

    public TripsAdapter(List<Trip> trips){
        this.trips = trips;
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

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            Log.e("Este Permis?", String.valueOf(Environment.isExternalStorageManager()));
//        }

        Uri uri = Uri.parse(currentTrip.getImageUri());
        String imagePath = uri.getPath();
        Log.e("UriCast", uri.toString());
        holder.getTextViewName().setText(currentTrip.getName());
        Picasso.get().load(uri).into(holder.getImageViewImage());
        //holder.getImageViewImage().setImageURI(uri);
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getTextViewType().setText(currentTrip.getTripType());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CardInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", currentTrip.getName());
                bundle.putString("imageUri", currentTrip.getImageUri());
                bundle.putString("destination", currentTrip.getDestination());
                bundle.putString("tripType", currentTrip.getTripType());
                bundle.putString("price", currentTrip.getPrice());
                bundle.putString("startDate", currentTrip.getStartDate());
                bundle.putString("endDate", currentTrip.getEndDate());
                bundle.putString("rating", currentTrip.getRating());

                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(trips == null){
            trips = new ArrayList<>();
            return 0;
        }
        return trips.size();
    }




}
