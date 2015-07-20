package com.talk51.dasheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talk51.dasheng.R;
import com.talk51.dasheng.activity.LoginActivity;

public class PreLoginFragment extends Fragment
{
    private View loginButton;
    private View registerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_prelogin, container, false);

        loginButton = view.findViewById(R.id.tv_login);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onLoginClicked();
            }
        });

        registerButton = view.findViewById(R.id.tv_register);
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onRegisterClicked();
            }
        });

        return view;
    }

    public void onLoginClicked()
    {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    public void onRegisterClicked()
    {
    }
}
