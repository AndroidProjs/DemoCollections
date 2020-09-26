package com.edger.networkdemo.http;

public interface HttpCallBackListener {

    void onFinish(String response);

    void onError(Exception e);
}
