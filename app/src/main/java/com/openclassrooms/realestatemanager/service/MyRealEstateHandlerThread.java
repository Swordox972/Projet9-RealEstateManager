package com.openclassrooms.realestatemanager.service;

import android.os.Handler;
import android.os.HandlerThread;

import com.openclassrooms.realestatemanager.fragment.RealEstateViewModel;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class MyRealEstateHandlerThread extends HandlerThread {

    // 1 - Constructor
    public MyRealEstateHandlerThread(String name) {
        super(name);
    }

    //2-Public methods that will start the handler
    public void startCreateRealEstateHandler(RealEstate realEstate,
                                             RealEstateViewModel realEstateViewModel) {
        if (!this.isAlive()) this.start();

        Handler handler = new Handler(this.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                //Long work
                realEstateViewModel.createRealEstate(realEstate);
            }
        });

    }

    public void startDeleteRealEstateHandler(long realEstateId,
                                             RealEstateViewModel realEstateViewModel) {

        if (!this.isAlive()) this.start();

        Handler handler = new Handler(this.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                realEstateViewModel.deleteRealEstate(realEstateId);
            }
        });
    }

    public void startUpdateRealEstateHandler(RealEstate realEstate,
                                             RealEstateViewModel realEstateViewModel) {
        if (!this.isAlive()) this.start();

        Handler handler = new Handler(this.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                realEstateViewModel.updateRealEstate(realEstate);
            }
        });
    }

}
