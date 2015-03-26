package com.orpheus.ARC.testing;

import android.content.Context;
import android.os.SystemClock;
import java.util.Calendar;
import java.util.Stack;
import com.orpheus.ARC.view.ArcView;
import com.orpheus.ARC.view.CameraActivity;
import com.orpheus.ARC.view.MapActivity;
import com.orpheus.ARC.presenter.GpsService;
import android.location.Location;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import android.util.Log;
import android.os.Environment;
import android.media.*;
import java.io.*;
import android.text.*;

public class TestWorld
{

    private Stack<Post> postStack;
    private GpsService gpsService;
    private Location lastKnownLocation;
    private Context CONTEXT;
    
    //testing ctor
    public TestWorld(Context context)
    {
        postStack = new Stack<Post>();
        postStack.push(new Post(1L, "bobTheTest","post 1","hello all im bob test subject 1"
                               , 51.533483d, -0.473592d, 1));
        postStack.push(new Post(2L, "daveTheTest","post 2","hello all im dave test subject 2"
                               , 51.533066d, -0.472723d, 2));
        postStack.push(new Post(3l, "timTheTest","post 3","hello all im tim test subject 3"
                               , 51.534244d, -0.473201d, 3));
        postStack.push(new Post(4l, "cliveTheTest","post 4","hello all im clive test subject 4"
                               , 51.534517d, -0.469655d, 4));

        CONTEXT = context;
        gpsService = new GpsService(CONTEXT);
    }

    /**
     *
     **/
     public void updateWorld(ArcView arcView)
     {
         while(postStack.size() != 0)
         {
             Post postToAdd = postStack.pop();
             arcView.addPost(postToAdd.getLatitude(), postToAdd.getLongditude()
                             , postToAdd.getTITLE(), postToAdd.getCONTENT()
                             , postToAdd.getID(), postToAdd.getTime());

         }
     }
     
     public void updatePostList
     (double lat, double lng, String title, String content)
     {
         Calendar c = Calendar.getInstance(); 
         long id = System.currentTimeMillis()+SystemClock.uptimeMillis();
         int time = c.get( (int) c.getTimeInMillis() );
         double locationID = 1d;
         
         postStack.push(new Post(id, "userName", title, content, lat, lng, time));   
         
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://134.83.83.28:3306/app_data","l2grp","l2gp7");
            st=con.prepareStatement("insert into Location values(locationID, lat, lng);
                                    insert into Posts(postId,userId,timestamp,locationId,title,content)
                                                        values ("+id+",1111,"+time+","+locationID+","+title+","+content)");
        }
        catch(Exception e){}
         
     }
     
     public Location updatePosition()
     {
         return gpsService.getLocation();
     }
     
     
     public void updateModel(String title, String content, double lat, double lng)
     {
         try {
             FileOutputStream fileout = CONTEXT.openFileOutput("ARCTEST",CONTEXT.MODE_WORLD_READABLE);
             OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
             outputWriter.write(""+title+", "+content+", "+lat+","+lng);
             outputWriter.close();


         } catch (Exception e) {
             e.printStackTrace();
         } 
         
         File path = Environment.getExternalStoragePublicDirectory(
             Environment.DIRECTORY_DOCUMENTS);
         File file = new File(path, "ARC-DEMO.txt");
         
         try {
             // Make sure the Pictures directory exists.
             path.mkdirs();
//             InputStream is = new InputStr;
             OutputStream os = new FileOutputStream(file);
             byte[] data = new byte[5];
             os.write(data);
             os.close();

         } catch (IOException e) {
             // Unable to create file, likely because external storage is
             // not currently mounted.
             Log.w("ExternalStorage", "Error writing " + file, e);
         }
         
     }         
     
}
