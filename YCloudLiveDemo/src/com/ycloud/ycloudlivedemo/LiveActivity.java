package com.ycloud.ycloudlivedemo;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ycloud.live.YCConstant;
import com.ycloud.live.YCMedia;
import com.ycloud.live.YCMediaRequest;
import com.ycloud.live.YCMessage;
import com.ycloud.live.YCMessage.AudioLinkInfo;
import com.ycloud.live.YCMessage.AudioSpeakerInfo;
import com.ycloud.live.YCMessage.VideoLinkInfo;
import com.ycloud.live.video.YCCameraStatusListener;
import com.ycloud.live.video.YCVideoPreview;
import com.ycloud.live.video.YCVideoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LiveActivity extends Activity implements YCCameraStatusListener {
	private String       TAG = "xuawang";

	private boolean        mAudioLinkConnected = false;
	private boolean        mVideoLinkConnected = false;
	private YCVideoPreview   mVideoPreview = null;
	private int 		   mUid = 0;
	private int            mSid = 0;
	private int            mAppId = 0;


	private ImageButton mBtnMicSwitch;
	private ImageButton mBtnAudioMute;
	private ImageButton mBtnVideoSwitch;
	private ImageButton mBtnCameraSwitch;
	private ImageButton mBtnSetAudioMode;
	private ListView mListViewChat;
	private EditText mEditText;
	private Button 	mBtnSend;
	private FrameLayout mCameraPreview;

	private int mCameraType = CameraInfo.CAMERA_FACING_FRONT;

	private boolean mIsOpenMic = false;
	private boolean mIsVideoPublished = false;
	private boolean mIsCameraStarted = false;
	private boolean mIsAudioMute = false;
	private boolean mIsAudioMode = true;

	private List<ChatEntity> mChatList = new ArrayList<ChatEntity>();
	private ChatAdpter mChatAdpter = new ChatAdpter(LiveActivity.this, mChatList);
	public static interface Operation {
		public static final int MSG_CAMERA_PREVIEW_CREATED = 1;
		public static final int MSG_CAMERA_PREVIEW_STOP = 2;
	}

	private YCVideoView mRemoteVideoView;
	private YCVideoView mRemoteVideoView2;
	private ChannelVideoController mChannelVideoController = null;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what)
			{
			case Operation.MSG_CAMERA_PREVIEW_CREATED:
				handleCameraPreviewReady();
				break;

			case Operation.MSG_CAMERA_PREVIEW_STOP:
				handlePreviewStoped();
				break;

			case YCMessage.MsgType.onVideoLinkInfoNotity:
				YCMessage.VideoLinkInfo videoLinkInfo = (YCMessage.VideoLinkInfo)msg.obj;
				Log.d(TAG, "onVideoLinkInfoNotity, state " + videoLinkInfo.state);
				mVideoLinkConnected =  videoLinkInfo.state == VideoLinkInfo.Connected ? true : false;
				break;

			case YCMessage.MsgType.onVideoStreamInfoNotify:
				YCMessage.VideoStreamInfo streamInfo = (YCMessage.VideoStreamInfo) msg.obj;
				mChannelVideoController.onVideoStreamInfoNotify(streamInfo);
				break;

			case YCMessage.MsgType.onAudioLinkInfoNotity:
				YCMessage.AudioLinkInfo audioLinkInfo = (YCMessage.AudioLinkInfo)msg.obj;
				Log.d(TAG, "onAudioLinkInfoNotity, state: " + audioLinkInfo.state);
				mAudioLinkConnected =  audioLinkInfo.state == AudioLinkInfo.Connected ? true : false;
				break;

			case YCMessage.MsgType.onMicStateInfoNotify:
				YCMessage.MicStateInfo micStateInfo = (YCMessage.MicStateInfo)msg.obj;
				Log.d(TAG, "onMicStateInfoNotify, state: " + micStateInfo.state);
				break;

			case YCMessage.MsgType.onAudioSpeakerInfoNotity:
				YCMessage.AudioSpeakerInfo speakerInfo = (YCMessage.AudioSpeakerInfo)msg.obj;
				Log.d(TAG, "onAudioSpeakerInfoNotity, state: " + speakerInfo.state);
				handleAudioSpeaker(speakerInfo.state);
				break;

			case YCMessage.MsgType.onChatTextNotify:
				YCMessage.ChatText sessionText = (YCMessage.ChatText)msg.obj;
				Log.d(TAG, "onChatTextNotify msg:" + sessionText.text);

				ChatEntity chatEntity = new ChatEntity();
				chatEntity.mColor = sessionText.color;
				chatEntity.mNickName = Integer.toString(sessionText.uid);
				chatEntity.mText = sessionText.text;

				mChatList.add(chatEntity);
				mChatAdpter.notifyDataSetChanged();
				break;
			default:
				Log.e(TAG, "can't handle the message:" + msg.what);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live);
		Log.d(TAG, "onCreate");

		initView();

		Intent intent = this.getIntent();
		mAppId = intent.getIntExtra("appid", 1268144600);
		mUid = intent.getIntExtra("uid", 0);
		mSid = intent.getIntExtra("sid", 0);

		mListViewChat.setAdapter(mChatAdpter);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//摄像头事件回调
		YCMedia.getInstance().setCameraStatusListener(this);
		//注册消息处理
		YCMedia.getInstance().addMsgHandler(mHandler);

		//参数配置
		Map<Integer, Integer> configs = new HashMap<Integer, Integer>();
		configs.put(YCConstant.ConfigKey.LOGIN_MODEL, YCConstant.LOGIN_MODEL_CHANNEL);
		//YCConstant.ConfigKey.
		//video setting
		configs.put(YCConstant.ConfigKey.VIDEO_RECORD_QUALITY, YCConstant.MEDIA_QUALITY_MEDIUM);
		configs.put(YCConstant.ConfigKey.VIDEO_MIN_BUFFER,  100);
		//audio setting
		configs.put(YCConstant.ConfigKey.AUDIO_RECORD_QUALITY, YCConstant.MEDIA_QUALITY_HIGH);
		configs.put(YCConstant.ConfigKey.AUDIO_MIN_BUFFER, 100);
		YCMedia.getInstance().requestMethod(new YCMediaRequest.YCSetConfigs(mAppId, configs));

	}

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

		mChannelVideoController = new ChannelVideoController(mRemoteVideoView, mRemoteVideoView2);

		 new Thread() {
	 	        @Override
	 	        public void run() {

	 	        	String token = ApTokenUtils.GenToken(String.valueOf(mAppId), "LIVE", 0x03, mUid, mSid, 98765412, "");
	 	        	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCLogin(mAppId, mSid, mUid, token));
	 	        }
	 	    }.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        leave();

    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy.");

    }

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onDestroy.");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}



	private void initView() {
		mCameraPreview = (FrameLayout) findViewById(R.id.camera_preview);
		mBtnMicSwitch = (ImageButton)findViewById(R.id.btn_mic_mute2);
		mBtnAudioMute = (ImageButton)findViewById(R.id.btn_audio_mute2);
		mBtnVideoSwitch = (ImageButton)findViewById(R.id.btn_switch_video2);
		mBtnCameraSwitch = (ImageButton)findViewById(R.id.btn_camera_switch);
		mBtnSetAudioMode = (ImageButton)findViewById(R.id.btn_set_audiomode);

		mBtnMicSwitch.setImageDrawable(getResources().getDrawable(R.drawable.mic_disabled));
		mBtnAudioMute.setImageDrawable(getResources().getDrawable(R.drawable.audio_on));
		mBtnVideoSwitch.setImageDrawable(getResources().getDrawable(R.drawable.btn_video_off));
		mBtnSetAudioMode.setImageDrawable(getResources().getDrawable(R.drawable.ic_call_speaker_on));
		mRemoteVideoView = (YCVideoView) findViewById(R.id.remote_view);
		mRemoteVideoView2 =  (YCVideoView) findViewById(R.id.video_view_2);

		mListViewChat = (ListView)findViewById(R.id.listview_chat);
		mEditText = (EditText)findViewById(R.id.edittext_msg);
		mBtnSend = (Button)findViewById(R.id.btn_send);
		initButtonEvent();

	}

	private void initButtonEvent()
	{

		mBtnMicSwitch.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(!mAudioLinkConnected) {
					Toast.makeText(getApplicationContext(), R.string.err_no_server,
						     Toast.LENGTH_SHORT).show();
					return;
				}

				if(mIsOpenMic) {
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCCloseMic());
					mBtnMicSwitch.setImageDrawable(getResources().getDrawable(
							R.drawable.mic_disabled));
					mIsOpenMic = false;
					Log.d(TAG,"close mic");

				}
				else {
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCOpenMic());
					mBtnMicSwitch.setImageDrawable(getResources().getDrawable(R.drawable.mic_enabled));
					mIsOpenMic = true;
					Log.d(TAG,"open mic");

				}

			}
		});


		mBtnAudioMute.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!mAudioLinkConnected) {
					Toast.makeText(getApplicationContext(), R.string.err_no_server,
						     Toast.LENGTH_SHORT).show();
					return;
				}

				if(mIsAudioMute) {
					Log.d(TAG,"audio mute,  disable");
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCMuteAudio(false));
					mIsAudioMute = false;
					mBtnAudioMute.setImageDrawable(getResources().getDrawable(R.drawable.audio_on));
				}
				else {
					Log.d(TAG,"audio mute,  enable");
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCMuteAudio(true));
					mIsAudioMute = true;
					mBtnAudioMute.setImageDrawable(getResources().getDrawable(R.drawable.audio_off));
				}
			}
		});


		mBtnVideoSwitch.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(!mVideoLinkConnected) {
					Toast.makeText(getApplicationContext(), R.string.err_no_server,
						     Toast.LENGTH_SHORT).show();
					return;
				}

				if(mIsCameraStarted) {
					Log.d(TAG, "stop camera");

					//停止服务器录制
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStopServerRecord());
					//停止视频流发布
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStopPublishVideo());
					//停止摄像头
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStopCamera());
					mBtnVideoSwitch.setImageDrawable(getResources().getDrawable(R.drawable.btn_video_off));
					mCameraPreview.removeAllViews();
					mCameraPreview.setVisibility(View.INVISIBLE);
					mIsCameraStarted = false;
					mIsVideoPublished = false;
				}
				else {
					Log.d(TAG, "start camera");
					mCameraPreview.setVisibility(View.VISIBLE);
					//打开摄像头
					YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStartCamera());
					mBtnVideoSwitch.setImageDrawable(getResources().getDrawable(R.drawable.btn_video_on));
					mIsCameraStarted = true;
				}

			}
		});

		mBtnCameraSwitch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(!mVideoLinkConnected) {
					Toast.makeText(getApplicationContext(), R.string.err_no_server,
						     Toast.LENGTH_SHORT).show();
					return;
				}

				mCameraType = mCameraType == CameraInfo.CAMERA_FACING_FRONT ? CameraInfo.CAMERA_FACING_BACK : CameraInfo.CAMERA_FACING_FRONT;
				YCMedia.getInstance().requestMethod(new YCMediaRequest.YCSwitchCamera(mCameraType));
			}
		});

		mBtnSetAudioMode.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
            @Override
			public void onClick(View arg0) {

				if(mIsAudioMode) {
					YCMedia.getInstance().setAudioMode(false);
					mBtnSetAudioMode.setImageDrawable(getResources().getDrawable(R.drawable.ic_call_speaker_off));
					mIsAudioMode = false;
				}
				else {
					YCMedia.getInstance().setAudioMode(true);
					mBtnSetAudioMode.setImageDrawable(getResources().getDrawable(R.drawable.ic_call_speaker_on));
					mIsAudioMode = true;
				}
			}
		});

		mBtnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String msg = mEditText.getText().toString();
				if (msg == null || msg.equals(""))
					return;

				//发送消息
				YCMediaRequest.YCSendChatText sendText = new YCMediaRequest.YCSendChatText(msg, 0, 0);
				YCMedia.getInstance().requestMethod(sendText);

				//显示到本地列表
				ChatEntity chatEntity = new ChatEntity();
				chatEntity.mIsSelf = true;
				chatEntity.mNickName = mUid + "(自己)";
				chatEntity.mText = msg;
				mChatList.add(chatEntity);
				mChatAdpter.notifyDataSetChanged();
				mEditText.setText("");
			}
		});
	}

	private void handleCameraPreviewReady() {

		Log.d(TAG, "preview update");

		mVideoPreview.setAspectRatio(50);
		Log.d(TAG, "startLiveBroadcast 1");
		mCameraPreview.removeAllViews();
		mCameraPreview.addView(mVideoPreview);


    	if(!mIsVideoPublished) {
    		//开始发布本地视频流
    		YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStartPublishVideo());
    		//programId 唯一识别字符串, 生成字符串的规则是: channelid_appid_spkuid_timestamp
    		String programId = String.format("%d_%d_%d_%d", mSid, mAppId, mUid,  System.currentTimeMillis() );
    		Log.d(TAG, "start server record:" + programId);
    		YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStartServerRecord(programId));
    		mIsVideoPublished = true;
    	}
	}

	private void handleAudioSpeaker(int state){

		if(state == AudioSpeakerInfo.Start && !mIsAudioMute) {
			YCMedia.getInstance().requestMethod(new YCMediaRequest.YCMuteAudio(false));
		}
	}

    private void handlePreviewStoped() {
    	Log.d(TAG, "handlePreviewStoped");
    }

    private void leave() {

    	mCameraPreview.removeAllViews();
    	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCCloseMic());
    	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCMuteAudio(false));
    	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStopCamera());
    	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCStopPublishVideo());
    	if(mChannelVideoController != null) {
			mChannelVideoController.destroy();
			mChannelVideoController = null;
		}
       	YCMedia.getInstance().requestMethod(new YCMediaRequest.YCLogout());
    }

	@Override
	public void onPreviewCreated(YCVideoPreview preview) {
		Log.d(TAG, "callback onPreviewCreated");

		mVideoPreview = preview;
		mVideoPreview.setZOrderMediaOverlay(true);
    	Message msg = mHandler.obtainMessage();
		msg.what = Operation.MSG_CAMERA_PREVIEW_CREATED;
        mHandler.sendMessage(msg);
        Log.d(TAG, "callback onPreviewCreated done");
    	if(mVideoPreview == null) {
			Log.d(TAG, "CameraActivity callback onPreviewCreated, null");
		}
	}

	@Override
	public void onPreviewStartSuccess() {
		Log.d(TAG, "CameraActivity callback onPreviewStartSuccess");
	}

	@Override
	public void onPreviewStartFailed() {
		Log.d(TAG, "CameraActivity callback onPreviewStartFailed");
	}

	@Override
	public void onPreviewStopped() {
		Log.d(TAG, "CameraActivity callback onPreviewStopped");
		mVideoPreview= null;
		Message msg = mHandler.obtainMessage();
    	msg.what = Operation.MSG_CAMERA_PREVIEW_STOP;
    	mHandler.sendMessage(msg);
	}

	@Override
	public void onOpenCameraFailed() {
		Log.d(TAG, "CameraActivity callback onOpenCameraFailed");
	}

	@Override
	public void onVideoRecordStarted() {
		Log.d(TAG, "CameraActivity callback onVideoRecordStarted");
	}

	@Override
	public void onVideoRecordStopped() {
		Log.d(TAG, "CameraActivity callback onVideoRecordStopped");
	}
}
