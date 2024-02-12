package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryActivity extends AppCompatActivity {

    private DataManager quizDataManager;
    private Button backButton;

    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton sortButton;
    private RecyclerView imageViews;
    private PhotoAdapter adapter;


    @Override
    public void onCreate(Bundle instanceViewState) {
        super.onCreate(instanceViewState);
        setContentView(R.layout.activity_gallery);

        quizDataManager = (DataManager) getApplication();

        backButton = findViewById(R.id.backGallery_Button);
        sortButton = findViewById(R.id.GallerySort_Button);
        imageViews = (RecyclerView) findViewById(R.id.recyclerImageView_Gallery);
        adapter = new PhotoAdapter(quizDataManager, this);
        linearLayoutManager = new LinearLayoutManager(this);
        imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(linearLayoutManager);
        this.Sort();

        backButton.setOnClickListener(v -> finish());

        Button uploadActivity = findViewById(R.id.new_Button);
        uploadActivity.setOnClickListener(v -> {
            Intent intent = new Intent(GalleryActivity.this, UploadActivity.class);
            startActivity(intent);
        });
        sortButton.setOnClickListener(v -> Sort());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void Sort(){
        DataManager.sort(quizDataManager.getPhotoList(), !DataManager.isListSorted(quizDataManager.getPhotoList()));
        //imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageViews = (RecyclerView) findViewById(R.id.recyclerImageView_Gallery);

        adapter = new PhotoAdapter(quizDataManager, this);
        imageViews.setAdapter(adapter);
        imageViews.setLayoutManager(linearLayoutManager);
    }
}

