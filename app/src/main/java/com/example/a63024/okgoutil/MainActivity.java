package com.example.a63024.okgoutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a63024.okgoutil.Utils.Utils;
import com.example.a63024.okgoutil.network.IOkCallback;
import com.example.a63024.okgoutil.network.LzyResponse;
import com.example.a63024.okgoutil.network.MyAbsCallback;
import com.example.a63024.okgoutil.network.OkGoUtil;
import com.example.a63024.okgoutil.network.UrlApi;
import com.lzy.okgo.OkGo;

import org.json.JSONStringer;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplication();
        //request();
        Log.d("12#", "MainActivity: ");
        register();
    }

    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login_btn:
                login();
                break;
            case R.id.txt_action_btn:
                action();
                break;
        }
    }

    private void action() {

    }

    private void login() {
        String result = "";
        try {
            JSONStringer jsonStringer = null;
            jsonStringer = new JSONStringer();
            jsonStringer.object();
            jsonStringer.key("staff_code");
            jsonStringer.value("1111122222");
            jsonStringer.key("password");
            jsonStringer.value("123456");
            jsonStringer.key("login_type");
            jsonStringer.value("1");
            jsonStringer.endObject();
            //result = UrlApi.encrypt(jsonStringer.toString(), app.getUserModel().secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGoUtil.getInstance().post(UrlApi.URL_LOGIN + UrlApi.headers(app), result, this, new IOkCallback() {
            @Override
            public void onSuccess(Object o, Call call, Response response) {

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                Log.e("错误信息", call.request().method() + "\n" + "url：" + call.request().url() + e.toString());
            }
        });

    }

    private void register() {
        OkGoUtil.getInstance().refreshToken();
    }

    private void request() {
        String params = "";
        OkGoUtil.getInstance()
                .post(UrlApi.URL_ATTENTION_TASK, params, this, new IOkCallback<String>() {
                    @Override
                    public void onSuccess(String result, Call call, Response response) {

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {

                    }
                });
    }
}
