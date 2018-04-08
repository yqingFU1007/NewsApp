package com.example.asa.news;

public class News {


    private String mTitle;
    private String mCatagory;
    private String mTime;

    //Constructor
    public News(String title, String catagory, String time) {
        mTitle = title;
        mCatagory = catagory;
        mTime = time;
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
}
