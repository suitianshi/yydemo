package com.talk51.dasheng.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.duowan.mobile.YYApp;
//import com.duowan.mobile.utils.YLog;
//import com.duowan.mobile.utils.FP;
import com.talk51.dasheng.R;

public class MultiTabView extends LinearLayout
{
    public static interface TabSelChangeListener
    {
        void onTabSelChanged(int index);
    }

    public MultiTabView(Context context)
    {
        super(context);
        init();
    }

    public MultiTabView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public class Tab
    {
        private View mTabView = null;
        private int mUid = 0;

        public Tab(String title, int uid)
        {
            mTabView = MultiTabView.inflate(getContext(), R.layout.item_tab_view, null);

            ((TextView) mTabView.findViewById(R.id.text)).setText(title);
            mUid = uid;
        }

        public View view()
        {
            return mTabView;
        }

        public int uid()
        {
            return mUid;
        }

        public void setUid(int uid)
        {
            mUid = uid;
        }

        public void setTitle(String title)
        {
            ((TextView) mTabView.findViewById(R.id.text)).setText(title);
        }

        public String title()
        {
            return ((TextView) mTabView.findViewById(R.id.text)).getText().toString();
        }

        public void select(boolean isSelect)
        {
            if (isSelect)
            {
                mTabView.findViewById(R.id.focus).setVisibility(View.VISIBLE);
            }
            else
            {
                mTabView.findViewById(R.id.focus).setVisibility(View.GONE);
            }
        }

        public void setOnClickListener(View.OnClickListener l)
        {
            mTabView.findViewById(R.id.text).setOnClickListener(l);
        }

        public boolean subViewOnClick(View v)
        {
            return mTabView.findViewById(R.id.text) == v;
        }
    }

    private Tab mTabs[] = null;
    private Tab mCurrentTab = null;
    private TabSelChangeListener mTabSelChangeListener;

    private void init()
    {
    }

    public void setTabOnClickListener(TabSelChangeListener tabOnClickListener)
    {
        this.mTabSelChangeListener = tabOnClickListener;
    }

    public void setTitle(int[] strResIds)
    {
        if (strResIds != null)
        {
            String[] titles = new String[strResIds.length];
//            Context context = YYApp.gContext;
//            for (int i = 0, n = titles.length; i < n; i++)
//            {
//                titles[i] = context.getString(strResIds[i]);
//            }
//            setTitle(titles);
        }
    }

    public void setTitle(String[] titles)
    {
        removeAllViews();
        if (mTabs == null)
        {
            mTabs = new Tab[titles.length];
        }

        if (mTabs.length != titles.length)//kuainan
        {
            mTabs = new Tab[titles.length];
        }

        mCurrentTab = null;

        for (int i = 0; i < mTabs.length; i++)
        {
            if (mTabs[i] == null)
            {
                mTabs[i] = new Tab(titles[i], i);
                mTabs[i].setOnClickListener(onClickListener);
            }
            else
            {
                mTabs[i].setTitle(titles[i]);
                mTabs[i].setUid(i);
            }
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, (int) getContext().getResources()
                    .getDimension(R.dimen.tab_height));
            lp.weight = 1.0f;
            MultiTabView.this.addView(mTabs[i].view(), lp);
        }
        // select first
    }

    public void select(int index)
    {
        select(index, true);
    }

    public void select(int index, boolean notify)
    {
        if (mTabs != null)
        {
            Tab tmp = null;
            for (int i = 0; i < mTabs.length; i++)
            {
                tmp = mTabs[i];
                if (i == index)
                {
                    tmp.select(true);
                }
                else
                {
                    tmp.select(false);
                }
            }

            mCurrentTab = mTabs[index];
            if (notify && mTabSelChangeListener != null)
            {
                mTabSelChangeListener.onTabSelChanged(mCurrentTab.uid());
            }
        }
    }

    /**
     * Get index of currently selected tab.
     *
     * @return -1 for no tab is selected.
     */
    public int getSelectedIndex()
    {
        if (mTabs != null)
        {
            for (int i = 0, n = mTabs.length; i < n; ++i)
            {
                if (mCurrentTab == mTabs[i])
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public void updateTitle(int index, String title)
    {
        if (index < 0 || index >= mTabs.length)
        {
            return;
        }
        mTabs[index].setTitle(title);
    }

    public void updateTitle(int index, int resId, Object... formatArgs)
    {
//        String string = YYApp.gContext.getString(resId, formatArgs);
//        updateTitle(index, string);
    }

    public String titleAt(int index)
    {
//        return (0 <= index && index < FP.length(mTabs)) ? mTabs[index].title() : "";
    	return "";
    }

    public int getTabCount()
    {
        return mTabs.length;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //YLog.debug(this, "tab on click");
            if (mTabs == null || v == mCurrentTab.view())
            {
                return;
            }
//            YLog.debug(this, "find tab");
            for (int i = mTabs.length; --i >= 0;)
            {
                if (mTabs[i].subViewOnClick(v))
                {
//                    YLog.debug(this, "t = %d found", i);
                    select(i);
                    break;
                }
            }
        }

    };

    public Tab[] getTabs()
    {
        return mTabs;
    }

    /*
     * private void select(TabType tab) { TextView tView = findTab(tab); for (TextView e : allTabs) { boolean
     * selected = e == tView; // TODO int bgClr = selected ? 0xff777777 : 0xffffffff;
     * e.setBackgroundColor(bgClr);
     *
     * int fgClr = selected ? TEXT_COLOR_WHITE : TEXT_COLOR_GRAY; e.setTextColor(fgClr); } if
     * (tabSelChangeListener != null) { tabSelChangeListener.onTabSelChanged(tab); } }
     */
}
