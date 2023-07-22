package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterFav extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] data;
    int[] imgData;
    ListView lista;
    List<Integer> listaFavs = new ArrayList<>();

    public AdapterFav(Context mContext, List<Integer> listaFavs, String[][] dataList, ListView list)
    {
        this.context = mContext;
        this.data = dataList;
        this.lista = list;
        this.listaFavs = listaFavs;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);
        // Obtén la vista del elemento de la lista
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(view.getContext()).inflate(R.layout.listelement, parent, false);
        }


        // Verifica si la posición actual está contenida en la lista de posiciones
        if (listaFavs.contains(position)) {
            // Si la posición está en la lista de posiciones, muestra el elemento en la vista
            TextView textView = itemView.findViewById(R.id.tvTitle);
            textView.setText(String.valueOf(data[position][0]));
        } else {
            // Si la posición no está en la lista de posiciones, oculta el elemento en la vista
            // Por ejemplo, si tienes un TextView en el layout del elemento, puedes ocultarlo así:
            TextView textView = itemView.findViewById(R.id.tvTitle);
            textView.setVisibility(View.GONE);
        }

        return view;
    }

}
