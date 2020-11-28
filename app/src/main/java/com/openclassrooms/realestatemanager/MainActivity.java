package com.openclassrooms.realestatemanager;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.openclassrooms.realestatemanager.databinding.ActivityMainBinding;
import com.openclassrooms.realestatemanager.fragment.MapsFragment;
import com.openclassrooms.realestatemanager.fragment.RealEstateFragment;
import com.openclassrooms.realestatemanager.fragment.RealEstateViewModel;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.MyRealEstateHandlerThread;

public class MainActivity extends AppCompatActivity {

    private static final int ALL_PERMISSIONS = 10;

    public static final String ADD_REAL_ESTATE = "ADD_REAL_ESTATE";

    public static final int ADD_REAL_ESTATE_REQUEST_CODE = 100;

    public static final int NOTIFICATION_REQUEST_CODE = 20;

    private ActivityMainBinding binding;


    private RealEstateViewModel viewModel;

    private MyRealEstateHandlerThread myRealEstateHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkPermissionsGranted();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_fragment_container_view_list,
                        new RealEstateFragment()).commit();

        initializeBottomNavigationView();

        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);

        myRealEstateHandlerThread = new MyRealEstateHandlerThread(
                "InsertRealEstateInDatabase");

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
                Intent intent = new Intent(this, AddOrEditRealEstateActivity.class);
                RealEstate realEstate = new RealEstate();
                intent.putExtra(ADD_REAL_ESTATE, realEstate);
                startActivityForResult(intent, ADD_REAL_ESTATE_REQUEST_CODE);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_main_fragment_container_view_list,
                                    new RealEstateFragment()).commit();
                    break;
                case R.id.page2:
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.activity_main_fragment_container_view_list, new MapsFragment())
                            .commit();
                    break;

            }
            return true;
        });
    }

    private void checkPermissionsGranted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            getPermissions();
        }
    }

    private void getPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, ALL_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == ALL_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] ==
                    PackageManager.PERMISSION_GRANTED) {

            } else {
                getPermissions();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_REAL_ESTATE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                RealEstate newRealEstate = (RealEstate)
                        data.getSerializableExtra(MainActivity.ADD_REAL_ESTATE);

                myRealEstateHandlerThread.startCreateRealEstateHandler(newRealEstate, viewModel);

                showNotificationOnAddRealEstate();
            }
        }

    }

    private void showNotificationOnAddRealEstate() {
        String CHANNEL_ID = "notification_add_real_estate";
        String title = "RealEstateManager";
        String message = "Real Estate correctly added";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                getApplicationContext(), CHANNEL_ID);
        notificationBuilder
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "RealEstateAddChannel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, channelName,
                    importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(NOTIFICATION_REQUEST_CODE, notificationBuilder.build());


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealEstateHandlerThread.quit();
    }
}