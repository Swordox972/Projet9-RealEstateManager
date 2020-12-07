package com.openclassrooms.realestatemanager.model;

import java.io.Serializable;
import java.util.ArrayList;

public class RealEstateList implements Serializable {

    private ArrayList<RealEstate> realEstateList;

    public RealEstateList(ArrayList<RealEstate> realEstateList) {
        this.realEstateList = realEstateList;
    }

    public ArrayList<RealEstate> getRealEstateList() {
        return realEstateList;
    }

    public void setRealEstateList(ArrayList<RealEstate> realEstateList) {
        this.realEstateList = realEstateList;
    }
}
