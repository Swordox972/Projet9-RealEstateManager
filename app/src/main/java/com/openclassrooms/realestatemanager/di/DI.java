package com.openclassrooms.realestatemanager.di;

import android.app.Application;

import com.openclassrooms.realestatemanager.repository.RealEstateDataRepository;

public class DI {
    private static RealEstateDataRepository repository;

    public static RealEstateDataRepository getRepository(Application application) {
        if (repository == null)
            repository = new RealEstateDataRepository(application);

        return repository;
    }
}
