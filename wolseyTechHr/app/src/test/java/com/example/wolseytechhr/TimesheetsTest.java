package com.example.wolseytechhr;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

public class TimesheetsTest {

    private Timesheets timesheets;

    @Before
    public void setUp() {
        timesheets = new Timesheets();
    }

    @Test
    public void testOpenFirstDateWithInternetConnection() {
        ConnectivityManager connectivityManager = mock(ConnectivityManager.class);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(true);

        timesheets.openFirstDate(mock(View.class));

       // verify(timesheets).displayData(anyString(), anyString());
    }

    @Test
    public void testOpenFirstDateWithoutInternetConnection() {
        ConnectivityManager connectivityManager = mock(ConnectivityManager.class);
        NetworkInfo networkInfo = mock(NetworkInfo.class);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        when(networkInfo.isConnectedOrConnecting()).thenReturn(false);

       // timesheets.setConnectivityManager(connectivityManager);

        TextView noInternetDisplay = mock(TextView.class);
        when(timesheets.findViewById(R.id.noInternetDisplay)).thenReturn(noInternetDisplay);

        timesheets.openFirstDate(mock(View.class));

        verify(noInternetDisplay).setVisibility(View.VISIBLE);
       // verify(timesheets, never()).displayData(anyString(), anyString());
    }


}
