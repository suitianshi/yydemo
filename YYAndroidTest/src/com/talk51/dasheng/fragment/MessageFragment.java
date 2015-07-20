package com.talk51.dasheng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talk51.dasheng.R;

public class MessageFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        return view;
    }

    public void onLoginClicked()
    {
    }

    public void onRegisterClicked()
    {
    }
}
