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
        this.sort(this.getPhotoList(), isListSorted(this.getPhotoList()));
    }

    public List<PhotoInfo> getPhotoList(){
        return photoList;
    }

    public void addPhoto(String name, Uri imageUri){
        photoList.add(new PhotoInfo(name, imageUri));
    }

    public static void sort(List<PhotoInfo> photoList, boolean isSorted) {
        if (isSorted) {
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName, Collections.reverseOrder()));
        } else {
            // Sort alphabetically
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName));
        }
    }

    public static boolean isListSorted(List<PhotoInfo> photoList) {
        String name1 = photoList.get(0).getName();
        String name2 = photoList.get(1).getName();

        // Check if the first two elements are in ascending order
        boolean isAscending = name1.compareTo(name2) <= 0;

        // Iterate through the rest of the list to ensure it is sorted
        for (int i = 1; i < photoList.size() - 1; i++) {
            name1 = photoList.get(i).getName();
            name2 = photoList.get(i + 1).getName();

            if ((isAscending && name1.compareTo(name2) > 0) || (!isAscending && name1.compareTo(name2) < 0)) {
                return false; // The list is not sorted
            }
        }
        return true; // The list is sorted
    }


}
