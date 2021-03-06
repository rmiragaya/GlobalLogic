package com.rodrigo.globallogic.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodrigo.globallogic.Models.Laptop;
import com.rodrigo.globallogic.Repositories.LaptopsRepo;

import java.util.List;


public class MainActivityVM extends ViewModel {

    private MutableLiveData<List<Laptop>> mLaptops;

    public void init(){
        if (mLaptops != null){
            return;
        }
        LaptopsRepo mRepo = LaptopsRepo.getInstance();
        mLaptops = mRepo.getLaptopsList();
    }

    public LiveData<List<Laptop>> getLaptops(){
        return mLaptops;
    }

}
