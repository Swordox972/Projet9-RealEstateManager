package com.openclassrooms.realestatemanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.realestatemanager.databinding.FragmentRealEstateBinding;
import com.openclassrooms.realestatemanager.event.OpenRealEstateEvent;
import com.openclassrooms.realestatemanager.model.RealEstate;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MyRealEstateRecyclerViewAdapter extends RecyclerView.Adapter
        <MyRealEstateRecyclerViewAdapter.ViewHolder> {

    private final List<RealEstate> mRealEstateList = new ArrayList<>();
    int row_index = -1;

    public MyRealEstateRecyclerViewAdapter() {
    }

    public void setRealEstateList(List<RealEstate> realEstateList) {
        mRealEstateList.clear();
        mRealEstateList.addAll(realEstateList);
        notifyDataSetChanged();
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

        holder.itemView.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
            EventBus.getDefault().post(new OpenRealEstateEvent(mRealEstate));
        });

        if (row_index == position) {
            holder.fragmentRealEstateBinding.fragmentRealEstateMainLinearLayout.setBackgroundColor(
                    Color.parseColor("#84FFFF"));
        } else {
            holder.fragmentRealEstateBinding.fragmentRealEstateMainLinearLayout.setBackgroundColor(
                    Color.parseColor("#FAFAFA"));
        }
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
