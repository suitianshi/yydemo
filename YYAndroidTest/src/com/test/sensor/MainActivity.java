package com.test.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements SensorEventListener {

    AudioManager am;
    int test=0;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        setContentView(com.test.sensor.R.layout.account_item);
        final TextView t=(TextView)findViewById(R.id.laji);
        t.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mMediaPlayer==null){
                    mMediaPlayer=new MediaPlayer();
                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mMediaPlayer.setDataSource(getPath());
                        mMediaPlayer.prepare();
                        mMediaPlayer.start();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else {
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                }
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
        if(mMediaPlayer!=null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
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
    private MediaPlayer mMediaPlayer;
    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float distance = event.values[0];
        Log.e("xuawang", "d: "+distance);
        if(distance > 5) {
            //far
            if(mIsDarkMode){
                darkScreen(false, getWindow());
                am.setMode(AudioManager.MODE_NORMAL);
                am.setSpeakerphoneOn(true);
                am.setBluetoothScoOn(false);
            }
            mIsDarkMode = false;
        } else {
            //close enough
            if(!mIsDarkMode){
                darkScreen(true, getWindow());
                am.setMode(AudioManager.MODE_IN_CALL);
                am.setSpeakerphoneOn(false);
                am.setBluetoothScoOn(true);
            }
            mIsDarkMode = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + File.separator
                + "51talk"
                + File.separator + "123.aac";
        return path;
    }
}
