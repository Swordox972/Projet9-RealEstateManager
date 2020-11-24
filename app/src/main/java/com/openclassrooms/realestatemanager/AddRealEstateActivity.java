package com.openclassrooms.realestatemanager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.realestatemanager.databinding.ActivityAddRealEstateBinding;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.Calendar;

public class AddRealEstateActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    ActivityAddRealEstateBinding binding;

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
        binding = ActivityAddRealEstateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mNewRealEstate = (RealEstate) getIntent().getSerializableExtra(MainActivity.ADD_REAL_ESTATE);
        othersPhotosList = new ArrayList<>();
        initializeSpinners();

        selectMainPhotoIntent();
        selectOtherPhotosIntent();

        initializeButtonSelectEntryDate();
        initializeButtonSelectSaleDate();

        initializeFinishButton();
    }

    private void initializeSpinners() {
        Spinner spinnerType = binding.activityAddRealEstateTypeSpinner;
        Spinner spinnerStatus = binding.activityAddRealEstateStatusSpinner;
        Spinner spinnerAgent = binding.activityAddRealEstateAgentSpinner;

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
        if (adapterView.getId() == R.id.activity_add_real_estate_type_spinner)
            type = adapterView.getItemAtPosition(i).toString();

        if (adapterView.getId() == R.id.activity_add_real_estate_status_spinner)
            status = adapterView.getItemAtPosition(i).toString();

        if (adapterView.getId() == R.id.activity_add_real_estate_agent_spinner)
            agent = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void selectMainPhotoIntent() {
        binding.activityAddRealEstateButtonMainPhoto.setOnClickListener(view -> {
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
        binding.activityAddRealEstatePickPhotosButton.setOnClickListener(view -> {
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
        binding.activityAddRealEstateEntryDateButton.setOnClickListener(view ->
                selectDate(binding.activityAddRealEstateEntryDateEditText));

    }

    private void initializeButtonSelectSaleDate() {
        binding.activityAddRealEstateSaleDateButton.setOnClickListener(view ->
                selectDate(binding.activityAddRealEstateSaleDateEditText));
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

    private void setNewRealEstateValue() {
        mNewRealEstate.setType(type);
        mNewRealEstate.setStatus(status);
        mNewRealEstate.setAgent(agent);

        String firstLocation = binding.activityAddRealEstateFirstLocationEditText.getText().toString();
        String price = binding.activityAddRealEstatePriceEditText.getText().toString();
        int surface = Integer.parseInt(binding.activityAddRealEstateSurfaceEditText.getText().toString());
        int numberOfRooms = Integer.parseInt(
                binding.activityAddRealEstateNumberOfRoomsEditText.getText().toString());
        int numberOfBathrooms = Integer.parseInt(
                binding.activityAddRealEstateNumberOfBathroomsEditText.getText().toString());
        int numberOfBedrooms = Integer.parseInt(binding
                .activityAddRealEstateNumberOfBedroomsEditText.getText().toString());
        String secondLocation = binding.activityAddRealEstateAddressEditText.getText().toString();
        String entryDate = binding.activityAddRealEstateEntryDateEditText.getText().toString();
        String saleDate = binding.activityAddRealEstateSaleDateEditText.getText().toString();

        BitmapDrawable mainPhoto = (BitmapDrawable) binding.activityAddRealEstateMainPhoto.getDrawable();
        Bitmap mainPhotoBitmap = mainPhoto.getBitmap();
        String mainPhotoBitmapToString = RealEstatePhotos.bitMapToString(mainPhotoBitmap);

        mNewRealEstate.setFirstLocation(firstLocation);
        mNewRealEstate.setPrice(price);
        mNewRealEstate.setMainPhotoString(mainPhotoBitmapToString);
        //mNewRealEstate.setOthersPhotos is in onActivityResult
        mNewRealEstate.setSurface(surface);
        mNewRealEstate.setNumberOfRooms(numberOfRooms);
        mNewRealEstate.setNumberOfBathrooms(numberOfBathrooms);
        mNewRealEstate.setNumberOfBedrooms(numberOfBedrooms);
        mNewRealEstate.setSecondLocation(secondLocation);
        mNewRealEstate.setEntryDate(entryDate);
        mNewRealEstate.setDateOfSale(saleDate);

    }

    private void initializeFinishButton() {
        //Set mNewRealEstate all value selected previously
        binding.activityAddRealEstateOkButton.setOnClickListener(view -> {
            setNewRealEstateValue();

            Intent intent = new Intent();
            intent.putExtra(MainActivity.ADD_REAL_ESTATE, mNewRealEstate);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

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
                binding.activityAddRealEstateMainPhoto.setImageBitmap(selectedImage);
            }
        } else if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                //set image in ImageView from gallery
                Uri selectedImage = data.getData();
                binding.activityAddRealEstateMainPhoto.setImageURI(selectedImage);
            }
        } else if (requestCode == TAKE_PICTURE_FOR_OTHER_PHOTOS) {
            if (resultCode == RESULT_OK) {
                //set image in othersPhotosList from camera
                Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                RealEstatePhotos realEstatePhotos = new RealEstatePhotos();

                Uri imageUri = RealEstatePhotos.bitmapToImageUri(this, selectedImage);
                String imageUriToString = RealEstatePhotos.uriToString(imageUri);
                realEstatePhotos.setPhotoUri(imageUriToString);
                othersPhotosList.add(realEstatePhotos);
                mNewRealEstate.setPhotos(othersPhotosList);

            }
        } else if (requestCode == PICK_PHOTO_FOR_OTHER_PHOTOS) {
            if (resultCode == RESULT_OK) {
                //set image in othersPhotosList from gallery
                Uri selectedImage = data.getData();
                RealEstatePhotos realEstatePhotos = new RealEstatePhotos();

                String imageUriToString = RealEstatePhotos.uriToString(selectedImage);
                realEstatePhotos.setPhotoUri(imageUriToString);
                othersPhotosList.add(realEstatePhotos);
                mNewRealEstate.setPhotos(othersPhotosList);
            }
        }
    }
}