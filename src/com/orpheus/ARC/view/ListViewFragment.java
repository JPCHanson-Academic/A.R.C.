package com.orpheus.ARC.view;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.*;

import com.orpheus.ARC.testing.TestWorld;

public class ListViewFragment extends ListFragment implements ArcView
{
    private final String LATITUDE   = "1";
    private final String LONGDITUDE = "2";
    private final String TITLE      = "3";
    private final String CONTENT    = "4";
    private final String TIMESTAMP  = "5";

    Map<Long, Map<String, String>> postFullList 
    = new HashMap<Long, Map<String, String>>();

    ArrayList<String> postPreviewList = new ArrayList<String>();

    ArrayAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreateView(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter
        (getActivity(), android.R.layout.simple_list_item_1, postPreviewList);
        setListAdapter(adapter);

        TestWorld testWorld = new TestWorld(this.getActivity());
        testWorld.updateWorld(this);
    }

    public void addPost
    (double lat, double lng, String title, String content, long id, int timeSSMMHHDDMMYY)
    {

        individualPost(lat,lng,title,content,id,timeSSMMHHDDMMYY);
        postFullList.put(id, individualPost(lat,lng,title,content,id,timeSSMMHHDDMMYY));

        postPreviewList.add(title);
        adapter.notifyDataSetChanged();
    }

    public void clearPosts()
    {
        postPreviewList.clear();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {

        Long item = (Long) getListAdapter().getItem(position);

        Toast.makeText(getActivity(), postFullList.get(item).get(TITLE).toString(), 5);

//        new postDialog(this, item, item);
    }

//    
//    move everything that uses this signiature to use a Post object
//    
    private final Map<String , String> individualPost
    (double lat, double lng, String title, String content, long id, int timeSSMMHHDDMMYY)
    {
        Map<String, String> individualPost = new HashMap<String, String>();

        individualPost.put(LATITUDE, ""+lat);
        individualPost.put(LONGDITUDE, ""+lng);
        individualPost.put(TITLE, title);
        individualPost.put(CONTENT, content);
        individualPost.put(TIMESTAMP, ""+timeSSMMHHDDMMYY);

        return individualPost;
    }
}
