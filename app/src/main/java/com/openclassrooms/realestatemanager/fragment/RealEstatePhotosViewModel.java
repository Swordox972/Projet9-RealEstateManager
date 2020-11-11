package com.openclassrooms.realestatemanager.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

import java.util.List;

public class RealEstatePhotosViewModel extends ViewModel {

    private final RealEstateApiService apiService;

    private final MutableLiveData<List<RealEstatePhotos>> _photos = new MutableLiveData<List<RealEstatePhotos>>();
    public LiveData<List<RealEstatePhotos>> photos = _photos;

    public RealEstatePhotosViewModel() {
        apiService = DI.getRealEstateApiService();
        _photos.postValue(apiService.getRealEstatePhotos1());
    }
}