package com.openclassrooms.realestatemanager.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.MyRealEstatePhotosRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.model.RealEstatePhotos;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

import java.util.List;

public class RealEstatePhotosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<RealEstatePhotos> mRealEstatePhotosList;
    private RealEstateApiService apiService;
    private MyRealEstatePhotosRecyclerViewAdapter adapter;

    public RealEstatePhotosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = DI.getRealEstateApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_real_estate_photos_list,container,
                false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false));

        initList();
        return view;
    }

    private void initList() {
        mRealEstatePhotosList = apiService.getRealEstatePhotos1();
        adapter = new MyRealEstatePhotosRecyclerViewAdapter(mRealEstatePhotosList);
        mRecyclerView.setAdapter(adapter);

    }
}