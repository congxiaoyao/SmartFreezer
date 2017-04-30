package com.jaycejia.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.jaycejia.MainActivity;
import com.jaycejia.R;
import com.jaycejia.databinding.ActivityLoginBinding;
import com.jaycejia.utils.ToastUtil;

/**
 * Created by NiYang on 2017/4/30.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_login, null, false);
        setContentView(this.binding.getRoot());

        initView();
    }

    private void initView() {
        this.binding.ivBack.setOnClickListener(this);
        this.binding.btnLogin.setOnClickListener(this);
    }

    //TODO login
    private void login() {
        ToastUtil.showToast("功能暂未开发!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                login();
                break;
            default:break;
        }
    }
}
