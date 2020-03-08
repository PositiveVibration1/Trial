package com.example.trial;

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public ExampleItem(String mCreator, int mLikes, int mImageUrl) {

        this.mCreator = mCreator;
        this.mLikes = mLikes;
        this.mImageUrl = String.valueOf(mImageUrl);
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }
}
