package com.rodrigo.retrofitmvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rodrigo.retrofitmvvm.Models.Laptop;
import com.rodrigo.retrofitmvvm.R;

public class DetalleLaptop extends AppCompatActivity {
    private static final String TAG = "DetalleLaptop";

    private Laptop laptopSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_laptop);

        Intent intent = getIntent();
        laptopSeleccionada = intent.getParcelableExtra(MainActivity.DETALLE);
        Log.d(TAG, "getTitle: " + laptopSeleccionada.getTitle());
        Log.d(TAG, "getDescription: " + laptopSeleccionada.getDescription());
        Log.d(TAG, "getImageUrl: " + laptopSeleccionada.getImageUrl());
    }
}
