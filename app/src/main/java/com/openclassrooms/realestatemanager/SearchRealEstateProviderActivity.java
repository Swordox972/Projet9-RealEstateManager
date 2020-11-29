package com.openclassrooms.realestatemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.realestatemanager.databinding.ActivitySearchRealEstateProviderBinding;
import com.openclassrooms.realestatemanager.fragment.RealEstateViewModel;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchRealEstateProviderActivity extends AppCompatActivity {

    ActivitySearchRealEstateProviderBinding binding;
    private RealEstateViewModel viewModel;
    private ArrayList<RealEstate> realEstateFilterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchRealEstateProviderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getRealEstatesInDatabase();

        initializeSearchTerms();
    }

    private void getRealEstatesInDatabase() {
        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);

        realEstateFilterList = getIntent().getParcelableArrayListExtra(MainActivity.SEARCH_REAL_ESTATE);

        viewModel.getRealEstates().observe(this, realEstates -> {
            realEstateFilterList.addAll(realEstates);
        });

    }

    private void initializeSearchTerms() {
        binding.activitySearchRealEstateButtonSearch.setOnClickListener(view -> {

            String minimumPrice = binding.activitySearchRealEstatePriceMinimumEditText.getText().toString();
            String maximumPrice = binding.activitySearchRealEstatePriceMaximumEditText.getText().toString();
            String minimumSurface = binding.activitySearchRealEstateSurfaceMinimumEditText.getText().toString();
            String maximumSurface = binding.activitySearchRealEstateSurfaceMaximumEditText.getText().toString();
            String pointOfInterest = binding.activitySearchRealEstatePointOfInterestEditText.getText().toString();
            String entryDateInDays = binding.activitySearchRealEstateEntryDateSinceEditText.getText().toString();
            String saleDateInDays = binding.activitySearchRealEstateSaleDateSinceEditText.getText().toString();

            //Ensure min and max price has value and filter with price
            if (!minimumPrice.isEmpty() && !maximumPrice.isEmpty()) {

                int minimumPriceInt = Integer.parseInt(minimumPrice);
                int maximumPriceInt = Integer.parseInt(maximumPrice);

                for (int i = 0; i < realEstateFilterList.size(); i++) {
                    int price = realEstateFilterList.get(i).getPrice();

                    if (price < minimumPriceInt || price > maximumPriceInt)
                        realEstateFilterList.remove(i);
                }
            }

            //Ensure min and max surface has value and filter with surface
            if (!minimumSurface.isEmpty() && !maximumSurface.isEmpty()) {

                int minimumSurfaceInt = Integer.parseInt(minimumSurface);
                int maximumSurfaceInt = Integer.parseInt(maximumSurface);

                for (int i = 0; i < realEstateFilterList.size(); i++) {
                    int surface = realEstateFilterList.get(i).getSurface();

                    if (surface < minimumSurfaceInt || surface > maximumSurfaceInt)
                        realEstateFilterList.remove(i);
                }
            }

            //Ensure point of interest has value and filter with it
            if (!pointOfInterest.isEmpty()) {

                for (int i = 0; i < realEstateFilterList.size(); i++) {

                    if (!realEstateFilterList.get(i).getPointsOfInterest().equals(pointOfInterest))
                        realEstateFilterList.remove(i);
                }
            }

            //Ensure entry date has value and filter with it
            if (!entryDateInDays.isEmpty()) {
                String todayDate = DateUtils.returnTodayDate();
                int entryDateInDaysInt = Integer.parseInt(entryDateInDays);

                for (int i = 0; i < realEstateFilterList.size(); i++) {
                 long days=DateUtils.getDaysBetweenDates(realEstateFilterList.get(i).getEntryDate(),
                            todayDate);

                 if (entryDateInDaysInt > days) {
                     realEstateFilterList.remove(i);
                 }

                }
            }

            //Ensure sale date has value and filter with it
            if (!saleDateInDays.isEmpty()) {
                String todayDate = DateUtils.returnTodayDate();
                int saleDateInDaysInt = Integer.parseInt(saleDateInDays);

                for (int i = 0; i < realEstateFilterList.size(); i++) {
                    long days=DateUtils.getDaysBetweenDates(realEstateFilterList.get(i).getDateOfSale(),
                            todayDate);

                    if (saleDateInDaysInt > days) {
                        realEstateFilterList.remove(i);
                    }
                }
            }

            Intent intent = new Intent();
            intent.putParcelableArrayListExtra(MainActivity.SEARCH_REAL_ESTATE, realEstateFilterList);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

}