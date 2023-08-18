package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] data;
    int[] imgData;
    ListView lista;
    List<Integer> listaFavs;
    List<Boolean> listaEstados = new ArrayList<Boolean>();
    private SharedPreferences sharedPreferences;
    private HomeScreenActivity home;


    public Adapter(Context context, String[][] data, int[] images, ListView list, HomeScreenActivity homescreen, List<Integer> listaFavs)
    {
        this.context = context;
        this.data = data;
        this.imgData = images;
        this.lista = list;
        this.home = homescreen;
        this.listaFavs = listaFavs != null ? listaFavs : new ArrayList<>();

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);

        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        TextView  subtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        ImageView image = (ImageView) view.findViewById(R.id.ivImage);
        RatingBar rating = (RatingBar) view.findViewById(R.id.eRatingBar);
        Button hola = (Button) view.findViewById(R.id.hola);

        title.setText(data[i][0]);
        subtitle.setText(data[i][1]);

        new DownloadImageTaskActivity(image).execute(data[i][4]);

        rating.setProgress(Integer.valueOf(data[i][2]));

        // Inicialice listaEstados
                for (int j = 0; j < 10; j++) {
                    listaEstados.add(false);
                }

        if (listaFavs != null && !listaFavs.isEmpty()) {
            for (int position : listaFavs) {
                if (position >= 0 && position <= listaEstados.size()) {
                    listaEstados.set(position, true);
                }
            }
        }


        //CLICK CHECKBOX
        CheckBox checkBox = view.findViewById(R.id.favbtn);

        // Establecer el estado del CheckBox según la lista de estados
        if (listaEstados.get(i)){
            checkBox.setChecked(true);
        }


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            int position = i;

            if (isChecked == true) {
                  if (!listaFavs.contains(position) || listaFavs.isEmpty()) {
                    listaFavs.add(position);
                }

            } else {
                listaFavs.remove(Integer.valueOf(position));
            }
                // Actualizar el estado del CheckBox en la lista de estados
                listaEstados.set(position, isChecked);

                saveinfoDB();
            }
        });


        hola.setOnClickListener(new View.OnClickListener() { //EditProfile button
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PlaceDetailsActivity.class);

                intent.putExtra("POSITION", i);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", data);
                bundle.putSerializable("ImagesData", imgData);

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View convertView, int position, long id) {
                //Crea una pagina detallada y le pasa la posicion seleccionada
                PlaceDetailsActivity detailsPage = new PlaceDetailsActivity();
            }
        });


        saveinfoDB();
        return view;
    }


    public void saveinfoDB() {
        //Save the ListFavs in the DB

        Gson gson = new Gson();
        String jsonData = gson.toJson(listaFavs);

        String userId = home.getUserid();
        FavouritesEntity favouritesEntity = new FavouritesEntity(1, userId, jsonData);

        AppDatabase appDatabase = AppDatabase.getAppDatabase(context.getApplicationContext());
        FavouritesDao favouritesDao = appDatabase.favouritesDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                favouritesDao.insertFavourites(favouritesEntity);

            }
        }).start();
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public SharedPreferences getSharedPreferences(String mis_preferencias, int modePrivate) {
        return sharedPreferences;
    }
}
