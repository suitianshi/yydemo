package com.talk51.dasheng.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
//import com.duowan.mobile.utils.YLog;

public class CustomListView extends ListView {


    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        ListAdapter adapterOld = getAdapter();
        if (adapterOld != null) {
            if (adapter == adapterOld) {
//                YLog.debug(this, "same adapter skip setAdapter");
                return;
            }
            if (adapterOld instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapterOld;
                if (headerViewListAdapter.getWrappedAdapter() == adapter) {
//                    YLog.debug(this, "same adapter in headViewAdapter skip setAdapter");
                    return;
                }
            }
        }
        super.setAdapter(adapter);
    }
}
