package com.openclassrooms.realestatemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.databinding.ActivityOnClickRealEstateBinding;
import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class OnClickRealEstateActivity extends AppCompatActivity {

    private ActivityOnClickRealEstateBinding binding;
    private OnClickRealEstateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnClickRealEstateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(OnClickRealEstateViewModel.class);

        final RealEstate realEstate = (RealEstate) getIntent().getSerializableExtra(RealEstateFragment.KEY);

        viewModel.displayRealEstate(realEstate);

        viewModel.viewState.observe(this, new Observer<OnClickRealEstateViewModel.ViewState>() {
            @Override
            public void onChanged(OnClickRealEstateViewModel.ViewState viewState) {
                Glide.with(binding.activityOnClickRealEstateImageView.getContext())
                        .load(viewState.imageUrl)
                        .into(binding.activityOnClickRealEstateImageView);

                binding.activityOnClickRealEstateDescription.setText(viewState.description);


                Glide.with(binding.activityOnClickRealEstateAgentPhoto.getContext())
                        .load(viewState.agentImage)
                        .into(binding.activityOnClickRealEstateAgentPhoto);

                binding.activityOnClickRealEstateAgentName.setText(viewState.agentName);

                binding.activityOnClickRealEstateStatus.setTextColor(getResources()
                        .getColor(viewState.statusColor));

                binding.activityOnClickRealEstateStatus.setText(viewState.status);
                binding.activityOnClickRealEstateSurfaceValue.setText(viewState.surface);
                binding.activityOnClickRealEstateRoomsValue.setText(viewState.numberOfRooms);
                binding.activityOnClickRealEstateBathroomsValue.setText(viewState.numberOfBathrooms);
                binding.activityOnClickRealEstateBedroomsValue.setText(viewState.numberOfBedrooms);
                binding.activityOnClickRealEstateLocationValue.setText(viewState.secondLocation);
                binding.activityOnClickRealEstatePriceValue.setText(viewState.price);
                binding.activityOnClickRealEstateEntryDateValue.setText(viewState.entryDate);
                binding.activityOnClickRealEstateSaleDateValue.setText(viewState.dateOfSale);
            }
        });
    }
}