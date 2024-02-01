package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private DataManager quizDataManager;


    @Override
    public void onCreate(Bundle instanceViewState) {
        super.onCreate(instanceViewState);
        setContentView(R.layout.activity_gallery);

        quizDataManager = (DataManager) getApplication();
        quizDataManager.sort(quizDataManager.getPhotoList(), isListSorted(quizDataManager.getPhotoList())); //

        Button backButton = findViewById(R.id.backGallery_Button);
        FloatingActionButton sortButton = findViewById(R.id.GallerySort_Button);
        RecyclerView imageViews = (RecyclerView) findViewById(R.id.recyclerImageView_Gallery);
        PhotoAdapter adapter = new PhotoAdapter(quizDataManager, this);
        imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(new LinearLayoutManager(this));

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button uploadActivity = findViewById(R.id.new_Button);
        uploadActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sort(imageViews, adapter);
            }
        });
    }

    private void Sort(RecyclerView imageViews, PhotoAdapter adapter){
        sort(quizDataManager.getPhotoList(), isListSorted(quizDataManager.getPhotoList()));
        imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView imageViews = (RecyclerView) findViewById(R.id.recyclerImageView_Gallery);

        PhotoAdapter adapter = new PhotoAdapter(quizDataManager, this);
        imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(new LinearLayoutManager(this));
    }

    private void sort(List<PhotoInfo> photoList, boolean isSorted) {
        if (isSorted) {
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName, Collections.reverseOrder()));
        } else {
            // Sort alphabetically
            Collections.sort(photoList, Comparator.comparing(PhotoInfo::getName));
        }
    }
    private boolean isListSorted(List<PhotoInfo> photoList) {

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

