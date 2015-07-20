package com.talk51.dasheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.talk51.dasheng.R;
import com.talk51.dasheng.YYApplication;

public class ChannelEntryActivity extends UIActivity implements CompoundButton.OnCheckedChangeListener
{
//    private MultiTabView mTabView;
//    private ViewPageSet mViewPageSet;
//    private Switch sw;

	EditText mTopSid;
	
    private YYApplication mApp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.channel_entry_activity);

        mApp = (YYApplication)getApplication();

        mTopSid = (EditText) findViewById(R.id.EdtTopSid);

        // onclick
        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String sid = mTopSid.getText().toString();
                int topSid = 0;
                
                try
                {
                    if (sid != null && sid.length() > 0)
                    	topSid = Integer.valueOf(sid).intValue();               	
                }
                catch (Exception e)
                {
                	Log.i("YCSdk", "ChannelEntryActivity::onCreate::onClick, error:" + e);
                }

                joinChannel(topSid, 0);
            }
        });      
        

//        mTabView = (MultiTabView) findViewById(R.id.tab);
//        mTabView.setTabOnClickListener(new TabSelChangeListener()
//        {
//            @Override
//            public void onTabSelChanged(int index)
//            {
//                mViewPageSet.setDisplayedChild(index);
//            }
//        });
//
//        mViewPageSet = (ViewPageSet) findViewById(R.id.view_page_set);
//
//        ChannelSearchView myChannelView1 = new ChannelSearchView(this);
//        MyChannelView myChannelView2 = new MyChannelView(this);
//
//        sw = (Switch)findViewById(R.id.hwSwitch);
//        sw.setOnCheckedChangeListener(this);
//
//        IDatabase db = mApp.getDb();
//        ITable table = db.getTable(ProtoTable.TABLE_ID.E_TBL_GUILD.ordinal());
//        List<IRow> rows = table.getAllRows();
//        List<MyChannelAdapter.Info> infos = new ArrayList<MyChannelAdapter.Info>();
//        for(IRow row: rows)
//        {
//            MyChannelAdapter.Info info = new MyChannelAdapter.Info();
//            info.title = row.getString(LOGINGUILD.strName.ordinal());
//            info.asid = row.getInt32(LOGINGUILD.dwAsid.ordinal());
//            infos.add(info);
//        }
//        myChannelView2.refreshGuild(infos);
//
//        table = db.getTable(ProtoTable.TABLE_ID.E_TBL_SLIST.ordinal());
//        rows = table.getAllRows();
//        infos = new ArrayList<MyChannelAdapter.Info>();
//        for(IRow row: rows)
//        {
//            MyChannelAdapter.Info info = new MyChannelAdapter.Info();
//            info.title = row.getString(LOGINSLIST.strName.ordinal());
//            info.asid = row.getInt32(LOGINSLIST.dwAsid.ordinal());
//            infos.add(info);
//        }
//        myChannelView2.refreshFav(infos);
//
//        ViewContainer[] views = new ViewContainer[2];
//        views[0] = myChannelView1;
//        views[1] = myChannelView2;
//
//        addView(views,
//                new String[] {getResources().getString(R.string.str_search)
//                              , getResources().getString(R.string.my_channel)
//                             });
//
//        mTabView.select(0);
    }

//    private void addView(ViewContainer[] views, String[] titles)
//    {
//        int viewSize = views.length;
//        int titleSize = titles.length;
//        int size = viewSize < titleSize ? viewSize : titleSize;
//        if (size != titleSize)
//        {
//            String t[] = new String[size];
//            for (int i = 0; i < size; i++)
//            {
//                t[i] = titles[i];
//            }
//            titles = t;
//        }
//        mTabView.setTitle(titles);
//        for (int i = 0; i < size; i++)
//        {
//            mViewPageSet.addView(views[i], i);
//        }
//    }


    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
    }


    @Override
    protected void onStop()
    {
        // TODO Auto-generated method stub
        super.onStop();
    }

    public void joinChannel(int sid, int subsid)
    {
        //mApp.join(sid, subsid);
        /*if(!mApp.isOnline())
        {
        	new AlertDialog.Builder(ChannelEntryActivity.this)
        	.setMessage(R.string.str_alert_login)
        	.setPositiveButton(R.string.str_confirm, null)
        	.show();
        	return;
        }*/

    	Log.i("YCSdk", "ChannelEntryActivity:joinChannel: sid=" + sid + ", subsid=" + subsid);
    	
        Intent intent = new Intent(ChannelEntryActivity.this, ChannelActivity.class);
        intent.putExtra("subsid", subsid);
        intent.putExtra("sid", sid);
        startActivity(intent);
        
 
        /*mProgressDlg = new AdvProgressDlg(this);
        mProgressDlg.setMessage("进频道...");
        mProgressDlg.setOnCancelListener(new OnCancelListener() {
        	@Override
        	public void onCancel(DialogInterface dialog) {
        		leave();
        	}
        });
        mProgressDlg.setTimeOut(10*1000, new AdvProgressDlg.OnTimeOutListener() {
        	@Override
        	public void onTimeOut(AdvProgressDlg dialog) {
        		leave();
        		new AlertDialog.Builder(ChannelEntryActivity.this)
        		.setMessage("进频道超时")
        		.show();
        	}
        });
        mProgressDlg.show();*/
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
//        useHardWareToDecode = isChecked;
    }
}
