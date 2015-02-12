package com.orpheus.ARC.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.os.SystemClock;
import java.util.Calendar;

import com.orpheus.ARC.testing.TestWorld;

public class PostActivity extends Activity
{
    private TestWorld testWorld;
    private String title;
    private String subject;
    private double latitude;
    private double longditude;
    
    @Override
    public void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.post_view);
        
        testWorld = new TestWorld(this);
//        testWorld.updateModel();
    }
    
    public void updatePostList(View view)
    {
        setTitleString();
        setSubjectString();
        latitude = (double)testWorld.updatePosition().getLatitude();
        longditude = (double)testWorld.updatePosition().getLongitude();
        
        testWorld.updatePostList(latitude, longditude, title, subject);
    }
    
    private void setTitleString()
    {
        title = getDataFromView(R.id.title);
    }
    private void setSubjectString()
    {
        subject = getDataFromView(R.id.subjectArea);
    }
    
    /**
    * gets string from the layout to use in the construction of a post.
    **/
    private String getDataFromView(int id)
    {
        return ((EditText) findViewById(id))
            .getText().toString();
        
    }
}
