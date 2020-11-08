package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

public class DummyRealEstateApiService implements RealEstateApiService {
    List<RealEstate> realEstates = DummyRealEstateApiGenerator.getRealEstates();

    @Override
    public List<RealEstate> getRealEstates() {
        return realEstates;
    }
}
