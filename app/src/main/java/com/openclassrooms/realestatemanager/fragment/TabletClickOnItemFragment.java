package com.openclassrooms.realestatemanager.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabletClickOnItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabletClickOnItemFragment extends Fragment {


    public TabletClickOnItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tablet_click_on_item, container, false);
    }
}