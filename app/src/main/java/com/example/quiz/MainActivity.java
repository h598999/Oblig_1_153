package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Boolean isReady;
    private DataManager quizDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quizButton = findViewById(R.id.quiz_Button);
        Button galleryButton = findViewById(R.id.gallery_Button);
        quizDataManager = (DataManager) getApplication();


        quizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class );
                startActivityForResult(intent, 0);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class );
                startActivity(intent);
            }
        });

        quizDataManager.getPhotoList().forEach( t -> {
            Log.d("Images", "Name: "  + t.getName() + " Uri: " + t.getPhotoUri());
        });
    }
}