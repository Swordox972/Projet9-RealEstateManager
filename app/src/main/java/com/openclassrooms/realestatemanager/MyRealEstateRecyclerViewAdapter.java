package com.openclassrooms.realestatemanager;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.realestatemanager.databinding.FragmentRealEstateBinding;
import com.openclassrooms.realestatemanager.model.RealEstate;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MyRealEstateRecyclerViewAdapter extends RecyclerView.Adapter
        <MyRealEstateRecyclerViewAdapter.ViewHolder> {

    private List<RealEstate> mRealEstateList;



    public MyRealEstateRecyclerViewAdapter(List<RealEstate> items) {
        this.mRealEstateList = items;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentRealEstateBinding.inflate(LayoutInflater.from(
                parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RealEstate mRealEstate = mRealEstateList.get(position);

        Glide.with(holder.fragmentRealEstateBinding.fragmentRealEstateImageView.getContext())
                .load(mRealEstate.getPhotoUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.fragmentRealEstateBinding.fragmentRealEstateImageView);

        holder.fragmentRealEstateBinding.fragmentRealEstateItemType.setText(mRealEstate.getType()
                .toString());
        holder.fragmentRealEstateBinding.fragmentRealEstateItemFirstLocation.setText(
                mRealEstate.getFirstLocation());
        holder.fragmentRealEstateBinding.fragmentRealEstateItemPrice.setText(mRealEstate.getPrice());


    }

    @Override
    public int getItemCount() {
        return mRealEstateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentRealEstateBinding fragmentRealEstateBinding;

        public ViewHolder(FragmentRealEstateBinding fragmentRealEstateBinding) {
            super(fragmentRealEstateBinding.getRoot());

            this.fragmentRealEstateBinding = fragmentRealEstateBinding;
        }
    }

}
