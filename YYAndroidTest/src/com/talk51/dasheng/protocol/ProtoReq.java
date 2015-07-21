package com.talk51.dasheng.protocol;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class ProtoReq
{
    static public class ReqType
    {
        final static int PROTO_REQ_LOGIN                = 1;
        final static int PROTO_REQ_LOGOUT               = 2;

        // sess
        final static int PROTO_REQ_JOIN                 = 1000;
        final static int PROTO_SESS_REQ_LEAVE           = 1001;
        final static int PROTO_SESS_UPDATE_CHANNEL_INFO = 1002;
        final static int PROTO_SESS_UPDATE_USER_INFO    = 1003;
        final static int PROTO_SESS_PULL_USER_LIST      = 1004;
        final static int PROTO_SESS_QUERY_USER_INFO     = 1005;
        final static int PROTO_SESS_FETCH_CH_INFO       = 1006;
        final static int PROTO_SESS_TEXT_CHAT           = 1007;

        // session queue
        final static int PROTO_SESS_JOIN_QUEUE          = 1100;
        final static int PROTO_SESS_LEAVE_QUEUE         = 1101;
        final static int PROTO_SESS_QUERY_QUEUE         = 1102;

        final static int PROTO_REQ_CONFIGINFO           = 20003;
        final static int PROTO_REQ_SYSTEMINFO           = 20004;
    }

    static public class IntProp
    {
        public int    propKey;
        public int    propVal;

        public IntProp(int key, int val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class StrProp
    {
        public int    propKey;
        public String propVal = null;

        public StrProp(int key, String val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class Str2StrProp
    {
        public String propKey = null;
        public String propVal = null;

        public Str2StrProp(String key, String val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class Str2StrProps_t
    {
        public Str2StrProp[] props = null;

        Str2StrProps_t()
        {
            props = null;
        }

        public void setProp(Str2StrProp[] prop)
        {
            props = prop;
        }

        public void addProp(Str2StrProp prop)
        {
            if (prop == null)
                return;

            int len = 0;
            if (props != null)
                len = props.length;

            Str2StrProp[] tmp = props;
            props = new Str2StrProp[len + 1];
            for (int i = 0; i < len; ++i)
            {
                props[i] = tmp[i];
            }

            props[len] = prop;
        }

        public JSONArray getObject()
        {
            JSONArray obj = new JSONArray();

            try
            {
                int len = 0;
                if (props != null)
                    len = props.length;

                for (int i = 0; i < len; ++i)
                {
                    Str2StrProp prop = props[i];
                    if (prop != null)
                    {
                        obj.put(obj.length(), prop.getObject());
                    }
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "Str2StrProps_t::getObject: failed:" + e);
            }

            return obj;
        }
    }

    static public class Str2U32Prop
    {
        public String propKey = null;
        public int    propVal;

        public Str2U32Prop(String key, int val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class Str2U32Props_t
    {
        public Str2U32Prop[] props = null;

        Str2U32Props_t()
        {
            props = null;
        }

        public void setProp(Str2U32Prop[] prop)
        {
            props = prop;
        }

        public void addProp(Str2U32Prop prop)
        {
            if (prop == null)
                return;

            int len = 0;
            if (props != null)
                len = props.length;

            Str2U32Prop[] tmp = props;
            props = new Str2U32Prop[len + 1];
            for (int i = 0; i < len; ++i)
            {
                props[i] = tmp[i];
            }

            props[len] = prop;
        }

        public JSONArray getObject()
        {
            JSONArray obj = new JSONArray();

            try
            {
                int len = 0;
                if (props != null)
                    len = props.length;

                for (int i = 0; i < len; ++i)
                {
                    Str2U32Prop prop = props[i];
                    if (prop != null)
                    {
                        obj.put(obj.length(), prop.getObject());
                    }
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "Str2U32Prop_t::getObject: failed:" + e);
            }

            return obj;
        }
    }

    static public class Str2U64Prop
    {
        public String propKey = null;
        public long   propVal;

        public Str2U64Prop(String key, long val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class Str2U64Props_t
    {
        public Str2U64Prop[] props = null;

        Str2U64Props_t()
        {
            props = null;
        }

        public void setProp(Str2U64Prop[] prop)
        {
            props = prop;
        }

        public void addProp(Str2U64Prop prop)
        {
            if (prop == null)
                return;

            int len = 0;
            if (props != null)
                len = props.length;

            Str2U64Prop[] tmp = props;
            props = new Str2U64Prop[len + 1];
            for (int i = 0; i < len; ++i)
            {
                props[i] = tmp[i];
            }

            props[len] = prop;
        }

        public JSONArray getObject()
        {
            JSONArray obj = new JSONArray();

            try
            {
                int len = 0;
                if (props != null)
                    len = props.length;

                for (int i = 0; i < len; ++i)
                {
                    Str2U64Prop prop = props[i];
                    if (prop != null)
                    {
                        obj.put(obj.length(), prop.getObject());
                    }
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "Str2U64Prop_t::getObject: failed:" + e);
            }

            return obj;
        }
    }

    static public class YCTokenRequest
    {
        private int             appKey;
        private int             version;
        private int             expireTime;
        private String          secretKey      = null;
        private Str2StrProps_t  strExtendProps = new Str2StrProps_t();
        private Str2U32Props_t  u32ExtendProps = new Str2U32Props_t();
        private Str2U64Props_t  u64ExtendProps = new Str2U64Props_t();

        public YCTokenRequest(int appkey, int ver, int expiretime, String secretkey)
        {
            appKey     = appkey;
            version    = ver;
            expireTime = expiretime;
            secretKey  = secretkey;
        }

        public void setStr2StrProps(Str2StrProps_t prop)
        {
            if (prop != null)
                strExtendProps = prop;
        }

        public void setStr2U32Props(Str2U32Props_t prop)
        {
            if (prop != null)
                u32ExtendProps = prop;
        }

        public void setStr2U64Props(Str2U64Props_t prop)
        {
            if (prop != null)
                u64ExtendProps = prop;
        }

        public void addStr2StrProp(Str2StrProp prop)
        {
            strExtendProps.addProp(prop);
        }

        public void addStr2U32Prop(Str2U32Prop prop)
        {
            u32ExtendProps.addProp(prop);
        }

        public void addStr2U64Prop(Str2U64Prop prop)
        {
            u64ExtendProps.addProp(prop);;
        }

        @Override
        public String toString()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("appKey",     appKey);
                obj.put("version",    version);
                obj.put("expireTime", expireTime);
                obj.put("secretKey",  secretKey);
                obj.put("strExtendProps", strExtendProps.getObject());
                obj.put("u32ExtendProps", u32ExtendProps.getObject());
                obj.put("u64ExtendProps", u64ExtendProps.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "YCTokenRequest::toString: convert to string failed:" + e);
            }

            return obj.toString();
        }

        public final byte[] getBytes()
        {
            return toString().getBytes();
        }
    }

    static public class ProtoReqBase
    {
        JSONObject obj = new JSONObject();

        public int    reqType;
        public String context;

        ProtoReqBase()
        {
            reqType = 0;
            context = "";
        }

        public String toString()
        {
            try
            {
                obj.put("reqType", reqType);
                obj.put("context", context);

                return obj.toString();
            }
            catch (Exception e)
            {
                return "";
            }
        }

        public final byte[] getBytes()
        {
            return toString().getBytes();
        }
    }

    static public class LoginReq extends ProtoReqBase
    {
        final public static int ACCOUNT_LOGIN = 1;
        final public static int THIRD_LOGIN   = 2;

        private String account;
        private String passwd;
        private String ycToken;
        private int    type;

        public LoginReq(String acc, String pwd, String token, int typeName)
        {
            reqType = ReqType.PROTO_REQ_LOGIN;

            account = acc;
            passwd  = pwd;
            ycToken = token;
            type    = typeName;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("account", account);
                obj.put("passwd",  passwd);
                obj.put("ycToken", ycToken);
                obj.put("type",    type);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class LogoutReq extends ProtoReqBase
    {
        public LogoutReq()
        {
            reqType = ReqType.PROTO_REQ_LOGOUT;
        }

        @Override
        public String toString()
        {
            try
            {

            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class ProtoSessBase extends ProtoReqBase
    {
        public int topSid;

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid", topSid);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class SessionJoinProp
    {
        public int    propKey;
        public String propVal = null;

        SessionJoinProp(int key, String val)
        {
            propKey = key;
            propVal = val;
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("propKey", propKey);
                obj.put("propVal", propVal);
            }
            catch (Exception e)
            {
            }

            return obj;
        }
    }

    static public class SessJoinReq extends ProtoSessBase
    {
        public int    subSid;
        public int    asid;
        public String appToken;

        public JSONArray props = new JSONArray();

        public SessJoinReq()
        {
            reqType = ReqType.PROTO_REQ_JOIN;
        }

        public void addProp(SessionJoinProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = props.length();
                props.put(len, prop.getObject());
            }
            catch (Exception e)
            {

            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("subSid",   subSid);
                obj.put("asid",     asid);
                obj.put("appToken", appToken);
                obj.put("props",    props);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class SessLeaveReq extends ProtoSessBase
    {
        public String appToken;

        public SessLeaveReq()
        {
            reqType = ReqType.PROTO_SESS_REQ_LEAVE;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("appToken", appToken);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class SessUpdateChInfoReq extends ProtoSessBase
    {
        public int       subSid;
        public String    appToken;
        public JSONArray sinfo = new JSONArray();

        public SessUpdateChInfoReq()
        {
            reqType = ReqType.PROTO_SESS_UPDATE_CHANNEL_INFO;
        }

        public void addProp(StrProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = sinfo.length();
                sinfo.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessUpdateChInfoReq: addProp failed:" + e);
            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("subSid",    subSid);
                obj.put("appToken",  appToken);
                obj.put("sinfo",     sinfo);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessUpdateChInfoReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessUpdateUserInfoReq extends ProtoSessBase
    {
        public long      uid;
        public String    appToken;
        public JSONArray uinfo = new JSONArray();

        public SessUpdateUserInfoReq()
        {
            reqType = ReqType.PROTO_SESS_UPDATE_USER_INFO;
        }

        public void addProp(StrProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = uinfo.length();
                uinfo.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessUpdateUserInfoReq: addProp failed:" + e);
            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("uid",       uid);
                obj.put("appToken",  appToken);
                obj.put("uinfo",     uinfo);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessUpdateUserInfoReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessPullUserListReq extends ProtoSessBase
    {
        public SessPullUserListReq()
        {
            reqType = ReqType.PROTO_SESS_PULL_USER_LIST;
        }

        @Override
        public String toString()
        {
            try
            {

            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessPullUserListReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessQueryUserInfoReq extends ProtoSessBase
    {
        public String    callBack;
        public long[]    ulist = null;

        public SessQueryUserInfoReq()
        {
            reqType = ReqType.PROTO_SESS_QUERY_USER_INFO;
        }

        public SessQueryUserInfoReq(String callBk, long[] users)
        {
            reqType = ReqType.PROTO_SESS_QUERY_USER_INFO;

            callBack = callBk;
            ulist    =  users;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("callBack", callBack);

                JSONArray users = new JSONArray();
                for (int i = 0, len = ulist.length; i < len; ++i)
                {
                    Long uid   = Long.valueOf(ulist[i]);
                    int  index = users.length();

                    users.put(index, uid);
                }

                obj.put("ulist",  users);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessQueryUserInfoReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessFetchChInfoReq extends ProtoSessBase
    {
        public SessFetchChInfoReq()
        {
            reqType = ReqType.PROTO_SESS_FETCH_CH_INFO;
        }

        @Override
        public String toString()
        {
            try
            {

            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessFetchChInfoReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessJoinQueueReq extends ProtoSessBase
    {
        public String    appToken;
        public JSONArray userProps = new JSONArray();

        public SessJoinQueueReq()
        {
            reqType = ReqType.PROTO_SESS_JOIN_QUEUE;
        }

        public void addProp(StrProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = userProps.length();
                userProps.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessJoinQueueReq: addProp failed:" + e);
            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("appToken",  appToken);
                obj.put("userProps", userProps);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessJoinQueueReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessLeaveQueueReq extends ProtoSessBase
    {
        public String    appToken;

        public SessLeaveQueueReq()
        {
            reqType = ReqType.PROTO_SESS_LEAVE_QUEUE;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("appToken", appToken);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessLeaveQueueReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessQueryQueueReq extends ProtoSessBase
    {
        public int       subSid;
        public String    callBack;

        public SessQueryQueueReq()
        {
            reqType = ReqType.PROTO_SESS_QUERY_QUEUE;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("subSid",   subSid);
                obj.put("callBack", callBack);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessQueryQueueReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class SessTextChatReq extends ProtoSessBase
    {
        public String    chat;
        public JSONArray extProps = new JSONArray();

        public SessTextChatReq()
        {
            reqType = ReqType.PROTO_SESS_TEXT_CHAT;
        }

        public void addProp(StrProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = extProps.length();
                extProps.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessTextChatReq: addProp failed:" + e);
            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("chat",     chat);
                obj.put("extProps", extProps);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoReq::SessTextChatReq: Convert to String failed:" + e);
            }

            return super.toString();
        }
    }

    static public class PlatformConfigInfoReq extends ProtoReqBase
    {
        final static public int DBG_DISABLE = 0;
        final static public int DBG_SIGNAL  = 1;

        public int	     mode;
        public String    apIp;
        public String    apPort;

        public PlatformConfigInfoReq()
        {
            reqType = ReqType.PROTO_REQ_CONFIGINFO;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("mode",   mode);
                obj.put("apIp",   apIp);
                obj.put("apPort", apPort);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }

    static public class PlatformSystemInfoReq extends ProtoReqBase
    {
        public int	     platform;   //[required]android or ios
        public int	     netType;    //[required]wifi, gprs...
        public int       mnc;
        public int       mcc;
        public int       appKey;     //[required]
        public String    deviceInfo; //[required]imei for android, deviceId for iOS
        public String    phoneModel;
        public String    systemVer;
        public String    macaddr;    //[required]
        public String    appVer;     //[required]
        public String    logFilePath;
        public long      terminalType;   //[required]

        public PlatformSystemInfoReq()
        {
            reqType = ReqType.PROTO_REQ_SYSTEMINFO;
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("platform", platform);
                obj.put("netType", netType);
                obj.put("mnc",   mnc);
                obj.put("mcc",  mcc);
                obj.put("appKey", appKey);
                obj.put("deviceInfo", deviceInfo);
                obj.put("phoneModel",   phoneModel);
                obj.put("systemVer",  systemVer);
                obj.put("macaddr", macaddr);
                obj.put("appVer", appVer);
                obj.put("logFilePath",   logFilePath);
                obj.put("terminalType",  terminalType);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }
    }
}