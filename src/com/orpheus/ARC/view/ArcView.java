package com.orpheus.ARC.view;

public interface ArcView
{

    public void addPost(double lat, double lng, String title, String content, long id, int timeSSMMHHDDMMYY);
    
    
    public void clearPosts();
}
