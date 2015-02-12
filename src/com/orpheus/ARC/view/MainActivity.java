package com.orpheus.ARC.view;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;


//import com.orpheus.ARC.testing.TestWorld;

public class MainActivity extends FragmentActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /**
    * onClick method, starts the Camera Activity
    **/
    public void startCamView(View view)
    {
     Intent camIntent = new Intent(this, CameraActivity.class);
     startActivity(camIntent);
    }

    /**
     * onClick method, starts the Map view
     **/
    public void startMapView(View view)
    {
        Intent mapIntent = new Intent(this, MapActivity.class);
        startActivity(mapIntent);
    }

    /**
     * onClick method, Starts the create post Activity
     **/
    public void startPostView(View view)
    {
        Intent postIntent = new Intent(this, PostActivity.class);
        startActivity(postIntent);
    }
}
