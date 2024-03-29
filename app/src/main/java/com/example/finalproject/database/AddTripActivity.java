package com.example.finalproject.database;



import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.finalproject.MainActivity;

import com.example.finalproject.R;
import com.example.finalproject.fragments.AllTripsFragment;
import com.squareup.picasso.Picasso;

import java.io.OutputStream;

public class AddTripActivity extends AppCompatActivity {

    private Button startDateButton;
    private Button endDateButton;
    private Button selectImageButton;
    private Button saveButton;
    private ImageView imageGalleryView;
    private EditText nameEditText;
    private EditText destinationEditText;
    private RadioGroup radioGroup;
    private EditText priceEditText;
    private RatingBar ratingBar;
    private RadioButton selectedRadioButton;

    public  int IS_ADD_INSERT;
    private TripViewModel tripViewModel;
    private ActivityResultLauncher<Intent> pickMedia;

    private Uri imageUri;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);


        startDateButton = findViewById(R.id.startDatePick);
        endDateButton = findViewById(R.id.endDatePick);
        selectImageButton = findViewById(R.id.selectImageButton);
        imageGalleryView = findViewById(R.id.imageGalleryView);
        nameEditText = findViewById(R.id.nameText);
        destinationEditText = findViewById(R.id.destinationText);
        radioGroup = findViewById(R.id.radioGroup);
        priceEditText = findViewById(R.id.priceText);
        ratingBar = findViewById(R.id.ratingBar);
        saveButton = findViewById(R.id.saveButton);

        Bundle bundle = getIntent().getExtras();
        IS_ADD_INSERT = bundle.getInt("isAddInsert");

        if(IS_ADD_INSERT == 2){
            Uri uri = Uri.parse(bundle.getString("imageUri"));
            imageUri = uri;
            nameEditText.setText(bundle.getString("name"));
            Picasso.get().load(uri).into(imageGalleryView);
            destinationEditText.setText(bundle.getString("destination"));
            priceEditText.setText(bundle.getString("price"));
            startDateButton.setText(bundle.getString("startDate"));
            endDateButton.setText(bundle.getString("endDate"));
            ratingBar.setRating(Float.valueOf(bundle.getString("rating")));
        }

        pickMedia = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result->{
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                if (data != null && data.getData() != null) {
                    imageUri = data.getData();
                    getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Picasso.get().load(imageUri).into(imageGalleryView);
                }
            }
        });



        startDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(startDateButton);
            }
        });

        endDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(endDateButton);
            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                i.setType("image/*");
                pickMedia.launch(i);
            }
        });


        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                Trip trip = new Trip();
                trip.build(nameEditText.getText().toString(), destinationEditText.getText().toString(), selectedRadioButton.getText().toString());
                trip.setImageUri(imageUri.toString());
                trip.setPrice(priceEditText.getText().toString());
                trip.setStartDate(startDateButton.getText().toString());
                trip.setEndDate(startDateButton.getText().toString());
                trip.setRating(String.valueOf(ratingBar.getRating()));
                if(IS_ADD_INSERT == 1){
                    tripViewModel.insert(trip);
                } else if(IS_ADD_INSERT == 2){
                    trip.setId(bundle.getInt("id"));
                    tripViewModel.update(trip);
                }
                openMainActivity();
            }
        });
    }


    private void openDialog(Button button){
        DatePickerDialog dialog = new DatePickerDialog(this,R.style.CustomDatePickerDialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                button.setText(String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year));
            }
        }, 2023, 0, 15);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog1) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });


        dialog.show();
    }
    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}