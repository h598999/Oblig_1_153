package com.example.quiz;

import android.net.Uri;

public class PhotoInfo {

    private String name;
    private Uri photoUri;

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
}
