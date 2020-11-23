package com.openclassrooms.realestatemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.databinding.FragmentPickPhotosBinding;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.List;

public class MyPickPhotosRecyclerViewAdapter extends
        RecyclerView.Adapter<MyPickPhotosRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<RealEstatePhotos> realEstatePhotosList = new ArrayList<>();

    public MyPickPhotosRecyclerViewAdapter() {
    }

    public void setRealEstatePhotosList(ArrayList<RealEstatePhotos> realEstatePhotosList) {
        this.realEstatePhotosList.clear();
        this.realEstatePhotosList.addAll(realEstatePhotosList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentPickPhotosBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RealEstatePhotos realEstatePhotos = realEstatePhotosList.get(position);

        holder.fragmentPickPhotosBinding.fragmentPickPhotosImageView.setImageURI(
                RealEstatePhotos.stringToUri(realEstatePhotos.getPhotoUri()));

        
    }

    @Override
    public int getItemCount() {
        return realEstatePhotosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  FragmentPickPhotosBinding fragmentPickPhotosBinding;

        public ViewHolder(@NonNull FragmentPickPhotosBinding fragmentPickPhotosBinding) {
            super(fragmentPickPhotosBinding.getRoot());

            this.fragmentPickPhotosBinding = fragmentPickPhotosBinding;
        }
    }
}

