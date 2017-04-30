package com.jaycejia.network;
import com.jaycejia.utils.LogUtil;
import com.jaycejia.utils.ToastUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

/**
 * @Author: NiYang
 * @Date: 2017/4/10.
 */
public class RetrofitException {
    private static final String TAG = "RetrofitException";
    public static void httpException(Throwable e) {
        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            int code = ((HttpException) e).response().code();
            try {
                LogUtil.e(TAG,code+"-"+body.string());
            } catch (IOException e1) {
                LogUtil.e("IOException", e1.getMessage());
            }
            return;
        }
        ToastUtil.showToast(e.getMessage());
        LogUtil.e("error", e.getMessage());
    }
}
