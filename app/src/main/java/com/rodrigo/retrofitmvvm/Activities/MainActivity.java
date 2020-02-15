package com.rodrigo.retrofitmvvm.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rodrigo.retrofitmvvm.Adapters.RecyclerAdapter;
import com.rodrigo.retrofitmvvm.Models.Laptop;
import com.rodrigo.retrofitmvvm.R;
import com.rodrigo.retrofitmvvm.ViewModel.MainActivityVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener {
    private static final String TAG = "MainActivity";
    public static final String DETALLE = "detalle";

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Laptop> laptopArrayList = new ArrayList<>();

    /* LiveData */
    private MainActivityVM mainActivityVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityVM = ViewModelProviders.of(this).get(MainActivityVM.class);
        mainActivityVM.init();
        mainActivityVM.getLaptops().observe(this, new Observer<List<Laptop>>() {
            @Override
            public void onChanged(List<Laptop> laptops) {
                laptopArrayList = new ArrayList<>(laptops);
                //todo: adapter.notifyDataSetChanged();
//                adapter.notifyDataSetChanged();
                adapter.updateData(laptopArrayList);
            }
        });

        initRecycler();
    }

    private void initRecycler(){
        adapter = new RecyclerAdapter(this, laptopArrayList);
        recyclerView = findViewById(R.id.recy_id);
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
