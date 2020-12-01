package com.openclassrooms.realestatemanager;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.openclassrooms.realestatemanager.database.MyRealEstateDatabase;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.LiveDataTestUtil;
import com.openclassrooms.realestatemanager.service.RealEstateDescription;
import com.openclassrooms.realestatemanager.service.RealEstatePhotoService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RealEstateDaoTest {

    // FOR DATA
    private MyRealEstateDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                MyRealEstateDatabase.class)
                .allowMainThreadQueries()
                .build();
    }


    // DATA SET FOR TEST
    RealEstate realEstateToTest = new RealEstate(1, "Loft", 13950000,
            423, 8, 4, 4,
            RealEstateDescription.returnFirstDescription(),
            "https://i.ibb.co/WKx9zZj/Loft-Manhattan.jpg",
            null, RealEstatePhotoService.getRealEstatePhotos1(),
            "Manhattan",
            "41 Great Jones Street Penthouse\n" +
                    "Lafayette\n" +
                    "NoHo\n" +
                    "New York", "Shop",
            40.726649, -73.992833,
            "For sale", "07/11/2020", null,
            "Jessica C. Campbell",
            "https://i.ibb.co/0MZZf43/Jessica-CCampbell.jpg",
            "KBN60UbSoSM");

    @Test
    public void getRealEstatesWhenNoRealEstateInserted() throws InterruptedException {
        List<RealEstate> realEstates = LiveDataTestUtil.getValue(this.database.realEstateDao()
                .getRealEstates());

        RealEstate realEstate = LiveDataTestUtil.getValue(this.database.realEstateDao().getRealEstate(1));

        assertTrue(realEstates.isEmpty());
        assertNull(realEstate);
    }

    @Test
    public void insertAndGetRealEstate() throws InterruptedException {
        this.database.realEstateDao().insertRealEstate(realEstateToTest);

        RealEstate realEstate = LiveDataTestUtil.getValue(this.database.realEstateDao()
                .getRealEstate(1));
        List<RealEstate> realEstates = LiveDataTestUtil.getValue(this.database.realEstateDao()
        .getRealEstates());

        assertNotNull(realEstate);
        assertEquals(1, realEstate.getId());

        assertEquals(1, realEstates.size());

    }

    @Test
    public void insertAndUpdateRealEstate() throws InterruptedException {
        this.database.realEstateDao().insertRealEstate(realEstateToTest);

        RealEstate realEstate = LiveDataTestUtil.getValue(this.database.realEstateDao()
                .getRealEstate(1));

        assertEquals( "For sale" ,realEstate.getStatus());

        realEstate.setStatus("Sold");
        this.database.realEstateDao().updateRealEstate(realEstate);

        assertEquals("Sold", realEstate.getStatus());
    }

    @Test
    public void insertAndDeleteAllRealEstate()  throws InterruptedException{
        this.database.realEstateDao().insertRealEstate(realEstateToTest);

        List<RealEstate> realEstates = LiveDataTestUtil.getValue(this.database.realEstateDao()
                .getRealEstates());

        assertEquals(1, realEstates.size());

        this.database.realEstateDao().deleteAll();

        realEstates = LiveDataTestUtil.getValue(this.database.realEstateDao().getRealEstates());
        assertEquals(0, realEstates.size());
    }


    @After
    public void closeDb() throws Exception {
        database.close();
    }



}
