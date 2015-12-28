package cn.com.duiba.credits;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class GuideActivity extends Activity  {

    private WebView mWebView;
    private WebSettings mSetting;

    private String mContentUrl = "";
    @Override
    public void onCreate(Bundle b) {
super.onCreate(b);
/*View view = initLayout(R.layout.activity_ac_guide);
setContentView(view);
mWebView = (WebView) findViewById(R.id.web_ac_guide);*/

mSetting = mWebView.getSettings();
mSetting.setJavaScriptEnabled(true);
mSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

// 设置可以支持缩放
mSetting.setSupportZoom(true);
// 设置出现缩放工具
mSetting.setBuiltInZoomControls(true);
//扩大比例的缩放
mSetting.setUseWideViewPort(true);
//自适应屏幕
mSetting.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
mSetting.setLoadWithOverviewMode(true);


mWebView.loadUrl(mContentUrl);
mWebView.setWebViewClient(new MyWebViewClient());
    }










    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

        }
    }

    public static final String INVALID_TITLE_PLACEHOLDER = "     ";

}
