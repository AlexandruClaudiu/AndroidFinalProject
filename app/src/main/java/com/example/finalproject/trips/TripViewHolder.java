package com.example.finalproject.trips;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.R;

public class TripViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private ImageView imageViewImage;
    private TextView textViewDestination;
    private TextView textViewType;

    private RatingBar ratingBar;

    private Button addToFavoriteButton;

    public TripViewHolder(@NonNull View cardView){
        super(cardView);
        textViewName = cardView.findViewById(R.id.name);
        imageViewImage = cardView.findViewById(R.id.image);
        textViewDestination = cardView.findViewById(R.id.destination);
        textViewType = cardView.findViewById(R.id.type);
        addToFavoriteButton = cardView.findViewById(R.id.addToFavoriteButton);
        ratingBar = cardView.findViewById(R.id.ratingBarCard);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public ImageView getImageViewImage() {
        return imageViewImage;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public TextView getTextViewType() {
        return textViewType;
    }

    public Button getAddToFavoriteButton(){return addToFavoriteButton;}

    public RatingBar getRatingBar() {
        return ratingBar;
    }
}
