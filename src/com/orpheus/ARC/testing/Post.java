package com.orpheus.ARC.testing;

/**
* IS A post, HAS attributes that it must be initialised with AND can be set
* using methods. It also has OPTIONAL attributes that MAY be set(TODO int ICON)
*
*@AUTHOR orpheus
**/
public class Post
{
    private long ID;
    private String USERNAME;
    private String TITLE;
    private String CONTENT;
    private double LATITUDE;
    private double LONGDITUDE;
    private int    TIME;

    /**
    * construct a post object
    **/
    public Post(long id,String userName, String title, String content
                         , double latitude, double longditude, int time)
    {
        this.ID           = id;
        this.USERNAME     = userName;
        this.TITLE        = title;
        this.CONTENT      = content;
        this.LATITUDE     = latitude;
        this.LONGDITUDE   = longditude;
        this.TIME         = time;
    }

//----------------------------------GETTERS----------------------------------------
    /**
     * get the ID of this post

     **/
    public long getID()
    {
        return ID;
    }

    /**
     * get the username associated with this post
     **/
    public String getUSERNAME()
    {
        return USERNAME;
    }

    /**
     * get the title of this post
     **/
    public String getTITLE()
    {
        return TITLE;
    }
    
    /**
     * get the content of this post
     **/
    public String getCONTENT()
    {
        return CONTENT;
    }

    /**
     * get the latitude of this post
     **/
    public double getLatitude()
    {
        return LATITUDE;
    }


    /**
     * get the longditude of this post
     **/
    public double getLongditude()
    {
        return LONGDITUDE;
    }
   
    /**
     * get the timestamp of this post
     **/
    public int getTime()
    {
        return TIME;
    }
    
    
//---------------------------------------SETTERS----------------------------------
    /**
     * set the ID of this post
     **/
    public void setID(long iD)
    {
        ID = iD;
    }

    /**
     * set the username associated with this post
     **/
    public void setUSERNAME(String uSERNAME)
    {
        USERNAME = uSERNAME;
    }

    /**
     * set the title of this post
     **/
    public void setTITLE(String tITLE)
    {
        TITLE = tITLE;
    }

    /**
     * set the content of this post
     **/    
    public void setCONTENT(String cONTENT)
    {
        CONTENT = cONTENT;
    }
    
    /**
     * set the latitude and longditude of this post
     **/
     public void setGeoPosition(double latitude, double longditude)
     {
         this.LATITUDE = latitude;
         this.LONGDITUDE = longditude;
     }     
     
    /**
     * SET the longditude of this post
     **/
    public void setTime(int timeSSMMHHDDMMYY)
    {
        this.TIME = timeSSMMHHDDMMYY;
    }
    
}
