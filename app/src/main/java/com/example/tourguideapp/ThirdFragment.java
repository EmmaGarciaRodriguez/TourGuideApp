package com.example.tourguideapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
    private AdapterFavActivity adapterFav;

    private SecondFragment.DataListener dataListener;

    ListView list;
    List<Integer> listaFavs;
    String[][] dataList;
    private HomeScreenActivity homeScreen;
    int[] imgData = {R.drawable.alahambragranada, R.drawable.basilicasagradafamiliabarcelona, R.drawable.casabatllobarcelona, R.drawable.catedralsevilla, R.drawable.mercadocentralvalencia,R.drawable.mezquitacordoba, R.drawable.palaciorealmadrid, R.drawable.plazaespanasevilla, R.drawable.puertaalcalamadrid, R.drawable.realalcazarsevilla};


    public ThirdFragment(HomeScreenActivity homeScreen) {
        // Required empty public constructor
        this.homeScreen = homeScreen;
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
    public static ThirdFragment newInstance(String param1, String param2, HomeScreenActivity homeScreen) {
        ThirdFragment fragment = new ThirdFragment(homeScreen);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public interface DataLoadListener {
        void onDataLoaded(List<Integer> listaFavs, String[][] dataList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void recuperateData(DataLoadListener listener) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(mContext.getApplicationContext()); // O getContext() según el método donde estés
        DataDao dataDao = appDatabase.dataDao();
        FavouritesDao favouritesDao = appDatabase.favouritesDao();

        Integer entityId = 2;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Recupera el JSON almacenado en la entidad
                DataEntity dataEntity = dataDao.getDataById(entityId);

                String jsonData = dataEntity.getDataJson();

                // Convierte el JSON a una lista String[][]
                Gson gson = new Gson();
                dataList = gson.fromJson(jsonData, String[][].class);
                Log.d("TAG", "DATOS DATA LEIDOS CORRECTAMENTE");
                // Ahora dataList contiene la lista original de String[][]



                //Recuperate listFavs
                String userId = homeScreen.getUserid();
                FavouritesEntity favsEntity = favouritesDao.getFavouritesByUserId(userId);
                // Recupera el JSON almacenado en la entidad
                String jsonFavsData = favsEntity.getFavouritePositionsJson();

                // Convierte el JSON a una lista de enteros utilizando Gson
                Gson g = new Gson();
                Type listType = new TypeToken<List<Integer>>(){}.getType();
                listaFavs = g.fromJson(jsonFavsData, listType);


                listener.onDataLoaded(listaFavs, dataList);
            }
        }).start();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);


        list = (ListView) view.findViewById(R.id.IvListaFavs);

        recuperateData(new DataLoadListener() {
            @Override
            public void onDataLoaded(List<Integer> listaFavs, String[][] dataList) {
                // Aquí puedes configurar el adaptador y otros ajustes para tu ListView

                adapterFav = new AdapterFavActivity(mContext, listaFavs, dataList, list, imgData);
                adapterFav.notifyDataSetChanged();
                list.setAdapter(adapterFav);
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (dataListener != null) {
            dataListener.onDataReceived(dataList);
        }

    }

    public AdapterFavActivity getAdapterFav() {
        return adapterFav;
    }

}