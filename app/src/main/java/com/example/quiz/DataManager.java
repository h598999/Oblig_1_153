package com.example.quiz;

import android.app.Application;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DataManager extends Application {

    private List<PhotoInfo> photoList;

    @Override
    public void onCreate(){
        super.onCreate();

        photoList = new ArrayList<>();
        photoList.add(new PhotoInfo("Java", Uri.parse("android.resource://com.example.quiz/" + R.drawable.java)));
        photoList.add(new PhotoInfo("Python", Uri.parse("android.resource://com.example.quiz/" + R.drawable.python)));
        photoList.add(new PhotoInfo("Neovim", Uri.parse("android.resource://com.example.quiz/" + R.drawable.neovim)));
    }

    public List<PhotoInfo> getPhotoList(){
        return photoList;
    }

    public void addPhoto(String name, Uri imageUri){
        photoList.add(new PhotoInfo(name, imageUri));
    }
}
