package com.talk51.dasheng.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.talk51.dasheng.R;
import com.talk51.dasheng.activity.LogoutListener;
import com.talk51.dasheng.widget.CustomListView;

public class ProfileFragment extends Fragment
{
    private ListView mListView;
    private View mHeadView;
    private HeadeViewHolder mHeadViewHolder;
//    private MyChannelAdapter mMyChannelAdapter;
    private Button mLogoutBtn;
    private Button mLivecastBtn;
    private LogoutListener mListener;

    private class HeadeViewHolder
    {
        TextView mNickName;
        ImageView mGenderIV;
        TextView mFlowers;
        TextView mFans;
        ImageView mPortrait;
        ImageView mAttention;
        TextView mModifyPwdTip;
    }

    public ProfileFragment()
    {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Log.i("YCSdk", "ProfileFragment::onCreateView");
        
        
        mListView = (CustomListView) view.findViewById(R.id.lv_container);
//        mListView.setOnScrollListener(new OnScrollListener()
//        {
//            @Override
//            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3)
//            {
//
//            }
//            @Override
//            public void onScrollStateChanged(AbsListView arg0, int arg1)
//            {
//
//            }
//        });
//
//        if (mHeadView == null || mHeadViewHolder == null)
//        {
//            mHeadView = inflater.inflate(R.layout.layout_profile_head_view, null);
//            mHeadViewHolder = new HeadeViewHolder();
//            mHeadViewHolder.mGenderIV = (ImageView) mHeadView.findViewById(R.id.head_portrait_gender_view);
//            mHeadViewHolder.mFlowers = (TextView) mHeadView.findViewById(R.id.tv_flowers);
//            mHeadViewHolder.mFans = (TextView) mHeadView.findViewById(R.id.tv_fans);
//            mHeadViewHolder.mNickName = (TextView) mHeadView.findViewById(R.id.tv_nick);
//            mHeadViewHolder.mPortrait = (ImageView) mHeadView.findViewById(R.id.head_portrait_small_image);
//            mHeadViewHolder.mAttention = (ImageView) mHeadView.findViewById(R.id.iv_attention);
//            mHeadViewHolder.mModifyPwdTip = (TextView) mHeadView.findViewById(R.id.tv_modifypwd);
//            mHeadView.setTag(mHeadViewHolder);
//        }
//        else
//        {
//            mHeadViewHolder = (HeadeViewHolder) mHeadView.getTag();
//        }
//
//        updateHeader();
//        mHeadViewHolder.mPortrait.setClickable(true);
//        mHeadViewHolder.mPortrait.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//            }
//        });
//
//        mHeadViewHolder.mModifyPwdTip.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//            }
//        });
//
//        mListView.addHeaderView(mHeadView);

//        mMyChannelAdapter = new MyChannelAdapter(getActivity().getApplicationContext(), true);
//        updateGuild();
//        updateSList();
//        mListView.setAdapter(mMyChannelAdapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                if (position < parent.getAdapter().getCount())
//                {
//                    Object item = parent.getAdapter().getItem(position);
//                    if (item instanceof MyChannelAdapter.Info)
//                    {
//                        MyChannelAdapter.Info ci = (MyChannelAdapter.Info) item;
//
//                        Intent intent = new Intent(getActivity().getApplicationContext(), ChannelActivity.class);
//
//                        intent.putExtra("subsid", 0);
//                        intent.putExtra("sid", ci.asidOrSid());
//                        startActivity(intent);
//
//                    }
//                }
//            }
//        });

        mLogoutBtn = (Button)view.findViewById(R.id.btn_logout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                mListener.Logout();
            }
        });

//        mLivecastBtn = (Button)view.findViewById(R.id.btn_livecast);
//        mLivecastBtn.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//                //Intent intent = new Intent(getActivity().getApplicationContext(), CameraActivity.class);
//                //startActivity(intent);
//            }
//        });

        return view;
    }

    @Override
    public void onAttach(Activity activity)
    {
        // TODO Auto-generated method stub
        mListener = (LogoutListener)activity;
        super.onAttach(activity);
    }

    public void onDCChanged(int type)
    {
        switch(type)
        {
            //		case LoginEvent.LoginDCChanged.UINFO_CHANGED:
            //		{
            //			updateHeader();
            //			break;
            //		}
            //		case LoginEvent.LoginDCChanged.SLIST_CHANGED:
            //		{
            //			updateSList();
            //			break;
            //		}
            //		case LoginEvent.LoginDCChanged.GUILD_CHANGED:
            //		{
            //			updateGuild();
            //			break;
            //		}
        default:
            break;
        }
    }

    private void updateGuild()
    {
//        if(mMyChannelAdapter == null)
//            return;

        //		YYApplication mApp = (YYApplication)getActivity().getApplication();
        //		IDatabase db = mApp.getDb();
        //		ITable table = db.getTable(ProtoTable.TABLE_ID.E_TBL_GUILD.ordinal());
        //		List<IRow> rows = table.getAllRows();
        //		List<MyChannelAdapter.Info> infos = new ArrayList<MyChannelAdapter.Info>();
        //		for(IRow row: rows){
        //			MyChannelAdapter.Info info = new MyChannelAdapter.Info();
        //			info.title = row.getString(LOGINGUILD.strName.ordinal());
        //			info.asid = row.getInt32(LOGINGUILD.dwAsid.ordinal());
        //			infos.add(info);
        //		}
        //
        //		mMyChannelAdapter.refreshGuild(infos);
    }

    private void updateSList()
    {
//        if(mMyChannelAdapter == null)
//            return;

        //		YYApplication mApp = (YYApplication)getActivity().getApplication();
        //		IDatabase db = mApp.getDb();
        //		ITable table = db.getTable(ProtoTable.TABLE_ID.E_TBL_SLIST.ordinal());
        //		List<IRow> rows = table.getAllRows();
        //		List<MyChannelAdapter.Info> infos = new ArrayList<MyChannelAdapter.Info>();
        //		for(IRow row: rows){
        //			MyChannelAdapter.Info info = new MyChannelAdapter.Info();
        //			info.title = row.getString(LOGINSLIST.strName.ordinal());
        //			info.asid = row.getInt32(LOGINSLIST.dwAsid.ordinal());
        //			infos.add(info);
        //		}
        //
        //		mMyChannelAdapter.refreshFav(infos);
    }

    private void updateHeader()
    {
        //		YYApplication mApp = (YYApplication)getActivity().getApplication();
        //		IDatabase db = mApp.getDb();
        //    	ITable table = db.getTable(ProtoTable.TABLE_ID.E_TBL_LOGINUINFO.ordinal());
        //    	IRow row = table.getRow(ProtoTable.ONE_DIM_KEY);
        //    	String nick = row.getString(ProtoTable.LOGINUINFO.strNick.ordinal());
        //
        //		mHeadViewHolder.mNickName.setText(nick);
        //		mHeadViewHolder.mFlowers.setText(getString(R.string.flowers_num_x, 0));
    }
}
