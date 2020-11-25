package com.openclassrooms.realestatemanager.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.databinding.FragmentPickPhotosBinding;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPickPhotosRecyclerViewAdapter extends
        RecyclerView.Adapter<MyPickPhotosRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<RealEstatePhotos> realEstatePhotosList = new ArrayList<>();
    public static HashMap<Integer, String> map = new HashMap<>();

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

        holder.fragmentPickPhotosBinding.fragmentRealEstatePhotosDescription.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String value = map.get(position);
                        if (value == null) {
                            map.put(position, holder.fragmentPickPhotosBinding
                                    .fragmentRealEstatePhotosDescription.getText().toString());
                        }
                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return realEstatePhotosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentPickPhotosBinding fragmentPickPhotosBinding;

        public ViewHolder(@NonNull FragmentPickPhotosBinding fragmentPickPhotosBinding) {
            super(fragmentPickPhotosBinding.getRoot());

            this.fragmentPickPhotosBinding = fragmentPickPhotosBinding;
        }
    }
}

