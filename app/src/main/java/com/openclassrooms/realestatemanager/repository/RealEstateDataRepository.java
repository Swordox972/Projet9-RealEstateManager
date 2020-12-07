package com.openclassrooms.realestatemanager.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.SearchRealEstateProviderActivity;
import com.openclassrooms.realestatemanager.database.MyRealEstateDatabase;
import com.openclassrooms.realestatemanager.database.dao.RealEstateDao;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.query.MyRealEstateQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealEstateDataRepository {

    private final RealEstateDao mRealEstateDao;
    private LiveData<List<RealEstate>> mRealEstates;
    private MutableLiveData<SearchRealEstateProviderActivity.SearchCritaries> filter =
            new MutableLiveData<>(null);

    public RealEstateDataRepository(Application application) {
        MyRealEstateDatabase database = MyRealEstateDatabase.getDatabase(application);
        mRealEstateDao = database.realEstateDao();
        mRealEstates = Transformations.switchMap(filter, filter -> {
            if (filter == null) {
               return mRealEstateDao.getRealEstates();
            } else {
             return mRealEstateDao.getRealEstatesFiltered(MyRealEstateQuery.generateQuery(
                        filter.getMinimumPrice(),
                        filter.getMaximumPrice(),
                        filter.getMinimumSurface(),
                        filter.getMaximumSurface(),
                        filter.getFirstLocation(),
                        filter.getNumberOfPhotos(),
                        filter.getPointOfInterest(),
                        filter.getEntryDateInDate(),
                        filter.getSaleDateInDate()));
            }

        });
    }

    public void resetFilter(){
        filter.setValue(null);
    }

    public void setFilter(SearchRealEstateProviderActivity.SearchCritaries filter){
        this.filter.setValue(filter);
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

    //Filter
    public LiveData<List<RealEstate>> getRealEstatesFiltered(SupportSQLiteQuery query) {
        return mRealEstateDao.getRealEstatesFiltered(query);
    }
}
