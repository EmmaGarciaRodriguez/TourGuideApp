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
    List<Integer> listaFavs= new ArrayList<>();

    public AdapterFav(Context mContext, List<Integer> listaFavs, String[][] dataList, ListView list)
    {
        this.context = mContext;
        this.data = dataList;
        this.lista = list;
        this.listaFavs = listaFavs;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);

        // Verifica si la posición actual está contenida en la lista de posiciones
        if (listaFavs.contains(position)) {
            // Si la posición está en la lista de posiciones, muestra el elemento en la vista
            TextView textView = view.findViewById(R.id.tvTitle);

            textView.setText(String.valueOf(data[position][0]));
        } else {
            // Si la posición no está en la lista de posiciones, oculta el elemento en la vista
            // Por ejemplo, si tienes un TextView en el layout del elemento, puedes ocultarlo así:
            TextView textView = view.findViewById(R.id.tvTitle);
            textView.setVisibility(View.GONE);
        }

        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public int[] getImgData() {
        return imgData;
    }

    public void setImgData(int[] imgData) {
        this.imgData = imgData;
    }

    public ListView getLista() {
        return lista;
    }

    public void setLista(ListView lista) {
        this.lista = lista;
    }

    public List<Integer> getListaFavs() {
        return listaFavs;
    }

    public void setListaFavs(List<Integer> listaFavs) {
        this.listaFavs = listaFavs;
    }

    @Override
    public int getCount() {
        return listaFavs.size();
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
