package com.rzxrsd.yz.fragments;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaygoo.widget.RangeSeekBar;
import com.rzxrsd.yz.R;
import com.rzxrsd.yz.activities.MainActivity;
import com.rzxrsd.yz.tools.LocationUtils;
import com.rzxrsd.yz.tools.MyApplication;
import com.rzxrsd.yz.widgets.CustomBottomDialog;


public class BottomFragment1 extends Fragment {

    private LinearLayout pp;
    private TextView b_sex_all, b_sex_gentle, b_sex_lady;
    private TextView b_range_3, b_range_6, b_range_9, b_range_12;
    private TextView sexshow, rangeshow, ageshow;
    private RangeSeekBar s_age;
    private Button btn_look;
    private int sex = 0x00;
    private int range = 0x00;
    private int[] age = { 0 , 25 };
    public static BottomFragment1 newInstance() {
        BottomFragment1 fragment = new BottomFragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_bottom_fragment1, container, false);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
        CustomBottomDialog customBottomDialog = new CustomBottomDialog(contentView, getActivity(), R.style.style_dialog);
        pp = view.findViewById(R.id.pp);
        b_sex_all = contentView.findViewById(R.id.all);
        b_sex_gentle = contentView.findViewById(R.id.gentle);
        b_sex_lady = contentView.findViewById(R.id.lady);
        b_range_3 = contentView.findViewById(R.id.range3);
        b_range_6 = contentView.findViewById(R.id.range6);
        b_range_9 = contentView.findViewById(R.id.range9);
        b_range_12 = contentView.findViewById(R.id.range12);
        s_age = contentView.findViewById(R.id.seekbar);
        sexshow = contentView.findViewById(R.id.sexshow);
        rangeshow = contentView.findViewById(R.id.rangeshow);
        ageshow = contentView.findViewById(R.id.ageshow);
        btn_look=contentView.findViewById(R.id.btn_look);
        /**
         * 获取性别事件
         */
        View.OnClickListener listener_sex = v -> {
            switch (v.getId()) {
                case R.id.all:
                    b_sex_all.setBackgroundResource(R.drawable.check_active);
                    b_sex_gentle.setBackgroundResource(R.drawable.check);
                    b_sex_lady.setBackgroundResource(R.drawable.check);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    sex = 0x00;
                    sexshow.setText("(全部)");
                    break;
                case R.id.gentle:
                    b_sex_all.setBackgroundResource(R.drawable.check);
                    b_sex_gentle.setBackgroundResource(R.drawable.check_active);
                    b_sex_lady.setBackgroundResource(R.drawable.check);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    sex = 0x01;
                    sexshow.setText("(男)");
                    break;
                case R.id.lady:
                    b_sex_all.setBackgroundResource(R.drawable.check);
                    b_sex_gentle.setBackgroundResource(R.drawable.check);
                    b_sex_lady.setBackgroundResource(R.drawable.check_active);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorPrimary));
                    sex = 0x02;
                    sexshow.setText("(女)");
                    break;
            }
        };
        /**
         * 获取距离事件
         */
        View.OnClickListener listener_range = v -> {
            switch (v.getId()) {
                case R.id.range3:
                    b_range_3.setBackgroundResource(R.drawable.check_active);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    range = 0x00;
                    rangeshow.setText("(3km)");
                    break;
                case R.id.range6:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check_active);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    range = 0x01;
                    rangeshow.setText("(6km)");
                    break;
                case R.id.range9:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check_active);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    range = 0x02;
                    rangeshow.setText("(9km)");
                    break;
                case R.id.range12:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check_active);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorPrimary));
                    range = 0x03;
                    rangeshow.setText("(12km)");
                    break;
            }
        };
        b_sex_all.setOnClickListener(listener_sex);
        b_sex_gentle.setOnClickListener(listener_sex);
        b_sex_lady.setOnClickListener(listener_sex);
        b_range_3.setOnClickListener(listener_range);
        b_range_6.setOnClickListener(listener_range);
        b_range_9.setOnClickListener(listener_range);
        b_range_12.setOnClickListener(listener_range);
        s_age.setValue(16, 41);
        /**
         * 获取年龄事件
         *
         */
        s_age.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                if(Math.floor(min) == 16 && Math.floor(max) == 41)
                    ageshow.setText("(全部)");
                else if(Math.floor(max) == 41)
                    ageshow.setText("(" + (int) Math.floor(min) + " - 40+)");
                else
                    ageshow.setText("(" + (int) Math.floor(min) + " - " + (int) Math.floor(max) + ")");
                age[0] = (int) Math.floor(min);
                age[1] = (int) Math.floor(max);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
        /**
         * 选择性别事件
         */
        pp.setOnClickListener(v -> {
            customBottomDialog.show();
            switch (sex) {
                case 0x00:
                    b_sex_all.setBackgroundResource(R.drawable.check_active);
                    b_sex_gentle.setBackgroundResource(R.drawable.check);
                    b_sex_lady.setBackgroundResource(R.drawable.check);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    break;
                case 0x01:
                    b_sex_all.setBackgroundResource(R.drawable.check);
                    b_sex_gentle.setBackgroundResource(R.drawable.check_active);
                    b_sex_lady.setBackgroundResource(R.drawable.check);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    break;
                case 0x02:
                    b_sex_all.setBackgroundResource(R.drawable.check);
                    b_sex_gentle.setBackgroundResource(R.drawable.check);
                    b_sex_lady.setBackgroundResource(R.drawable.check_active);
                    b_sex_all.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_gentle.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_sex_lady.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
            switch (range) {
                case 0x00:
                    b_range_3.setBackgroundResource(R.drawable.check_active);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    break;
                case 0x01:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check_active);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    break;
                case 0x02:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check_active);
                    b_range_12.setBackgroundResource(R.drawable.check);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorPrimary));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    break;
                case 0x03:
                    b_range_3.setBackgroundResource(R.drawable.check);
                    b_range_6.setBackgroundResource(R.drawable.check);
                    b_range_9.setBackgroundResource(R.drawable.check);
                    b_range_12.setBackgroundResource(R.drawable.check_active);
                    b_range_3.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_6.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_9.setTextColor(getResources().getColor(R.color.colorSeekBarDefault));
                    b_range_12.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        });
        return view;
    }
}
