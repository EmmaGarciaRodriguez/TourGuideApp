package com.example.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private Adapter adapter;

    private DataListener dataListener;


    ListView list;
    String[][] data = {
            {"Alhambra de Granada","Granada","10"},
            {"Basílica de la Sagrada Familia","Barcelona","2"},
            {"Casa Batlló","Barcelona","3"},
            {"Catedral de Sevilla","Sevilla","4"},
            {"Mercado Central","Valencia","7"},
            {"Mezquita de Córdoba","Córdoba","8"},
            {"Palacio Real","Madrid","9"},
            {"Plaza de España","Sevilla","4"},
            {"Puerta de Alcalá","Madrid","10"},
            {"Real Alcázar","Sevilla","6"}
    };

    // Supongamos que tienes una lista de datos que deseas guardar en la base de datos
    //List<DataEntity> dataList = data;

    int[] imgData = {R.drawable.alahambragranada, R.drawable.basilicasagradafamiliabarcelona, R.drawable.casabatllobarcelona, R.drawable.catedralsevilla, R.drawable.mercadocentralvalencia, R.drawable.palaciorealmadrid, R.drawable.plazaespanasevilla, R.drawable.puertaalcalamadrid, R.drawable.realalcazarsevilla};

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Convierte la lista String[][] a JSON utilizando Gson
        /*Gson gson = new Gson();
        String jsonData = gson.toJson(data);

        // Crea una instancia de la entidad y asigna el JSON a su campo correspondiente
        int entityId = 1;
        DataEntity dataEntity = new DataEntity(entityId, jsonData);

        // Inserta la entidad en la base de datos
        AppDatabase.getInstance(mContext).dataDao().insertData(dataEntity);*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // Obtiene una referencia al ListView
        list = (ListView) view.findViewById(R.id.IvLista);

        // Aquí puedes configurar el adaptador y otros ajustes para tu ListView
        adapter = new Adapter(mContext, data, imgData, list);
        list.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (dataListener != null) {
            dataListener.onDataReceived(data);
        }

    }

    public Adapter getAdapter() {
        return adapter;
    }

    public interface DataListener {
        void onDataReceived(String[][] dataList);
    }

    public void setDataListener(DataListener listener) {
        this.dataListener = listener;
    }

}