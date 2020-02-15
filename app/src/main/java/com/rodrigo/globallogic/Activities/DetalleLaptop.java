package com.rodrigo.globallogic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.R;
import com.squareup.picasso.Picasso;

public class DetalleLaptop extends AppCompatActivity {
    private static final String TAG = "DetalleLaptop";

    private Laptop  laptopSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_laptop);

        Intent intent = getIntent();
        laptopSeleccionada = intent.getParcelableExtra(MainActivity.DETALLE);
        Log.d(TAG, "getTitle: " + laptopSeleccionada.getTitle());
        Log.d(TAG, "getDescription: " + laptopSeleccionada.getDescription());
        Log.d(TAG, "getImageUrl: " + laptopSeleccionada.getImageUrl());

        initViews();
    }

    private void initViews(){
        TextView titulo = findViewById(R.id.tituloDetalle);
        TextView descripcion = findViewById(R.id.descripcionDetalle);
        ImageView imageDetalle = findViewById(R.id.imagenDetalle);

        titulo.setText(laptopSeleccionada.getTitle());
        descripcion.setText(laptopSeleccionada.getDescription());

        Picasso.get().load(laptopSeleccionada.getImageUrl()).fit().centerInside().placeholder(R.drawable.globallogic_logo_chico).error(R.drawable.globallogic_logo_chico).into(imageDetalle);
    }
}
