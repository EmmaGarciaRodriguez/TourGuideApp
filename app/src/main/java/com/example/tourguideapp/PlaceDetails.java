package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        TextView title = (TextView) findViewById(R.id.title);
        TextView  description = (TextView) findViewById(R.id.description);
        ImageView image = (ImageView) findViewById(R.id.image);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b!=null) {
            title.setText(b.getString("TIT"));
            description.setText(b.getString("DES"));
            image.setImageResource(Integer.parseInt(b.getString("IM")));
            rating.setProgress(Integer.valueOf(b.getString("RA")));
        }
    }
}