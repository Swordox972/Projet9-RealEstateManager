package com.openclassrooms.realestatemanager.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.database.MyRealEstateDatabase;
import com.openclassrooms.realestatemanager.database.dao.RealEstateDao;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

public class RealEstateDataRepository {

    private final RealEstateDao mRealEstateDao;
    private LiveData<List<RealEstate>> mRealEstates;

    public RealEstateDataRepository(Application application) {
        MyRealEstateDatabase database = MyRealEstateDatabase.getDatabase(application);
        mRealEstateDao = database.realEstateDao();
        mRealEstates = mRealEstateDao.getRealEstates();
    }

    // Create
    public void createRealEstate(RealEstate realEstate) {
        mRealEstateDao.insertRealEstate(realEstate);
    }

    //Read
    public LiveData<RealEstate> getRealEstate(long realEstateId) {
        return
                mRealEstateDao.getRealEstate(realEstateId);
    }

    //Real all
    public LiveData<List<RealEstate>> getRealEstates() {
        return mRealEstates;
    }

    //Update
    public void updateRealEstate(RealEstate realEstate) {
        mRealEstateDao.updateRealEstate(realEstate);
    }

    //Delete
    public void deleteRealEstate(long realEstateId) {
        mRealEstateDao.deleteRealEstate(realEstateId);
    }


}
