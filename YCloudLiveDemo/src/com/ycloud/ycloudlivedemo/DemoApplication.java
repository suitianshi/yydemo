package com.ycloud.ycloudlivedemo;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.ycloud.live.YCMedia;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import android.util.Log;

public class DemoApplication extends Application {
	static private String TAG = "DemoApplication";
	private static AtomicReference<DemoApplication> mInstance = 
			new AtomicReference<DemoApplication>();
	
	
	public static DemoApplication getInstance() {
        return mInstance.get();
    }
	
	
	@Override
	public void onCreate(){
		super.onCreate();
		Log.d(TAG, "onCreate");
		mInstance.set(this);
		if(isPkgMainProc())
		{
			Log.i(TAG,"DemoApplication onCreate, sdk version: " + YCMedia.getSdkVersion());
			YCMedia.getInstance().init(this);
		}
	}
	@Override
	public void onTerminate() {
		Log.d(TAG, "onTerminate");
		YCMedia.getInstance().unInit();
		
	}
	
	public boolean isPkgMainProc()
	{
	      ActivityManager am = ((ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE));
	        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
	        String mainProcessName = this.getPackageName();
	        int myPid = Process.myPid();
	        for (RunningAppProcessInfo info : processInfos) {
	            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
	                return true;
	            }
	        }
	        return false;
	}
}
