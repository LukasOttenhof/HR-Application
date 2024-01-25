package com.example.wolseytechhr;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TimesheetsDataTest {

    @Test
    public void testGetUserRawInfoFromServer() {
        // GIVEN
        String startDate = "2024-01-01";
        String endDate = "2024-01-15";
        String authCode = "30XONYJKS0RX2IQYGBUCDKGSB";

        TimesheetsData timesheetsData = new TimesheetsData(startDate, endDate, authCode);



        // making sure there is data
        assertEquals(timesheetsData.getIsNoData(), false);

    }

    @Test
    public void testConvertRawInfo() {
        // GIVEN
        String startDate = "2024-01-01";
        String endDate = "2024-01-15";
        String authCode = "30XONYJKS0RX2IQYGBUCDKGSB";

        TimesheetsData timesheetsData = new TimesheetsData(startDate, endDate, authCode);


        assertEquals(Integer.valueOf(timesheetsData.getConvertedInfo().get(1)[5]), Integer.valueOf(0));
    }

    @Test
    public void testNoDataScenario() {
        // GIVEN
        // Test when there is no data available
        String startDate = "2024-01-01";
        String endDate = "2024-01-15";
        String authCode = "INVALID_AUTH_CODE";

        TimesheetsData timesheetsData = new TimesheetsData(startDate, endDate, authCode);

        // Should be true to indicate no data
        assertEquals(timesheetsData.getIsNoData(), true);
    }



}
