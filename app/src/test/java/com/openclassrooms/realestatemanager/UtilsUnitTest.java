package com.openclassrooms.realestatemanager;

import com.openclassrooms.realestatemanager.service.DateUtils;
import com.openclassrooms.realestatemanager.service.Utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class UtilsUnitTest {


    @Before
    public void setUp() {
    }

    @Test
    public void assertConvertDollarToEuroWorks() {
        int dollars = 8;
        assertEquals(Utils.convertDollarToEuro(dollars), Math.round(dollars * 0.812));
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

    @Test
    public void assertGetDaysBetweenTwoDatesWorks() {
        long days = DateUtils.getDaysBetweenDates("01/11/20", "30/11/20");
        long daysExpected = 29;

        long daysUnexpected= 30;

        assertEquals(daysExpected, days);
        assertNotEquals(daysUnexpected, days);
    }

    @Test
    public void assertReturnTodayDateWorks() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate =  dateFormat.format(new Date());
        assertEquals(DateUtils.returnTodayDate(), currentDate);
    }

}