package com.example.a63024.okgoutil;

import android.app.Application;

import com.example.a63024.okgoutil.model.UserModel;
import com.example.a63024.okgoutil.network.OkGoUtil;

/**
 * Created by 63024 on 2017/6/15 0015.
 */

public class App extends Application {
    public String secret;


    private static UserModel userModel;
    @Override
    public void onCreate() {
        super.onCreate();
        OkGoUtil.init(this);
        userModel = new UserModel();
    }

    public static UserModel getUserModel() {
        return userModel;
    }

    public static void setUserModel(UserModel userModel) {
        App.userModel = userModel;
    }
}
