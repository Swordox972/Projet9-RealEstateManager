package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.databinding.FragmentOnClickRealEstateBinding;
import com.openclassrooms.realestatemanager.model.RealEstate;


public class OnClickRealEstateFragment extends Fragment implements OnMapReadyCallback {

    private FragmentOnClickRealEstateBinding binding;
    private RealEstate mRealEstate;
    private GoogleMap mMap;

    public OnClickRealEstateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnClickRealEstateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mRealEstate = (RealEstate) getArguments().getSerializable(RealEstateFragment.KEY);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(binding.activityOnClickRealEstateImageView.getContext())
                .load(mRealEstate.getPhotoUrl())
                .into(binding.activityOnClickRealEstateImageView);

        binding.activityOnClickRealEstateDescription.setText(mRealEstate.getDescription());

        Glide.with(binding.activityOnClickRealEstateAgentPhoto.getContext())
                .load(mRealEstate.getAgentPhotoUrl())
                .into(binding.activityOnClickRealEstateAgentPhoto);

        if (mRealEstate.getAgent().equals(RealEstate.Agent.jessicaCCampbell)) {
            binding.activityOnClickRealEstateAgentName.setText("Jessica C. Campbell");
        } else {
            binding.activityOnClickRealEstateAgentName.setText("Christian Haag");
        }

        if (mRealEstate.getStatus() == RealEstate.Status.forSell) {
            binding.activityOnClickRealEstateStatus.setText("For sale");
            binding.activityOnClickRealEstateStatus.setTextColor(getResources()
                    .getColor(R.color.fragment_on_click_real_estate_for_sale_status_color));
        } else {
            binding.activityOnClickRealEstateStatus.setText("Sold");
            binding.activityOnClickRealEstateStatus.setTextColor(getResources().getColor(
                    R.color.fragment_on_click_real_estate_sold_status_color));
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


        binding.activityOnClickRealEstateSurfaceValue.setText(surface);
        binding.activityOnClickRealEstateRoomsValue.setText(numberOfRooms);
        binding.activityOnClickRealEstateBathroomsValue.setText(numberOfBathrooms);
        binding.activityOnClickRealEstateBedroomsValue.setText(numberOfBedrooms);
        binding.activityOnClickRealEstateLocationValue.setText(mRealEstate.getSecondLocation());
        binding.activityOnClickRealEstatePriceValue.setText(mRealEstate.getPrice());
        binding.activityOnClickRealEstateEntryDateValue.setText(mRealEstate.getEntryDate());
        binding.activityOnClickRealEstateSaleDateValue.setText(dateOfSale);


        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.fragment_on_click_real_estate_map_fragment);
        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng realEstateLatLng = new LatLng(mRealEstate.getLatitude(), mRealEstate.getLongitude());
        mMap.addMarker(new MarkerOptions().position(realEstateLatLng).title("Real estate marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(realEstateLatLng, 18));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}