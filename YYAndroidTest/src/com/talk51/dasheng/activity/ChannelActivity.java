package com.talk51.dasheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.talk51.dasheng.R;
import com.talk51.dasheng.YYApplication;
import com.talk51.dasheng.YYSdkWrapper;
import com.talk51.dasheng.protocol.ProtoEvent;
import com.talk51.dasheng.protocol.ProtoEvent.OnlineUser;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessJoinQueueRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessLeaveQueueRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessOperRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessQueryQueueRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessQueryUserInfoRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessTextChatBroadRes;
import com.talk51.dasheng.protocol.ProtoReq;
import com.talk51.dasheng.protocol.ProtoReq.SessFetchChInfoReq;
import com.talk51.dasheng.protocol.ProtoReq.SessJoinQueueReq;
import com.talk51.dasheng.protocol.ProtoReq.SessLeaveQueueReq;
import com.talk51.dasheng.protocol.ProtoReq.SessLeaveReq;
import com.talk51.dasheng.protocol.ProtoReq.SessPullUserListReq;
import com.talk51.dasheng.protocol.ProtoReq.SessQueryQueueReq;
import com.talk51.dasheng.protocol.ProtoReq.SessQueryUserInfoReq;
import com.talk51.dasheng.protocol.ProtoReq.SessTextChatReq;
import com.talk51.dasheng.protocol.ProtoReq.SessUpdateChInfoReq;
import com.talk51.dasheng.protocol.ProtoReq.SessUpdateUserInfoReq;
import com.talk51.dasheng.protocol.ProtoReq.StrProp;
import com.yyproto.base.YYHandler;
import com.yyproto.outlet.SDKParam;

import java.util.LinkedList;
import java.util.Queue;

public class ChannelActivity extends UIFragmentActivity
{
    static final String TAG = "xuawang";

    static final String mAppToken = "10000";

    private TextView mChannelName;
    private EditText mMyText;
    private ImageButton mBtnAudioSwitch;
    private ImageButton mBtnVideoSwitch;
    private ImageButton mBtnSwitchCamera;
    private ImageButton mBtnMediaPause;
    private ImageButton mBtnMicSwitch;
    private Button mBtnChangeVideoCodeRate;
    private Button mBtnFullScreen = null;

    private PopupWindow mPopup;

    private int mSid = 0;
    private int mAsid = 0;
    private int mSubsid = 0;
    private int[] m_micList;
    private String mChannelNameStr;
    private int mTotalCnt = 0;
    private Queue<String> mChatList = new LinkedList<String>();
    private int ctxTest = 0;

