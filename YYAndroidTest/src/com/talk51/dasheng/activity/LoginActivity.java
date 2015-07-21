package com.talk51.dasheng.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.talk51.dasheng.R;
import com.talk51.dasheng.YYApplication;
import com.talk51.dasheng.protocol.ProtoEvent;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEventBase;
import com.talk51.dasheng.protocol.ProtoEvent.ProtoEvtLoginRes;
import com.talk51.dasheng.protocol.ProtoReq.LoginReq;
import com.talk51.dasheng.protocol.ProtoReq.LogoutReq;
import com.talk51.dasheng.protocol.ProtoReq.YCTokenRequest;
import com.yyproto.base.YYHandler;
import com.yyproto.outlet.SDKParam;

public class LoginActivity extends UIActivity
{
    private final String TAG = "xuawang";
    private EditText mUserName, mPassword;
//    private List<AccountInfo>  accountInfoList;
//    private AccountList accountList;
//    private AdvProgressDlg mProgressDlg = null;

    private YYApplication mApp = null;

    private YYHandler mHandler = new YYHandler()
    {
        @MessageHandler(message = SDKParam.Message.messageId)
        public void onEvent(byte[] data)
        {
        	ProtoEventBase base = new ProtoEventBase();
        	base.unmarshal(data);

        	switch (base.eventType)
        	{
        	    case ProtoEvent.EventType.PROTO_EVENT_LOGIN_RES:
        	    {
        	    	onLoginRes(data);
                    break;
        	    }
        	    default:
        	    {
        	    	Log.i("YCSdk", "LoginActivity::YYHandler: Not care eventType:" + base.eventType + ", len:");
        	    }
        	}
        }


//        @MessageHandler(message = YYMessage.LoginMessage.onLogin)
//        public void onLogin(LoginResEvent et)
//        {
//            if(mProgressDlg != null)
//                mProgressDlg.dismiss();
//
//            if (et == null)
//                return;
//
//            int res = et.rescode;
//            if(res == LoginResEvent.RES_SUCCESS)
//            {
//                Log.i(TAG, "onLogin");
//                //LoginData.getInstance().load();
//                mApp.setOnline(true);
//
//                IDatabase db = mApp.getDb();
//                ITable table = db.getTable(ProtoTable.TABLE_ID.E_TBL_LOGINUINFO.ordinal());
//                IRow row = table.getRow(ProtoTable.ONE_DIM_KEY);
//                int uid = row.getInt32(ProtoTable.LOGINUINFO.dwUid.ordinal());
//                String passport = row.getString(ProtoTable.LOGINUINFO.strPassport.ordinal());
//                String userName = mUserName.getText().toString();
//                String password = mPassword.getText().toString();
//
//                AccountInfo account = AccountManager.getInstance().getAccount(uid);
//                if(account == null)
//                {
//                    account = new AccountInfo();
//                    account.uid = uid;
//                    account.username = userName;
//                    account.passport = passport;
//                    account.password = password;
//                    byte[] passwdByte = mApp.getLogin().getPasswdSha1(password);
//                    String passwdSha1 = new String(passwdByte);
//                    account.passwordSha1 = passwdSha1;
//                    AccountManager.getInstance().saveAccount(account);
//                }
//
//                //send
//
//                LoginRequest.GetUInfoLogoReq req = new LoginRequest.GetUInfoLogoReq(account.uid, SDKParam.EUserLogoType.HD_LOGO_144);
//                mApp.getLogin().sendRequest(req);
//
//                //test
//                GetIMUInfoReq req2 = new LoginRequest.GetIMUInfoReq();
//                //get partial
//                //req2.mGetall = false;
//                req2.mUids = new int[2];
//                //req2.mUids[0] = 50013215;
//                //req2.mUids[1] = 842494409;
//                req2.mUids[0] = uid;
//                req2.mProps.add(SDKParam.IMUInfoPropSet.uid.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.nick.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.sex.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.birthday.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.area.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.province.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.city.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.sign.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.intro.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.jifen.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.yyno.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.logo_index.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.custom_logo.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.hd_logo_100.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.hd_logo_144.getBytes());
//                req2.mProps.add(SDKParam.IMUInfoPropSet.hd_logo_640.getBytes());
//                //get all
//                //req2.mGetall = true;
//                mApp.getLogin().sendRequest(req2);
//
//
//                //slist
//                SyncMyListReq sycReq = new SyncMyListReq();
//                sycReq.cmd = (SyncMyListReq.CMD_SYNC_QLIST_FLAG | SyncMyListReq.CMD_SYNC_SLIT_FLAG | SyncMyListReq.CMD_YSNC_GUILD_FLAG);
//                mApp.getLogin().sendRequest(sycReq);
//
//                //test get user guild
//                int tmpUid = 627511384;
//                GetGuildInfoReq guildReq = new GetGuildInfoReq(tmpUid);
//                mApp.getLogin().sendRequest(guildReq);
//
//                //test get my channel list
//                /*
//                for(int i=0; i<6; i++)
//                {
//                	GetMyChanListReq getChReq = new GetMyChanListReq();
//                	int reqRes = mApp.getLogin().sendRequest(getChReq);
//                	Log.i(TAG, "get my channel list res="+reqRes);
//                }
//                */
//                //service test
//
//                //subscribe svc data
//                //int[] appIds = new int[1];
//                //appIds[0] = 6;
//                //LoginRequest.AppSubcribeReq req = new LoginRequest.AppSubcribeReq(LoginRequest.AppSubcribeReq.CMD_APPSUB_BYAPPID, appIds);
//                //mApp.getLogin().sendRequest(req);
//
//                //send ServiceData
//                //int mSvcType = 6;
//                //final String jsonData = "{\"2\":{\"sid\":1953846290},\"0\":102}";
//                //LoginRequest.SvcDataReq req = new LoginRequest.SvcDataReq(mSvcType, jsonData.getBytes());
//                //mApp.getLogin().sendRequest(req);
//                //Log.i(TAG, "[service] send service data:"+jsonData);
//                //Log.i(TAG, "[service] test send service data:"+ jsonData);
//
//                //test case1:
//                //free charge redirect
//                /*
//                LoginChargeFreeAuthorized authReq = new LoginChargeFreeAuthorized();
//                authReq.type = SDKParam.FreeChargeType.UNICOM;
//                mApp.getLogin().sendRequest(authReq);
//                */
//
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//            else if(res != 0)
//            {
//                mApp.setOnline(false);
//                logout();
//                new AlertDialog.Builder(LoginActivity.this)
//                .setMessage("login failed, res=" + res + " udbRes=" + et.udbRes)
//                .show();
//            }
//        }

//
//        @MessageHandler(message = YYMessage.LoginMessage.onPicCode)
//        public void onPicCode(final ETPicCode et)
//        {
//            if(mProgressDlg != null)
//                mProgressDlg.dismiss();
//
//            LayoutInflater LayoutInflater = (LayoutInflater) mApp.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            if (LayoutInflater == null)
//            {
//                throw new AssertionError("LayoutInflater not found.");
//            }
//            View v = LayoutInflater.inflate(R.layout.login_image_code, null, false);
//
//            ImageView iv = (ImageView) v.findViewById(R.id.image_code);
//            iv.setImageBitmap(BitmapFactory.decodeByteArray(et.picCode, 0, et.picCode.length));
//
//            final EditText editText = (EditText) v.findViewById(R.id.text);
//
//            new CustomAlert.Builder(LoginActivity.this).title(R.string.str_input_piccode).customView(v).cancelable(false)
//            .negative(R.string.str_cancel).positive(R.string.str_confirm)
//            .onClickListener(new DialogInterface.OnClickListener()
//            {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i)
//                {
//                    if (i == DialogInterface.BUTTON_POSITIVE)
//                    {
//                        LoginRequest.PicCodeVerifyReq req = new LoginRequest.PicCodeVerifyReq();
//                        Log.i(TAG, "onPicCode picId" + new String(et.picId) + " context size=" + et.wrapContext.size() + " resCode=" + et.resCode);
//                        req.picId = et.picId;
//                        req.valiCode = editText.getText().toString().getBytes();
//                        req.wrapContext = et.wrapContext;
//
//                        mApp.getLogin().sendRequest(req);
//                    }
//                    else
//                    {
//                        //test: refresh pic code
//                        /*
//                        RefreshPicCodeReq req = new LoginRequest.RefreshPicCodeReq();
//                        req.picCodeType = LoginRequest.RefreshPicCodeReq.CHINESE_PIC_CODE;
//                        req.strSeq = "".getBytes();
//
//                        mApp.getLogin().sendRequest(req);
//                        */
//                        //
//                        logout();
//                    }
//                }
//            }).show();
//        }
//
//        @MessageHandler(message = YYMessage.LoginMessage.onRefreshPicCode)
//        public void onRefreshPicCode(final ETRefreshPicCode et)
//        {
//            if(mProgressDlg != null)
//                mProgressDlg.dismiss();
//
//            LayoutInflater LayoutInflater = (LayoutInflater) mApp.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            if (LayoutInflater == null)
//            {
//                throw new AssertionError("LayoutInflater not found.");
//            }
//            View v = LayoutInflater.inflate(R.layout.login_image_code, null, false);
//
//            ImageView iv = (ImageView) v.findViewById(R.id.image_code);
//            iv.setImageBitmap(BitmapFactory.decodeByteArray(et.picCode, 0, et.picCode.length));
//
//            final EditText editText = (EditText) v.findViewById(R.id.text);
//
//            new CustomAlert.Builder(LoginActivity.this).title(R.string.str_input_piccode).customView(v).cancelable(false)
//            .negative(R.string.str_cancel).positive(R.string.str_confirm)
//            .onClickListener(new DialogInterface.OnClickListener()
//            {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i)
//                {
//                    if (i == DialogInterface.BUTTON_POSITIVE)
//                    {
//                        LoginRequest.PicCodeVerifyReq req = new LoginRequest.PicCodeVerifyReq();
//                        Log.i(TAG, "onRefreshPicCode picId" + new String(et.picId) + " resCode=" + et.resCode);
//                        req.picId = et.picId;
//                        req.valiCode = editText.getText().toString().getBytes();
//
//                        mApp.getLogin().sendRequest(req);
//                    }
//                    else
//                    {
//                        //test: refresh pic code
//                        /*
//                        RefreshPicCodeReq req = new LoginRequest.RefreshPicCodeReq();
//                        req.picCodeType = LoginRequest.RefreshPicCodeReq.CHINESE_PIC_CODE;
//                        req.strSeq = "".getBytes();
//
//                        mApp.getLogin().sendRequest(req);
//                        */
//                        //
//                        logout();
//                    }
//                }
//            }).show();
//        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApp = (YYApplication)getApplication();

        mUserName = (EditText) findViewById(R.id.EdtAccount);
        mPassword = (EditText) findViewById(R.id.EdtPassword);

//        initAccountList();

        // onclick
        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                login(username, password);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mApp.getProtoMgr().addHandlerWatcher(mHandler);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mApp.getProtoMgr().removeHandlerWatcher(mHandler);
    }
//
//    private void initAccountList()
//    {
//        accountInfoList = AccountManager.getInstance().getAccounts();
//        if (accountInfoList.size() > 0)
//        {
//            AccountInfo info = accountInfoList.get(0);
//            if (info != null)
//            {
//                mUserName.setText(info.username);
//                mPassword.setText(info.password);
//            }
//        }
//
//        View viewShowAccounts = findViewById(R.id.show_list);
//        if (accountInfoList.size() > 0)
//        {
//            final View anchor = findViewById(R.id.edit_account_parent);
//            accountList = new AccountList(this, anchor,
//                                          new SelectAccountListener()
//            {
//                @Override
//                public void onSelect(int index)
//                {
//                    AccountInfo info = accountInfoList.get(index);
//                    mUserName.setText(info.username);
//                    mPassword.setText(info.password);
//                }
//
//                @Override
//                public void onRemove(int index)
//                {
//                    if (index >= accountInfoList.size())
//                    {
//                        return;
//                    }
//                    final AccountInfo info = accountInfoList.get(index);
//                    if (info == null)
//                    {
//                        return;
//                    }
//                    String title = getString(R.string.str_remove_account);
//                    String msg = getString(R.string.str_want_to_remove_account) + info.username;
//
//                    new AlertDialog.Builder(LoginActivity.this)
//                    .setTitle(title)
//                    .setMessage(msg)
//                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//                            AccountManager.getInstance().deleteAccount(info.username);
//                            accountList.dismiss();
//                            accountInfoList.remove(info);
//                            initAccountList();
//                        }
//                    })
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//
//                        }
//                    })
//                    .show();
//                }
//            });
//            viewShowAccounts.setVisibility(View.VISIBLE);
//            viewShowAccounts.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View v)
//                {
//                    accountList.show(anchor);
//                }
//            });
//        }
//    }

