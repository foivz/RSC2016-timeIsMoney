package com.rsc.rschackathon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentListOfQuizzes extends Fragment implements RecyclerViewAdapter.Listener{

    private static  final String ARG_EXAMPLE = "this_is_a_constant";
    private String podatak;

    RecyclerViewAdapter recyclerViewAdapter;

    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.list_of_items)
    RecyclerView recyclerView;

    List<String> testList = new ArrayList<>();


    public static FragmentListOfQuizzes newInstance(String example_argument) {
        FragmentListOfQuizzes fragmentListOfQuizzes = new FragmentListOfQuizzes();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        fragmentListOfQuizzes.setArguments(args);
        return fragmentListOfQuizzes;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_view_pager_one_layout, container, false);
        podatak = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with", podatak);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAdapter = new RecyclerViewAdapter();
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        testList.add("TEST PODATAKA 1");
        testList.add("TEST PODATAKA 2");
        testList.add("TEST PODATAKA 3");
        testList.add("TEST PODATAKA 4");
        testList.add("TEST PODATAKA 5");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");
        testList.add("TEST PODATAKA 6");

//        recyclerViewAdapter.setData(testList);
        recyclerViewAdapter.setListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void getDeviceAtPosition(int position) {
        Toast.makeText(getActivity(), testList.get(position), Toast.LENGTH_SHORT).show();
    }
}
