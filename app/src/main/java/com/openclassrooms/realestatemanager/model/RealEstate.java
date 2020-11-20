package com.openclassrooms.realestatemanager.model;

import android.content.ContentValues;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class RealEstate implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String type;
    private String price;
    private int surface;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfBedrooms;
    private String description;
    private String photoUrl;
    private ArrayList photos;
    private String firstLocation;
    private String secondLocation;
    private double latitude;
    private double longitude;
    private String status;
    private String entryDate;
    private String dateOfSale;
    private String agent;
    private String agentPhotoUrl;


    public RealEstate() {
    }

    public RealEstate(long id, String type, String price, int surface, int numberOfRooms,
                      int numberOfBathrooms, int numberOfBedrooms, String description, String photoUrl,
                      ArrayList photos, String firstLocation, String secondLocation, double latitude,
                      double longitude, String status, String entryDate, String dateOfSale,
                      String agent, String agentPhotoUrl) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.surface = surface;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.description = description;
        this.photoUrl = photoUrl;
        this.photos = photos;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.entryDate = entryDate;
        this.dateOfSale = dateOfSale;
        this.agent = agent;
        this.agentPhotoUrl = agentPhotoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList photos) {
        this.photos = photos;
    }

    public String getFirstLocation() {
        return firstLocation;
    }

    public void setFirstLocation(String firstLocation) {
        this.firstLocation = firstLocation;
    }

    public String getSecondLocation() {
        return secondLocation;
    }

    public void setSecondLocation(String secondLocation) {
        this.secondLocation = secondLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgentPhotoUrl() {
        return agentPhotoUrl;
    }

    public void setAgentPhotoUrl(String agentPhotoUrl) {
        this.agentPhotoUrl = agentPhotoUrl;
    }


    //Content Provider
    public static RealEstate fromContentValues(ContentValues values) {
        final RealEstate realEstate = new RealEstate();
        if (values.containsKey("type")) realEstate.setType(values.getAsString("type"));
        if (values.containsKey("price")) realEstate.setPrice(values.getAsString("price"));
        if (values.containsKey("surface")) realEstate.setSurface(values.getAsInteger("surface"));
        if (values.containsKey("numberOfRooms")) realEstate.setNumberOfRooms(
                values.getAsInteger("numberOfRooms"));
        if (values.containsKey("numberOfBathrooms")) realEstate.setNumberOfBathrooms(
                values.getAsInteger("numberOfBathrooms"));
        if (values.containsKey("numberOfBedrooms")) realEstate.setNumberOfBedrooms(
                values.getAsInteger("numberOfBedrooms"));
        if (values.containsKey("description")) realEstate.setDescription(
                values.getAsString("description"));
        if (values.containsKey("photoUrl")) realEstate.setPhotoUrl(values.getAsString("photoUrl"));
        if (values.containsKey("firstLocation")) realEstate.setFirstLocation(
                values.getAsString("firstLocation"));
        if (values.containsKey("secondLocation")) realEstate.setSecondLocation(
                values.getAsString("secondLocation"));
        if (values.containsKey("latitude")) realEstate.setLatitude(values.getAsDouble("latitude"));
        if (values.containsKey("longitude")) realEstate.setLongitude(values.getAsDouble("longitude"));
        if (values.containsKey("status")) realEstate.setStatus(values.getAsString("status"));
        if (values.containsKey("entryDate")) realEstate.setEntryDate(values.getAsString("entryDate"));
        if (values.containsKey("dateOfSale")) realEstate.setDateOfSale(values.getAsString("dateOfSale"));
        if (values.containsKey("agent")) realEstate.setAgent(values.getAsString("agent"));
        if (values.containsKey("agentPhotoUrl")) realEstate.setAgentPhotoUrl(values.getAsString("agentPhotoUrl"));

        return realEstate;
    }
}

