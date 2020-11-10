package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.service.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class UtilsClassTest {


    @Before
    public void setUp() {
    }

    @Test
    public void assertConvertDollarToEuroWorks () {
        int dollars = 8;
        assertEquals(Utils.convertDollarToEuro(dollars), Math.round(dollars * 0.812 ));
    }

    @Test
    public void assertConvertEuroToDollarWorks() {
        int euros = 5;
        assertEquals(Utils.convertEuroToDollar(euros), Math.round(euros * 1.17));
    }

    @Test
    public void assertGetTodayDateWorks() {
     Date today = new Date();
     DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
     assertEquals(Utils.getTodayDate(), dateFormat.format(today));
    }
}