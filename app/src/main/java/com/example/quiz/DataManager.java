package com.example.quiz;

import android.app.Application;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        this.sort(this.getPhotoList(), true);
    }

    public List<PhotoInfo> getPhotoList(){
        return photoList;
    }

    public void addPhoto(String name, Uri imageUri){
        photoList.add(new PhotoInfo(name, imageUri));
    }

    public static void sort(List<PhotoInfo> photoList, boolean isSorted) {
        if (!isSorted) {
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName, Collections.reverseOrder()));
        } else {
            // Sort alphabetically
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName));
        }
    }

    public static boolean isListSorted(List<PhotoInfo> list) {
        // Check if the list is empty or has only one element
        if (list == null || list.size() <= 1) {
            return true;
        }
        // Iterate through the list to check for alphabetical order
        for (int i = 0; i < 1; i++) {
            if (list.get(i).getName().compareTo(list.get(i + 1).getName()) > 0) {
                return false;
            }
        }
        // If no out-of-order elements found, the list is sorted
        return true;
    }
}
