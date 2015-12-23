package com.test.sensor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

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
                if(test==0){
                    test=1;
                    darkScreen(true, getWindow());
                } else{
                    test=0;
                    darkScreen(false, getWindow());
                }
            }
        });

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
}