    private String mFileName = "";
    private int mNameCount = 0;
    private YYApplication mApp = null;
    private Runnable mOnlineReqRunnable = null;
    private YYHandler mHandler = new YYHandler()
    {
        @MessageHandler(message = SDKParam.Message.messageId)
        public void onEvent(byte[] data)
        {
            int eventType = YYSdkWrapper.getEventType(data);
            switch (eventType)
            {
            case ProtoEvent.EventType.PROTO_EVENT_SESS_JOIN_RES:
            {
                onJoinRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_OPER_RES:
            {
                onCommonOperatorAuthRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_JOIN_QUEUE_RES:
            {
                onJoinQueueRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_LEAVE_QUEUE_RES:
            {
                onLeaveQueueRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_QUERY_QUEUE_RES:
            {
                onQueryQueueRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_TEXT_CHAT_BROAD_RES:
            {
                onTextChatBroadRes(data);
                break;
            }
            case ProtoEvent.EventType.PROTO_EVENT_SESS_QUERY_USER_INFO_RES:
            {
                onQueryUserInfoRes(data);
                break;
            }
            default:
            {
                Log.i("xuawang", "ChannelActivity::YYHandler: Not care eventType:" + eventType + ", len:" + data.length);
            }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //        YLog.info(TAG, ">>ChannelActivity onCreate.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        mApp = (YYApplication)getApplication();

        ctxTest = 0;
        Intent intent = getIntent();
        mSid = intent.getIntExtra("sid", 0);
        mSubsid = intent.getIntExtra("subsid", 0);

        YYSdkWrapper.joinSession(mSid, mSubsid);

        initButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(Menu.NONE, Menu.FIRST + 1, 1, "丢包率模拟")
        .setIcon(android.R.drawable.ic_menu_edit);
        return true;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        YYSdkWrapper.addHandlerWatcher(mHandler);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        YYSdkWrapper.removeHandlerWatcher(mHandler);
    }

    private void initButton()
    {
        // leave
        findViewById(R.id.btn_leave).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                leaveChannel();
            }
        });

        // join queue
        findViewById(R.id.btn_joinQueue).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                joinQueue();
            }
        });

        // leave queue
        findViewById(R.id.btn_leaveQueue).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                leaveQueue();
            }
        });

        // query queue
        findViewById(R.id.btn_queryQueue).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                queryQueueInfo();
            }
        });

        // 发送公屏
        findViewById(R.id.btn_sendTextChat).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textChatReq("测试");
            }
        });

        // 查询用户信息
        findViewById(R.id.btn_queryUserInfo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                queryUserInfoReq();
            }
        });

        // 拉用户列表
        findViewById(R.id.btn_pullUserList).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pullUserListReq();
            }
        });

        // 更新用户信息
        findViewById(R.id.btn_updateUserInfo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateUserInfoReq();
            }
        });

        // 取频道信息
        findViewById(R.id.btn_fetchChInfo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fetchChInfoReq();
            }
        });

        // 更新频道信息
        findViewById(R.id.btn_updateChInfo).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateChInfoReq();
            }
        });
    }

    private void onJoinRes(byte []data)
    {
        mChannelName = (TextView)findViewById(R.id.channelname);
        YYSdkWrapper.parseJoinSessionResponse(data);
        if (YYSdkWrapper.isInSession())
        {
            mChannelName.setText("ok, sid:"+YYSdkWrapper.getSessionId());
       }
        else
        {
            mChannelName.setText("failed,sid:"+YYSdkWrapper.getSessionId());
       }

    }

    private void leaveChannel()
    {
        SessLeaveReq req = new SessLeaveReq();
        req.topSid   = mSid;
        req.appToken = mAppToken;

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::leaveChannel: req:" + req);

        finish();
    }

    private void onCommonOperatorAuthRes(byte[] data)
    {
        ProtoEvtSessOperRes res = new ProtoEvtSessOperRes();
        res.unmarshal(data);

        Log.i(TAG, "ChannelActivity::onCommonOperatorAuthRes: uid=" + res.uid + ", innerUri=" + res.innerUri +
              ", topSid=" + res.topSid + ", subSid=" + res.subSid + ", appKey=" + res.appKey + ", resCode=" +
              res.resCode + ", propsSize=" + res.props.length());
    }

    private void joinQueue()
    {
        SessJoinQueueReq req = new SessJoinQueueReq();
        req.topSid   = mSid;
        req.appToken = mAppToken;

        req.addProp(new StrProp(1, "2"));
        req.addProp(new StrProp(10, "20"));

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::joinQueue: JoinQueueReq req:" + req);
    }

    private void onJoinQueueRes(byte[] data)
    {
        ProtoEvtSessJoinQueueRes res = new ProtoEvtSessJoinQueueRes();
        res.unmarshal(data);

        Log.i(TAG, "ChannelActivity::onJoinQueueRes: uid=" + res.uid + ", topSid=" + res.topSid +
              ", subSid=" + res.subSid + ", appKey=" + res.appKey + ", propSize=" + res.userProps.length());
    }

    private void leaveQueue()
    {
        SessLeaveQueueReq req = new SessLeaveQueueReq();
        req.topSid   = mSid;
        req.appToken = mAppToken;

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::leaveQueue: LeaveQueueReq req:" + req);
    }

    private void onLeaveQueueRes(byte[] data)
    {
        ProtoEvtSessLeaveQueueRes res = new ProtoEvtSessLeaveQueueRes();
        res.unmarshal(data);

        Log.i(TAG, "ChannelActivity::onLeaveQueueRes: uid=" + res.uid + ", topSid=" + res.topSid +
              ", subSid=" + res.subSid + ", appKey=" + res.appKey);
    }

    private void queryQueueInfo()
    {
        SessQueryQueueReq req = new SessQueryQueueReq();
        req.topSid   = mSid;
        req.subSid   = mSubsid;
        req.callBack = "callBack";

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::queryQueueInfo: req:" + req);
    }

    private void onQueryQueueRes(byte[] data)
    {
        ProtoEvtSessQueryQueueRes res = new ProtoEvtSessQueryQueueRes();
        res.unmarshal(data);

        int userSize = 0;
        if (res.userList != null)
            userSize = res.userList.length;

        Log.i(TAG, "ChannelActivity::onQueryQueueRes: topSid=" + res.topSid +
              ", subSid=" + res.subSid + ", appKey=" + res.appKey + ", callBack=" +
              res.callBack + ", userSize=" + userSize);



    }

    private void textChatReq(String chat)
    {
        SessTextChatReq req = new SessTextChatReq();
        req.topSid = mSid;
        req.chat   = chat;

        req.addProp(new ProtoReq.StrProp(1, "2"));
        req.addProp(new ProtoReq.StrProp(10, "20"));

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::textChatReq: req:" + req);
    }

    private void onTextChatBroadRes(byte[] data)
    {
        ProtoEvtSessTextChatBroadRes res = new ProtoEvtSessTextChatBroadRes();
        res.unmarshal(data);

        Log.i(TAG, "ChannelActivity::onTextChatBroadRes: topSid=" + res.topSid +
              ", subSid=" + res.subSid + ", from=" + res.from + ", chat=" + res.chat);



    }

    private void queryUserInfoReq()
    {
        long[] ulist = new long[3];
        ulist[0] = mApp.getUid();
        ulist[1] = 456;
        ulist[2] = 789;

        SessQueryUserInfoReq req = new SessQueryUserInfoReq();
        req.topSid   = mSid;
        req.ulist    = ulist;
        req.callBack = "callBack";

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::queryUserInfoReq: req:" + req);
    }

    private void onQueryUserInfoRes(byte[] data)
    {
        ProtoEvtSessQueryUserInfoRes res = new ProtoEvtSessQueryUserInfoRes();
        res.unmarshal(data);

        StringBuilder build = new StringBuilder();
        build.append("leaves:");
        for (int i = 0, len = res.leaves.length; i < len; ++i)
        {
            if (i != 0)
                build.append(", ");

            build.append(res.leaves[i]);
        }

        build.append(", users:");
        int len = 0;
        if (res.users != null)
            len = res.users.length;

        for (int i = 0; i < len; ++i)
        {
            if (i != 0)
                build.append(", ");

            OnlineUser user = res.users[i];
            if (user != null)
            {
                build.append("uid=" + user.uid);
                build.append(", intProp size=" + user.intProp.length());
                build.append(", strProp size=" + user.strProp.length());
            }
        }

        Log.i(TAG, "ChannelActivity::onQueryUserInfoRes: topSid=" + res.topSid + ", appKey=" + res.appKey +
              ", callBack=" + res.callBack + ", leavesSize=" + res.leaves.length + ", usersSize=" + res.users.length +
              "," + build.toString());
    }

    private void pullUserListReq()
    {
        SessPullUserListReq req = new SessPullUserListReq();
        req.topSid   = mSid;

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::pullUserListReq: req:" + req);
    }











    private void updateUserInfoReq()
    {
        SessUpdateUserInfoReq req = new SessUpdateUserInfoReq();
        req.topSid   = mSid;
        req.uid      = mApp.getUid();
        req.appToken = mAppToken;
        req.addProp(new StrProp(32, "test-32"));
        req.addProp(new StrProp(33, "test-33"));

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::updateUserInfoReq: req:" + req);
    }







    private void updateChInfoReq()
    {
        SessUpdateChInfoReq req = new SessUpdateChInfoReq();
        req.topSid   = mSid;
        req.appToken = mAppToken;
        req.addProp(new StrProp(40960, "test-40960"));
        req.addProp(new StrProp(40961, "test-40961"));

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::updateChInfoReq: req:" + req);
    }










    private void fetchChInfoReq()
    {
        SessFetchChInfoReq req = new SessFetchChInfoReq();
        req.topSid   = mSid;

        mApp.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "ChannelActivity::fetchChInfoReq: req:" + req);
    }








}

