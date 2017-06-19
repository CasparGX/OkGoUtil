package com.example.a63024.okgoutil.model;


/**
 * Created by Android on 2016/9/8.
 */
public class UserModel {


    public String loginId;//登陆账号
    public String password;//密码/*/
    public boolean loginStatus = true;//是否已登录（true表示已登录 ， false表示退出登录）

    public String lastLoginTime;//用户上次登录时间

    public String custom_id;//用户ID

    public String auth_code;//授权编码
    public String token ;//TOKEN
    public String secret ;//AES秘钥
    public String app_code;//
    public String client_code;
    public String mall_id;//
    public String mobile;//手机号码

    public String staff_id;//	String	员工编号
    public String staff_name;//	String	员工姓名
    public String staff_code;//	String	员工工号
    public String staff_status;//	String	员工状态
    public String nickname;//	String	昵称
    public String staff_alias;//	String	别名
    public String dept_name	;//String	部门名称
    public String email	;//String	邮箱
    public String avatar_url;//	String	头像地址
    public String myTask_is_visible;//我的任务是否可见
    public String myTask_node_id;//我的任务ID
    public String allTask_is_visible;//全部任务是否可见
    public String allTask_node_id;//全部任务ID

    public String myLog_is_visible;//我的日志是否可见
    public String myLog_node_id;//我的日志ID
    public String allLog_is_visible;//全部日志是否可见
    public String allLog_node_id;//全部日志IDs
    public String parent_dept;//

    public String task;
    public String taskCode;
    public String alltask;
    public String alltaskCode;
    public String malls;//所属商场权限节点

    public boolean workorder = true;//是否有工单权限（true表示有我的和全部权限 ， false表示只有我的权限）

}
