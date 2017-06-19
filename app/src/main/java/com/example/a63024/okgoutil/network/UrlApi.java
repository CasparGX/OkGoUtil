package com.example.a63024.okgoutil.network;


import com.example.a63024.okgoutil.App;
import com.example.a63024.okgoutil.Utils.BASE64Decod;
import com.example.a63024.okgoutil.Utils.Base64;

/**
 * Created by admin on 2017/3/15.
 */

public class UrlApi {
    /**
     * 加密
     * @param requestStr
     * @return
     * @throws Exception
     */
    public static String encrypt(String requestStr , String aesKey) throws Exception {
        byte[] result = Base64.encrypt( requestStr.getBytes(), aesKey.getBytes());
        return BASE64Decod.encode(result);
    }
    /**
     * 解密
     * @throws Exception
     */
    public static String decryptResult(String requestStr, String aesKey) throws Exception {
        byte[] str = Base64.decode(requestStr);
        byte[] res = Base64.decrypt( str, aesKey.getBytes());
        String result = new String(res);
        return result;
    }

    //公共参数
    public static String headers(App app){
        StringBuffer getparams = new StringBuffer();
        getparams.append("?").append("app_code").append("=").append("56287D168F8FC6CD")
                .append("&").append("token").append("=").append(app.getUserModel().token).append("&")
                .append("auth_code").append("=").append(app.getUserModel().auth_code);

        return  getparams.toString();
    }

//    public static final String SERVER = "https://192.168.11.225";
//    public static final String SERVER = "https://192.168.11.40";
    public static final String SERVER = "https://appapi.hicimen.com";//线上服务器z/

    public static final String URL_AUTHORIZE_REGISTER = SERVER + "/appapi/authorize/register?app_code=56287D168F8FC6CD";//设备注册