    private void login(String account, String password)
    {
//        //get Network status
//        int networkType = getNetType(LoginActivity.this);
//        Log.i(TAG, "networkType is " + networkType + "");
//
//        if(networkType != ProtoConst.SYSNET_MOBILE && networkType != ProtoConst.SYSNET_WIFI)
//        {
//            Toast.makeText(LoginActivity.this, getResources().getString(R.string.str_disconnect), Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        mProgressDlg = new AdvProgressDlg(LoginActivity.this);
//        mProgressDlg.setMessage("logining");
//        mProgressDlg.setOnCancelListener(new OnCancelListener()
//        {
//            @Override
//            public void onCancel(DialogInterface dialog)
//            {
//                logout();
//            }
//        });
//
//        mProgressDlg.show();

    	Log.i(TAG, "LoginActivity::login: account=" + account + ", password=" + password);

        YYApplication app = (YYApplication)getApplication();
        int appKey       = app.getAppKey();
        int ver          = app.getAppVerInt();
        int expiretime   = 60 * 60 * 24;
        String secretKey = app.getSecretKey();

        YCTokenRequest tokenReq = new YCTokenRequest(appKey, ver, expiretime, secretKey);
//        tokenReq.addStr2StrProp(new Str2StrProp("strPropKey", "strPropVal"));
//        tokenReq.addStr2U32Prop(new Str2U32Prop("key32", 32));
//        tokenReq.addStr2U64Prop(new Str2U64Prop("key64", 64));

        String token =  new String(app.getProtoMgr().getYCTokenHex(tokenReq.getBytes()));
        LoginReq req = new LoginReq(account, password, token, LoginReq.THIRD_LOGIN);
        req.context = "login";

        app.getProtoMgr().sendRequest(req.getBytes());

        Log.i(TAG, "LoginActivity::login: token:" + token.length());
//        byte[] passwdByte = mApp.getLogin().getPasswdSha1(password);
//        String passwdSha1 = new String(passwdByte);
//        Log.i(TAG, "passwd=" + password + "passwdSha1=" + passwdSha1 + " length=" + passwdSha1.length());
//        LoginReqLogin req = new LoginReqLogin(account, passwdSha1);
//        req.setLoginType(LoginReqLogin.LOGIN_TYPE_PASSPORT);
//        mApp.getLogin().sendRequest(req);

        //Test: 3rd part login

        //		String tmpAccount = "gr_test01";
        //		String passwd = "CQBncl90ZXN0MDFpjapTBHEAW_-4ZQFXq2DdWp64F8vwbH61CijCypM8v9FbmNLtJcx3vkvY7_fJFtnBRath3Z7iMpOMpgVOcwHqzJUz0XTeWmL4merChtjAuqHArYDwEtmcYoimUEaygw9Q_R0OumIkZFWl_sPUIqR2T31gZKIclZwAAAAA";
        //		LoginReqLogin req2 = new LoginReqLogin(tmpAccount, passwd);
        //		req2.setLoginType(LoginReqLogin.LOGIN_TYPE_3RD);
        //
        //		mApp.getLogin().sendRequest(req2);
    }

