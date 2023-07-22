package com.example.tourguideapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;

    private SecondFragment.DataListener dataListener;

    ListView list;
    List<Integer> listaFavs;
    String[][] dataList;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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


        AppDatabase appDatabase = AppDatabase.getInstance(requireContext()); // O getContext() según el método donde estés
        DataDao dataDao = appDatabase.dataDao();

        int entityId = 1; // Asigna el ID de la entidad que deseas recuperar
        DataEntity dataEntity = dataDao.getDataById(entityId);


        // Recupera el JSON almacenado en la entidad
        String jsonData = dataEntity.getDataJson();

        // Convierte el JSON a una lista String[][]
        Gson gson = new Gson();
        dataList = gson.fromJson(jsonData, String[][].class);

        // Ahora dataList contiene la lista original de String[][]



        //Recuperar la lista con las posiciones de los elementos favoritos

        int entId = 2; // El mismo ID que utilizaste al guardar la lista
        DataEntity favsEntity = dataDao.getDataById(entId);
        // Recupera el JSON almacenado en la entidad
        String jsonFavsData = favsEntity.getDataJson();

        // Convierte el JSON a una lista de enteros utilizando Gson
        Gson g = new Gson();
        Type listType = new TypeToken<List<Integer>>(){}.getType();
        listaFavs = g.fromJson(jsonFavsData, listType);

        // Ahora listaEnteros contiene la lista original de enteros que habías guardado

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // Obtiene una referencia al ListView
        list = (ListView) view.findViewById(R.id.IvListaFavs);

        // Aquí puedes configurar el adaptador y otros ajustes para tu ListView
        list.setAdapter(new AdapterFav(mContext, listaFavs, dataList, list));

        return view;
    }
}