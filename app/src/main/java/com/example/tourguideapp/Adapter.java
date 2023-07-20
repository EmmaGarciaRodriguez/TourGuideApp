package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] data;
    int[] imgData;
    ListView lista;

    public Adapter(Context context, String[][] data, int[] images, ListView list)
    {
        this.context = context;
        this.data = data;
        this.imgData = images;
        this.lista = list;


        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);
        //final View view2 = inflater.inflate(R.layout.fragment_second, null);

        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        TextView  subtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        ImageView image = (ImageView) view.findViewById(R.id.ivImage);
        RatingBar rating = (RatingBar) view.findViewById(R.id.eRatingBar);
        Button hola = (Button) view.findViewById(R.id.hola);

        title.setText(data[i][0]);
        subtitle.setText(data[i][1]);
        image.setImageResource(imgData[i]);
        rating.setProgress(Integer.valueOf(data[i][2]));

        image.setTag(i); //keeps the reference of the image clicked

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImage = new Intent(context, Visor.class);
                visorImage.putExtra("IMG", imgData[(Integer)v.getTag()]);
                context.startActivity(visorImage);
            }
        });

        // Configurar el evento de clic del botón
        hola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva Activity
                Intent intent = new Intent(context, PlaceDetails.class);

                intent.putExtra("POSITION", i);
                //intent.putExtra("DATA", data);

                // Crear un Bundle y agregar el array bidimensional como un extra en el Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", data);

                // Agregar el Bundle como un extra en el Intent
                intent.putExtras(bundle);

                // Iniciar la nueva Activity utilizando el Intent
                context.startActivity(intent);
            }
        });

        //Cuando se clica en un elemento de la lista

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                //Crea una pagina detallada y le pasa la posicion seleccionada
                PlaceDetails detailsPage = new PlaceDetails();
            }
        });
        return view;
    }


    @Override
    public int getCount() {
        return imgData.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
