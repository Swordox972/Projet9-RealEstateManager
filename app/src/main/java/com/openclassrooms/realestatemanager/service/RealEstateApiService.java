package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.List;

public interface RealEstateApiService {

    List<RealEstate> getRealEstates();

    List<RealEstatePhotos> getRealEstatePhotos1();

    List<RealEstatePhotos> getRealEstatePhotos2();
}
