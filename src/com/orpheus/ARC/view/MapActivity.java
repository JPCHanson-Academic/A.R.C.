package com.orpheus.ARC.view;


import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.support.v4.app.NavUtils;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapActivity;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.OverlayItem;
import com.mapquest.android.maps.ItemizedOverlay;
//import android.text.*;
import com.orpheus.ARC.testing.TestWorld;

public class MapActivity extends MapActivity implements ArcView
{
    private DefaultItemizedOverlay postOverlay;
    private Drawable icon;
    private MapView map;
    private MyLocationOverlay myLocationOverlay;
    private AnnotationView annotation;
    private GeoPoint currentLocation;
    private GeoPoint defaultLocation;

    

    /** 
     * Called when the activity is first created. 
     **/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        setupMapView();
        setupMyLocation();
        addPostOverlay();
        annotation = new AnnotationView(map);
        TestWorld testyworld = new TestWorld(this);
        testyworld.updateWorld(this);
    }

    /**
     * for adding a post to the map overlay
     **/
    public void addPost
    (double lat, double lng, String title, String content, long id, int timeSSMMHHDDMMYY)
    {
        OverlayItem Post = new OverlayItem(new GeoPoint(lat, lng)
                                           , title, content);
        postOverlay.addItem(Post);
    }

    /**
     * for use when filtering/sorting/etc
     **/
    public void clearPosts()
    {
        postOverlay.clear();
    }
  
    /**
     * we dont want to display a route hence.....false
     **/
    @Override
    protected boolean isRouteDisplayed(){return false;}

    /**
     *re-enable functions when activity resumes foreground
     **/
    @Override
    protected void onResume()
    {
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableCompass();
        super.onResume();
    }

    /**
     * disable functions when activity goes to background
     **/
    @Override
    protected void onPause()
    {        
        super.onPause();
        myLocationOverlay.disableCompass();
        myLocationOverlay.disableMyLocation();
    }

    /**
     * setup for MapView.
     **/
    private void setupMapView()
    {
        this.map = (MapView) findViewById(R.id.map);
        map.setBuiltInZoomControls(true);
    }

    /**
     * location setup and default conditions when map starts
     **/
    private void setupMyLocation()
    {
        defaultLocation = new GeoPoint(51.534481, -0.469590);
        map.getController().setCenter(defaultLocation);
        map.getController().setZoom(16);
        
        this.myLocationOverlay = new MyLocationOverlay(this, map);
        myLocationOverlay.enableCompass();
        myLocationOverlay.enableMyLocation();

        currentLocation = myLocationOverlay.getMyLocation();
        map.getController().animateTo(currentLocation);
        
        map.getOverlays().add(myLocationOverlay);
        myLocationOverlay.setFollowing(true);
    }

    /**
     * add the overlay showing the posts over the map.
     **/
    private void addPostOverlay()
    {
        icon = getResources().getDrawable(R.drawable.ic_launcher);
        postOverlay = new DefaultItemizedOverlay(icon);
        this.addTapListener(postOverlay); 
        map.getOverlays().add(postOverlay);
    }

    /**
     * objects of type ItemizedOverlay have an inner class defining the 
     * tap-listener, there must be a nicer wy to do this.
     **/
    private void addTapListener(final DefaultItemizedOverlay postOverlay)
    {
        postOverlay.setTapListener(new ItemizedOverlay.OverlayTapListener()
            {
                @Override
                public void onTap(GeoPoint pt, MapView mapView)
                {
                    int lastTouchedIndex = postOverlay.getLastFocusedIndex();

                    if(lastTouchedIndex>-1)
                    {
                        OverlayItem tapped = postOverlay.getItem(lastTouchedIndex);
                        annotation.showAnnotationView(tapped);
                    }
                }
            });       
    }
}

