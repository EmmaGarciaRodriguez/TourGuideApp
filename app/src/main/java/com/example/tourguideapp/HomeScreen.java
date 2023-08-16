package com.example.tourguideapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {

    TextView tName;
    String[][] data;

    FirstFragment firstFragment = new FirstFragment(this);
    SecondFragment secondFragment = new SecondFragment(this);
    ThirdFragment thirdFragment = new ThirdFragment(this);
    String userid;
    String name;
    String password;
    private Context context;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //tName = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        String password = getIntent().getStringExtra("password");
        String userId = getIntent().getStringExtra("userId");

        //guarda el userid del usuario
        setUserid(getIntent().getStringExtra("userId"));
        setName(getIntent().getStringExtra("name"));
        setPassword(getIntent().getStringExtra("password"));


        Bundle bundle = new Bundle();
        bundle.putString("name", name); // Agrega los datos que deseas pasar al Fragment
        bundle.putString("password", password); // Agrega los datos que deseas pasar al Fragment
        bundle.putString("userId", userId); // Agrega los datos que deseas pasar al Fragment


        firstFragment.setArguments(bundle);



        // Luego, realiza la transacción del Fragment

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        loadFragment(secondFragment);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;

                case R.id.secondFragment:

                    loadFragment(secondFragment);
                    return true;

                case R.id.thirdFragment:
                    //secondFragment.getAdapter().saveinfoDB();
                    loadFragment(thirdFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    public String[][] getData(){
        //MainActivity instancia = new MainActivity(); // Reemplaza con el nombre de la clase real
        String selectedLang = MainActivity.getSelectedLang(this);
        if (selectedLang.equals("Spanish")){
            data = new String[][]{
                    {"Alhambra de Granada", "Granada", "9", "La Alhambra es un complejo monumental sobre una ciudad palatina andalusí situada en Granada, España. Consiste en un conjunto de antiguos palacios, jardines y fortalezas inicialmente concebido para alojar al emir y la corte del reino Nazarí, más tarde como residencia de los reyes de Castilla y de sus representantes.", "38.9424300", "-122,42"},
                    {"Basílica de la Sagrada Familia", "Barcelona", "2"},
                    {"Casa Batlló", "Barcelona", "3"},
                    {"Catedral de Sevilla", "Sevilla", "4"},
                    {"Mercado Central", "Valencia", "7"},
                    {"Mezquita de Córdoba", "Córdoba", "8"},
                    {"Palacio Real", "Madrid", "9"},
                    {"Plaza de España", "Sevilla", "4"},
                    {"Puerta de Alcalá", "Madrid", "10"},
                    {"Real Alcázar", "Sevilla", "6"}
            };
            }else{
            data = new String[][]{
                    {"Alhambra de Granada", "Granada", "9", "The Alhambra is a monumental complex on an Andalusian palatine city located in Granada, Spain. It consists of a set of old palaces, gardens and fortresses initially conceived to house the emir and the court of the Nasrid kingdom, later as the residence of the kings of Castile and their representatives.", "37.7749", "-122,42"},
                    {"Basílica de la Sagrada Familia", "Barcelona", "2"},
                    {"Casa Batlló", "Barcelona", "3"},
                    {"Catedral de Sevilla", "Sevilla", "4"},
                    {"Mercado Central", "Valencia", "7"},
                    {"Mezquita de Córdoba", "Córdoba", "8"},
                    {"Palacio Real", "Madrid", "9"},
                    {"Plaza de España", "Sevilla", "4"},
                    {"Puerta de Alcalá", "Madrid", "10"},
                    {"Real Alcázar", "Sevilla", "6"}
            };

            }
        return data;
        };


}