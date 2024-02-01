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
        DataManager.sort(quizDataManager.getPhotoList(), DataManager.isListSorted(quizDataManager.getPhotoList())); //

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
        DataManager.sort(quizDataManager.getPhotoList(), !DataManager.isListSorted(quizDataManager.getPhotoList()));
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
}