    public static final String URL_LOGIN = SERVER + "/homeMall/appapi/staff/login";//用户登录
    public static final String URL_GET_LOGIN_CODE = SERVER + "/homeMall/appapi/staff/getLoginCode";//获取验证码
    public static final String URL_GET_TASTLIST = SERVER + "/homeMall/appapi/task/getTaskList";//获取任务列表
    public static final String URL_UPLODE_STAFF_AVATAR = SERVER + "/homeMall/appapi/staff/uplodeStaffAvatar";//更换头像
    public static final String URL_GET_STAFF_PERMISSION = SERVER + "/homeMall/appapi/staff/getStaffPermission";//获取权限节点信息
    public static final String URL_GET_STAFF_INFO = SERVER + "/homeMall/appapi/staff/getStaffInfo";//获取员工信息
    public static final String URL_GET_TASK_OBJECT_LIST = SERVER + "/homeMall/appapi/task/getTaskObjectList";//获取任务对象列表
    public static final String URL_SUBMIT_TASK_OBJECT = SERVER + "/homeMall/appapi/task/submitTaskObject";//提交任务检查对象
    public static final String URL_GET_TASK_OBJECT_DETAIL = SERVER + "/homeMall/appapi/task/getTaskObjectDetail";//获取任务对象详情接口
    public static final String URL_UPLOAD_VEDIO   = SERVER + "/homeMall/appapi/task/uploadVideo";//视频上传接口
    public static final String URL_GET_TASK_LOG_LIST   = SERVER + "/homeMall/appapi/taskLog/getTaskLogList";//日志列表接口
    public static final String URL_UPLOAD_IMAGE   = SERVER + "/homeMall/appapi/task/uploadImage";//上传图片接口
    public static final String URL_GET_TASK_DETAILS   = SERVER + "/homeMall/appapi/task/getTaskDetails";//日志列表接口
    public static final String URL_ATTENTION_TASK   = SERVER + "/homeMall/appapi/taskComment/attentionTask";//任务点赞接口
    public static final String URL_SUBMIT_TASK_COMMENT   = SERVER + "/homeMall/appapi/taskComment/submitTaskComment";//任务评论接口
    public static final String URL_GET_MALLAREA   = SERVER + "/homeMall/appapi/mall/area/getMallAreaList";//获取区域列表
    public static final String URL_GET_TASK_TYPELIST   = SERVER + "/homeMall/appapi/taskType/getTaskTypeList";//6、	获取任务类型对象
    public static final String URL_GET_CREATE_TASK   = SERVER + "/homeMall/appapi/task/createTask";//新建任务
    public static final String URL_CHECK_SOFT_UPDATE   = SERVER + "/homeMall/appapi/version/checkSoftUpdate";//版本更新
    public static final String URL_GET_WORKORDER_STATISTIC_LIST   = SERVER + "/homeMall/appapi/task/getWorkorderStatisticList";//2、	工单任务统计列表
    public static final String URL_GET_WORKORDER_TASK_LIST   = SERVER + "/homeMall/appapi/task/getWorkorderTaskList";//3、	工单任务列表
    public static final String URL_GET_TASK_ITEM_LIST   = SERVER + "/homeMall/appapi/task/getTaskItemList";//5、	获取任务内容项列表
    public static final String URL_GET_TASK_ITEM_DETAIL   = SERVER + "/homeMall/appapi/task/getTaskItemDetail";//6、	获取任务项详情
    public static final String URL_SUBMIT_ITEM   = SERVER + "/homeMall/appapi/task/submitTaskItem";//12、	提交任务项检查结果
    public static final String URL_GET_WORKORDER_LIST_BY_ITEM   = SERVER + "/homeMall/appapi/workorder/getWorkorderListByItem";//获取异常项的工单列表
    public static final String URL_GET_FP_WORKORDER_TASK_CONTENT   = SERVER + "/homeMall/appapi/task/getFpWorkorderTaskContent";//19、	获取分配类工单任务内容
    public static final String URL_GET_ZX_WORKORDER_TASK_CONTENT   = SERVER + "/homeMall/appapi/task/getZxWorkorderTaskContent";//19、	获取执行类工单任务内容
    public static final String URL_GET_JH_WORKORDER_TASK_CONTENT   = SERVER + "/homeMall/appapi/task/getJhWorkorderTaskContent";//19、	获取检核类工单任务内容
    public static final String URL_GET_STAFF_SUBOREDINATE   = SERVER + "/homeMall/appapi/staff/getStaffSubordinate";//19、	获取下属列表
    public static final String URL_GET_FP_WORKORDER_TASK_RESULT   = SERVER + "/homeMall/appapi/task/submitFpWorkorderTaskResult";//20、	分配类工单任务提交
    public static final String URL_GET_JH_WORKORDER_TASK_RESULT   = SERVER + "/homeMall/appapi/task/submitJhWorkorderTaskResult";//23、	检核类工单任务提交
    public static final String URL_GET_ZX_WORKORDER_TASK_RESULT   = SERVER + "/homeMall/appapi/task/submitZxWorkorderTaskResult";//18、	执行类工单任务提交
    public static final String URL_GET_WORKORDER_INFO   = SERVER + "/homeMall/appapi/workorder/getWorkorderInfo";//1、获取工单详情
    public static final String URL_GET_WORKORDER_LOG_LIST   = SERVER + "/homeMall/appapi/workorder/getWorkorderLogList";//5、获取工单流转记录
    public static final String URL_GET_NEW_CREATE_EORKORDER   = SERVER + "/homeMall/appapi/workorder/createWorkorder";//新建工单接口
    public static final String URL_GET_WORKORDER_LIST  = SERVER + "/homeMall/appapi/workorder/getWorkorderList";//3、获取分页工单列表
    public static final String URL_GET_TASK_OBJECT_AND_ITEM_LIST  = SERVER + "/homeMall/appapi/taskType/getTaskObjectAndItemList";//10、	获取任务类型对应的对象列表，任务项列表
    public static final String URL_GET_STAFF_MALLS_LIST   = SERVER + "/homeMall/appapi/staff/getMallStaffList";//19、	获取全部人员






















}
