package com.example.tourguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class PlaceDetails extends AppCompatActivity implements SecondFragment.DataListener {

    String[][] listaDatos;

    public PlaceDetails() {
    }
    @Override
    public void onDataReceived(String[][] dataList) {
        listaDatos = dataList;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        TextView title = findViewById(R.id.eTitle);
        TextView description = findViewById(R.id.eDescription);
        ImageView image = findViewById(R.id.eImage);
        RatingBar rating = findViewById(R.id.eRatingBar);



        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Obtener el valor de la posición que se pasó como extra en el Intent
        int posicion = intent.getIntExtra("POSITION", -1);

        // Obtener el Bundle como extra utilizando getExtras()
        Bundle bundle = intent.getExtras();

        // Verificar si el Bundle no es nulo y contiene el array bidimensional
        if (bundle != null && bundle.containsKey("DATA")) {
            // Obtener el array bidimensional de cadenas de texto
            String[][] data = (String[][]) bundle.getSerializable("DATA");

            title.setText(data[posicion][0]);
            description.setText(data[posicion][1]);
            //rating.setProgress(listaDatos[listPosition][0]);
        }

    }
}