package com.openclassrooms.realestatemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.AddOrEditRealEstateActivity;
import com.openclassrooms.realestatemanager.MainActivity;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.adapter.MyRealEstateRecyclerViewAdapter;
import com.openclassrooms.realestatemanager.di.DI;
import com.openclassrooms.realestatemanager.event.EditRealEstateEvent;
import com.openclassrooms.realestatemanager.event.OpenRealEstateEvent;
import com.openclassrooms.realestatemanager.model.RealEstate;
import com.openclassrooms.realestatemanager.model.RealEstateList;
import com.openclassrooms.realestatemanager.repository.RealEstateDataRepository;
import com.openclassrooms.realestatemanager.service.MyRealEstateHandlerThread;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;

import static android.app.Activity.RESULT_OK;


public class RealEstateFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private RealEstateViewModel viewModel;

    public static MyRealEstateRecyclerViewAdapter adapter;

    MyRealEstateHandlerThread myRealEstateHandlerThread;

    public static final String KEY = "RealEstateClicked";

    public static final String EDIT_REAL_ESTATE = "RealEstateToEdit";
    public static final int EDIT_REQUEST_CODE = 25;

    private RealEstateDataRepository repository;

    public RealEstateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
 repository = DI.getRepository(getActivity().getApplication());
        return inflater.inflate(R.layout.fragment_real_estate_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        adapter = new MyRealEstateRecyclerViewAdapter();
        mRecyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RealEstateViewModel.class);

            viewModel.getRealEstates().observe(getViewLifecycleOwner(), realEstates -> {
                adapter.setRealEstateList(realEstates);
            });
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

        OnClickRealEstateFragment onClickRealEstateFragment = new OnClickRealEstateFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, mRealEstate);
        onClickRealEstateFragment.setArguments(args);

        Fragment fragmentContainerViewDetail = getParentFragmentManager().findFragmentById(
                R.id.activity_main_fragment_container_view_detail);


        //Ensure that real estate is not null and containerView to display properly
        //on phone
        if (mRealEstate != null && fragmentContainerViewDetail == null) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main_fragment_container_view_list,
                            onClickRealEstateFragment)
                    .addToBackStack(OnClickRealEstateFragment.class.getSimpleName())
                    .commit();
        } else if (mRealEstate != null && fragmentContainerViewDetail.isVisible()) { //on tablet
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main_fragment_container_view_detail,
                            onClickRealEstateFragment)
                    .commit();
        }
    }

    @Subscribe
    public void onEditRealEstate(EditRealEstateEvent event) {
        RealEstate mRealEstate = event.mRealEstate;

        Intent intent = new Intent(getContext(), AddOrEditRealEstateActivity.class);
        intent.putExtra( EDIT_REAL_ESTATE, (Serializable) mRealEstate);
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                RealEstate realEstate = (RealEstate) data.getSerializableExtra(EDIT_REAL_ESTATE);
                myRealEstateHandlerThread =
                        new MyRealEstateHandlerThread("UpdateRealEstateInDatabase");

                myRealEstateHandlerThread.startUpdateRealEstateHandler(realEstate, viewModel);
            }
        }
    }
}