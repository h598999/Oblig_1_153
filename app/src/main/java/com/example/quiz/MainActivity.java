package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Boolean isReady;
    private DataManager quizDataManager;
    private Button quizButton;
    private Button galleryButton;

    private Switch difficultySwitch;

    private Boolean hardmode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizButton = findViewById(R.id.quiz_Button);
        galleryButton = findViewById(R.id.gallery_Button);
        quizDataManager = (DataManager) getApplication();
        difficultySwitch = findViewById(R.id.MainDifficultySwitch_MAIN);

        difficultySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            hardmode = isChecked;
        });

        quizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class );
                intent.putExtra("hardmode", hardmode);
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