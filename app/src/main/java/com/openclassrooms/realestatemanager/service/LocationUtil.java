package com.openclassrooms.realestatemanager.service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.io.IOException;
import java.util.List;

public class LocationUtil {


    public static void getLocationFromAddress(Context context, RealEstate realEstate, String strAddress) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> address;

        try {
            //Get latLng from String
            address = geocoder.getFromLocationName(strAddress, 5);

            if (address != null) {

                // Take first possibility from the all possibilities.
                try {
                    Address location = address.get(0);
                    realEstate.setLatitude(location.getLatitude());
                    realEstate.setLongitude(location.getLongitude());
                } catch (IndexOutOfBoundsException e) {

                }

            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
