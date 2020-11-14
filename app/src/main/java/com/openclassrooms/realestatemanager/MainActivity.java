package com.openclassrooms.realestatemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;

public class MainActivity extends AppCompatActivity{

private static final int RC_LOCATION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionFineLocation();

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_frame_layout,
                new RealEstateFragment()).commit();
    }

    private void requestPermissionFineLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            getLocatePermission();
        }
    }

    private void getLocatePermission() {
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RC_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                getLocatePermission();
            }
        }
    }
}