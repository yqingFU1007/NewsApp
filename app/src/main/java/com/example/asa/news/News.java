package com.example.asa.news;

public class News {


    private String mTitle;
    private String mCatagory;
    private String mTime;
    private String mURL;

    //Constructor
    public News(String title, String catagory, String time, String url) {
        mTitle = title;
        mCatagory = catagory;
        mTime = time;
        mURL = url;
    }

    //Getter fonctions
    public String getmTitle() {
        return mTitle;
    }

    public String getmCatagory() {
        return mCatagory;
    }


    public String getmTime() {
        return mTime;
    }

    public String getmURL() {
        return mURL;
    }
}
