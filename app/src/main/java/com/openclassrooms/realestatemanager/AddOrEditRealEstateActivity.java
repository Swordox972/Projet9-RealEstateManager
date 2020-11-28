package com.openclassrooms.realestatemanager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.adapter.MyPickPhotosRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.databinding.ActivityAddOrEditRealEstateBinding;
import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddOrEditRealEstateActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    ActivityAddOrEditRealEstateBinding binding;

    public static final int TAKE_PICTURE = 0;
    public static final int PICK_PHOTO = 1;
    public static final int TAKE_PICTURE_FOR_OTHER_PHOTOS = 2;
    public static final int PICK_PHOTO_FOR_OTHER_PHOTOS = 3;

    int lastSelectedDayOfMonth;
    int lastSelectedMonth;
    int lastSelectedYear;

    private RealEstate mNewRealEstate;
    public static ArrayList<RealEstatePhotos> othersPhotosList;

    private String type;
    private String status;
    private String agent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddOrEditRealEstateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //If intent coming from AddRealEstate
        if (getIntent().getSerializableExtra(MainActivity.ADD_REAL_ESTATE) != null) {
            mNewRealEstate = (RealEstate) getIntent().getSerializableExtra(MainActivity.ADD_REAL_ESTATE);
        } //Else if intent coming from EditRealEstate
        else if (getIntent().getSerializableExtra(RealEstateFragment.EDIT_REAL_ESTATE) != null) {
            mNewRealEstate = (RealEstate) getIntent().getSerializableExtra(
                    RealEstateFragment.EDIT_REAL_ESTATE);
        }

        othersPhotosList = new ArrayList<>();
        initializeSpinners();

        selectMainPhotoIntent();
        selectOtherPhotosIntent();

        initializeButtonSelectEntryDate();
        initializeButtonSelectSaleDate();

        //if editing, set all value in spinners, editTexts and ImageViews
        if (mNewRealEstate.equals(getIntent().getSerializableExtra(RealEstateFragment.EDIT_REAL_ESTATE))) {
            initializeRealEstateToEdit();
        }
        initializeFinishButton();
    }


    private void initializeSpinners() {
        Spinner spinnerType = binding.activityAddOrEditRealEstateTypeSpinner;
        Spinner spinnerStatus = binding.activityAddOrEditRealEstateStatusSpinner;
        Spinner spinnerAgent = binding.activityAddOrEditRealEstateAgentSpinner;

        initializeSpinnerAdapter(spinnerType, R.array.real_estate_types);
        initializeSpinnerAdapter(spinnerStatus, R.array.real_estate_status);
        initializeSpinnerAdapter(spinnerAgent, R.array.real_estate_agent);

        spinnerType.setOnItemSelectedListener(this);
        spinnerStatus.setOnItemSelectedListener(this);
        spinnerAgent.setOnItemSelectedListener(this);


    }


    private void initializeSpinnerAdapter(Spinner spinner, int resourceArray) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                resourceArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    //Spinner onItemSelected
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.activity_add_or_edit_real_estate_type_spinner)
            type = adapterView.getItemAtPosition(i).toString();

        if (adapterView.getId() == R.id.activity_add_or_edit_real_estate_status_spinner)
            status = adapterView.getItemAtPosition(i).toString();

        if (adapterView.getId() == R.id.activity_add_or_edit_real_estate_agent_spinner)
            agent = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void selectMainPhotoIntent() {
        binding.activityAddOrEditRealEstateButtonMainPhoto.setOnClickListener(view -> {
            final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose real estate main photo");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (options[i].equals("Take Photo")) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, TAKE_PICTURE);
                    } else if (options[i].equals("Choose from Gallery")) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_PHOTO);
                    } else if (options[i].equals("Cancel")) {
                        dialogInterface.dismiss();
                    }
                }
            });
            builder.show();
        });
    }

    private void selectOtherPhotosIntent() {
        binding.activityAddOrEditRealEstatePickPhotosButton.setOnClickListener(view -> {
            final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose real estate other photos");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (options[i].equals("Take Photo")) {
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, TAKE_PICTURE_FOR_OTHER_PHOTOS);
                    } else if (options[i].equals("Choose from Gallery")) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_PHOTO_FOR_OTHER_PHOTOS);
                    } else if (options[i].equals("Cancel")) {
                        dialogInterface.dismiss();
                    }
                }
            });
            builder.show();
        });
    }

    private void initializeButtonSelectEntryDate() {
        binding.activityAddOrEditRealEstateEntryDateButton.setOnClickListener(view ->
                selectDate(binding.activityAddOrEditRealEstateEntryDateEditText));

    }

    private void initializeButtonSelectSaleDate() {
        binding.activityAddOrEditRealEstateSaleDateButton.setOnClickListener(view ->
                selectDate(binding.activityAddOrEditRealEstateSaleDateEditText));
    }

    private void selectDate(EditText editText) {
        //Get current Date
        final Calendar calendar = Calendar.getInstance();
        lastSelectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        lastSelectedMonth = calendar.get(Calendar.MONTH);
        lastSelectedYear = calendar.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editText.setText(i2 + "/" + (i1 + 1) + "/" + i);

                lastSelectedDayOfMonth = i2;
                lastSelectedMonth = i1;
                lastSelectedYear = i;
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);

        datePickerDialog.show();
    }

    // -- Real Estate To Edit --
    private void initializeRealEstateToEdit() {
        //Initialize Spinners with the value of editing real estate
        String[] types = getResources().getStringArray(R.array.real_estate_types);
        String[] statusArray = getResources().getStringArray(R.array.real_estate_status);
        String[] agents = getResources().getStringArray(R.array.real_estate_agent);

        type = mNewRealEstate.getType();
        status = mNewRealEstate.getStatus();
        agent = mNewRealEstate.getAgent();

        for (int i = 0; i < types.length; i++) {
            if (type.equals(types[i])) {
                binding.activityAddOrEditRealEstateTypeSpinner.setSelection(i);
            }
        }
        for (int i = 0; i < statusArray.length; i++) {
            if (status.equals(statusArray[i])) {
                binding.activityAddOrEditRealEstateStatusSpinner.setSelection(i);
            }
        }
        for (int i = 0; i < agents.length; i++) {
            if (agent.equals(agents[i])) {
                binding.activityAddOrEditRealEstateAgentSpinner.setSelection(i);
            }
        }

        String surface = Integer.toString(mNewRealEstate.getSurface());
        String numberOfRooms = Integer.toString(mNewRealEstate.getNumberOfRooms());
        String numberOfBathrooms = Integer.toString(mNewRealEstate.getNumberOfBathrooms());
        String numberOfBedrooms = Integer.toString(mNewRealEstate.getNumberOfBedrooms());


        if (mNewRealEstate.getMainPhotoUrl() != null) {
            Glide.with(binding.activityAddOrEditRealEstateMainPhoto.getContext())
                    .load(mNewRealEstate.getMainPhotoUrl())
                    .into(binding.activityAddOrEditRealEstateMainPhoto);
        } else {
            Uri mainPhotoUri = RealEstatePhotos.stringToUri(mNewRealEstate.getMainPhotoString());
            binding.activityAddOrEditRealEstateMainPhoto.setImageURI(mainPhotoUri);
        }

        binding.activityAddOrEditRealEstateFirstLocationEditText.setText(mNewRealEstate.getFirstLocation());
        binding.activityAddOrEditRealEstatePriceEditText.setText(mNewRealEstate.getPrice());
        binding.activityAddOrEditRealEstateDescriptionEditText.setText(mNewRealEstate.getDescription());
        othersPhotosList.addAll(mNewRealEstate.getPhotos());
        binding.activityAddOrEditRealEstateSurfaceEditText.setText(surface);
        binding.activityAddOrEditRealEstateNumberOfRoomsEditText.setText(numberOfRooms);
        binding.activityAddOrEditRealEstateNumberOfBathroomsEditText.setText(numberOfBathrooms);
        binding.activityAddOrEditRealEstateNumberOfBedroomsEditText.setText(numberOfBedrooms);
        binding.activityAddOrEditRealEstateAddressEditText.setText(mNewRealEstate.getSecondLocation());
        binding.activityAddOrEditRealEstatePointsOfInterestEditText.setText(
                mNewRealEstate.getPointsOfInterest());
        binding.activityAddOrEditRealEstateEntryDateEditText.setText(mNewRealEstate.getEntryDate());
        binding.activityAddOrEditRealEstateSaleDateEditText.setText(mNewRealEstate.getDateOfSale());


    }

    // -- Finish activity --
    private void setNewRealEstateValue() {
        mNewRealEstate.setType(type);
        mNewRealEstate.setStatus(status);
        mNewRealEstate.setAgent(agent);

        if (agent.equals("Jessica C. Campbell")) {
            mNewRealEstate.setAgentPhotoUrl("https://i.ibb.co/0MZZf43/Jessica-CCampbell.jpg");
        } else {
            mNewRealEstate.setAgentPhotoUrl("https://i.ibb.co/Y71g9LB/Christian-Haag.jpg");
        }

        String firstLocation = binding.activityAddOrEditRealEstateFirstLocationEditText.getText().toString();
        String price = binding.activityAddOrEditRealEstatePriceEditText.getText().toString();
        String description = binding.activityAddOrEditRealEstateDescriptionEditText.getText().toString();
        int surface = Integer.parseInt(binding.activityAddOrEditRealEstateSurfaceEditText.getText().toString());
        int numberOfRooms = Integer.parseInt(
                binding.activityAddOrEditRealEstateNumberOfRoomsEditText.getText().toString());
        int numberOfBathrooms = Integer.parseInt(
                binding.activityAddOrEditRealEstateNumberOfBathroomsEditText.getText().toString());
        int numberOfBedrooms = Integer.parseInt(binding
                .activityAddOrEditRealEstateNumberOfBedroomsEditText.getText().toString());
        String secondLocation = binding.activityAddOrEditRealEstateAddressEditText.getText().toString();
        String pointsOfInterest = binding.activityAddOrEditRealEstatePointsOfInterestEditText.getText()
                .toString();
        String entryDate = binding.activityAddOrEditRealEstateEntryDateEditText.getText().toString();
        String saleDate = binding.activityAddOrEditRealEstateSaleDateEditText.getText().toString();

        mNewRealEstate.setFirstLocation(firstLocation);
        if (price.charAt(0) != '$') {
        mNewRealEstate.setPrice("$" + price);
        } else {
            mNewRealEstate.setPrice(price);
        }
        mNewRealEstate.setDescription(description);
        //mNewRealEstate.setMainPhoto is in onActivityResult or already assigned if editing without changes
        //mNewRealEstate.setOthersPhotos is in onActivityResult or already assigned if editing without changes
        mNewRealEstate.setSurface(surface);
        mNewRealEstate.setNumberOfRooms(numberOfRooms);
        mNewRealEstate.setNumberOfBathrooms(numberOfBathrooms);
        mNewRealEstate.setNumberOfBedrooms(numberOfBedrooms);
        mNewRealEstate.setSecondLocation(secondLocation);
        //Set latitude and longitude
        getLocationFromAddress(secondLocation);
        mNewRealEstate.setPointsOfInterest(pointsOfInterest);
        mNewRealEstate.setEntryDate(entryDate);
        mNewRealEstate.setDateOfSale(saleDate);


    }

    private void initializeFinishButton() {
        //Set mNewRealEstate all value selected previously
        // If intent comes from Main Activity pass data back
        if (getIntent().getSerializableExtra(MainActivity.ADD_REAL_ESTATE) != null) {
            binding.activityAddOrEditRealEstateOkButton.setOnClickListener(view -> {
                setNewRealEstateValue();

                Intent intent = new Intent();
                intent.putExtra(MainActivity.ADD_REAL_ESTATE, mNewRealEstate);
                setResult(RESULT_OK, intent);
                finish();
            });
        }// Else if intent comes from Real Estate Fragment pass data back

        else if (getIntent().getSerializableExtra(RealEstateFragment.EDIT_REAL_ESTATE) != null) {
            binding.activityAddOrEditRealEstateOkButton.setOnClickListener(view -> {

                //Verify if when "Sold" status is selected that Sale date has a value
                if (binding.activityAddOrEditRealEstateStatusSpinner.getSelectedItem().toString()
                        .equals("For sale") || (binding.activityAddOrEditRealEstateStatusSpinner
                        .getSelectedItem().toString().equals("Sold") &&
                        !binding.activityAddOrEditRealEstateSaleDateEditText.getText().toString()
                                .isEmpty())) {
                    setNewRealEstateValue();

                    Intent intent = new Intent();
                    intent.putExtra(RealEstateFragment.EDIT_REAL_ESTATE, mNewRealEstate);
                    setResult(RESULT_OK, intent);
                    finish();
                }// Toast to inform user that he put "Sold" status without sale date value
                else {
                    Toast.makeText(this,
                            "Oops ...You specified Sold status without value to sale date",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
    }

    private void getLocationFromAddress(String strAddress) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> address;

        try {
            //Get latLng from String
            address = geocoder.getFromLocationName(strAddress, 5);

            if (address != null) {

                // Take first possibility from the all possibilities.
                try {
                    Address location = address.get(0);
                    mNewRealEstate.setLatitude(location.getLatitude());
                    mNewRealEstate.setLongitude(location.getLongitude());
                } catch (IndexOutOfBoundsException e) {

                }

            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // -- OnActivityRESULT --
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        takePhotoOrGalleryOnActivityResult(requestCode, resultCode, data);

    }

    private void takePhotoOrGalleryOnActivityResult(int requestCode, int resultCode,
                                                    @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                //set image in ImageView from camera
                Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                binding.activityAddOrEditRealEstateMainPhoto.setImageBitmap(selectedImage);
                Uri imageUri = RealEstatePhotos.bitmapToImageUri(this, selectedImage);
                String imageUriToString = RealEstatePhotos.uriToString(imageUri);
                mNewRealEstate.setMainPhotoString(imageUriToString);
            }
        } else if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                //set image in ImageView from gallery
                Uri selectedImage = data.getData();
                binding.activityAddOrEditRealEstateMainPhoto.setImageURI(selectedImage);
                String selectedImageToString = RealEstatePhotos.uriToString(selectedImage);

                mNewRealEstate.setMainPhotoString(selectedImageToString);
            }
        } else if (requestCode == TAKE_PICTURE_FOR_OTHER_PHOTOS) {
            if (resultCode == RESULT_OK) {
                //set image in othersPhotosList from camera
                Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                RealEstatePhotos realEstatePhotos = new RealEstatePhotos();
                //Set photo uri
                Uri imageUri = RealEstatePhotos.bitmapToImageUri(this, selectedImage);
                String imageUriToString = RealEstatePhotos.uriToString(imageUri);
                realEstatePhotos.setPhotoUri(imageUriToString);

                othersPhotosList.add(realEstatePhotos);
                //Set photo description
                if (othersPhotosList.size() != 0) {
                    String photoDescription = MyPickPhotosRecyclerViewAdapter.map
                            .get(othersPhotosList.size() - 1);
                    realEstatePhotos.setDescription(photoDescription);
                    mNewRealEstate.setPhotos(othersPhotosList);
                }
            }
        } else if (requestCode == PICK_PHOTO_FOR_OTHER_PHOTOS) {
            if (resultCode == RESULT_OK) {
                //set image in othersPhotosList from gallery
                Uri selectedImage = data.getData();
                RealEstatePhotos realEstatePhotos = new RealEstatePhotos();

                String imageUriToString = RealEstatePhotos.uriToString(selectedImage);
                realEstatePhotos.setPhotoUri(imageUriToString);
                othersPhotosList.add(realEstatePhotos);
                //Set photo description
                if (othersPhotosList.size() != 0) {
                    String photoDescription = MyPickPhotosRecyclerViewAdapter.map.get(
                            othersPhotosList.size() - 1);
                    realEstatePhotos.setDescription(photoDescription);
                    mNewRealEstate.setPhotos(othersPhotosList);
                }
            }
        }
    }
}