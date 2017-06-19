package com.example.a63024.okgoutil.Utils;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by 63024 on 2017/6/15 0015.
 */

public class Utils {

    //Android 手机上获取物理唯一标识码
    public static String getAndroidId(Context context) {
        try {
            String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            return android_id;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
