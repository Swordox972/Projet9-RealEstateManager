package com.openclassrooms.realestatemanager.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.databinding.FragmentRealEstatePhotosBinding;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.List;

public class MyRealEstatePhotosRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRealEstatePhotosRecyclerViewAdapter.ViewHolder> {

    private final List<RealEstatePhotos> realEstatePhotosList = new ArrayList<>();

    public MyRealEstatePhotosRecyclerViewAdapter() {

    }

    //method to update the items of adapter
    public void setRealEstatePhotosList(List<RealEstatePhotos> realEstatePhotosList) {
        this.realEstatePhotosList.clear();
        this.realEstatePhotosList.addAll(realEstatePhotosList);
        notifyDataSetChanged();
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

        if (mRealEstatePhotos.getPhotoUrl() != null) {
            Glide.with(holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosImageView.getContext())
                    .load(mRealEstatePhotos.getPhotoUrl())
                    .into(holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosImageView);
        }

        if (mRealEstatePhotos.getPhotoUri() != null) {
            Uri imageUri = RealEstatePhotos.stringToUri(mRealEstatePhotos.getPhotoUri());
            holder.fragmentRealEstatePhotosBinding.fragmentRealEstatePhotosImageView.setImageURI(
                    imageUri);
        }
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
