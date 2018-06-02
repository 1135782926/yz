package com.rzxrsd.yz.fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzxrsd.yz.R;
import com.rzxrsd.yz.util.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class BottomFragment3 extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private List<Fragment> mFragments;
    private List<String> mTitles;
    public static BottomFragment3 newInstance() {
        BottomFragment3 fragment = new BottomFragment3();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bottom_fragment3, container, false);
        mViewPager = view.findViewById(R.id.viewpager);
        mTabLayout = view.findViewById(R.id.tablayout);
        mTitles = new ArrayList<>();
        mTitles.add("附近");
        mTitles.add("关注");
        mFragments = new ArrayList<>();

        mFragments.add(TabFragment1.newInstance());
        mFragments.add(TabFragment2.newInstance());
        adapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for(int i = 0; i < mTitles.size(); i++) {
            mTabLayout.getTabAt(i).setText(mTitles.get(i));
        }

        return view;
    }
}
