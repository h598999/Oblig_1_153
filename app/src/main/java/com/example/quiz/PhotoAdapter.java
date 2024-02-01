package com.example.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PhotoAdapter extends RecyclerView.Adapter<ViewHolder> {

    private DataManager dataManager;
    private Context context;

    public PhotoAdapter(DataManager dataManager, Context context){
        this.dataManager = dataManager;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.bind(dataManager.getPhotoList().get(position));
    }

    @Override
    public int getItemCount() {
        return dataManager.getPhotoList().size();
    }

}
