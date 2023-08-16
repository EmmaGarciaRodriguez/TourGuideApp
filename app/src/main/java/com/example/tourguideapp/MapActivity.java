package com.example.tourguideapp;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MapActivity extends AppCompatActivity {

    private WebView webView;
    String longitude;
    String latitude;



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("file:///android_asset/leaflet_map.html");
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        double lat = Double.parseDouble(getIntent().getStringExtra("latitude"));
        double longit = Double.parseDouble(getIntent().getStringExtra("longitude"));

        setContentView(R.layout.map);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.setWebChromeClient(new WebChromeClient());


        // Llama a la función JavaScript con las coordenadas
        webView.evaluateJavascript("initMap(" + lat + "," + longit + ");", null);

        webView.loadUrl("file:///android_asset/leaflet_map.html");
        }
    }

