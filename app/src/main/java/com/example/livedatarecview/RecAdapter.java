package com.example.livedatarecview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class RecAdapter extends RecyclerView.Adapter<RecAdapter.myViewHolder> {
    private List<AnimeModel> animeModels;
    private Context mContext;

    public RecAdapter(Context mContext, List<AnimeModel> animeModels) {
        this.animeModels = animeModels;
        this.mContext = mContext;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextviewName;
        public TextView mTextviewTitle;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextviewName = itemView.findViewById(R.id.textViewName);
            mTextviewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        AnimeModel currentItem = animeModels.get(position);
        holder.mTextviewName.setText(currentItem.getCharName());
        holder.mTextviewTitle.setText(currentItem.getCharTitle());
        Glide.with(mContext).asBitmap().load(currentItem.getImageURL()).dontAnimate().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        if (animeModels == null) {
            return 0;
        } else {
            return animeModels.size();
        }
    }
}
