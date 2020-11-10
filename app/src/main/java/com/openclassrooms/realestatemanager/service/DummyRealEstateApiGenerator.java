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
                    "https://i.ibb.co/WKx9zZj/Loft-Manhattan.jpg", null,
                    "Manhattan",
                    "41 Great Jones Street Penthouse\n" +
                    "Lafayette\n" +
                    "NoHo\n" +
                    "New York", RealEstate.Status.forSell, "07/11/2020",null,
                    RealEstate.Agent.jessicaCCampbell,
                    "https://i.ibb.co/0MZZf43/Jessica-CCampbell.jpg"),

            new RealEstate(RealEstate.Type.Penthouse, "$5,400,000", 582,
                    7, 4, 3,
                    RealEstateDescription.returnSecondDescription(),
                    "https://i.ibb.co/9NXstNR/Brooklyn-Penthouse.jpg", null,
                    "Brooklyn"
            , "The Gretsch Building PH1A\n" +
                    "60 Broadway\n" +
                    "Wythe & Berry\n" +
                    "Williamsburg\n" +
                    "Brooklyn", RealEstate.Status.forSell, "08/11/2020", null,
                    RealEstate.Agent.christianHaag,
                    "https://i.ibb.co/Y71g9LB/Christian-Haag.jpg")
    );

    public static List<RealEstate> getRealEstates() {
        return new ArrayList<>(DUMMY_REAL_ESTATE);
    }

    public static List<RealEstatePhotos> DUMMY_REAL_ESTATE_PHOTOS1= Arrays.asList(
            new RealEstatePhotos("https://i.ibb.co/d21p5mr/Lounge1.jpg",
                    "Lounge 1"),
            new RealEstatePhotos("https://i.ibb.co/dDJgnLj/Lounge2.jpg",
                    "Lounge 2"),
            new RealEstatePhotos("https://i.ibb.co/ssvkpLq/Kitchen.jpg",
                    "Kitchen"),
            new RealEstatePhotos("https://i.ibb.co/Fw7S4vc/bathroom1.jpg",
                    "Bathroom 1"),
            new RealEstatePhotos("https://i.ibb.co/yRxrBXc/bathroom2.jpg",
                    "Bathroom 2"),
            new RealEstatePhotos("https://i.ibb.co/3Ty6dVf/Bedroom1.jpg",
                    "Bedroom 1"),
            new RealEstatePhotos("https://i.ibb.co/M53J6FX/bedroom2.jpg",
                    "Bedroom 2")
    );

    public static List<RealEstatePhotos> getRealEstatePhotos1() {
        return new ArrayList<>(DUMMY_REAL_ESTATE_PHOTOS1);
    }

    public static List<RealEstatePhotos> DUMMY_REAL_ESTATE_PHOTOS2 = Arrays.asList(
            new RealEstatePhotos("https://i.ibb.co/CPKwSKh/Lounge1.jpg",
                    "Lounge 1"),
            new RealEstatePhotos("https://i.ibb.co/02SfH9X/Kitchen.jpg",
                    "Kitchen"),
            new RealEstatePhotos("https://i.ibb.co/hgzWDGP/bathroom1.jpg",
                    "Bathroom 1"),
            new RealEstatePhotos("https://i.ibb.co/SQ7kPx3/bathroom2.jpg",
                    "Bathroom 2"),
            new RealEstatePhotos("https://i.ibb.co/YRvChkY/bedroom1.jpg",
                    "Bedroom 1"),
            new RealEstatePhotos("https://i.ibb.co/89krJvd/bedroom2.jpg",
                    "Bedroom 2")
    );

    public static List<RealEstatePhotos> getRealEstatePhotos2() {
        return new ArrayList<>(DUMMY_REAL_ESTATE_PHOTOS2);
    }
}
