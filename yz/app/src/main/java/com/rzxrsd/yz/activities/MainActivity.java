package com.rzxrsd.yz.activities;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.rzxrsd.yz.R;
import com.rzxrsd.yz.fragments.BottomFragment1;
import com.rzxrsd.yz.fragments.BottomFragment2;
import com.rzxrsd.yz.fragments.BottomFragment3;
import com.rzxrsd.yz.tools.LocationUtils;
import com.rzxrsd.yz.tools.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Fragment fragment1, fragment2, fragment3;
    private Fragment currentFragment = new Fragment();
    private FragmentManager manager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("消息");
        context=getApplicationContext();
        StatusBarUtil.setTransparent(this);
        getBestLocation();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, 0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        LinearLayout avatar = findViewById(R.id.avatar);
        avatar.setOnClickListener(v -> drawer.openDrawer(GravityCompat.START));
        NavigationView navigationView = findViewById(R.id.nav_view);
        View drawview = navigationView.getHeaderView(0);
        ListView listView = drawview.findViewById(R.id.listView);
        int[] imageId = new int[]{ R.drawable.ic_profile, R.drawable.ic_dynamic, R.drawable.ic_vip, R.drawable.ic_setting };
        String[] title = new String[]{ "个人资料", "我的动态", "会员中心", "设置"};
        List<Map<String, Object>> listitems = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            Map<String, Object> id = new HashMap<>();
            id.put("image", imageId[i]);
            id.put("title", title[i]);
            listitems.add(id);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this ,listitems,  R.layout.icon_arrow_item,new String[]{ "title", "image" },new int[] { R.id.title, R.id.image });
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0:
                    break;
            }
        });
        setListViewHeightBasedOnChildren(listView);


        InitFragment();
        RelativeLayout navigation_home = findViewById(R.id.navigation_home);
        RelativeLayout navigation_message = findViewById(R.id.navigation_message);
        RelativeLayout navigation_found = findViewById(R.id.navigation_found);
        navigation_home.setOnClickListener(view -> {
            LoadFragment(R.id.navigation_home);
        });
        navigation_message.setOnClickListener(view -> {
            LoadFragment(R.id.navigation_message);
        });
        navigation_found.setOnClickListener(view -> {
            LoadFragment(R.id.navigation_found);
        });
        LoadFragment(R.id.navigation_home);
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
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void InitFragment() {
        manager = getSupportFragmentManager();
        fragment1 = BottomFragment1.newInstance();
        fragment2 = BottomFragment2.newInstance();
        fragment3 = BottomFragment3.newInstance();
    }
    private void ShowFragment(Fragment fragment) {
        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.content_frame, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void LoadFragment(int id) {
        switch (id) {
            case R.id.navigation_home:
                setTitle("首页");
                ShowFragment(fragment1);
                findViewById(R.id.bottom).setBackgroundColor(Color.parseColor("#1995fe"));
                findViewById(R.id.bottom).setElevation(0);
                ((ImageView) findViewById(R.id.icon_home)).setImageResource(R.drawable.ic_home_active);
                ((ImageView) findViewById(R.id.icon_message)).setImageResource(R.drawable.ic_message_light);
                ((ImageView) findViewById(R.id.icon_found)).setImageResource(R.drawable.ic_found_light);
                ((TextView) findViewById(R.id.text_home)).setTextColor(Color.parseColor("#FFFFFF"));
                ((TextView) findViewById(R.id.text_message)).setTextColor(Color.parseColor("#8ad3ff"));
                ((TextView) findViewById(R.id.text_found)).setTextColor(Color.parseColor("#8ad3ff"));
                break;
            case R.id.navigation_message:
                setTitle("消息");
                ShowFragment(fragment2);
                findViewById(R.id.bottom).setBackgroundColor(Color.parseColor("#FFFFFF"));
                findViewById(R.id.bottom).setElevation(16);
                ((ImageView) findViewById(R.id.icon_home)).setImageResource(R.drawable.ic_home);
                ((ImageView) findViewById(R.id.icon_message)).setImageResource(R.drawable.ic_message_active);
                ((ImageView) findViewById(R.id.icon_found)).setImageResource(R.drawable.ic_found);
                ((TextView) findViewById(R.id.text_home)).setTextColor(Color.parseColor("#bababa"));
                ((TextView) findViewById(R.id.text_message)).setTextColor(Color.parseColor("#0EA3FE"));
                ((TextView) findViewById(R.id.text_found)).setTextColor(Color.parseColor("#bababa"));
                break;
            case R.id.navigation_found:
                setTitle("发现");
                ShowFragment(fragment3);
                findViewById(R.id.bottom).setBackgroundColor(Color.parseColor("#FFFFFF"));
                findViewById(R.id.bottom).setElevation(16);
                ((ImageView) findViewById(R.id.icon_home)).setImageResource(R.drawable.ic_home);
                ((ImageView) findViewById(R.id.icon_message)).setImageResource(R.drawable.ic_message);
                ((ImageView) findViewById(R.id.icon_found)).setImageResource(R.drawable.ic_found_active);
                ((TextView) findViewById(R.id.text_home)).setTextColor(Color.parseColor("#bababa"));
                ((TextView) findViewById(R.id.text_message)).setTextColor(Color.parseColor("#bababa"));
                ((TextView) findViewById(R.id.text_found)).setTextColor(Color.parseColor("#0EA3FE"));
                break;
        }
    }
    @Override
    public void setTitle(CharSequence charSequence) {
        super.setTitle("");
        TextView textView = findViewById(R.id.title);
        textView.setVisibility(View.VISIBLE);
        textView.setText(charSequence);
    }
    public void getGPSLocation() {
        Location gps = LocationUtils.getGPSLocation(this);
        if (gps == null) {
            //设置定位监听，因为GPS定位，第一次进来可能获取不到，通过设置监听，可以在有效的时间范围内获取定位信息
            LocationUtils.addLocationListener(context, LocationManager.GPS_PROVIDER, new LocationUtils.ILocationListener() {
                @Override
                public void onSuccessLocation(Location location) {
                    if (location != null) {
                        Toast.makeText(MainActivity.this, "gps onSuccessLocation location:  lat==" + location.getLatitude() + "     lng==" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "gps location is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "gps location: lat==" + gps.getLatitude() + "  lng==" + gps.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过网络等获取定位信息
     */
    private void getNetworkLocation() {
        Location net = LocationUtils.getNetWorkLocation(this);
        if (net == null) {
            Toast.makeText(this, "net location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "network location: lat==" + net.getLatitude() + "  lng==" + net.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 采用最好的方式获取定位信息
     */
    private void getBestLocation() {
        Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
        c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
        c.setAltitudeRequired(true);//设置需要海拔
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
        //... Criteria 还有其他属性，就不一一介绍了
        Location best = LocationUtils.getBestLocation(this, c);
        if (best == null) {
            Toast.makeText(this, " best location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "best location: lat==" + best.getLatitude() + " lng==" + best.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

}
