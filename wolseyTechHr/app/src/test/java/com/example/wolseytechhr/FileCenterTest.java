package com.example.wolseytechhr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wolseytechhr.FileCenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileCenterTest {

    @Mock
    private Context mockContext;

    @Mock
    private FileCenter mockFileCenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testOpenProfile() {
        when(mockFileCenter.findViewById(anyInt())).thenReturn(new View(mockContext));
        when(mockFileCenter.getIntent()).thenReturn(new Intent());

        mockFileCenter.openProfile();

        // Assert that findViewById and getIntent methods were called
        verify(mockFileCenter, times(1)).findViewById(anyInt());
        verify(mockFileCenter, times(1)).getIntent();

    }

    @Test
    public void testOpenTimeSheet() {
        when(mockFileCenter.findViewById(anyInt())).thenReturn(new View(mockContext));
        when(mockFileCenter.getIntent()).thenReturn(new Intent());

        mockFileCenter.openTimeSheet();

        // Assert that findViewById and getIntent methods were called
        verify(mockFileCenter, times(1)).findViewById(anyInt());
        verify(mockFileCenter, times(1)).getIntent();

    }

    @Test
    public void testOpenSettings() {
        when(mockFileCenter.findViewById(anyInt())).thenReturn(new View(mockContext));
        when(mockFileCenter.getSharedPreferences(anyString(), anyInt())).thenReturn(mock(SharedPreferences.class));
        when(mockFileCenter.getIntent()).thenReturn(new Intent());

        mockFileCenter.openSettings();

        // Assert that findViewById, getSharedPreferences, and getIntent methods were called
        verify(mockFileCenter, times(1)).findViewById(anyInt());
        verify(mockFileCenter, times(1)).getSharedPreferences(anyString(), anyInt());
        verify(mockFileCenter, times(1)).getIntent();

    }

    @Test
    public void testLogout() {
        when(mockFileCenter.getSharedPreferences(anyString(), anyInt())).thenReturn(mock(SharedPreferences.class));
        when(mockFileCenter.getIntent()).thenReturn(new Intent());

        mockFileCenter.logout(mock(View.class));

        // Assert that getSharedPreferences and getIntent methods were called
        verify(mockFileCenter, times(1)).getSharedPreferences(anyString(), anyInt());
        verify(mockFileCenter, times(1)).getIntent();
    }

    @Test
    public void testOnItemSelected() {
        Spinner mockSpinner = mock(Spinner.class);
        AdapterView mockAdapterView = mock(AdapterView.class);

        when(mockAdapterView.getItemAtPosition(anyInt())).thenReturn("Profile");
        when(mockSpinner.getContext()).thenReturn(mockContext);
        when(mockFileCenter.getSharedPreferences(anyString(), anyInt())).thenReturn(mock(SharedPreferences.class));

        mockFileCenter.onItemSelected(mockAdapterView, mock(View.class), 0, 0);

    }

    @Test
    public void testCreateNavbar() {
        Spinner mockSpinner = mock(Spinner.class);

        when(mockFileCenter.findViewById(anyInt())).thenReturn(mockSpinner);
        when(mockSpinner.getContext()).thenReturn(mockContext);

        mockFileCenter.createNavbar();

        // Assert that findViewById and getContext methods were called
        verify(mockFileCenter, times(1)).findViewById(anyInt());
        verify(mockSpinner, times(1)).getContext();
    }

}
