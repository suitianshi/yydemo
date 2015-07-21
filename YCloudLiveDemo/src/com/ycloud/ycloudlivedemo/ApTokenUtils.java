package com.ycloud.ycloudlivedemo;


import android.util.Log;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class ApTokenUtils {
	public static String TAG = "ApTokenUtils";

	public static String GenToken(String appid, String tokentype, int auth,int uid, int sid, int ttl, String context) {
		//String.valueOf(mAppId), "LIVE", 0x03, mUid, mSid, 98765412, ""
	    Log.d(TAG, "GenToken appid: " + appid + " tokenType: " + tokentype + " auth:" + auth);

		try {

			long currentTime = (System.currentTimeMillis() / 1000);
			long expiredTime = currentTime + 60 * 60 * 24 * 30;
			HttpClient client = new DefaultHttpClient();
			String url =  "http://live.huanjuyun.com/token/"+ appid;
			Log.d(TAG, "GenToken url: " + url);
			HttpPost request = new HttpPost(url);
			request.addHeader("Content-Type", "application/json");

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("appid", appid);
			jsonObject.put("auth", String.valueOf(auth));
			jsonObject.put("context", context);
			jsonObject.put("tokenExpire", String.valueOf(expiredTime));
			jsonObject.put("uid", String.valueOf(uid));

			if (tokentype == "")
				jsonObject.put("tokenType", "VOD");
			else
				jsonObject.put("tokenType", tokentype);

			jsonObject.put("sid", String.valueOf(sid));
			jsonObject.put("Audio_recv_expire", String.valueOf(expiredTime));
			jsonObject.put("Audio_send_expire", String.valueOf(expiredTime));
			jsonObject.put("Video_recv_expire", String.valueOf(expiredTime));
			jsonObject.put("Video_send_expire", String.valueOf(expiredTime));
			jsonObject.put("Text_recv_expire", String.valueOf(expiredTime));
			jsonObject.put("Text_send_expire", String.valueOf(expiredTime));


			request.setEntity(new StringEntity(jsonObject.toString()));
			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();

			if (code == 200) {
				JSONObject resultObject = new JSONObject(EntityUtils.toString(response.getEntity()));
				String token = (String) resultObject.get("token");
				return token;
			}
			else {
				Log.e(TAG, "GenToken failed code: " + code);
			}
		} catch (ClientProtocolException e) {
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, e.getStackTrace().toString());
		}


		return "";
	}
}
