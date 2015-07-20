package com.talk51.dasheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.talk51.dasheng.R;
import com.talk51.dasheng.YYApplication;
import com.talk51.dasheng.fragment.MainFragment;
import com.talk51.dasheng.fragment.MessageFragment;
import com.talk51.dasheng.fragment.PreLoginFragment;
import com.talk51.dasheng.fragment.ProfileFragment;
import com.talk51.dasheng.protocol.ProtoReq.LoginoutReq;
import com.talk51.dasheng.widget.SlidingPaneLayout;
import com.yyproto.base.YYHandler;
import com.yyproto.outlet.SDKParam;

public class MainActivity extends UIFragmentActivity implements LogoutListener
{
    private final String  TAG   = MainActivity.class.getName();
    private long mTouchTime     = 0;
    private YYApplication mApp  = null;

    public  static final long KWaitTime = 3 * 1000;
    private static int currentTab       = R.id.rb_profile;

    private SlidingPaneLayout mSlidingPaneLayout;
    private RadioGroup        mTabRaidoGroup;
    private RadioButton       mRadioProfile;
    private MainFragment      mMainFragment;
    private MessageFragment   mMessageFragment;
    private PreLoginFragment  mPreloginFragment;
    private ProfileFragment   mProfileFragment;
public static String tag = "xuawang";
    private YYHandler mHandler = new YYHandler()
    {
        @MessageHandler(message = SDKParam.Message.messageId)
        public void onEvent(byte[] data)
        {
            Log.e(tag, "onEvent:"+new String(data));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApp = (YYApplication)getApplication();
        // ui
        mSlidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout_container);
        mSlidingPaneLayout.setSliderFadeColor(0);
        mSlidingPaneLayout.setCoveredFadeColor(0);
        mSlidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener()
        {
            @Override
            public void onPanelSlide(View panel, float slideOffset)
            {
                // To change body of implemented methods use File |
                // Settings | File Templates.
            }

            @Override
            public void onPanelOpened(View panel)
            {
            }

            @Override
            public void onPanelClosed(View panel)
            {
            }
        });

        mMainFragment     = new MainFragment();
        mMessageFragment  = new MessageFragment();
        mPreloginFragment = new PreLoginFragment();
        mProfileFragment  = new ProfileFragment();

        mTabRaidoGroup = (RadioGroup) findViewById(R.id.rg_tab);

        mTabRaidoGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id)
            {
            	/*********************************************************/
            	Log.i(TAG, "MainActivity::taggleTab: onCheckedChanged, tabId=" + id);
            	/*********************************************************/

                taggleTab(id);
            }
        });

    	/*********************************************************/
    	Log.i(TAG, "MainActivity::taggleTab: onCreate");
    	/*********************************************************/


        mRadioProfile = (RadioButton)findViewById(R.id.rb_profile);
        mRadioProfile.setChecked(true);
        taggleTab(R.id.rb_profile);
    }

    private void taggleTab(int tabId)
    {
    	/*********************************************************/
    	//Log.i(TAG, "MainActivity::taggleTab: tabId=" + tabId);
    	/*********************************************************/

        Fragment toShow = null;
        currentTab = tabId;
        if (tabId == R.id.rb_main)
        {
            mSlidingPaneLayout.setTouchMode(SlidingPaneLayout.TOUCHMODE_FULLSCREEN);
            toShow = mMainFragment;
        }
        else if (tabId == R.id.rb_message)
        {
            mSlidingPaneLayout.setTouchMode(SlidingPaneLayout.TOUCHMODE_FULLSCREEN);
            toShow = mMessageFragment;
        }
        else if (tabId == R.id.rb_profile)
        {
        	/*********************************************************/
        	Log.i(TAG, "MainActivity::taggleTab: tabId=" + tabId + ", isOnline=" + mApp.isOnline());
        	/*********************************************************/


            mSlidingPaneLayout.setTouchMode(SlidingPaneLayout.TOUCHMODE_NONE);
            if(mApp.isOnline())
            {
                toShow = mProfileFragment;
            }
            else
            {
                toShow = mPreloginFragment;
            }
        }

        if (toShow != null && !toShow.isVisible())
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (toShow != mMainFragment && mMainFragment.isVisible())
            {
                ft.hide(mMainFragment);
            }

            if (toShow != mMessageFragment && mMessageFragment.isVisible())
            {
                ft.hide(mMessageFragment);
            }

            if (toShow != mProfileFragment && mProfileFragment.isVisible())
            {
                ft.hide(mProfileFragment);
            }

            if (toShow != mPreloginFragment && mPreloginFragment.isVisible())
            {
                ft.hide(mPreloginFragment);
            }

            if (!toShow.isAdded())
            {
                ft.add(R.id.container_fragment_content, toShow);
            }
            else if (toShow.isHidden())
            {
                ft.show(toShow);
            }

            ft.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
        }
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

    @Override
    public void onBackPressed()
    {
        long now = System.currentTimeMillis();
        if ((now - mTouchTime) >= KWaitTime)
        {
            Toast.makeText(this, getString(R.string.press_again_to_exit),
                           Toast.LENGTH_SHORT).show();
            mTouchTime = now;
        }
        else
        {
            super.onBackPressed();
            mApp.deInit();
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);



    	/*********************************************************/
    	Log.i(TAG, "MainActivity::taggleTab: onNewIntent, tabId=" + currentTab);
    	/*********************************************************/







        taggleTab(currentTab);
    }

    @Override
    public void Logout()
    {
        // TODO Auto-generated method stub
        Log.i(TAG, "kelvin MainActivity Logout");

    	LoginoutReq req = new LoginoutReq();
    	mApp.getProtoMgr().sendRequest(req.getBytes());

    	mApp.setUid(0);
        mApp.setOnline(false);




    	/*********************************************************/
    	Log.i(TAG, "MainActivity::taggleTab: Logout, tabId=" + currentTab);
    	/*********************************************************/



        taggleTab(R.id.rb_profile);
    }
}
