package com.openclassrooms.realestatemanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.databinding.FragmentRealEstatePhotosBinding;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.List;

public class MyRealEstatePhotosRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRealEstatePhotosRecyclerViewAdapter.ViewHolder> {


    private List<RealEstatePhotos> realEstatePhotosList;

    public MyRealEstatePhotosRecyclerViewAdapter(List<RealEstatePhotos> items) {
        this.realEstatePhotosList = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentRealEstatePhotosBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final RealEstatePhotos mRealEstatePhotos = realEstatePhotosList.get(position);

        Glide.with(holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosImageView.getContext())
                .load(mRealEstatePhotos.getPhotoUrl())
                .into(holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosImageView);

        holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosDescription.setText(
                mRealEstatePhotos.getDescription());


    }

    @Override
    public int getItemCount() {
        return realEstatePhotosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentRealEstatePhotosBinding fragmentRealEstatePhotosBinding;

        public ViewHolder(@NonNull FragmentRealEstatePhotosBinding fragmentRealEstatePhotosBinding) {
            super(fragmentRealEstatePhotosBinding.getRoot());

            this.fragmentRealEstatePhotosBinding = fragmentRealEstatePhotosBinding;
        }
    }

}
