package com.openclassrooms.realestatemanager.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.realestatemanager.database.dao.RealEstateDao;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;
import com.openclassrooms.realestatemanager.service.RealEstateDescription;
import com.openclassrooms.realestatemanager.service.RealEstatePhotoService;
import com.openclassrooms.realestatemanager.service.RealEstateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RealEstate.class}, version = 1, exportSchema = false)
@TypeConverters(RealEstateTypeConverter.class)
public abstract class MyRealEstateDatabase extends RoomDatabase {

    // --- SINGLETON ---
      public static volatile MyRealEstateDatabase  INSTANCE;

    // --- DAO ---
    public abstract RealEstateDao realEstateDao();

    // Executors
    private static final int NUMBER_OF_THREAD = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    // --- INSTANCE --
    public static MyRealEstateDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRealEstateDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRealEstateDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                databaseWriteExecutor.execute(() -> {
                    RealEstateDao realEstateDao = INSTANCE.realEstateDao();

                    realEstateDao.deleteAll();

                     RealEstate realEstate1 = new RealEstate(1,"Loft", "$13,950,000",
                             423, 8, 4, 4,
                             RealEstateDescription.returnFirstDescription(),
                             "https://i.ibb.co/WKx9zZj/Loft-Manhattan.jpg",
                             RealEstatePhotoService.getRealEstatePhotos1(),
                             "Manhattan",
                             "41 Great Jones Street Penthouse\n" +
                                     "Lafayette\n" +
                                     "NoHo\n" +
                                     "New York",
                             40.726649, -73.992833,
                             "For sale", "07/11/2020", null,
                             "Jessica C. Campbell",
                             "https://i.ibb.co/0MZZf43/Jessica-CCampbell.jpg");

                     RealEstate realEstate2 = new RealEstate(2,"Penthouse", "$5,400,000", 582,
                             7, 4, 3,
                             RealEstateDescription.returnSecondDescription(),
                             "https://i.ibb.co/9NXstNR/Brooklyn-Penthouse.jpg",
                             RealEstatePhotoService.getRealEstatePhotos2(),
                             "Brooklyn"
                             , "The Gretsch Building PH1A\n" +
                             "60 Broadway\n" +
                             "Wythe & Berry\n" +
                             "Williamsburg\n" +
                             "Brooklyn", 40.710313, -73.966295,
                             "For sale", "08/11/2020", null,
                             "Christian Haag",
                             "https://i.ibb.co/Y71g9LB/Christian-Haag.jpg");

                     realEstateDao.insertRealEstate(realEstate1);
                     realEstateDao.insertRealEstate(realEstate2);
                });

            }
        };
    }

}
