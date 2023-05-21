package com.example.finalproject.trips;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
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


        createRequest();
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
                            Picasso.get().load(uri).into(mainImageView);
                        }
                    });
                    destinationTextView.setText(bundle.getString("destination"));
                    tripTypeTextView.setText(bundle.getString("tripType"));
                    priceTextView.setText(bundle.getString("price") + " €");
                    startDateTextView.setText(bundle.getString("startDate"));
                    endDateTextView.setText(bundle.getString("endDate"));
                    ratingTextView.setText(bundle.getString("rating") + "/5.0");
                    nameTextView.setText(bundle.getString("name"));
                }
            }
        });
    }



}