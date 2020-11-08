package com.openclassrooms.realestatemanager.di;

import com.openclassrooms.realestatemanager.service.DummyRealEstateApiService;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

public class DI {
    private static RealEstateApiService service = new DummyRealEstateApiService();

    /**
     * Get an instance on @{@link RealEstateApiService}
     *
     * @return
     */
    public static RealEstateApiService getRealEstateApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link RealEstateApiService}. Useful for tests, so we ensure
     * the context is clean.
     *
     * @return
     */
    public static RealEstateApiService getNewInstanceApiService() {
        return new DummyRealEstateApiService();
    }
}
