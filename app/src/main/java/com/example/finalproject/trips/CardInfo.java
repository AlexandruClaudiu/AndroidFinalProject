package com.example.finalproject.trips;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


        Bundle bundle = getIntent().getExtras();
        String destination = bundle.getString("destination");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                                .build();

        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);
        Call<WeatherData> call = weatherApiService.getCurrentWeather("6cbeb158ad1347e5bb7123238231405", destination);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if(response.isSuccessful()){
                    WeatherData weatherData = response.body();
                    String time = weatherData.getLocation().getTime();
                    int isDay = weatherData.getCurrent().getIsDay();
                    String temperature = weatherData.getCurrent().getTemperature() + " °C";

                    timeTextView.setText(time);
                    temperatureTextView.setText(temperature);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(isDay == 0){
                                Picasso.get().load("https://imgur.com/5vyZA2g.jpg").into(weatherIconImageView);
                            }
                            else {
                                Picasso.get().load("https://imgur.com/wG6gAuI.jpg").into(weatherIconImageView);
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

                } else{

                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnAlertDialog(thisTrip).show();
            }
        });
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private Context getThisContext(){
        return this;
    }

    private AlertDialog returnAlertDialog(Trip trip){
        AlertDialog.Builder builder = new AlertDialog.Builder(getThisContext(), R.style.CustomAlertDialog);
        builder.setCancelable(true);
        builder.setTitle("Are you sure?");
        builder.setMessage("By confirming this you will permanently delete this trip!");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tripViewModel.delete(trip);
                openMainActivity();
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