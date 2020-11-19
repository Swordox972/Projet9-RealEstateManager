package com.openclassrooms.realestatemanager.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.openclassrooms.realestatemanager.model.RealEstate;

import java.util.List;

@Dao
public interface RealEstateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRealEstate(RealEstate realEstate);

    @Query("SELECT * FROM RealEstate WHERE id = :realEstateId")
    LiveData<RealEstate> getRealEstate(long realEstateId);

    @Query("SELECT * FROM RealEstate")
    LiveData<List<RealEstate>> getRealEstates();

    @Update
    int updateRealEstate(RealEstate realEstate);

    @Query("DELETE FROM RealEstate")
    void deleteAll();
}
