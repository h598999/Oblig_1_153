package com.example.quiz;

import android.net.Uri;

public class PhotoInfo {

    private String name;
    private Uri photoUri;
    int index;

    public PhotoInfo(String name, Uri photoUri){
        this.name = name;
        this.photoUri = photoUri;
    }

    public String getName() {
        return name;
    }

    public Uri getPhotoUri(){
        return photoUri;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }
}
