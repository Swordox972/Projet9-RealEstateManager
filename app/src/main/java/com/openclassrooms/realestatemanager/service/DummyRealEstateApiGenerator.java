package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyRealEstateApiGenerator {

    public static List<RealEstate> DUMMY_REAL_ESTATE = Arrays.asList(
            new RealEstate(RealEstate.Type.Loft, "$13,950,000", 423, 8,
                    4,4,
                    RealEstateDescription.returnFirstDescription(),
                    "https://i.ibb.co/WKx9zZj/Loft-Manhattan.jpg",
                    RealEstatePhotoService.getRealEstatePhotos1(),
                    "Manhattan",
                    "41 Great Jones Street Penthouse\n" +
                    "Lafayette\n" +
                    "NoHo\n" +
                    "New York",
                    40.726649, -73.992833,
                    RealEstate.Status.forSell, "07/11/2020",null,
                    RealEstate.Agent.jessicaCCampbell,
                    "https://i.ibb.co/0MZZf43/Jessica-CCampbell.jpg"),

            new RealEstate(RealEstate.Type.Penthouse, "$5,400,000", 582,
                    7, 4, 3,
                    RealEstateDescription.returnSecondDescription(),
                    "https://i.ibb.co/9NXstNR/Brooklyn-Penthouse.jpg",
                    RealEstatePhotoService.getRealEstatePhotos2(),
                    "Brooklyn"
            , "The Gretsch Building PH1A\n" +
                    "60 Broadway\n" +
                    "Wythe & Berry\n" +
                    "Williamsburg\n" +
                    "Brooklyn",40.710313 , -73.966295,
                    RealEstate.Status.forSell, "08/11/2020", null,
                    RealEstate.Agent.christianHaag,
                    "https://i.ibb.co/Y71g9LB/Christian-Haag.jpg")
    );

    public static List<RealEstate> getRealEstates() {
        return new ArrayList<>(DUMMY_REAL_ESTATE);
    }



}
