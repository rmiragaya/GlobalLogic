package com.rodrigo.globallogic.Retrofit;

import com.rodrigo.globallogic.Models.Laptop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GlobalApi {
    @GET("list")
    Call<List<Laptop>> getLaptops();
}
