package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.MyRealEstatePhotosRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;

import java.util.ArrayList;
import java.util.List;

public class RealEstatePhotosFragment extends Fragment {

    //no more service here

    private RecyclerView mRecyclerView;
    private MyRealEstatePhotosRecyclerViewAdapter adapter;

    public RealEstatePhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_estate_photos_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));

        RealEstate mRealEstate = (RealEstate) getParentFragment().getArguments().getSerializable(
                RealEstateFragment.KEY);

        adapter = new MyRealEstatePhotosRecyclerViewAdapter(); //empty constructor adapter
        mRecyclerView.setAdapter(adapter);

        adapter.setRealEstatePhotosList(mRealEstate.getPhotos());
    }
}