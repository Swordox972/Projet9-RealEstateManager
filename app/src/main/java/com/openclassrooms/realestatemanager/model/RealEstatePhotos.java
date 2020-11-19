package com.openclassrooms.realestatemanager.model;

import java.io.Serializable;


public class RealEstatePhotos implements Serializable {

    private long id;
    private String photoUrl;
    private String description;

    public RealEstatePhotos(long id, String photoUrl, String description) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
