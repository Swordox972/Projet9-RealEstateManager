package com.openclassrooms.realestatemanager.service;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RealEstateTypeConverter {

    @TypeConverter
    public String fromValuesToList(ArrayList<RealEstatePhotos> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<RealEstatePhotos>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public ArrayList<RealEstatePhotos> toOptionValuesList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<RealEstatePhotos>>() {
        }.getType();
        return gson.fromJson(value, type);
    }

}
