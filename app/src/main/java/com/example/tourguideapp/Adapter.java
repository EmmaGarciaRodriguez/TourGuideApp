package com.example.tourguideapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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
    private HomeScreen home;


    public Adapter(Context context, String[][] data, int[] images, ListView list, HomeScreen homescreen, List<Integer> listaFavs)
    {
        this.context = context;
        this.data = data;
        this.imgData = images;
        this.lista = list;
        this.home = homescreen;
        this.listaFavs = listaFavs != null ? listaFavs : new ArrayList<>();

        // Inicializar la lista con 10 elementos en false
        for (int j = 0; j < 10; j++) {
            listaEstados.add(false);
        }

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


        // Marcar como true los elementos en listaEstados que coincidan con los índices en listaFavs
        if (listaFavs != null && !listaFavs.isEmpty()) {
            for (int position : listaFavs) {
                if (position >= 0 && position <= listaEstados.size()) {
                    listaEstados.set(position, true);
                }
            }
        }


        //CLICK EL CHECKBOX
        CheckBox checkBox = view.findViewById(R.id.favbtn);

        // Establecer el estado del CheckBox según la lista de estados

        checkBox.setChecked(listaEstados.get(i));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Obtén la posición del elemento asociada al CheckBox
            int position = i; // Esta variable debería contener la posición del elemento que corresponde al CheckBox

            if (isChecked == true) {
                // Si el CheckBox está marcado, agrega la posición a la lista de elementos seleccionados
                if (!listaFavs.contains(position) || listaFavs.isEmpty()) {
                    listaFavs.add(position);
                }
            } else {
                // Si el CheckBox está desmarcado, elimina la posición de la lista de elementos seleccionados
                listaFavs.remove(Integer.valueOf(position));
            }
                // Actualizar el estado del CheckBox en la lista de estados
                listaEstados.set(position, isChecked);

                saveinfoDB();
            }
        });

        // Configurar el evento de clic del botón
        hola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva Activity
                Intent intent = new Intent(context, PlaceDetails.class);

                intent.putExtra("POSITION", i);

                // Crear un Bundle y agregar el array bidimensional como un extra en el Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", data);
                bundle.putSerializable("ImagesData", imgData);

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
        saveinfoDB();
        return view;
    }


    public void saveinfoDB() {
        //GUARDAR LA LISTA DE FAVORITOS EN LA BD

        // Convierte la lista de enteros a JSON utilizando Gson
        Gson gson = new Gson();
        String jsonData = gson.toJson(listaFavs);

        // Crea una instancia de la entidad y asigna el JSON a su campo correspondiente
        //int entityId = 1;

        String userId = home.getUserid();
        FavouritesEntity favouritesEntity = new FavouritesEntity(1, userId, jsonData);

        // Inserta la entidad en la base de datos
        //AppDatabase.getInstance(context).dataDao().insertData(dataEntity);


        AppDatabase appDatabase = AppDatabase.getAppDatabase(context.getApplicationContext());
        FavouritesDao favouritesDao = appDatabase.favouritesDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                favouritesDao.insertFavourites(favouritesEntity);
                Log.d("TAG", "DATOS LISTAFAVS GUARDADOS BIEN");

            }
        }).start();
        //Guardar dato
        //dataDao.insertData(dataEntity);
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
