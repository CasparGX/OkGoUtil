package com.example.a63024.okgoutil.network;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.a63024.okgoutil.App;
import com.example.a63024.okgoutil.Utils.Utils;
import com.example.a63024.okgoutil.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.logging.Level;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 63024 on 2017/6/15 0015.
 */

public class OkGoUtil<T> {

    private static App app;
    private String TAG = "OkGoUtil";
    private int refreshFlag = 0;

    private OkGoUtil() {

    }

    public static OkGoUtil getInstance() {
        return OkGoHolder.holder;
    }

    private static class OkGoHolder {
        private static OkGoUtil holder = new OkGoUtil();
    }

    public void post(String url, String params, Activity o,IOkCallback<T> iOkCallback) {
        post(url, params, o, app.secret, iOkCallback);
    }

    public void post(final String url, final String params, final Activity o, String secret, final IOkCallback<T> iOkCallback) {
        final Activity activity = (Activity) o;
        String secretParams = "";
        try {
            if (!params.equals("")) {
                secretParams = UrlApi.encrypt(params, secret);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkGo.post(url)//
                .tag(o)//
                .upString(secretParams)
                .execute(new JsonCallback<T>(app.secret) {


                    @Override
                    public void onSuccess(T t, Call call, Response response) {

                        try {
//                            LzyResponse<T> result = (LzyResponse) t;
//                            String obj = com.lzy.okgo.utils.UrlApi.decryptResult((String) result.data, app.secret);
//                            Log.d(TAG, "refreshToken onSucceed: "+obj);
//                            Gson gson = new Gson();
//                            T convertResult = gson.fromJson(obj, new TypeToken<T>() {}.getType());
                            refreshFlag = 0;
                            iOkCallback.onSuccess(t, call, response);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {

                        Log.e("错误信息", call.request().method() + "\n" + "url：" + call.request().url() + e.toString());
                        String[] infos = e.getLocalizedMessage().split("#");
                        if(infos[0].equals("1") && refreshFlag<2){
                            //refresh token
                            refreshFlag++;
                            refreshToken(url, params, o, iOkCallback);
                        } else if(infos[0].equals("998")){
                            Toast.makeText(activity,infos[1],Toast.LENGTH_LONG).show();
                        }
                        iOkCallback.onError(call, response, e);
                    }
                });
    }

    public void refreshToken(){
        refreshToken("", "", new Activity(), new IOkCallback<T>() {
            @Override
            public void onSuccess(T t, Call call, Response response) {

            }

            @Override
            public void onError(Call call, Response response, Exception e) {

            }
        });
    }

    private void refreshToken(final String url, final String params, final Activity o, final IOkCallback<T> iOkCallback) {
        Activity activity = (Activity) o;
        try {
            String id = Utils.getAndroidId(activity);
            final String result = "";
            JSONStringer jsonStringer = null;
            jsonStringer = new JSONStringer();
            jsonStringer.object();
            jsonStringer.key("client_code");
            jsonStringer.value(id);
            jsonStringer.endObject();
            OkGo.post(UrlApi.URL_AUTHORIZE_REGISTER)//
                    //.tag(activity)//
                    .upString(UrlApi.encrypt(jsonStringer.toString(), "e51804acf9a042899d35537cf76fe056"))
                    .execute(new JsonCallback<UserModel>("e51804acf9a042899d35537cf76fe056") {

                        @Override
                        public void onSuccess(UserModel result, Call call, Response response) {
                            String obj = null;
                            try {
                                //obj = UrlApi.decryptResult(result,"e51804acf9a042899d35537cf76fe056");
                                app.setUserModel(result);
                                Log.d(TAG, "refreshToken onSucceed: "+result);

                                if (!url.equals("")) {
                                    post(url,params,o,iOkCallback);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            Log.e("错误信息", call.request().method() + "\n" + "url：" + call.request().url() + e.toString());
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init(Application application) {
        //必须调用初始化
        app = (App) application;
        OkGo.init(application);
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)

                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//                .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates();                                  //方法一：信任所有证书,不安全有风险
//                    .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
//                    //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//                    .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

            //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//                    .setHostnameVerifier(new SafeHostnameVerifier())

            //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })
            //这两行同上，不需要就不要加入
//                    .addCommonHeaders(headers)  //设置全局公共头
//                    .addCommonParams(params);   //设置全局公共参数

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
