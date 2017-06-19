package com.example.a63024.okgoutil.network;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 63024 on 2017/6/15 0015.
 */

public interface IOkCallback<T> {
    void onSuccess(T t, Call call, Response response);
    void onError(Call call, Response response, Exception e);
}
