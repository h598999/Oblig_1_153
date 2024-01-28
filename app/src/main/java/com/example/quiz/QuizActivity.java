package com.example.quiz;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        String value = getIntent().getStringExtra("difficulty");
        if (value != null) {
            Log.d("Difficulty", value);
        } else {
            Log.d("Difficulty", "Not found");
        }
    }
}
