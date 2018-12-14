package com.example.nex4jmq.paytackapp.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nex4jmq.paytackapp.R;

public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView tvSignUp;
    private LinearLayout socialLogin;
    private LinearLayout login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
        setListener();
    }

    private void initView() {
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        socialLogin = (LinearLayout) findViewById(R.id.llSocialLogin);
        login = (LinearLayout) findViewById(R.id.llLogin);
    }

    private void setListener() {
        tvSignUp.setOnClickListener(this);
        socialLogin.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSignUp:
                Intent signUp = new Intent(this, SignUpActivity.class);
                startActivity(signUp);
                break;
            case R.id.llSocialLogin:
                break;
            case R.id.llLogin:
                break;
        }
    }
}
