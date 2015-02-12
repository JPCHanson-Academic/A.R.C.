package com.orpheus.ARC.view;

import android.support.v4.app.Fragment;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

public class ActionBarFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.arc_action_bar, container, false);
    }
    
    public void switchToCamView(View view)
    {
        Toast.makeText(this.getActivity(), "itworks", 10).show();
        
    }
}
