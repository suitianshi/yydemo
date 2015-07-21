package com.talk51.dasheng;

import android.content.Context;
import android.util.Log;

import com.hjc.SDKParam.SDKParam.AppInfo;
import com.talk51.dasheng.protocol.ProtoEvent;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEventBase;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtLoginRes;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtSessJoinRes;
import com.talk51.dasheng.protocol.ProtoReq.LoginReq;
import com.talk51.dasheng.protocol.ProtoReq.LogoutReq;
import com.talk51.dasheng.protocol.ProtoReq.SessJoinReq;
import com.talk51.dasheng.protocol.ProtoReq.SessionJoinProp;
import com.talk51.dasheng.protocol.ProtoReq.YCTokenRequest;
import com.yyproto.base.YYHandler;
import com.yyproto.outlet.IProtoMgr;

public class YYSdkWrapper {
    public static final int APP_KEY = 1818061784;
    private static final int TERMINAL_TYPE = 0x20001;
    public static final byte[] APP_VERSION = "1".getBytes();
    public static final int APP_VERSION_INT = 1;
    private static final byte[] APP_SECRET = "9f93c099_4".getBytes();
    public static final String APP_SECRET_STR = "9f93c099_4";
    private static final String TEST_ACCOUNT = "51talkwx";
    private static final int EXPIRE_IN = Integer.MAX_VALUE;
    private static final ProtoEventBase mEventBase = new ProtoEventBase();

    private static final int FLAG_NONE = 0x0;
    private static final int FLAG_LOGGED_IN = 0x1;
    private static final int FLAG_IN_SESSION = 0x2;

    private static final String tag = "xuawang";
    private static IProtoMgr mProtoMgr;

    /**
     * can only be modified in UI thread
     */
    private static int mFlag = FLAG_NONE;

    private static long mUid = 0;
    private static String mAccount = "";
    private static int mSid = 0;

    public static int getEventType(byte[] data) {
        if(data == null) {
            return ProtoEvent.EventType.PROTO_EVENT_INVALID;
        }
        mEventBase.eventType = ProtoEvent.EventType.PROTO_EVENT_INVALID;
        mEventBase.context = "";
        mEventBase.unmarshal(data);
        return mEventBase.eventType;
    }

    public static void parseLoginResponse(byte[] data) {
        ProtoEvtLoginRes res = new ProtoEvtLoginRes();
        res.unmarshal(data);

        setLoggedIn(res.res == ProtoEvtLoginRes.LOGIN_SUCESS);

        if (res.res != ProtoEvtLoginRes.LOGIN_SUCESS)
        {
            Log.e(tag, "login failed:" + res.toString());
            return;
        }
        Log.e(tag, "login success: uid=" + res.uid);
        mUid = res.uid;
    }

    public static boolean isLoggedIn() {
        return (mFlag & FLAG_LOGGED_IN) != 0;
    }

    public static void setLoggedIn(boolean whether) {
        if(whether) {
            mFlag |= FLAG_LOGGED_IN;
        } else {
            //we can do nothing if we're logged off
            mFlag = FLAG_NONE;
            mAccount = "";
            mUid = 0;
        }
    }

    public static void joinSession(int sid, int subSid) {
        String token = getYCTokenHex();

        SessJoinReq req = new SessJoinReq();
        //to kick off the same account
        req.addProp(new SessionJoinProp(3, "sd"));
        req.topSid   = sid;
        req.subSid   = subSid;
        req.asid     = 0;
        req.appToken = token;

        mSid = sid;

        mProtoMgr.sendRequest(req.getBytes());
    }

    public static void parseJoinSessionResponse(byte []data)
    {
        ProtoEvtSessJoinRes res = new ProtoEvtSessJoinRes();
        res.unmarshal(data);

        mSid = res.topSid;
        if (res.errId == ProtoEvtSessJoinRes.OPERATOR_SUCCESS)
        {
            mFlag |= FLAG_IN_SESSION;
        }
        else
        {
            mFlag &= ~FLAG_IN_SESSION;
            //mSid = 0;
        }
    }

    public static long getUid() {
        return mUid;
    }

    public static int getSessionId() {
        return mSid;
    }

    public static boolean isInSession() {
        return ((mFlag & FLAG_IN_SESSION) != 0);
    }

    public static void logout() {
        LogoutReq req = new LogoutReq();
        mProtoMgr.sendRequest(req.getBytes());
        setLoggedIn(false);
    }

    public static String getYCTokenHex() {
        int appKey       = APP_KEY;
        int ver          = APP_VERSION_INT;
        int expiretime   = EXPIRE_IN;
        String secretKey = APP_SECRET_STR;

        YCTokenRequest tokenReq = new YCTokenRequest(appKey, ver, expiretime, secretKey);

        String token =  new String(mProtoMgr.getYCTokenHex(tokenReq.getBytes()));
        return token;
    }

    public static void login(String account, String passwd) {
        if(account == null || account.length() == 0) {
            account = TEST_ACCOUNT;
        }
        LoginReq req = new LoginReq(account, passwd, getYCTokenHex(), LoginReq.THIRD_LOGIN);
        req.context = "login";
        mAccount = account;
        mProtoMgr.sendRequest(req.getBytes());
    }

    public static IProtoMgr getProtoMgr() {
        return mProtoMgr;
    }

    public static void addHandlerWatcher(YYHandler h) {
        mProtoMgr.addHandlerWatcher(h);
    }

    public static void removeHandlerWatcher(YYHandler h) {
        mProtoMgr.removeHandlerWatcher(h);
    }

    /**
     * must be called in Application.onCreate
     * @param context
     */
    public static void initProtoMgr(Context context) {
        AppInfo app = new AppInfo();
        app.appKey       = APP_KEY;
        app.terminalType = TERMINAL_TYPE;
        app.logPath      = null;
        app.appVer       = APP_VERSION;

        mProtoMgr = IProtoMgr.instance();
        mProtoMgr.init(context.getApplicationContext(), app, null);
    }

    public static void deInitProtoMgr() {
        IProtoMgr.instance().deInit();
    }
}
