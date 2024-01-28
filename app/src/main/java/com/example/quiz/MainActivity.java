package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button quizButton = findViewById(R.id.quiz_Button);
        Button galleryButton = findViewById(R.id.gallery_Button);
        Switch difficultySwitch = (Switch) findViewById(R.id.difficulty_Switch);

        quizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class );
                intent.putExtra("difficulty", difficultySwitch.getAutofillValue());
                startActivity(intent);
            }
        });











    }
}
