package com.orpheus.ARC.view;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.beyondar.android.fragment.BeyondarFragmentSupport;
import com.beyondar.android.world.World;
import com.beyondar.android.world.GeoObject;
import com.beyondar.android.world.BeyondarObject;
import com.beyondar.android.view.OnClickBeyondarObjectListener;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.Dialog;
import java.util.Map;
import java.util.HashMap;
import com.orpheus.ARC.testing.TestWorld;


/**
* This activity encapsulates the CameraView view (in this MVP, or attempt at, implementation)
*
*@AUTHOR orpheus
**/
public class CameraActivity extends FragmentActivity implements OnClickBeyondarObjectListener , ArcView
{
    /** provides access to methods supporting camera/preview/overlay **/
    private BeyondarFragmentSupport mBeyondarFragment;
    /** holds data to be displayed in beyondar fragment **/
    private World world;
    /** 
    * as beyondar GeoObjects only have one String field, this is storageFor/accessTo
    * post content.
    **/
    private Map<Long, String> postContentMap;
    /** C **/
    private double currentLat;
    private double currentLong;
    
    /** 
     *Called when the activity is first created. 
     **/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {   
        super.onCreate(savedInstanceState);
        postContentMap = new HashMap<Long, String>();
        //load the layout from the xml file
        loadLayoutFromXML();
        //create the world to render
        TestWorld testyworld = new TestWorld(this);
        testyworld.updateWorld(this);
    }

    /**
     * handles displaying the post as a dialog on screen
     **/
    @Override
    public void onClickBeyondarObject(ArrayList<BeyondarObject> beyondarObjects)
    {
        Long postID = beyondarObjects.get(0).getId();
        String postContent = postContentMap.get(postID);
        String postTitle = beyondarObjects.get(0).getName();
        new postDialog(this, postContent, postTitle); 
    }

    /**
     * Add a post to the view
     **/
    public void addPost
    (double lat, double lng, String title, String content, long id, int timeSSMMHHDDMMYY)
    {
        GeoObject objectToAdd = new GeoObject(id);
        objectToAdd.setGeoPosition(lat, lng);
        objectToAdd.setImageResource(R.drawable.ic_launcher);
        objectToAdd.setName(title);
        world.addBeyondarObject(objectToAdd);
        mBeyondarFragment.setWorld(world);

        //add post content to internal Map
        postContentMap.put(objectToAdd.getId() , content);
    }

    /**
     * clear all posts from the view
     **/
    public void clearPosts()
    {
        while(world.getBeyondarObjectList(0).size() != 1)
        {
            world.remove(world.getBeyondarObjectList(0).get(0));
        }
        postContentMap.clear();
    }
    
    public void updatePosition()
    {
        
    }
    
    /**
     * load the xml layout file, initialise beyondar fragment, set onClickListener
     * and initialise the world (mwahahahaha).
     **/
    private void loadLayoutFromXML()
    {
        setContentView(R.layout.camera_activity);
        
        mBeyondarFragment = (BeyondarFragmentSupport)getSupportFragmentManager()
            .findFragmentById(R.id.beyondarFragment);
        mBeyondarFragment.setOnClickBeyondarObjectListener(this);
        mBeyondarFragment.setMaxDistanceToRender(1000f);
        mBeyondarFragment.setPullCloserDistance(100f);
        
        world = new World(this);
        world.setDefaultBitmap(R.drawable.ic_launcher, R.drawable.ic_launcher);
        world.setGeoPosition(51.534481d, -0.469590d);
        
        mBeyondarFragment.setWorld(world);
    }
}
