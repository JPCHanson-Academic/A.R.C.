package com.orpheus.ARC.view;


import android.app.Activity;
import android.content.Context;
import android.app.Dialog;
import android.app.AlertDialog;

/**
 * This class initialises a dialog containing a message and displays it on the 
 * Context.
 *
 * @AUTHOR []rpheus
 **/
public class postDialog extends Activity
{
    /** titleBar of the post */
    private String title;
    /**message to display*/
    private String content;
    /**the context of the activity the dialog should display in*/
    private Context activityToDisplayIn;

    /**
     * constructs a postDialog displaying default text
     **/
    public postDialog(Context context)
    {
        this.activityToDisplayIn = context;
        this.content = "empty";
        showDialog(10);
    }

    /**
     * constructs a postDialog displaying a string
     **/
    public postDialog(Context context, String popUpContent, String post_title)
    {
        this.activityToDisplayIn = context;
        this.content = popUpContent;    
        this.title = post_title;
        showDialog(10);
    }

    /**
     * set the dialog message string
     **/
    public void setContent(String popUpContent)
    {
        this.content = popUpContent;
    }

    /**
     * creates post dialog for camera view, contains all settings for post dialogs
     **/
    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case 10:
                AlertDialog.Builder builder = new AlertDialog.Builder(activityToDisplayIn);
                builder.setMessage(content);
                builder.setTitle(title);
                builder.setIcon(R.drawable.arc_logo);
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }

}

