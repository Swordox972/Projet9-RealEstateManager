package com.openclassrooms.realestatemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.openclassrooms.realestatemanager.databinding.ActivityOnClickRealEstateBinding;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class OnClickRealEstateActivity extends AppCompatActivity {

    private ActivityOnClickRealEstateBinding binding;
    private RealEstate mRealEstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnClickRealEstateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initializeRealEstateValueAndDescription();
        initializeRealEstateIconsValue();

    }

    private void initializeRealEstateValueAndDescription() {
        Intent intent= getIntent();
        mRealEstate = (RealEstate) intent.getSerializableExtra(RealEstateFragment.KEY);

        binding.activityOnClickRealEstateDescription.setText(mRealEstate.getDescription());
    }

    private void initializeRealEstateIconsValue() {
        String surface = mRealEstate.getSurface() + " sq" + " m";
        String numberOfRooms =Integer.toString(mRealEstate.getNumberOfRooms());
        String numberOfBathrooms = Integer.toString(mRealEstate.getNumberOfBathrooms());
        String numberOfBedrooms = Integer.toString(mRealEstate.getNumberOfBedrooms());

        binding.activityOnClickRealEstateSurfaceValue.setText(surface);
        binding.activityOnClickRealEstateRoomsValue.setText(numberOfRooms);
        binding.activityOnClickRealEstateBathroomsValue.setText(numberOfBathrooms);
        binding.activityOnClickRealEstateBedroomsValue.setText(numberOfBedrooms);
        binding.activityOnClickRealEstateLocationValue.setText(mRealEstate.getSecondLocation());
    }
}