package com.example.finalproject.trips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.database.Trip;
import com.example.finalproject.database.TripViewModel;
import com.example.finalproject.fragments.AllTripsFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CardInfo extends AppCompatActivity {

    public TextView timeTextView;
    public TextView temperatureTextView;
    public ImageView weatherIconImageView;
    public TextView nameTextView;
    public ImageView mainImageView;
    public TextView destinationTextView;
    public TextView tripTypeTextView;
    public TextView priceTextView;
    public TextView startDateTextView;
    public TextView endDateTextView;
    public TextView ratingTextView;
    public Button deleteButton;
    private TripViewModel tripViewModel;
    private Trip thisTrip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);

        timeTextView = findViewById(R.id.time);
        temperatureTextView = findViewById(R.id.temperature);
        weatherIconImageView = findViewById(R.id.icon);
        nameTextView = findViewById(R.id.name);
        mainImageView = findViewById(R.id.image);
        destinationTextView = findViewById(R.id.destination);
        tripTypeTextView = findViewById(R.id.tripType);
        priceTextView = findViewById(R.id.price);
        startDateTextView = findViewById(R.id.startDate);
        endDateTextView = findViewById(R.id.endDate);
        ratingTextView = findViewById(R.id.rating);
        deleteButton = findViewById(R.id.deleteButton);

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        createRequest();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnAlertDialog(thisTrip).show();
            }
        });
    }

    private void createRequest(){
        Bundle bundle = getIntent().getExtras();
        String destination = bundle.getString("destination");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.weatherapi.com/v1/current.json?key=6cbeb158ad1347e5bb7123238231405&q=" + destination)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("RequestFailed", "request doesn't work");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    String json = responseBody.string();

                    Gson gson = new Gson();
                    WeatherDao weatherDao = gson.fromJson(json, WeatherDao.class);

                    String time = weatherDao.getLocation().getTime();
                    int isDay = weatherDao.getCurrent().getIsDay();
                    String temperature = weatherDao.getCurrent().getTemperature() + " °C";

                    timeTextView.setText(time);
                    temperatureTextView.setText(temperature);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(isDay == 0){
                                Picasso.get().load("https://imgur.com/5vyZA2g.jpg").into(weatherIconImageView);
                                Log.e("Night", "its night");
                            }
                            else {
                                Picasso.get().load("https://imgur.com/wG6gAuI.jpg").into(weatherIconImageView);
                                Log.e("Day", "its day");
                            }
                            Uri uri = Uri.parse(bundle.getString("imageUri"));
                            Picasso.get().load(uri).resize(1280, 720).into(mainImageView);
                        }
                    });
                    thisTrip = new Trip();
                    thisTrip.setName(bundle.getString("name"));
                    thisTrip.setImageUri(bundle.getString("imageUri"));
                    thisTrip.setDestination(bundle.getString("destination"));
                    thisTrip.setTripType(bundle.getString("tripType"));
                    thisTrip.setPrice(bundle.getString("price"));
                    thisTrip.setStartDate(bundle.getString("startDate"));
                    thisTrip.setEndDate(bundle.getString("endDate"));
                    thisTrip.setRating(bundle.getString("rating"));
                    thisTrip.setId(bundle.getInt("id"));


                    nameTextView.setText(thisTrip.getName());
                    destinationTextView.setText(thisTrip.getDestination());
                    tripTypeTextView.setText(thisTrip.getTripType());
                    priceTextView.setText(thisTrip.getPrice() + " €");
                    startDateTextView.setText(thisTrip.getStartDate());
                    endDateTextView.setText(thisTrip.getEndDate());
                    ratingTextView.setText(thisTrip.getRating() + "/5.0");
                }
            }
        });
    }

    private void openMainFragment(){
        Intent intent = new Intent(this, AllTripsFragment.class);
        startActivity(intent);
    }
    private Context getThisContext(){
        return this;
    }

    private AlertDialog returnAlertDialog(Trip trip){
        AlertDialog.Builder builder = new AlertDialog.Builder(getThisContext());
        builder.setCancelable(true);
        builder.setTitle("Are you sure?");
        builder.setMessage("By confirming this you will permanently delete this trip!");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tripViewModel.delete(trip);
                openMainFragment();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog confirmDeletionDialog = builder.create();

        return  confirmDeletionDialog;
    }

}