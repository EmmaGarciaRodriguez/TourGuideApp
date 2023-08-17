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
                    {"Alhambra de Granada", "Granada", "9", "La Alhambra es un complejo monumental sobre una ciudad palatina andalusí situada en Granada, España. Consiste en un conjunto de antiguos palacios, jardines y fortalezas inicialmente concebido para alojar al emir y la corte del reino Nazarí, más tarde como residencia de los reyes de Castilla y de sus representantes.", "https://i0.wp.com/passporterapp.com/es/blog/wp-content/uploads/2020/12/que-ver-en-alhambra.jpg?fit=1440%2C1080&ssl=1"},
                    {"Basílica de la Sagrada Familia", "Barcelona", "2", "La Basílica de la Sagrada Familia es un icónico templo diseñado por el arquitecto Antoni Gaudí en Barcelona, España. Es una de las obras maestras de la arquitectura modernista y es conocida por su diseño extravagante y las intrincadas fachadas.", "https://images.adsttc.com/media/images/5ff4/88a7/63c0/17cd/f900/0527/medium_jpg/shutterstock_397537417.jpg?1609861261"},
                    {"Casa Batlló", "Barcelona", "3", " Casa Batlló es otro famoso edificio diseñado por Gaudí en Barcelona. Es conocido por su fachada única y ondulante, con elementos decorativos que lo hacen parecer una obra de arte arquitectónico.", "https://cdn.tiqets.com/wordpress/blog/wp-content/uploads/2022/08/11191347/CB3.jpeg"},
                    {"Catedral de Sevilla", "Sevilla", "4", "La Catedral de Sevilla es la catedral gótica más grande del mundo y un importante monumento en la ciudad de Sevilla. Alberga la tumba de Cristóbal Colón y es conocida por su impresionante arquitectura.", "https://www.adrianohotel.com/img/noticias/la-catedral-de-sevilla.jpg"},
                    {"Mercado Central", "Valencia", "7", "El Mercado Central de Valencia es uno de los mercados más grandes de Europa, conocido por su arquitectura modernista y su gran variedad de productos frescos.", "https://guias-viajar.com/wp-content/uploads/2016/09/valencia-mercado-central-12.jpg"},
                    {"Mezquita de Córdoba", "Córdoba", "8", "La Mezquita-Catedral de Córdoba es un lugar histórico en Córdoba, España. Fue originalmente una mezquita durante la época musulmana y más tarde se convirtió en una catedral católica. Su arquitectura única combina influencias islámicas y cristianas.", "https://www.laguiago.com/wp-content/uploads/2022/04/Mezquita-de-Cordoba.jpg"},
                    {"Palacio Real", "Madrid", "9", "El Palacio Real es la residencia oficial de la familia real española en Madrid. Es un impresionante palacio con una gran colección de arte y objetos históricos.", "https://images.musement.com/cover/0003/11/royal-palace-of-madrid_header-210276.jpeg?w=1200&h=630&q=95&fit=crop"},
                    {"Plaza de España", "Sevilla", "4", " La Plaza de España es una plaza monumental en Sevilla, conocida por su arquitectura y detalles decorativos. Fue construida como parte de la Exposición Iberoamericana de 1929", "https://s2.abcstatics.com/abc/sevilla/multimedia/sevilla/2022/10/26/plaza-espana-sevilla-RLxcQ4Xzw87GWHRDrlZAmeK-1200x630@abc.jpg"},
                    {"Puerta de Alcalá", "Madrid", "10", "La Puerta de Alcalá es una antigua puerta de la ciudad en Madrid. Es un monumento icónico y un símbolo histórico de la ciudad.", "https://www.lugaresquevisitar.com/wp-content/uploads/Madrid-Puerta-Alcal%C3%A1.jpg"},
                    {"Real Alcázar", "Sevilla", "6", "El Real Alcázar de Sevilla es un palacio real en Sevilla con una arquitectura impresionante y exquisitos jardines. Es uno de los palacios reales más antiguos en uso continuo en Europa.", "https://www.dosde.com/discover/wp-content/uploads/2017/02/patio-de-doncellas-alcazar-de-sevilla-dosde-publishing.jpg"}
            };
            }else{
            data = new String[][]{
                    {"Alhambra of Granada", "Granada", "9", "The Alhambra is a monumental complex situated on an Andalusian palatial city in Granada, Spain. It consists of a set of ancient palaces, gardens, and fortresses originally conceived to accommodate the emir and the court of the Nasrid kingdom, later used as a residence for the kings of Castile and their representatives.", "https://i0.wp.com/passporterapp.com/es/blog/wp-content/uploads/2020/12/que-ver-en-alhambra.jpg?fit=1440%2C1080&ssl=1"},
                    {"Basilica of the Sagrada Familia", "Barcelona", "2", "The Basilica of the Sagrada Familia is an iconic temple designed by architect Antoni Gaudí in Barcelona, Spain. It's one of the masterpieces of modernist architecture and is known for its extravagant design and intricate facades.", "https://images.adsttc.com/media/images/5ff4/88a7/63c0/17cd/f900/0527/medium_jpg/shutterstock_397537417.jpg?1609861261"},
                    {"Casa Batlló", "Barcelona", "3", "Casa Batlló is another famous building designed by Gaudí in Barcelona. It's known for its unique and undulating facade, with decorative elements that make it resemble a work of architectural art.", "https://cdn.tiqets.com/wordpress/blog/wp-content/uploads/2022/08/11191347/CB3.jpeg"},
                    {"Seville Cathedral", "Seville", "4", "Seville Cathedral is the largest Gothic cathedral in the world and a significant monument in the city of Seville. It houses the tomb of Christopher Columbus and is renowned for its impressive architecture.", "https://www.adrianohotel.com/img/noticias/la-catedral-de-sevilla.jpg"},
                    {"Central Market", "Valencia", "7", "The Central Market of Valencia is one of the largest markets in Europe, known for its modernist architecture and wide variety of fresh products.", "https://guias-viajar.com/wp-content/uploads/2016/09/valencia-mercado-central-12.jpg"},
                    {"Córdoba Mosque", "Córdoba", "8", "The Mosque-Cathedral of Córdoba is a historic site in Córdoba, Spain. It was originally a mosque during the Muslim era and later converted into a Catholic cathedral. Its unique architecture blends Islamic and Christian influences.", "https://www.laguiago.com/wp-content/uploads/2022/04/Mezquita-de-Cordoba.jpg"},
                    {"Royal Palace", "Madrid", "9", "The Royal Palace is the official residence of the Spanish royal family in Madrid. It's an impressive palace with a vast collection of art and historical objects.", "https://images.musement.com/cover/0003/11/royal-palace-of-madrid_header-210276.jpeg?w=1200&h=630&q=95&fit=crop"},
                    {"Plaza de España", "Seville", "4", "Plaza de España is a monumental square in Seville, known for its architecture and decorative details. It was built as part of the Ibero-American Exposition of 1929.", "https://s2.abcstatics.com/abc/sevilla/multimedia/sevilla/2022/10/26/plaza-espana-sevilla-RLxcQ4Xzw87GWHRDrlZAmeK-1200x630@abc.jpg"},
                    {"Puerta de Alcalá", "Madrid", "10", "Puerta de Alcalá is an ancient city gate in Madrid. It's an iconic monument and a historical symbol of the city.", "https://www.lugaresquevisitar.com/wp-content/uploads/Madrid-Puerta-Alcal%C3%A1.jpg"},
                    {"Royal Alcázar", "Seville", "6", "The Royal Alcázar of Seville is a royal palace in Seville with impressive architecture and exquisite gardens. It's one of the oldest continuously used royal palaces in Europe.", "https://www.dosde.com/discover/wp-content/uploads/2017/02/patio-de-doncellas-alcazar-de-sevilla-dosde-publishing.jpg"}
            };

            }
        return data;
        };


}