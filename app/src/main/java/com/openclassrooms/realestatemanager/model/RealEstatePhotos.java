package com.openclassrooms.realestatemanager.model;

import java.io.Serializable;

public class RealEstatePhotos implements Serializable {

    private String photoUrl;
    private String description;

    public RealEstatePhotos(String photoUrl, String description) {
        this.photoUrl = photoUrl;
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
