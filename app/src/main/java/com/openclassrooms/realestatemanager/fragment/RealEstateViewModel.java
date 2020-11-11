package com.openclassrooms.realestatemanager.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

import java.util.List;


public class RealEstateViewModel extends ViewModel {

    private final RealEstateApiService apiService;

    private final MutableLiveData<List<RealEstate>> _list = new MutableLiveData<>();
    final LiveData<List<RealEstate>> list = _list;

    public RealEstateViewModel() {
        apiService = DI.getRealEstateApiService();
        _list.postValue(apiService.getRealEstates());
    }
}