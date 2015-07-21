package com.talk51.dasheng;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.hjc.SDKParam.SDKParam.AppInfo;
import com.yyproto.outlet.IProtoMgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class YYApplication extends Application
{
    private boolean mIsOnline     = false;
    private int     mAppKey       = 1818061784;
    private long    mTerminalType = 0x20001;
    private long    mUid          = 0;
    private byte[]  mAppVer       = "2".getBytes();
    private byte[]  mSecretKey    = "9f93c099_4".getBytes();

    private static AtomicReference<YYApplication> mInstance =
        new AtomicReference<YYApplication>();

    private IProtoMgr mProtoMgr = null;

    public static YYApplication getInstance()
    {
        return mInstance.get();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance.set(this);

        if(isPkgMainProc())
        {
            Log.i("YYSDK", "YYApplication onCreate");

            AppInfo app = new AppInfo();
            app.appKey       = mAppKey;
            app.terminalType = mTerminalType;
            app.logPath      = null;
            app.appVer       = mAppVer;

            mProtoMgr = IProtoMgr.instance();
            mProtoMgr.init(getApplicationContext(), app, null);
        }
    }

    @Override
    public void onTerminate()
    {

    }

    public boolean isPkgMainProc()
    {
        ActivityManager am = ((ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = this.getPackageName();
        int myPid = Process.myPid();
        for (RunningAppProcessInfo info : processInfos)
        {
            if (info.pid == myPid && mainProcessName.equals(info.processName))
            {
                return true;
            }
        }
        return false;
    }

    public IProtoMgr getProtoMgr()
    {
        return mProtoMgr;
    }

    //	public YYHandlerMgr getHandlerMgr(){
    //		return mHandlerMgr;
    //	}

    public void deInit()
    {
        IProtoMgr.instance().deInit();
    }

    public boolean isOnline()
    {
        return mIsOnline;
    }

    public void setOnline(boolean online)
    {
        mIsOnline = online;

        Log.i("YCSdk", "YYApplication::setOnline: online=" + online);
    }

    public void setUid(long uid)
    {
        mUid = uid;
    }

    public long getUid()
    {
        return mUid;
    }

    public int getAppKey()
    {
        return mAppKey;
    }

    public String getAppVer()
    {
        return new String(mAppVer);
    }

    public int getAppVerInt()
    {
        int ver = 0;
        try
        {
            ver = Integer.parseInt(new String(mAppVer));
        }
        catch (Exception e)
        {
            ver = 0;
            Log.i("YCSdk", "YYApplication::getAppVerInt: Set ver as 0:" + mAppVer);
        }

        return ver;
    }

    public String getSecretKey()
    {
        return new String(mSecretKey);
    }
}
