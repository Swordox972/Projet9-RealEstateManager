package com.openclassrooms.realestatemanager.event;

import com.openclassrooms.realestatemanager.model.RealEstate;

public class EditRealEstateEvent {

    /**
     * RealEstate to edit
     */

    public RealEstate mRealEstate;

    /**
     * Constructor
     *
     * @param mRealEstate
     */

    public EditRealEstateEvent(RealEstate mRealEstate) {
        this.mRealEstate = mRealEstate;
    }
}
