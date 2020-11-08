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


    }

    private void initializeRealEstateValueAndDescription() {
        Intent intent= getIntent();
        mRealEstate = (RealEstate) intent.getSerializableExtra(RealEstateFragment.KEY);

        binding.activityOnClickRealEstateDescription.setText(mRealEstate.getDescription());
    }
}