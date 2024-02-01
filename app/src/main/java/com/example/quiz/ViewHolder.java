package com.example.quiz;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.function.Consumer;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView nameTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView2);
        nameTextView = itemView.findViewById(R.id.nameTextView);
    }

    public void bind(PhotoInfo photoInfo){
        imageView.setImageURI(photoInfo.getPhotoUri());
        nameTextView.setText(photoInfo.getName());
    }

}
