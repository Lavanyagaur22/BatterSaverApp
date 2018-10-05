package com.codingblocks.battersaverapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PowerDis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_dis);


        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        textView.setText("Battery Level :" + Integer.toString((int) getlevel()) + "%");


    }

    private float getlevel() {


        Intent i = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        int level = i.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = i.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return (float) level / (float) scale * 100;

    }

}
