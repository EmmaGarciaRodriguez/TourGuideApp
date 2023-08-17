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
                    {"Basílica de la Sagrada Familia", "Barcelona", "2", "La Basílica de la Sagrada Familia es un icónico templo diseñado por el arquitecto Antoni Gaudí en Barcelona, España. Es una de las obras maestras de la arquitectura modernista y es conocida por su diseño extravagante y las intrincadas fachadas.", "41.4036", "2.1744"},
                    {"Casa Batlló", "Barcelona", "3", " Casa Batlló es otro famoso edificio diseñado por Gaudí en Barcelona. Es conocido por su fachada única y ondulante, con elementos decorativos que lo hacen parecer una obra de arte arquitectónico.", "41.3916", "2.1649"},
                    {"Catedral de Sevilla", "Sevilla", "4", "La Catedral de Sevilla es la catedral gótica más grande del mundo y un importante monumento en la ciudad de Sevilla. Alberga la tumba de Cristóbal Colón y es conocida por su impresionante arquitectura.", "37.3886", "-5.9823"},
                    {"Mercado Central", "Valencia", "7", "El Mercado Central de Valencia es uno de los mercados más grandes de Europa, conocido por su arquitectura modernista y su gran variedad de productos frescos.", "39.4745", "-0.3756"},
                    {"Mezquita de Córdoba", "Córdoba", "8", "La Mezquita-Catedral de Córdoba es un lugar histórico en Córdoba, España. Fue originalmente una mezquita durante la época musulmana y más tarde se convirtió en una catedral católica. Su arquitectura única combina influencias islámicas y cristianas.", "37.8788", "-4.7791"},
                    {"Palacio Real", "Madrid", "9", "El Palacio Real es la residencia oficial de la familia real española en Madrid. Es un impresionante palacio con una gran colección de arte y objetos históricos.", "40.4170", "-3.7144"},
                    {"Plaza de España", "Sevilla", "4", " La Plaza de España es una plaza monumental en Sevilla, conocida por su arquitectura y detalles decorativos. Fue construida como parte de la Exposición Iberoamericana de 1929", "37.3766", " -5.9829"},
                    {"Puerta de Alcalá", "Madrid", "10", "La Puerta de Alcalá es una antigua puerta de la ciudad en Madrid. Es un monumento icónico y un símbolo histórico de la ciudad.", "40.4199", "-3.6885"},
                    {"Real Alcázar", "Sevilla", "6", "El Real Alcázar de Sevilla es un palacio real en Sevilla con una arquitectura impresionante y exquisitos jardines. Es uno de los palacios reales más antiguos en uso continuo en Europa.", "37.3833", "-5.9896"}
            };
            }else{
            data = new String[][]{
                    {"Alhambra of Granada", "Granada", "9", "The Alhambra is a monumental complex situated on an Andalusian palatial city in Granada, Spain. It consists of a set of ancient palaces, gardens, and fortresses originally conceived to accommodate the emir and the court of the Nasrid kingdom, later used as a residence for the kings of Castile and their representatives.", "38.9424300", "-122,42"},
                    {"Basilica of the Sagrada Familia", "Barcelona", "2", "The Basilica of the Sagrada Familia is an iconic temple designed by architect Antoni Gaudí in Barcelona, Spain. It's one of the masterpieces of modernist architecture and is known for its extravagant design and intricate facades.", "41.4036", "2.1744"},
                    {"Casa Batlló", "Barcelona", "3", "Casa Batlló is another famous building designed by Gaudí in Barcelona. It's known for its unique and undulating facade, with decorative elements that make it resemble a work of architectural art.", "41.3916", "2.1649"},
                    {"Seville Cathedral", "Seville", "4", "Seville Cathedral is the largest Gothic cathedral in the world and a significant monument in the city of Seville. It houses the tomb of Christopher Columbus and is renowned for its impressive architecture.", "37.3886", "-5.9823"},
                    {"Central Market", "Valencia", "7", "The Central Market of Valencia is one of the largest markets in Europe, known for its modernist architecture and wide variety of fresh products.", "39.4745", "-0.3756"},
                    {"Córdoba Mosque", "Córdoba", "8", "The Mosque-Cathedral of Córdoba is a historic site in Córdoba, Spain. It was originally a mosque during the Muslim era and later converted into a Catholic cathedral. Its unique architecture blends Islamic and Christian influences.", "37.8788", "-4.7791"},
                    {"Royal Palace", "Madrid", "9", "The Royal Palace is the official residence of the Spanish royal family in Madrid. It's an impressive palace with a vast collection of art and historical objects.", "40.4170", "-3.7144"},
                    {"Plaza de España", "Seville", "4", "Plaza de España is a monumental square in Seville, known for its architecture and decorative details. It was built as part of the Ibero-American Exposition of 1929.", "37.3766", "-5.9829"},
                    {"Puerta de Alcalá", "Madrid", "10", "Puerta de Alcalá is an ancient city gate in Madrid. It's an iconic monument and a historical symbol of the city.", "40.4199", "-3.6885"},
                    {"Royal Alcázar", "Seville", "6", "The Royal Alcázar of Seville is a royal palace in Seville with impressive architecture and exquisite gardens. It's one of the oldest continuously used royal palaces in Europe.", "37.3833", "-5.9896"}
            };

            }
        return data;
        };


}