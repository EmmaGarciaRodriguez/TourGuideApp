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
import java.util.ArrayList;
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

    List<Integer> listFavs;
    String[][] dataList;

    private DataListener dataListener;


    ListView list;
    String[][] data;


    int[] imgData = {R.drawable.alahambragranada, R.drawable.basilicasagradafamiliabarcelona, R.drawable.casabatllobarcelona, R.drawable.catedralsevilla, R.drawable.mercadocentralvalencia,R.drawable.mezquitacordoba, R.drawable.palaciorealmadrid, R.drawable.plazaespanasevilla, R.drawable.puertaalcalamadrid, R.drawable.realalcazarsevilla};
    private HomeScreenActivity homeScreen;

    public SecondFragment(HomeScreenActivity homeScreen) {
        // Required empty public constructor
            this.homeScreen = homeScreen;
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
    public static SecondFragment newInstance(String param1, String param2, HomeScreenActivity homeScreen) {
        SecondFragment fragment = new SecondFragment(homeScreen);
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
        data = homeScreen.getData();
        saveData();
    }

    public void saveData(){

        //Save data in the DB

        Gson gson = new Gson();
        String jsonData = gson.toJson(data);

        int entityId = 2;
        DataEntity dataEntity = new DataEntity(entityId, jsonData);


        AppDatabase appDatabase = AppDatabase.getAppDatabase(getContext().getApplicationContext());
        DataDao dataDao = appDatabase.dataDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dataDao.insertData(dataEntity);
                Log.d("TAG", "DATOS GENERALES GUARDADOS BIEN");
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
        View view = inflater.inflate(R.layout.fragment_second, container, false);


        // Obtiene una referencia al ListView
        list = (ListView) view.findViewById(R.id.IvLista);

        recuperateData();
        adapter = new Adapter(getContext(), data, imgData, list, homeScreen, listFavs);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        return view;
    }



    private void recuperateData() {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(getContext().getApplicationContext()); // O getContext() según el método donde estés
        DataDao dataDao = appDatabase.dataDao();
        FavouritesDao favouritesDao = appDatabase.favouritesDao();

        Integer entityId = 2;

        new Thread(new Runnable() {
            @Override
            public void run() {
                DataEntity dataEntity = dataDao.getDataById(entityId);

                String jsonData = dataEntity.getDataJson();


                Gson gson = new Gson();
                dataList = gson.fromJson(jsonData, String[][].class);


                //Recuperate listFavs
                String userId = homeScreen.getUserid();
                FavouritesEntity favsEntity = favouritesDao.getFavouritesByUserId(userId);

                if (favsEntity==null) {
                    listFavs = new ArrayList<>();
                }
                else {
                    String jsonFavsData = favsEntity.getFavouritePositionsJson();

                    Gson g = new Gson();
                    Type listType = new TypeToken<List<Integer>>() {
                    }.getType();
                    listFavs = g.fromJson(jsonFavsData, listType);
                }
            }
        }).start();

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