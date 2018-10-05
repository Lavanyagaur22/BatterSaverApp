package com.codingblocks.battersaverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyReceiver myBroadcast;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv1);

        myBroadcast = new MyReceiver();


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcast, intentFilter);



    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcast);
        super.onDestroy();
    }


    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                    Toast.makeText(context, "Airplane mode toggled", Toast.LENGTH_SHORT).show();

                    break;

                case Intent.ACTION_POWER_CONNECTED:
                    Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, PowerConn.class));

                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(context, PowerDis.class));

                    break;

                case Intent.ACTION_BATTERY_LOW:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        startActivity(new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS));
                    }


            }


        }
    }
}
