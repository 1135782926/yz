package com.rzxrsd.yz.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzxrsd.yz.R;


public class TabFragment2 extends Fragment {

    public static TabFragment2 newInstance() {
        TabFragment2 fragment = new TabFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
        return view;
    }
}
