package com.openclassrooms.realestatemanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.realestatemanager.databinding.ActivitySearchRealEstateProviderBinding;
import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.fragment.RealEstateViewModel;
import com.openclassrooms.realestatemanager.repository.RealEstateDataRepository;
import com.openclassrooms.realestatemanager.service.DateUtils;

import java.util.Date;

public class SearchRealEstateProviderActivity extends AppCompatActivity {

    ActivitySearchRealEstateProviderBinding binding;
    private RealEstateViewModel viewModel;
    private RealEstateDataRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchRealEstateProviderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        repository = DI.getRepository(getApplication());

        getRealEstatesInDatabase();

        initializeSearchTerms();
    }

    private void getRealEstatesInDatabase() {
        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);
    }

    private void initializeSearchTerms() {
        binding.activitySearchRealEstateButtonSearch.setOnClickListener(view -> {
            SearchCritaries critaries = new SearchCritaries();
            String minimumPrice = binding.activitySearchRealEstatePriceMinimumEditText.getText().toString();
            String maximumPrice = binding.activitySearchRealEstatePriceMaximumEditText.getText().toString();
            String minimumSurface = binding.activitySearchRealEstateSurfaceMinimumEditText.getText().toString();
            String maximumSurface = binding.activitySearchRealEstateSurfaceMaximumEditText.getText().toString();
            String firstLocation = binding.activitySearchRealEstateFirstLocationEditText.getText().toString();
            String numberOfPhotos = binding.activitySearchRealEstateNumberPhotosEditText.getText().toString();
            String pointOfInterest = binding.activitySearchRealEstatePointOfInterestEditText.getText().toString();
            String entryDate = binding.activitySearchRealEstateEntryDateSinceEditText.getText().toString();
            String saleDate = binding.activitySearchRealEstateSaleDateSinceEditText.getText().toString();


            try {
                critaries.setMinimumPrice(Integer.parseInt(minimumPrice));
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            try {
                critaries.setMaximumPrice(Integer.parseInt(maximumPrice));
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            try {
                critaries.setMinimumSurface(Integer.parseInt(minimumSurface));
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }
            try {
                critaries.setMaximumSurface(Integer.parseInt(maximumSurface));
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            try {
                critaries.setFirstLocation(firstLocation);
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            try {
                critaries.setNumberOfPhotos(Integer.parseInt(numberOfPhotos));
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            try {
                critaries.setPointOfInterest(pointOfInterest);
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }
            try {
                critaries.setPointOfInterest(pointOfInterest);
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }
            Date entryDateInDate = DateUtils.convertStringToDate(entryDate);

            try {
                critaries.setEntryDateInDate(entryDateInDate);
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            Date saleDateInDate = DateUtils.convertStringToDate(saleDate);
            try {
                critaries.setSaleDateInDate(saleDateInDate);
            } catch (Throwable t) {
                t.printStackTrace(); //if it's not an integer
            }

            critaries.setPointOfInterest(pointOfInterest);
            critaries.setEntryDateInDate(entryDateInDate);
            critaries.setSaleDateInDate(saleDateInDate);

            repository.setFilter(critaries);

            finish();
        });
    }

    public class SearchCritaries {
        private Integer minimumPrice;
        private Integer maximumPrice;
        private Integer minimumSurface;
        private Integer maximumSurface;
        private String firstLocation;
        private Integer numberOfPhotos;
        private String pointOfInterest;
        private Date entryDateInDate;
        private Date saleDateInDate;

        public SearchCritaries() {
            //empty constructor
        }

        public Integer getMinimumPrice() {
            return minimumPrice;
        }

        public void setMinimumPrice(Integer minimumPrice) {
            this.minimumPrice = minimumPrice;
        }

        public Integer getMaximumPrice() {
            return maximumPrice;
        }

        public void setMaximumPrice(Integer maximumPrice) {
            this.maximumPrice = maximumPrice;
        }

        public Integer getMinimumSurface() {
            return minimumSurface;
        }

        public void setMinimumSurface(Integer minimumSurface) {
            this.minimumSurface = minimumSurface;
        }

        public Integer getMaximumSurface() {
            return maximumSurface;
        }

        public void setMaximumSurface(Integer maximumSurface) {
            this.maximumSurface = maximumSurface;
        }

        public String getFirstLocation() {
            return firstLocation;
        }

        public void setFirstLocation(String firstLocation) {
            this.firstLocation = firstLocation;
        }

        public Integer getNumberOfPhotos() {
            return numberOfPhotos;
        }

        public void setNumberOfPhotos(Integer numberOfPhotos) {
            this.numberOfPhotos = numberOfPhotos;
        }

        public String getPointOfInterest() {
            return pointOfInterest;
        }

        public void setPointOfInterest(String pointOfInterest) {
            this.pointOfInterest = pointOfInterest;
        }

        public Date getEntryDateInDate() {
            return entryDateInDate;
        }

        public void setEntryDateInDate(Date entryDateInDate) {
            this.entryDateInDate = entryDateInDate;
        }

        public Date getSaleDateInDate() {
            return saleDateInDate;
        }

        public void setSaleDateInDate(Date saleDateInDate) {
            this.saleDateInDate = saleDateInDate;
        }
    }
}

