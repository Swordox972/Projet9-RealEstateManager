package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.List;

public class DummyRealEstateApiService implements RealEstateApiService {
    List<RealEstate> realEstates = DummyRealEstateApiGenerator.getRealEstates();
    List<RealEstatePhotos> realEstatePhotos1 = DummyRealEstateApiGenerator.getRealEstatePhotos1();
    List<RealEstatePhotos> realEstatePhotos2= DummyRealEstateApiGenerator.getRealEstatePhotos2();

    @Override
    public List<RealEstate> getRealEstates() {
        return realEstates;
    }

    @Override
    public List<RealEstatePhotos> getRealEstatePhotos1() {
        return realEstatePhotos1;
    }

    @Override
    public List<RealEstatePhotos> getRealEstatePhotos2() {
        return realEstatePhotos2;
    }
}
