package com.talk51.dasheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talk51.dasheng.R;
import com.talk51.dasheng.activity.ChannelEntryActivity;

public class MainFragment extends Fragment
{
    private View joinButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        joinButton = view.findViewById(R.id.tv_joinchannel);
        joinButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onJoin();
            }
        });

        return view;
    }

    public void onJoin()
    {
         Intent intent = new Intent(getActivity(), ChannelEntryActivity.class);
         startActivity(intent);
    }
}
