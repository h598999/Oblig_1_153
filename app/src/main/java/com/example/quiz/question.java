package com.example.quiz;

public class question {

    private PhotoInfo photo;
    private Boolean correct;

    public question(PhotoInfo photo, Boolean correct){
        this.photo = photo;
        this.correct = correct;
    }

    public boolean isCorrect(){
        return correct;
    }
}
