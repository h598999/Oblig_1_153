package com.example.quiz;


import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class UploadActivity extends AppCompatActivity {

    private static final int YOUR_PERMISSION_REQUEST_CODE = 1;
    private Uri imageUri;

    private DataManager quizDataManager;

    @Override
    protected void onCreate(Bundle instanceViewState) {
        super.onCreate(instanceViewState);
        setContentView(R.layout.upload_activity);
        Button uploadButton = findViewById(R.id.uploadPhoto_Button);
        Button submitButton = findViewById(R.id.submit_Button);
        Button goButton = findViewById(R.id.goButton);

        quizDataManager = (DataManager) getApplication();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){submit();}
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go();
            }
        });
    }
    private void openGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    private void go(){
        finish();
    }

    private void submit(){
        String enteredText = getEnteredText();
        Uri imageUri = getUri();
        if (enteredText == null || imageUri == null){
            return;
        }
        quizDataManager.addPhoto(enteredText, imageUri);
        Toast.makeText(this, "Image added", Toast.LENGTH_SHORT).show();
        int uid = Binder.getCallingUid();
        String callingPackage = getPackageManager().getNameForUid(uid);
        getApplication().grantUriPermission(callingPackage, imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        DataManager.sort(quizDataManager.getPhotoList(), (DataManager.isListSorted(quizDataManager.getPhotoList())));
        reset();
    }

    private void goBack(){
        Log.d("BackButton Upload", "Pressed back");
    }

    private void reset(){
        ImageView view = findViewById(R.id.imageView);
        view.setImageResource(0);
        EditText input = findViewById(R.id.inputText_input);
        input.setText("");
        this.imageUri = null;
    }

    private String getEnteredText(){
        EditText input = findViewById(R.id.inputText_input);
        String enteredText = input.getText().toString();

        if (enteredText == null || enteredText.isEmpty()){
            Toast.makeText(quizDataManager, "Please enter a name", Toast.LENGTH_SHORT).show();
            return null;
        }

        return enteredText;

    }

    private Uri getUri(){
        if (this.imageUri == null){
            Toast.makeText(quizDataManager, "Please enter an image", Toast.LENGTH_SHORT).show();
            return null;
        }
        return this.imageUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            Log.d("Image", "Image selected: " + imageUri.toString());
            ImageView view = findViewById(R.id.imageView);
            view.setImageURI(imageUri);
        }
    }
}
