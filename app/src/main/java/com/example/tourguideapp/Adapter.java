package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    String[][] data;
    int[] imgData;

    public Adapter(Context context, String[][] data, int[] images)
    {
        this.context = context;
        this.data = data;
        this.imgData = images;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View view = inflater.inflate(R.layout.listelement, null);

        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        TextView  subtitle = (TextView) view.findViewById(R.id.tvSubtitle);
        ImageView image = (ImageView) view.findViewById(R.id.ivImage);
        RatingBar rating = (RatingBar) view.findViewById(R.id.ratingBar);

        title.setText(data[i][0]);
        subtitle.setText(data[i][1]);
        image.setImageResource(imgData[i]);
        rating.setProgress(Integer.valueOf(data[i][2]));

        image.setTag(i);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImage = new Intent(context, Visor.class);
                visorImage.putExtra("IMG", imgData[(Integer)v.getTag()]);
                context.startActivity(visorImage);
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
