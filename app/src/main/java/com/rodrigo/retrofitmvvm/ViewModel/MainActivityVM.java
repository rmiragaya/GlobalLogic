package com.rodrigo.retrofitmvvm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodrigo.retrofitmvvm.Models.Laptop;
import com.rodrigo.retrofitmvvm.Repositories.LaptopsRepo;

import java.util.List;


public class MainActivityVM extends ViewModel {

    private MutableLiveData<List<Laptop>> mLaptops;
    private LaptopsRepo mRepo;

    public void init(){
        if (mLaptops != null){
            return;
        }
        mRepo = LaptopsRepo.getInstance();
        mLaptops = mRepo.getLaptopsList();

    }

    public LiveData<List<Laptop>> getLaptops(){
        return mLaptops;
    }
}
