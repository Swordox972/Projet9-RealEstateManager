package com.openclassrooms.realestatemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

        initializeRealEstateValueAndDescriptionAndImageView();
        initializeRealEstateIconsValue();

    }

    private void initializeRealEstateValueAndDescriptionAndImageView() {
        Intent intent = getIntent();
        mRealEstate = (RealEstate) intent.getSerializableExtra(RealEstateFragment.KEY);

        Glide.with(binding.activityOnClickRealEstateImageView.getContext())
                .load(mRealEstate.getPhotoUrl())
                .into(binding.activityOnClickRealEstateImageView);

        binding.activityOnClickRealEstateDescription.setText(mRealEstate.getDescription());
    }

    private void initializeRealEstateIconsValue() {
        String status;
        if (mRealEstate.getStatus() == RealEstate.Status.forSell) {
            status = "For sell";
            binding.activityOnClickRealEstateStatus.setTextColor(getResources()
                    .getColor(R.color.activity_on_click_real_estate_for_sell_status_color));
        } else {
            status = "Sold";
            binding.activityOnClickRealEstateStatus.setTextColor(getResources()
                    .getColor(R.color.activity_on_click_real_estate_sold_status_color));
        }

        String dateOfSale;
        if (mRealEstate.getDateOfSale() == null) {
            dateOfSale = "None";
        } else {
            dateOfSale = mRealEstate.getDateOfSale();
        }

        String surface = mRealEstate.getSurface() + " sq" + " m";
        String numberOfRooms = Integer.toString(mRealEstate.getNumberOfRooms());
        String numberOfBathrooms = Integer.toString(mRealEstate.getNumberOfBathrooms());
        String numberOfBedrooms = Integer.toString(mRealEstate.getNumberOfBedrooms());


        binding.activityOnClickRealEstateStatus.setText(status);
        binding.activityOnClickRealEstateSurfaceValue.setText(surface);
        binding.activityOnClickRealEstateRoomsValue.setText(numberOfRooms);
        binding.activityOnClickRealEstateBathroomsValue.setText(numberOfBathrooms);
        binding.activityOnClickRealEstateBedroomsValue.setText(numberOfBedrooms);
        binding.activityOnClickRealEstateLocationValue.setText(mRealEstate.getSecondLocation());
        binding.activityOnClickRealEstatePriceValue.setText(mRealEstate.getPrice());
        binding.activityOnClickRealEstateEntryDateValue.setText(mRealEstate.getEntryDate());
        binding.activityOnClickRealEstateSaleDateValue.setText(dateOfSale);
    }
}