package com.jaycejia.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.jaycejia.R;
import com.jaycejia.databinding.ActivityRegisterBinding;
import com.jaycejia.utils.ToastUtil;

/**
 * Created by NiYang on 2017/4/30.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRegisterBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_register, null, false);
        setContentView(this.binding.getRoot());

        initView();
    }

    private void initView() {
        this.binding.ivBack.setOnClickListener(this);
        this.binding.btnRegister.setOnClickListener(this);
    }

    //TODO register
    private void register() {
        ToastUtil.showToast("功能暂时未开发!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_register:
                register();
                break;
            default:break;
        }
    }
}
