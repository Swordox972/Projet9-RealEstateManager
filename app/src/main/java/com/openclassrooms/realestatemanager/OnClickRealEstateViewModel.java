package com.openclassrooms.realestatemanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class OnClickRealEstateViewModel extends ViewModel {

    public static class ViewState {
        final String imageUrl;
        final String description;
        final String agentName;
        final String agentImage;
        final String status;
        final int statusColor;
        final String dateOfSale;
        final String surface;
        final String numberOfRooms;
        final String numberOfBathrooms;
        final String numberOfBedrooms;
        final String secondLocation;
        final String price;
        final String entryDate;

        public ViewState(String imageUrl, String description, String agentName, String agentImage,
                         String status, int statusColor,
                         String dateOfSale, String surface,
                         String numberOfRooms, String numberOfBathrooms, String numberOfBedrooms,
                         String secondLocation, String price, String entryDate) {
            this.imageUrl = imageUrl;
            this.description = description;
            this.agentName = agentName;
            this.agentImage = agentImage;
            this.status = status;
            this.statusColor = statusColor;
            this.dateOfSale = dateOfSale;
            this.surface = surface;
            this.numberOfRooms = numberOfRooms;
            this.numberOfBathrooms = numberOfBathrooms;
            this.numberOfBedrooms = numberOfBedrooms;
            this.secondLocation = secondLocation;
            this.price = price;
            this.entryDate = entryDate;
        }
    }

    private final MutableLiveData<ViewState> _viewState = new MutableLiveData<>();
    final LiveData<ViewState> viewState = _viewState;

    private RealEstate mRealEstate;

    public void displayRealEstate(RealEstate realEstate){
        this.mRealEstate = realEstate;

        //Agent name
        final String agentName;
        if (mRealEstate.getAgent().equals(RealEstate.Agent.jessicaCCampbell)) {
            agentName = "Jessica C. Campbell";
        } else {
            agentName = "Christian Haag";
        }

        final String status;
        final int statusColor;
        if (mRealEstate.getStatus() == RealEstate.Status.forSell) {
            status = "For sell";
            statusColor = R.color.activity_on_click_real_estate_for_sell_status_color;
        } else {
            status = "Sold";
            statusColor = R.color.activity_on_click_real_estate_sold_status_color;
        }

        final String dateOfSale;
        if (mRealEstate.getDateOfSale() == null) {
            dateOfSale = "None";
        } else {
            dateOfSale = mRealEstate.getDateOfSale();
        }

        final String surface = mRealEstate.getSurface() + " sq" + " m";
        final String numberOfRooms = Integer.toString(mRealEstate.getNumberOfRooms());
        final String numberOfBathrooms = Integer.toString(mRealEstate.getNumberOfBathrooms());
        final String numberOfBedrooms = Integer.toString(mRealEstate.getNumberOfBedrooms());

        final ViewState newViewState = new ViewState(
                mRealEstate.getPhotoUrl(),
                mRealEstate.getDescription(),
                mRealEstate.getAgentPhotoUrl(),
                agentName,
                status,
                statusColor,
                dateOfSale,
                surface,
                numberOfRooms,
                numberOfBathrooms,
                numberOfBedrooms,
                mRealEstate.getSecondLocation(),
                mRealEstate.getPrice(),
                mRealEstate.getEntryDate()
        );
        _viewState.postValue(newViewState);
    }
}