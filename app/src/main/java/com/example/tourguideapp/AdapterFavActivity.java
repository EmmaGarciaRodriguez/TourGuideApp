package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterFavActivity extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] data;
    int[] imgData;
    ListView lista;
    List<Integer> listaFavs= new ArrayList<>();

    public AdapterFavActivity(Context mContext, List<Integer> listaFavs, String[][] dataList, ListView list, int[] imgData)
    {
        this.context = mContext;
        this.data = dataList;
        this.lista = list;
        this.listaFavs = listaFavs;
        this.imgData = imgData;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);


        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        TextView  subtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        ImageView image = (ImageView) view.findViewById(R.id.ivImage);
        RatingBar rating = (RatingBar) view.findViewById(R.id.eRatingBar);
        Button hola = (Button) view.findViewById(R.id.hola);
        CheckBox box = (CheckBox) view.findViewById(R.id.favbtn);


        if (listaFavs.contains(position)) {


            new DownloadImageTaskActivity(image).execute(data[position][4]);
            //image.setImageResource(imgData[position]);
            title.setText(data[position][0]);
            subtitle.setText(data[position][1]);
            //image.setImageResource(imgData[position]);
            rating.setProgress(Integer.valueOf(data[position][2]));
            box.setVisibility(View.GONE);

       }
        hola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva Activity
                Intent intent = new Intent(context, PlaceDetailsActivity.class);

                intent.putExtra("POSITION", position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", data);
                bundle.putSerializable("ImagesData", imgData);

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

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
