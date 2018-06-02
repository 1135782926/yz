package com.rzxrsd.yz.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzxrsd.yz.R;

public class BottomFragment2 extends Fragment {


    public static BottomFragment2 newInstance() {
        BottomFragment2 fragment = new BottomFragment2();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bottom_fragment2, container, false);

        return view;
    }
}

