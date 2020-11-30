package com.openclassrooms.realestatemanager.service;

import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;

public class RealEstatePhotoService {

    public static ArrayList<RealEstatePhotos> getRealEstatePhotos1() {
        ArrayList<RealEstatePhotos> realEstatePhotos1 = new ArrayList<>();

        realEstatePhotos1.add(new RealEstatePhotos(null, "" +
                "https://i.ibb.co/d21p5mr/Lounge1.jpg", "Lounge 1"));
        realEstatePhotos1.add(new RealEstatePhotos(null, "https://i.ibb.co/dDJgnLj/Lounge2.jpg",
                "Lounge 2"));
        realEstatePhotos1.add(new RealEstatePhotos(null,
                "https://i.ibb.co/ssvkpLq/Kitchen.jpg", "Kitchen"));
        realEstatePhotos1.add(new RealEstatePhotos(null,
                "https://i.ibb.co/Fw7S4vc/bathroom1.jpg", "Bathroom 1"));
        realEstatePhotos1.add(new RealEstatePhotos(null,
                "https://i.ibb.co/yRxrBXc/bathroom2.jpg", "Bathroom 2"));
        realEstatePhotos1.add(new RealEstatePhotos(null,
                "https://i.ibb.co/3Ty6dVf/Bedroom1.jpg", "Bedroom 1"));
        realEstatePhotos1.add(new RealEstatePhotos(null,
                "https://i.ibb.co/M53J6FX/bedroom2.jpg", "Bedroom 2"));

        return realEstatePhotos1;
    }


    public static ArrayList<RealEstatePhotos> getRealEstatePhotos2() {
        ArrayList<RealEstatePhotos> realEstatePhotos2 = new ArrayList<>();

        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/CPKwSKh/Lounge1.jpg", "Lounge 1"));
        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/02SfH9X/Kitchen.jpg", "Kitchen"));
        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/hgzWDGP/bathroom1.jpg", "Bathroom 1"));
        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/SQ7kPx3/bathroom2.jpg", "Bathroom 2"));
        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/YRvChkY/bedroom1.jpg", "Bedroom 1"));
        realEstatePhotos2.add(new RealEstatePhotos(null,
                "https://i.ibb.co/89krJvd/bedroom2.jpg", "Bedroom 2"));

        return realEstatePhotos2;

    }


}
