package com.rzxrsd.yz.activities;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.rzxrsd.yz.R;
import com.rzxrsd.yz.fragments.BottomFragment1;
import com.rzxrsd.yz.tools.BaseActivity;
import com.rzxrsd.yz.tools.LocationUtils;
import com.rzxrsd.yz.tools.MyApplication;

public class StartActivity extends BaseActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                performCodeWithPermission("位置信息", new PermissionCallback() {
                    @Override
                    public void hasPermission() {
                        Intent intent=new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void noPermission() {

                    }
                }, Manifest.permission.ACCESS_COARSE_LOCATION);

            }
        });
    }
}
