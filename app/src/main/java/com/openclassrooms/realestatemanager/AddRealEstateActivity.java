package com.openclassrooms.realestatemanager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.openclassrooms.realestatemanager.databinding.ActivityAddRealEstateBinding;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.Calendar;

public class AddRealEstateActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRealEstateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mNewRealEstate = (RealEstate) getIntent().getSerializableExtra(MainActivity.ADD_REAL_ESTATE);
        othersPhotosList = new ArrayList<>();

        selectMainPhotoIntent();
        selectOtherPhotosIntent();

        initializeButtonSelectEntryDate();
        initializeButtonSelectSaleDate();
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