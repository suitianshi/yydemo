package com.talk51.dasheng.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.talk51.dasheng.YYApplication;

public class UIFragmentActivity extends FragmentActivity {
	protected YYApplication mApp = null;
	private final String TAG = UIActivity.class.getName(); 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mApp = (YYApplication)getApplication();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onPause();
//		Boolean isForeGround = YYSdkService.isForeGround(getApplicationContext());
//		Log.i(TAG,"onPause foreGround "+isForeGround);
//
//		if(mApp.getAppState() != isForeGround)
//		{
//			mApp.setAppState(isForeGround);
//			LoginRequest.AppStatusReq req = new LoginRequest.AppStatusReq(isForeGround);
//			mApp.getLogin().sendRequest(req);
//		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		Boolean isForeGround = YYSdkService.isForeGround(getApplicationContext());
//		Log.i(TAG,"onResume foreGround "+isForeGround);
//		if(mApp.getAppState() != isForeGround)
//		{
//			mApp.setAppState(isForeGround);
//			LoginRequest.AppStatusReq req = new LoginRequest.AppStatusReq(isForeGround);
//			mApp.getLogin().sendRequest(req);
//		}
	}

}
