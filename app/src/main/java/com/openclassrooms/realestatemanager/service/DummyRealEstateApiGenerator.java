package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyRealEstateApiGenerator {

    public static List<RealEstate> DUMMY_REALESTATE = Arrays.asList(
            new RealEstate(RealEstate.Type.Loft, "$13,950,000", 423, 8,
                    4,4,
                    RealEstateDescription.returnFirstDescription(),
                    "https://i.ibb.co/WKx9zZj/Loft-Manhattan.jpg", "Manhattan",
                    "41 Great Jones Street Penthouse\n" +
                    "Lafayette\n" +
                    "NoHo\n" +
                    "New York", RealEstate.Status.forSell, "07/11/2020",null,
                    "Jessica"),

            new RealEstate(RealEstate.Type.Penthouse, "$5,400,000", 582,
                    7, 4, 3,
                    RealEstateDescription.returnSecondDescription(),
                    "https://i.ibb.co/9NXstNR/Brooklyn-Penthouse.jpg", "Brooklyn"
            , "The Gretsch Building PH1A\n" +
                    "60 Broadway\n" +
                    "Wythe & Berry\n" +
                    "Williamsburg\n" +
                    "Brooklyn", RealEstate.Status.forSell, "08/11/2020", null,
                    "Bianca")
    );

    public static List<RealEstate> getRealEstates() {
        return new ArrayList<>(DUMMY_REALESTATE);
    }
}