    private void onLoginRes(byte[] data)
    {
    	ProtoEvtLoginRes res = new ProtoEvtLoginRes();
    	res.unmarshal(data);

    	mApp.setOnline(res.res == ProtoEvtLoginRes.LOGIN_SUCESS);

    	if (res.res != ProtoEvtLoginRes.LOGIN_SUCESS)
    	{
            logout();
            new AlertDialog.Builder(LoginActivity.this)
            .setMessage("login failed, res=" + res + " udbRes=" + res.udbRes)
            .show();

    		return;
    	}

    	Log.i("xuawang", "LoginActivity::onLoginRes: Login succ, uid=" + res.uid + ", context=" + res.context);

        mApp.setUid(res.uid);

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void logout()
    {
    	LogoutReq req = new LogoutReq();
    	mApp.getProtoMgr().sendRequest(req.getBytes());

    	mApp.setUid(0);
        mApp.setOnline(false);
    }

//    interface SelectAccountListener
//    {
//        public void onSelect(int index);
//        public void onRemove(int index);
//    }
//
//    /**
//     * AccountList Popup
//     * @author chencb
//     *
//     */
//    private class AccountList extends PopupView
//    {
//        private ListView listView;
//        private SelectAccountListener listener;
//        private View view;
//
//        public AccountList(Activity activity, View view,
//                           SelectAccountListener listener)
//        {
//            super(activity);
//            this.listener = listener;
//            this.view = view;
//            inflater(R.layout.account_list);
//        }
//
//        @Override
//        protected void createPopup()
//        {
//            super.createPopup();
//            int width = view.getWidth();
//            mPopup.setWidth(width);
//            //mPopup.setHeight(LayoutParams.WRAP_CONTENT);
//            listView = (ListView) getView().findViewById(R.id.account_list);
//            listView.setOnItemClickListener(new ListView.OnItemClickListener()
//            {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view,
//                                        int position, long id)
//                {
//                    listener.onSelect(position);
//                    AccountList.this.dismiss();
//                }
//            });
//            listView.setAdapter(new BaseAdapter()
//            {
//                @Override
//                public View getView(final int position, View convertView, ViewGroup parent)
//                {
//                    final LayoutInflater inflater = LayoutInflater.from(getAct());
//                    View v1 = inflater.inflate(R.layout.account_item, parent, false);
//                    AccountInfo info = accountInfoList.get(position);
//                    TextView usernameTextView = (TextView) v1.findViewById(R.id.account_item_username);
//                    usernameTextView.setText(info.username);
//                    ImageView removeIcon = (ImageView) v1.findViewById(R.id.account_item_remove);
//                    removeIcon.setOnClickListener(new View.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(View v)
//                        {
//                            listener.onRemove(position);
//                        }
//                    });
//
//                    return v1;
//                }
//
//                @Override
//                public long getItemId(int position)
//                {
//                    return 0;
//                }
//
//                @Override
//                public Object getItem(int position)
//                {
//                    return null;
//                }
//
//                @Override
//                public int getCount()
//                {
//                    return accountInfoList.size();
//                }
//            });
//        }
//    }
//
//    public static byte getNetType(Context contxt)
//    {
//        byte netType = ProtoConst.SYSNET_DISCONNECT;
//        ConnectivityManager connMgr = (ConnectivityManager) contxt
//                                      .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//
//        if (networkInfo == null)
//        {
//            return netType;
//        }
//
//        int nType = networkInfo.getType();
//        //Log.i(TAG ,"kelvin networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo()+"");
//        if (nType == ConnectivityManager.TYPE_MOBILE)
//        {
//            netType =  ProtoConst.SYSNET_MOBILE;
//        }
//        else if (nType == ConnectivityManager.TYPE_WIFI)
//        {
//            netType = ProtoConst.SYSNET_WIFI;
//        }
//
//        return netType;
//    }
}
