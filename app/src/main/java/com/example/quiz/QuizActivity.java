package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final int DELAY_MILLIS = 5000;

    private DataManager quizDataManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button backButton = findViewById(R.id.backQuiz_button);
        quizDataManager = (DataManager) getApplication();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (quizDataManager.getPhotoList().isEmpty()) {
                    finish();
                } else {
                    Intent intent = new Intent(QuizActivity.this, Quiz.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, DELAY_MILLIS);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
