package com.test.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {

    int test=0;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(com.test.sensor.R.layout.account_item);
        View t=findViewById(R.id.laji);
        t.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    private SensorManager mSensorManager;
    private Sensor mProximity;

    @Override
    public void onResume() {
        super.onResume();
        Log.e("xuawang", "onresume");
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("xuawang", "onpause");
        mSensorManager.unregisterListener(this);
    }

    private void darkScreen(boolean dark, Window w) {
        WindowManager.LayoutParams lp = w.getAttributes();
        if(dark) {
            lp.screenBrightness = 0;
        } else {
            lp.screenBrightness = -1;
        }
        w.setAttributes(lp);
    }

    private boolean mIsDarkMode = false;
    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float distance = event.values[0];
        Log.e("xuawang", "d: "+distance);
        if(distance > 5) {
            //far
            if(mIsDarkMode)darkScreen(false, getWindow());
            mIsDarkMode = false;
        } else {
            //close enough
            if(!mIsDarkMode)darkScreen(true, getWindow());
            mIsDarkMode = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }
}
