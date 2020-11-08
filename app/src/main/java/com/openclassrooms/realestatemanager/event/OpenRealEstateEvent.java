package com.openclassrooms.realestatemanager.event;

import com.openclassrooms.realestatemanager.model.RealEstate;

public class OpenRealEstateEvent {

    /**
     * RealEstate to open
     */

    public RealEstate mRealEstate;

    /**
     * Constructor
     *
     * @param mRealEstate
     */

    public OpenRealEstateEvent(RealEstate mRealEstate) {
        this.mRealEstate = mRealEstate;
    }
}
