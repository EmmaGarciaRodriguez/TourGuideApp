package com.example.tourguideapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String selectedLang;
    //Spinner for the lenguage (en - es)

    Spinner spinner;

    public static final String[] languages = {"Select Idiom", "English", "Spanish"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        selectedLang = getSelectedLang(this);

        if (selectedLang.equals("en")) {
            spinner.setSelection(1);
        } else if (selectedLang.equals("es")) {
            spinner.setSelection(2);
        } else {
            spinner.setSelection(0);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newlySelectedLang = parent.getItemAtPosition(position).toString();
                if (!newlySelectedLang.equals(selectedLang)) {
                    selectedLang = newlySelectedLang;
                    setSelectedLang(selectedLang, getApplicationContext());
                    if (selectedLang == "English"){
                        setLocal(MainActivity.this, "en");
                    }else if (selectedLang == "Spanish"){
                        setLocal(MainActivity.this, "es");
                    }
                    recreate(); // Actualiza la vista actual para reflejar el nuevo idioma
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button boton = findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }


    public static void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    // Save in SharedPreferences
    public static void setSelectedLang(String langCode, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedLang", langCode);
        editor.apply();
    }

    // Recuperate from SharedPreferences
    public static String getSelectedLang(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("selectedLang", null);
    }

}