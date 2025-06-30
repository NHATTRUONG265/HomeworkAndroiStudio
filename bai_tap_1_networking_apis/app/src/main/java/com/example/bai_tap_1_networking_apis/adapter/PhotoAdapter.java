package com.example.bai_tap_1_networking_apis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bai_tap_1_networking_apis.R;
import com.example.bai_tap_1_networking_apis.model.Photo;
import java.util.List;
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> photoList;
    private Context context;
    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }
    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        Glide.with(context)
                .load(photo.getDownload_url())
                .placeholder(R.drawable.loading)
                .into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return photoList.size();
    }
    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}