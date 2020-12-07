package com.openclassrooms.realestatemanager.query;

import android.util.Log;

import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.openclassrooms.realestatemanager.service.DateConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyRealEstateQuery {
        public static SupportSQLiteQuery generateQuery(Integer minimumPrice, Integer maximumPrice,
                                                       Integer minimumSurface, Integer maximumSurface,
                                                       String firstLocation, Integer numberOfPhotos,
                                                       String pointOfInterest, Date minimumEntryDate,
                                                       Date minimumSaleDate){

            // List of bind parameters
            List<Object> args = new ArrayList();

            // Beginning of query string
            String selectString = " SELECT * FROM RealEstate";
            

            String whereString = " WHERE ";
            if(minimumPrice != null && minimumPrice >= 0){
                args.add(minimumPrice);
                whereString += "price >= ? AND ";
            }
            if(maximumPrice != null && minimumPrice >= 0){
                whereString += "price <= ? AND ";
                args.add(maximumPrice);
            }
            if(minimumSurface != null && minimumPrice >= 0){
                whereString += "surface >= ? AND ";
                args.add(minimumSurface);
            }
            if(maximumSurface != null && minimumPrice >= 0){
                whereString += "surface <= ? AND ";
                args.add(maximumSurface);
            }
            if(!firstLocation.isEmpty()){
                whereString += "firstLocation = ? AND ";
                args.add(firstLocation);
            }
            if(!pointOfInterest.isEmpty()){
                whereString += "pointsOfInterest = ? AND ";
                args.add(pointOfInterest);
            }
            if(minimumEntryDate != null){
                whereString += "entryDate >= ? AND ";
                args.add(DateConverter.fromDate(minimumEntryDate));
            }
            if(minimumSaleDate != null){
                whereString += "dateOfSale >= ? AND ";
                args.add(DateConverter.fromDate(minimumSaleDate));
            }

            if(numberOfPhotos != null){
                whereString += "numberOfPhotos >= ? AND ";
                args.add(numberOfPhotos);
            }

            //We have an AND at the end, we delete it
            int lastIndexAnd = whereString.lastIndexOf(" AND ");
            whereString = whereString.substring(0, lastIndexAnd);


            String queryString = selectString + whereString;

            Log.d( "SQL",queryString);
            return new SimpleSQLiteQuery(queryString, args.toArray());
        }

}
