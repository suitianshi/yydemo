package com.talk51.dasheng.protocol;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class ProtoEvent
{
    static public class EventType
    {
        final static public int PROTO_EVENT_LOGIN_RES                 = 1;
        final static public int PROTO_EVENT_LOGOUT                    = 2;


        final static public int PROTO_EVENT_SESS_OPER_RES             = 501;
        final static public int PROTO_EVENT_SESS_JOIN_RES             = 502;
        final static public int PROTO_EVENT_SESS_QUERY_USER_INFO_RES  = 509;
        final static public int PROTO_EVENT_SESS_TEXT_CHAT_BROAD_RES  = 512;

        // session queue
        final static public int PROTO_EVENT_SESS_JOIN_QUEUE_RES       = 600;
        final static public int PROTO_EVENT_SESS_LEAVE_QUEUE_RES      = 601;
        final static public int PROTO_EVENT_SESS_QUERY_QUEUE_RES      = 602;
    }

    static public class IntProp
    {
        public int    propKey;
        public int    propVal;

        public IntProp()
        {
            propKey = 0;
            propVal = 0;
        }

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

        public void unmarshal(JSONObject optObj)
        {
            try
            {
                propKey = optObj.optInt("propKey");
                propVal = optObj.optInt("propVal");
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "IntProp::unmarshal: error:" + e);
            }
        }
    }

    static public class StrProp
    {
        public int    propKey;
        public String propVal = null;

        StrProp()
        {
            propKey = 0;
            propVal = null;
        }

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

        public void unmarshal(JSONObject optObj)
        {
            try
            {
                propKey = optObj.optInt("propKey");
                propVal = optObj.optString("propVal");
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "StrProp::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEventBase
    {
        JSONObject obj = new JSONObject();

        public int    eventType;
        public String context;

        public ProtoEventBase()
        {
            eventType = 0;
            context   = "";
        }

        public String toString()
        {
            try
            {
                obj.put("eventType", eventType);
                obj.put("context",   context);

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

        public void unmarshal(byte[] data)
        {
            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                eventType = jobj.optInt("eventType");
                context   = jobj.optString("context");
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEventBase::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtLoginRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_LOGIN_RES;

        final public static int LOGIN_SUCESS            = 200;
        final public static int TIME_OUT                = 1100002;
        final public static int PICCODE_FAIL            = 1100003;
        final public static int AUTHE_FAIL              = 1100004;
        final public static int AUTHRES_LOGIN_DATA_ERR  = 1100005;
        final public static int RECEIVE_UDB_RES         = 1100006;
        final public static int RECEIVE_SMSCODE_FAIL    = 1100007;
        final public static int UDB_REJECT_ANONYM_LOGIN = 1100008;

        public boolean isAnonymous;
        public int     res;
        public long    uid;
        public long    udbRes;
        public byte[]  udbDescription = null;

        public ProtoEvtLoginRes()
        {

        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("isAnonymous", isAnonymous);
                obj.put("res",  res);
                obj.put("uid", uid);
                obj.put("udbRes",    udbRes);
                obj.put("udbDescription", new String(udbDescription));
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                isAnonymous    = jobj.optBoolean("isAnonymous");
                res            = jobj.optInt("res");
                uid            = jobj.optLong("uid");
                udbRes         = jobj.optLong("udbRes");
                udbDescription = jobj.optString("udbDescription").getBytes();
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEventBase::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessOperRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_OPER_RES;

        public long      uid;
        public int       innerUri;
        public int       topSid;
        public int       subSid;
        public int       appKey;
        public int       resCode;
        public JSONArray props = new JSONArray();

        public ProtoEvtSessOperRes()
        {

        }

        public void addProp(StrProp prop)
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
                obj.put("uid",      uid);
                obj.put("innerUri", innerUri);
                obj.put("topSid",   topSid);
                obj.put("subSid",   subSid);
                obj.put("appKey",   appKey);
                obj.put("resCode",  resCode);
                obj.put("props",    props);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                uid      = jobj.optLong("uid");
                innerUri = jobj.optInt("innerUri");
                topSid   = jobj.optInt("topSid");
                subSid   = jobj.optInt("subSid");
                appKey   = jobj.optInt("appKey");
                resCode  = jobj.optInt("resCode");

                JSONArray props = jobj.getJSONArray("props");

                int len = props.length();
                for (int i = 0; i < len; ++i)
                {
                    JSONObject optObj = (JSONObject)props.get(i);

                    StrProp prop = new StrProp();
                    prop.unmarshal(optObj);

                    addProp(prop);
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessOperRes::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessJoinRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_JOIN_RES;

        final static public int OPERATOR_SUCCESS = 0;

        public boolean isSuccess;
        public int     errId;
        public int     topSid;
        public int     aSid;
        public int     subSid;
        public int     appKey;

        public ProtoEvtSessJoinRes()
        {

        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("isSuccess", isSuccess);
                obj.put("errId",     errId);
                obj.put("topSid",    topSid);
                obj.put("aSid",      aSid);
                obj.put("subSid",    subSid);
                obj.put("appKey",    appKey);
            }
            catch (Exception e)
            {

            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                isSuccess = jobj.optBoolean("isSuccess");
                errId     = jobj.optInt("errId");
                topSid    = jobj.optInt("topSid");
                aSid      = jobj.optInt("aSid");
                subSid    = jobj.optInt("subSid");
                appKey    = jobj.optInt("appKey");
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessJoinRes::unmarshal: error:" + e);
            }
        }
    }

    static public class OnlineUser
    {
        public long      uid;
        public JSONArray intProp = new JSONArray();
        public JSONArray strProp = new JSONArray();

        public OnlineUser()
        {
            uid = 0;
        }

        public OnlineUser(int id, JSONArray iProp, JSONArray sProp)
        {
            uid     = id;
            intProp = iProp;
            strProp = sProp;
        }

        public void addProp(IntProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = intProp.length();
                intProp.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "OnlineUser:addProp: Add int prop error:" + e);
            }
        }

        public void addProp(StrProp prop)
        {
            if (prop == null)
                return;

            try
            {
                int len = strProp.length();
                strProp.put(len, prop.getObject());
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "OnlineUser:addProp: Add str prop error:" + e);
            }
        }

        public JSONObject getObject()
        {
            JSONObject obj = new JSONObject();

            try
            {
                obj.put("uid",     uid);
                obj.put("intProp", intProp);
                obj.put("strProp", strProp);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "OnlineUser::getObject: error:" + e);
            }

            return obj;
        }

        public void unmarshal(JSONObject optObj)
        {
            try
            {
                uid = optObj.optLong("uid");

                JSONArray iProps = optObj.getJSONArray("intProp");
                int       len    = iProps.length();
                for (int i = 0; i < len; ++i)
                {
                    JSONObject Obj = (JSONObject)iProps.get(i);
                    IntProp   prop = new IntProp();
                    prop.unmarshal(Obj);

                    addProp(prop);
                }

                JSONArray sProps = optObj.getJSONArray("strProp");
                len    = sProps.length();
                for (int i = 0; i < len; ++i)
                {
                    JSONObject Obj = (JSONObject)sProps.get(i);
                    StrProp   prop = new StrProp();
                    prop.unmarshal(Obj);

                    addProp(prop);
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "OnlineUser::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessQueryUserInfoRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_QUERY_USER_INFO_RES;

        public int           topSid;
        public int           appKey;
        public String        callBack;
        public long[]        leaves = null;
        public OnlineUser[]  users  = null;

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid",   topSid);
                obj.put("appKey",   appKey);
                obj.put("callBack", callBack);

                JSONArray left = new JSONArray();
                for (int i = 0, len = leaves.length; i < len; ++i)
                {
                    Long uid   = Long.valueOf(leaves[i]);
                    int  index = left.length();

                    left.put(index, uid);
                }

                obj.put("leaves",  left);

                JSONArray userList = new JSONArray();
                for (int i = 0, len = users.length; i < len; ++i)
                {
                    int index = userList.length();

                    if (users[i] != null)
                        userList.put(index, users[i].getObject());
                }

                obj.put("users", userList);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessJoinQueueRes::toString: error:" + e);
            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                topSid   = jobj.optInt("topSid");
                appKey   = jobj.optInt("appKey");
                callBack = jobj.optString("callBack");

                JSONArray left = jobj.getJSONArray("leaves");
                leaves = new long[left.length()];
                for (int i = 0, len = left.length(); i < len; ++i)
                {
                    leaves[i] = left.optLong(i);
                }

                JSONArray userList = jobj.getJSONArray("users");
                users = new OnlineUser[userList.length()];
                for (int i = 0, len = userList.length(); i < len; ++i)
                {
                    JSONObject userObj = (JSONObject)userList.get(i);
                    OnlineUser user    = new OnlineUser();
                    user.unmarshal(userObj);

                    users[i] = user;
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessQueryUserInfoRes::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessTextChatBroadRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_TEXT_CHAT_BROAD_RES;

        public int       topSid;
        public int       subSid;
        public long      from;
        public String    chat     = null;
        public JSONArray extProps = new JSONArray();

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
                Log.i("YCSdk", "ProtoEvtSessTextChatBroadRes::addProp: error:" + e);
            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid",   topSid);
                obj.put("subSid",   subSid);
                obj.put("from",     from);
                obj.put("chat",     chat);
                obj.put("extProps", extProps);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessTextChatBroadRes::toString: error:" + e);
            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                topSid = jobj.optInt("topSid");
                subSid = jobj.optInt("subSid");
                from   = jobj.optLong("from");
                chat   = jobj.optString("chat");

                JSONArray props = jobj.getJSONArray("extProps");

                int len = props.length();
                for (int i = 0; i < len; ++i)
                {
                    JSONObject optObj = (JSONObject)props.get(i);

                    StrProp prop = new StrProp();
                    prop.unmarshal(optObj);

                    addProp(prop);
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessTextChatBroadRes::unmarshal: error:" + e);
            }
        }
    }

    // session queue
    static public class ProtoEvtSessJoinQueueRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_JOIN_QUEUE_RES;

        public int       topSid;
        public int       subSid;
        public int       appKey;
        public long      uid;
        public JSONArray userProps = new JSONArray();

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

            }
        }

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid",    topSid);
                obj.put("subSid",    subSid);
                obj.put("appKey",    appKey);
                obj.put("uid",       uid);
                obj.put("userProps", userProps);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessJoinQueueRes::toString: error:" + e);
            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                topSid   = jobj.optInt("topSid");
                subSid   = jobj.optInt("subSid");
                appKey   = jobj.optInt("appKey");
                uid      = jobj.optLong("uid");

                JSONArray props = jobj.getJSONArray("userProps");

                int len = props.length();
                for (int i = 0; i < len; ++i)
                {
                    JSONObject optObj = (JSONObject)props.get(i);

                    StrProp prop = new StrProp();
                    prop.unmarshal(optObj);

                    addProp(prop);
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessJoinQueueRes::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessLeaveQueueRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_LEAVE_QUEUE_RES;

        public int     topSid;
        public int     subSid;
        public int     appKey;
        public long    uid;

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid", topSid);
                obj.put("subSid", subSid);
                obj.put("appKey", appKey);
                obj.put("uid",    uid);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessLeaveQueueRes::toString: error:" + e);
            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                topSid   = jobj.optInt("topSid");
                subSid   = jobj.optInt("subSid");
                appKey   = jobj.optInt("appKey");
                uid      = jobj.optLong("uid");
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessLeaveQueueRes::unmarshal: error:" + e);
            }
        }
    }

    static public class ProtoEvtSessQueryQueueRes extends ProtoEventBase
    {
        // eventType = PROTO_EVENT_SESS_QUERY_QUEUE_RES;

        public int       topSid;
        public int       subSid;
        public int       appKey;
        public String    callBack = null;
        public long[]    userList = null;

        @Override
        public String toString()
        {
            try
            {
                obj.put("topSid",   topSid);
                obj.put("subSid",   subSid);
                obj.put("appKey",   appKey);
                obj.put("callBack", callBack);

                JSONArray users = new JSONArray();
                for (int i = 0, len = userList.length; i < len; ++i)
                {
                    Long uid   = Long.valueOf(userList[i]);
                    int  index = users.length();

                    users.put(index, uid);
                }

                obj.put("userList",  users);
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessJoinQueueRes::toString: error:" + e);
            }

            return super.toString();
        }

        public void unmarshal(byte[] data)
        {
            super.unmarshal(data);

            try
            {
                String     buf  = new String(data);
                JSONObject jobj = new JSONObject(buf);

                topSid   = jobj.optInt("topSid");
                subSid   = jobj.optInt("subSid");
                appKey   = jobj.optInt("appKey");
                callBack = jobj.optString("callBack");

                if (jobj.has("userList"))
                {
                    JSONArray props = jobj.getJSONArray("userList");
                    userList = new long[props.length()];
                    for (int i = 0, len = props.length(); i < len; ++i)
                    {
                        userList[i] = props.optLong(i);
                    }
                }
            }
            catch (Exception e)
            {
                Log.i("YCSdk", "ProtoEvtSessQueryQueueRes::unmarshal: error:" + e);
            }
        }
    }
}