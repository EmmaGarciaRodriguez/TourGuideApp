package com.example.tourguideapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {

    TextView tName;

    FirstFragment firstFragment = new FirstFragment(this);
    SecondFragment secondFragment = new SecondFragment(this);
    ThirdFragment thirdFragment = new ThirdFragment(this);
    String userid;
    String name;
    String password;

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
}