package com.ycloud.ycloudlivedemo;

import java.util.Random;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	static final String TAG = "MainActivity";
	private Button mBtnLogin;
	private EditText mEditAppid;
	private EditText mEditUid;
	private EditText mEditSid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	public void initView() {
		mEditAppid = (EditText)findViewById(R.id.edittext_appid);
		mEditUid = (EditText)findViewById(R.id.edittext_uid);
		mEditSid = (EditText)findViewById(R.id.edittext_sid);	
		
		//默认appid
		mEditAppid.setText("1268144600");
				
		Random random = new Random();
		int x = random.nextInt(899999);
		int number = x+100000;
		mEditUid.setText(Integer.toString(number));
		mEditSid.setText("10086");
		
		mBtnLogin = (Button)findViewById(R.id.btn_login);
		mBtnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            	String appid = mEditAppid.getText().toString().trim();
            	String uid = mEditUid.getText().toString().trim();
            	String sid = mEditSid.getText().toString().trim();

            	if(uid.isEmpty()  || sid.isEmpty()) {
            		new AlertDialog.Builder(MainActivity.this)
    				.setMessage(R.string.param_invalid)
    				.show();
            		return;
            	}
            	
        		Intent intent = new Intent(MainActivity.this, LiveActivity.class);
        		intent.putExtra("appid", Integer.parseInt(appid));
        		intent.putExtra("uid", Integer.parseInt(uid));
        		intent.putExtra("sid", Integer.parseInt(sid));
        		startActivity(intent);
            
            }
		});
	}
}
