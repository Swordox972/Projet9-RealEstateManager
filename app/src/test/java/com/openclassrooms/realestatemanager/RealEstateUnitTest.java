package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;
import com.openclassrooms.realestatemanager.service.LocationUtil;
import com.openclassrooms.realestatemanager.service.RealEstateDescription;
import com.openclassrooms.realestatemanager.service.RealEstatePhotoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RealEstateUnitTest {

    @Before
    public void setUp() {

    }

    @Test
    public void assertGetRealEstateDescriptionWorks() {
        String description1 = "This exquisite Penthouse melds a bespoke collection of striking finishes with " +
                "soaring ceiling heights, floor to ceiling windows, grand room dimensions " +
                "including four bedrooms, four bathrooms and one powder room and is the epitome " +
                "of contemporary chic boutique living. With 12-21 foot soaring ceilings " +
                "throughout, this 4,550 square foot PH duplex with 1700sf of private outdoor " +
                "living includes a blend or 10-inch wide European oak and heated black granite " +
                "slab floors, original exposed brick detailing, an architecturally significant " +
                "iron street-facing balcony, an in-home washer dryer and a private voluminous " +
                "rear terrace off of the grand kitchen with a Weber gas grill, equally as " +
                "spectacular terrace with wall of windows and French doors off of the living " +
                "room plus a private roof deck with luxurious seating, iconic views and private " +
                "spa.";

        String description2 = "This 2,462sf residence has been meticulously renovated and features a jaw-dropping " +
                "3,800sf+ wrap-around terrace with endless NYC Skyline, East River and Williamsburg " +
                "Bridge views from every room. With 12' ceilings, this striking loft offers 3 " +
                "bedrooms, 3.5 baths, a home office, laundry room and incredible storage throughout."
                ;

        assertEquals(description1, RealEstateDescription.returnFirstDescription());
        assertEquals(description2, RealEstateDescription.returnSecondDescription());
    }


}
