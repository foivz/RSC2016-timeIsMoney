package com.rsc.rschackathon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsc.rschackathon.R;

import butterknife.ButterKnife;

public class FragmentViewPagerOne extends Fragment {

    private static  final String ARG_EXAMPLE = "this_is_a_constant";
    private String podatak;


    public static FragmentViewPagerOne newInstance(String example_argument) {
        FragmentViewPagerOne fragmentViewPagerOne = new FragmentViewPagerOne();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        fragmentViewPagerOne.setArguments(args);
        return fragmentViewPagerOne;
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
}
