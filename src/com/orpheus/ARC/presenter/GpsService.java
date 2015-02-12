package com.orpheus.ARC.presenter;

import android.location.*;
import android.app.*;
import android.os.*;
import android.content.*;
import android.provider.*; 
import android.util.*;
import android.net.*;



public class GpsService extends Service implements LocationListener
{

    private final Context mContext;

    boolean isGPSon = false;
    boolean isNetEnabled = false;
    boolean canGetLocation = false;
    Location location;
    double latitude;
    double longditude;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; //10M
    private static final long MIN_TIME_BW_UPDATES = 1000*1 ;//ONCE PER SEC

    private LocationManager locationManager; //was protected

    public GpsService(Context context)
    {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation()
    {
        //all hail the location manager, here for all your location needs
        locationManager = (LocationManager)mContext.getSystemService(LOCATION_SERVICE);

        isGPSon = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                               MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

        Location gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location netLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(gpsLocation != null){location = gpsLocation;}
        else{location = netLocation;}
        return location;
    }

    public boolean isGpsAvail()
    {
        if (isGPSon == true)
        {return true;}
        else
        {return false;}
    }

    public boolean isNetAvail()
    {
        if (isNetEnabled == true)
        {return true;}
        else
        {return false;}
    }
    
    
    @Override
    public void onStatusChanged(String p1, int p2, Bundle p3)
    {
        // TODO: Implement this method
    }

    @Override
    public void onLocationChanged(Location p1)
    {
        // TODO: Implement this method
    }

    @Override
    public void onProviderEnabled(String p1)
    {
        // TODO: Implement this method
    }

    @Override
    public void onProviderDisabled(String p1)
    {
        // TODO: Implement this method
    }

    @Override
    public IBinder onBind(Intent p1)
    {
        // TODO: Implement this method
        return null;
    }
    
}
