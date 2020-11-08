package com.openclassrooms.realestatemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.event.OpenRealEstateEvent;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.service.RealEstateApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class RealEstateFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private RealEstateApiService apiService;

    private List<RealEstate> mRealEstateList;

    private MyRealEstateRecyclerViewAdapter adapter;

    public static String KEY = "RealEstateClicked";

    public RealEstateFragment() {
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
        View view = inflater.inflate(R.layout.fragment_real_estate_list, container,
                false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));

        initList();
        return view;
    }

    private void initList() {
     mRealEstateList = apiService.getRealEstates();
     adapter = new MyRealEstateRecyclerViewAdapter(mRealEstateList);
     mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onOpenRealEstate(OpenRealEstateEvent event) {
        RealEstate mRealEstate = event.mRealEstate;
        Intent intent = new Intent(getContext(), OnClickRealEstateActivity.class);
        intent.putExtra(KEY, mRealEstate);
        //Ensure that real estate is not null
        if (mRealEstate != null) {
        startActivity(intent);
        }
    }
}