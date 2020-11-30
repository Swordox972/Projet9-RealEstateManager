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

import com.openclassrooms.realestatemanager.MainActivity;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.MyPickPhotosRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.model.RealEstate;

public class PickPhotosFragment extends Fragment {

    private RecyclerView mRecyclerviewView;
    public static MyPickPhotosRecyclerViewAdapter adapter;
    private RealEstate mNewRealEstate;


    public PickPhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pick_photos_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerviewView = (RecyclerView) view;
        mRecyclerviewView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));

        //If realEstate come from MainActivity to add a real estate
        if (getActivity().getIntent().getParcelableExtra(MainActivity.ADD_REAL_ESTATE) != null) {
            mNewRealEstate = (RealEstate) getActivity().getIntent()
                    .getParcelableExtra(MainActivity.ADD_REAL_ESTATE);
        } //Else if realEstate come from an existing real estate to edit
        else if (getActivity().getIntent().getParcelableExtra(
                RealEstateFragment.EDIT_REAL_ESTATE) != null) {
            mNewRealEstate = (RealEstate) getActivity().getIntent().getParcelableExtra(
                    RealEstateFragment.EDIT_REAL_ESTATE);
        }

        adapter = new MyPickPhotosRecyclerViewAdapter();
        mRecyclerviewView.setAdapter(adapter);

        if (mNewRealEstate.getPhotos() != null)
            adapter.setRealEstatePhotosList(mNewRealEstate.getPhotos());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNewRealEstate.getPhotos() != null) {
            adapter.setRealEstatePhotosList(mNewRealEstate.getPhotos());
        }
    }
}