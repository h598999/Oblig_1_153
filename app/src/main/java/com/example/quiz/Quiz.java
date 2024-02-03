package com.example.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private DataManager quizDataManager;
    private int Score = 0;
    private int Plays = 0;
    private PhotoInfo currentCorrect;

    private final static int DELAY_MILLIS = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_game);
        quizDataManager = (DataManager) getApplication();
        Score = 0;
        List<PhotoInfo> photoList = quizDataManager.getPhotoList();
        List<Integer> indexList = new ArrayList<>();
        indexList.add(0);
        indexList.add(1);
        indexList.add(2);


        ImageView view = findViewById(R.id.QuizImage_IMAGEVIEW);
        Button option1 = findViewById(R.id.QuizOption1_BUTTON);
        Button option2 = findViewById(R.id.QuizOption2_BUTTON);
        Button option3 = findViewById(R.id.QuizOption3_BUTTON);
        TextView score = findViewById(R.id.ScoreTextView_GAME);

        refresh(view, option1, option2, option3, photoList, indexList, score);


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isCorrect = checkAnswer(option1.getText().toString());
                answer(isCorrect, view, option1, option2, option3, photoList, indexList, score);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isCorrect = checkAnswer(option2.getText().toString());
                answer(isCorrect, view, option2, option1, option3, photoList, indexList, score);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isCorrect = checkAnswer(option3.getText().toString());
                answer(isCorrect, view, option3, option2, option1, photoList, indexList, score);
            }
        });
    }


    private boolean checkAnswer(String name){
        return currentCorrect.getName().equals(name);
    }

    private void answer(Boolean isCorrect, ImageView view, Button pressed, Button notPressed, Button notPressed2, List<PhotoInfo> photoList, List<Integer> indexList, TextView score){
        if (isCorrect){
            pressed.setBackgroundColor(Color.GREEN);
            notPressed.setBackgroundColor(Color.RED);
            notPressed2.setBackgroundColor(Color.RED);
            Handler handler = new Handler();
            Score++;
            Plays++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh(view, pressed, notPressed, notPressed2, photoList, indexList, score);
                }
            }, DELAY_MILLIS);
        } else {
            pressed.setBackgroundColor(Color.RED);
            notPressed.setBackgroundColor(Color.RED);
            notPressed2.setBackgroundColor(Color.RED);
            Handler handler = new Handler();
            Plays++;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh(view, pressed, notPressed, notPressed2, photoList, indexList, score);
                }
            }, DELAY_MILLIS);
        }
    }

    private void refresh(ImageView view, Button option1, Button option2, Button option3, List<PhotoInfo> photoList, List<Integer> indexList, TextView score){
        Collections.shuffle(photoList);
        Collections.shuffle(indexList);
        currentCorrect = photoList.get(0);

        view.setImageURI(currentCorrect.getPhotoUri());


        option1.setBackgroundColor(Color.MAGENTA);
        option2.setBackgroundColor(Color.MAGENTA);
        option3.setBackgroundColor(Color.MAGENTA);
        option1.setText(photoList.get(indexList.get(0)).getName());
        option2.setText(photoList.get(indexList.get(1)).getName());
        option3.setText(photoList.get(indexList.get(2)).getName());
        score.setText(Score + "/" + Plays);
    }
}
