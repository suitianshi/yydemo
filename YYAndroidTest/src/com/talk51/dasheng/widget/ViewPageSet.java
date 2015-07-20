package com.talk51.dasheng.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewAnimator;

public class ViewPageSet extends ViewAnimator
{

    List<ViewContainer> mPageSet = new ArrayList<ViewContainer>();
    OnTouchListener mListener = null;

    public ViewPageSet(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ViewPageSet(Context context)
    {
        super(context);
    }

    public void onResume()
    {
        ViewContainer v = getDisplayView();
        if (v != null)
        {
            v.onResume();
        }
    }

    public void onPause()
    {
        ViewContainer v = getDisplayView();

        if (v != null)
        {
            v.onPause();
        }
    }

    public void setDisplayedChild(int whichChild)
    {
        if (whichChild < 0 || whichChild >= mPageSet.size())
        {
            return;
        }
        int old = getDisplayedChild();

        if (old == whichChild)
        {
            super.setDisplayedChild(whichChild);
            return;
        }

        mPageSet.get(old).onPause();
        super.setDisplayedChild(whichChild);
        mPageSet.get(whichChild).onResume();
    }

    public void setOnDispatchTouchListener(OnTouchListener l)
    {
        mListener = l;
    }

    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        if (mListener != null && mListener.onTouch(this, ev))
        {
            return true;
        }

        return super.dispatchTouchEvent(ev);
    }

    boolean mIndirectCall = false;

    @Deprecated
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params)
    {
        if (!mIndirectCall)
        {
            throw new RuntimeException();
        }

        super.addView(child, index, params);
    }

    public void addView(ViewContainer child, int index)
    {
        mIndirectCall = true;
        super.addView(child.getView(), index);
        mIndirectCall = false;

        if (index >= 0)
        {
            if (index < mPageSet.size())
            {
                mPageSet.add(index, child);
            }
            else
            {
                for (int i = 0; i < index - mPageSet.size(); i++)
                {
                    mPageSet.add(null);
                }
                mPageSet.add(child);
            }
        }
    }

    public void addView(ViewContainer child)
    {
        mIndirectCall = true;
        super.addView(child.getView());
        mIndirectCall = false;

        addToSet(child);
    }

    public void addView(ViewContainer child, android.view.ViewGroup.LayoutParams params)
    {

        mIndirectCall = true;
        super.addView(child.getView(), params);
        mIndirectCall = false;

        addToSet(child);
    }

    private void addToSet(ViewContainer child)
    {
        child.mRoot = this;
        mPageSet.add(child);

        if (getChildCount() == 1)
        {
            super.setDisplayedChild(0);
            mPageSet.get(getDisplayedChild()).onResume();
        }
    }

    public void removeView(ViewContainer child)
    {
        if (child != null)
        {
            removeView(child.getView());
        }

        mPageSet.remove(child);
    }

    public void removeView(int index)
    {
        ViewContainer child = mPageSet.get(index);
        removeView(child.getView());

        mPageSet.remove(index);
    }

    public final List<ViewContainer> getChildList()
    {
        return mPageSet;
    }

    public ViewContainer getChild(int index)
    {
        if (index < 0 || index >= mPageSet.size())
        {
            return null;
        }

        return mPageSet.get(index);
    }

    public ViewContainer getDisplayView()
    {
        return getChild(getDisplayedChild());
    }

    protected Parcelable onSaveInstanceState()
    {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        ss.mSelIndex = getDisplayedChild();

        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state)
    {
        if (!(state instanceof SavedState))
        {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (getChildCount() != 0)
        {
            setDisplayedChild(ss.mSelIndex);
        }
    }

    public static class SavedState extends BaseSavedState
    {

        public SavedState(Parcelable in)
        {
            super(in);
        }

        int mSelIndex;

        private SavedState(Parcel in)
        {
            super(in);
            mSelIndex = in.readInt();
        }

        public void writeToParcel(Parcel dest, int flags)
        {
            super.writeToParcel(dest, flags);
            dest.writeInt(mSelIndex);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>()
        {
            public SavedState createFromParcel(Parcel in)
            {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size)
            {
                return new SavedState[size];
            }
        };
    }
}
