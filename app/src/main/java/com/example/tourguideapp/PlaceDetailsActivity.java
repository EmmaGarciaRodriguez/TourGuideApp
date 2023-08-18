package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class PlaceDetailsActivity extends AppCompatActivity implements SecondFragment.DataListener {

    String[][] listaDatos;
    String[][] data;

    @Override
    public void onDataReceived(String[][] dataList) {
        listaDatos = dataList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_place_details);
        } else {
            setContentView(R.layout.activity_place_details_land);
        }

        TextView title = findViewById(R.id.eTitle);
        TextView subtitle = findViewById(R.id.esubtitle);
        TextView description = findViewById(R.id.eDescription);
        ImageView image = findViewById(R.id.eImage);
        RatingBar rating = findViewById(R.id.eRatingBar);
        Button rtrnBtn = findViewById(R.id.returnBtn);


        Intent intent = getIntent();

        int posicion = intent.getIntExtra("POSITION", -1);

        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.containsKey("DATA") && bundle.containsKey("ImagesData")) {

            data = (String[][]) bundle.getSerializable("DATA");
            //int[] ImagesData = (int[]) bundle.getSerializable("ImagesData");

            title.setText(data[posicion][0]);
            subtitle.setText(data[posicion][1]);
            description.setText(data[posicion][3]);

            rating.setProgress(Integer.valueOf(data[posicion][2]));

            new DownloadImageTaskActivity(image).execute(data[posicion][4]);
            //image.setImageResource(ImagesData[posicion]);
        }

        rtrnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Come back to the last activity
            }
        });
    }
}