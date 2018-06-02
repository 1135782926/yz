package com.rzxrsd.yz.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.rzxrsd.yz.R;
import com.rzxrsd.yz.widgets.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TabFragment1 extends Fragment {
    private SwipeRefreshLayout mSwipeLayout;
    public static TabFragment1 newInstance() {
        TabFragment1 fragment = new TabFragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
        mSwipeLayout = view.findViewById(R.id.swipeLayout);
        ListView listview = view.findViewById(R.id.listView);

        Map<String, Object> config1 = new HashMap<>();
        config1.put("name", "测试");
        config1.put("avatar", R.drawable.avatar);
        config1.put("sex", 0x00);
        config1.put("age", "22");
        config1.put("isVip", true);
        config1.put("isThumbup", true);
        config1.put("thumbup", "99+");
        config1.put("comment", "0");
        config1.put("content","测试测试");
        config1.put("extra", "0:16 · 0.01km");

        Map<String, Object> config2 = new HashMap<>();
        config2.put("name", "测试");
        config2.put("avatar", R.drawable.avatar);
        config2.put("sex", 0x01);
        config2.put("age", "17");
        config2.put("isVip", true);
        config2.put("isThumbup", false);
        config2.put("thumbup", "0");
        config2.put("comment", "99+");
        config2.put("content","测试测试的点点滴滴多多");
        config2.put("extra", "0:10 · 0.01km");

        Map<String, Object> config3 = new HashMap<>();
        config3.put("name", "测试");
        config3.put("avatar", R.drawable.avatar);
        config3.put("sex", 0x00);
        config3.put("age", "30");
        config3.put("isVip", false);
        config3.put("isThumbup", false);
        config3.put("thumbup", "0");
        config3.put("comment", "0");
        config3.put("content","测试奥术大师大所大所大所多测试");
        config3.put("extra", "昨天 23:10 · 0.01km");

        List<Map<String, Object>> listitems = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("config", config1);
        listitems.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("config", config2);
        listitems.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("config", config3);
        listitems.add(map3);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), listitems, R.layout.items_dynamic, new String[] { "config", "config", "config", "config", "config", "config", "config", "config", "config" }, new int[] { R.id.title, R.id.image, R.id.content, R.id.vip, R.id.ageandsex, R.id.thumbup, R.id.thumbupnumbers, R.id.commentnumbers, R.id.extra});
        listview.setAdapter(adapter);
        adapter.setViewBinder((view1, data, textRepresentation) -> {
            HashMap<String, Object> d = (HashMap<String, Object>) data;

            switch (view1.getId()) {
                case R.id.title:
                    ((TextView) view1).setText((String) d.get("name"));
                    break;
                case R.id.image:
                    ((CircleImageView) view1).setImageResource((int) d.get("avatar"));
                    break;
                case R.id.content:
                    ((TextView) view1).setText((String) d.get("content"));
                    break;
                case R.id.ageandsex:
                    String sex;
                    if((int) d.get("sex") == 0x00) {
                        sex = "♂";
                        view1.setBackgroundResource(R.drawable.sex_gentle);
                    } else {
                        sex = "♀";
                        view1.setBackgroundResource(R.drawable.sex_lady);
                    }
                    ((TextView) view1).setText(sex + " " + d.get("age"));
                    break;
                case R.id.vip:
                    if((boolean) d.get("isVip"))
                        view1.setBackgroundResource(R.drawable.vip);
                    else
                        view1.setBackgroundResource(R.drawable.novip);
                    break;
                case R.id.extra:
                    ((TextView) view1).setText((String) d.get("extra"));
                    break;
                case R.id.thumbup:
                    if((boolean) d.get("isThumbup"))
                        ((ImageView) view1).setImageResource(R.drawable.thumbup_a);
                    else
                        ((ImageView) view1).setImageResource(R.drawable.thumbup);
                    break;
                case R.id.thumbupnumbers:
                    if(d.get("thumbup").equals("0"))
                        ((TextView) view1).setText("赞");
                    else
                        ((TextView) view1).setText((String) d.get("thumbup"));
                    break;
                case R.id.commentnumbers:
                    if(d.get("comment").equals("0"))
                        ((TextView) view1).setText("评论");
                    else
                        ((TextView) view1).setText((String) d.get("comment"));
                    break;
            }
            return true;
        });
        setListViewHeightBasedOnChildren(listview);
        mSwipeLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(() -> {
                    mSwipeLayout.setRefreshing(false);
            }, 2000);
        });
        return view;
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams p = listView.getLayoutParams();
        p.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(p);
    }
}
