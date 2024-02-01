package com.example.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

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

    public void delete(int position){
        dataManager.getPhotoList().remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.GalleryDelete_Button).setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageView2);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        public void bind(PhotoInfo photoInfo){
            imageView.setImageURI(photoInfo.getPhotoUri());
            nameTextView.setText(photoInfo.getName());
        }

        @Override
        public void onClick(View v) {
            delete(getAdapterPosition());
        }
    }

}
