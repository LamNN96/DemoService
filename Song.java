package com.lamnn.demoservice;

public class Song {

    private String mTitle;
    private String mPath;

    public Song() {
    }

    public Song(String mTitle, String mPath) {
        this.mTitle = mTitle;
        this.mPath = mPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }
}
