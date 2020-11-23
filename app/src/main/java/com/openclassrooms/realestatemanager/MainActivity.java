package com.openclassrooms.realestatemanager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class MainActivity extends AppCompatActivity {

    private static final int RC_LOCATION = 10;

    public static final String ADD_REAL_ESTATE = "ADD_REAL_ESTATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissionFineLocation();

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container_view_list,
                new RealEstateFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.menu_add):
                Intent intent = new Intent(this, AddRealEstateActivity.class);
                RealEstate realEstate = new RealEstate();
                intent.putExtra(ADD_REAL_ESTATE, realEstate);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void requestPermissionFineLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        } else {
            getLocatePermission();
        }
    }

    private void getLocatePermission() {
        ActivityCompat.requestPermissions(this, new String[]{
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