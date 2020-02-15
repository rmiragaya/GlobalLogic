package com.rodrigo.globallogic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.rodrigo.globallogic.Adapters.RecyclerAdapter;
import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.R;
import com.rodrigo.globallogic.ViewModel.MainActivityVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    public static final String DETALLE = "detalle";

    private RecyclerAdapter adapter;
    private ArrayList<Laptop> laptopArrayList = new ArrayList<>();

    /* Lottie */
    private LottieAnimationView noEncontrado;
    private ConstraintLayout loadingBkg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noEncontrado = findViewById(R.id.no_encontrado_lottie);
        loadingBkg = findViewById(R.id.loadingBkg);

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
                if (laptops != null){
                    noEncontrado.setVisibility(View.GONE);
                    loadingBkg.setVisibility(View.GONE);
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
}
