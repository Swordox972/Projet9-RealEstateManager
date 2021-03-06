package com.openclassrooms.realestatemanager.fragment;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.repository.RealEstateDataRepository;

import java.util.List;


public class RealEstateViewModel extends AndroidViewModel {

    //Repository
    private final RealEstateDataRepository mRepository;
    private final LiveData<List<RealEstate>> mRealEstates;

    public RealEstateViewModel(Application application) {
        super(application);
        mRepository = DI.getRepository(application);
        mRealEstates = mRepository.getRealEstates();

    }

    //Data
    @Nullable
    public LiveData<List<RealEstate>> getRealEstates() {
        return mRealEstates;
    }

    public void createRealEstate(RealEstate realEstate) {
        mRepository.createRealEstate(realEstate);
    }

    public void updateRealEstate(RealEstate realEstate) {
        mRepository.updateRealEstate(realEstate);
    }

    public LiveData<List<RealEstate>> getRealEstatesFiltered(SupportSQLiteQuery query) {
        return mRepository.getRealEstatesFiltered(query);
    }

}