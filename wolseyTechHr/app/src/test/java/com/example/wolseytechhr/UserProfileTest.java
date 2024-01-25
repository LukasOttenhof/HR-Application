package com.example.wolseytechhr;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wolseytechhr.UserProfile;
import com.example.wolseytechhr.RetrieveEmployeeProfile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileTest {

    @Mock
    private SharedPreferences mockSharedPreferences;

    @InjectMocks
    private UserProfile userProfile;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testOpenSavedData() {
        // Set up mock data for SharedPreferences
        when(mockSharedPreferences.getBoolean("saveProfile", false)).thenReturn(true);
        when(mockSharedPreferences.getString("address1", null)).thenReturn("MockAddress1");
        // Add more mock data as needed

        // Mock RetrieveEmployeeProfile class
        RetrieveEmployeeProfile mockEmployeeProfile = mock(RetrieveEmployeeProfile.class);
        when(mockEmployeeProfile.getAddress1()).thenReturn("MockAddress1");
        // Add more mock behavior as needed

        // Mock other necessary setup for the test

        userProfile.openSavedData();

        // Assert that homeAddress TextView displays the expected address
        TextView homeAddress = userProfile.findViewById(R.id.home_address);
        assertNotNull(homeAddress);
        assertEquals("MockAddress1", homeAddress.getText().toString());
    }

    @Test
    public void testDisplayEmployeeInfo() {
        // Mock RetrieveEmployeeProfile class
        RetrieveEmployeeProfile mockEmployeeProfile = mock(RetrieveEmployeeProfile.class);
        when(mockEmployeeProfile.getAddress1()).thenReturn("MockAddress1");
        // Add more mock behavior as needed

        // Mock other necessary setup for the test

        userProfile.displayEmployeeInfo();

        // Assert that homeAddress TextView displays the expected address
        TextView homeAddress = userProfile.findViewById(R.id.home_address);
        assertNotNull(homeAddress);
        assertEquals("MockAddress1", homeAddress.getText().toString());

    }

}
