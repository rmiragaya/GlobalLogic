package com.rodrigo.globallogic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.rodrigo.globallogic.Adapters.RecyclerAdapter;
import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.R;
import com.rodrigo.globallogic.Tools.Utils;
import com.rodrigo.globallogic.ViewModel.MainActivityVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    public static final String DETALLE = "detalle";

    private RecyclerAdapter adapter;
    private ArrayList<Laptop> laptopArrayList = new ArrayList<>();

    /* Lottie */
    private LottieAnimationView loading, noEncontrado;
    private ConstraintLayout loadingBkg, noEncontradoBkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.checkTls(this);

        loading = findViewById(R.id.loading);
        loadingBkg = findViewById(R.id.loadingBkg);
        noEncontrado = findViewById(R.id.noEncontrado);
        noEncontradoBkg = findViewById(R.id.no_encontrado_Bkg);

        initRecycler();

        getLaptops();
    }

    private void getLaptops(){
        /* live data */
        MainActivityVM mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);
        mainActivityVM.init();
        mainActivityVM.getLaptops().observe(this, new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptops) {

                if (laptops == null){
                    Log.d(TAG, "sin conexión");
                    showToast();
                    hideLoadingAnimation();
                    return;
                }

                if(!laptops.isEmpty()){
                   hideLoadingAnimation();
                    laptopArrayList = new ArrayList<>(laptops);
                    adapter.updateData(laptopArrayList);
                }

            }
        });
    }

    private void initRecycler(){
        adapter = new RecyclerAdapter(laptopArrayList);
        RecyclerView recyclerView = findViewById(R.id.recy_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    /* callback of interface from RecyclerV */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, laptopArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent detalleLaptopIntent = new Intent(this, DetalleLaptop.class);
        Laptop laptopSelected = laptopArrayList.get(position);
        Log.d(TAG, "laptopSelected= " + laptopSelected.toString());
        detalleLaptopIntent.putExtra(DETALLE,laptopSelected);
        startActivity(detalleLaptopIntent);
    }

    private void showToast(){
        noEncontrado.setVisibility(View.VISIBLE);
        noEncontradoBkg.setVisibility(View.VISIBLE);
        Toast toast = Toast.makeText(MainActivity.this, "Sin Conexión\nInténtelo más tarde", Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    private void hideLoadingAnimation(){
        loading.setVisibility(View.GONE);
        loadingBkg.setVisibility(View.GONE);
    }
}
