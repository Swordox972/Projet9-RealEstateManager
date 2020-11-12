package com.openclassrooms.realestatemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openclassrooms.realestatemanager.databinding.ActivityOnClickRealEstateBinding;
import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class OnClickRealEstateActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityOnClickRealEstateBinding binding;
    private OnClickRealEstateViewModel viewModel;
    private GoogleMap mMap;
    private RealEstate mRealEstate;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng realEstateLatLng = new LatLng(mRealEstate.getLatitude(), mRealEstate.getLongitude());
        mMap.addMarker(new MarkerOptions().position(realEstateLatLng).title("Real Estate Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(realEstateLatLng));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnClickRealEstateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MapFragment mapFragment = (MapFragment) getFragmentManager().
                findFragmentById(R.id.activity_on_click_real_estate_map_fragment);
        mapFragment.getMapAsync(this);

        viewModel = new ViewModelProvider(this).get(OnClickRealEstateViewModel.class);

         mRealEstate = (RealEstate) getIntent().getSerializableExtra(RealEstateFragment.KEY);

        viewModel.displayRealEstate(mRealEstate);

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