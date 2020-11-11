package com.openclassrooms.realestatemanager.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

import java.util.ArrayList;
import java.util.List;

public class RealEstatePhotosViewModel extends ViewModel {

    private final RealEstateApiService apiService;

    private final MutableLiveData<ArrayList<RealEstatePhotos>> _photos =
            new MutableLiveData<ArrayList<RealEstatePhotos>>();
    public LiveData<ArrayList<RealEstatePhotos>> getPhotos() {
        return _photos;
    }

    public RealEstatePhotosViewModel() {
        apiService = DI.getRealEstateApiService();
        
    }
}