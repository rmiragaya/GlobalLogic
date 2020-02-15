package com.rodrigo.globallogic.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.Retrofit.GlobalApi;
import com.rodrigo.globallogic.Retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Singleton
 */
public class LaptopsRepo {
    private static final String TAG = "LaptopsRepo";

    private static LaptopsRepo instance;
    private GlobalApi apiRequest;

    public static LaptopsRepo getInstance(){
        if (instance == null ){
            instance = new LaptopsRepo();
        }
        return instance;
    }

    public LaptopsRepo() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(GlobalApi.class);
    }


    public MutableLiveData<List<Laptop>> getLaptopsList(){
        final MutableLiveData<List<Laptop>> data = new MutableLiveData<>();
//        setLaptopsList();
        apiRequest.getLaptops().enqueue(new Callback<List<Laptop>>() {
            @Override
            public void onResponse(Call<List<Laptop>> call, Response<List<Laptop>> response) {
                Log.d(TAG, "onResponse: call OK");
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Laptop>> call, Throwable t) {
                //todo cartel de error
                data.setValue(null);
                Log.d(TAG, "onFailure: call");
                Log.d(TAG, "onFailure: " + t.fillInStackTrace());
            }
        });

        return data;
    }


}
