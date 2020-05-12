package com.forum.common.constan;

public final class Globals {
    public static String USER_SESSION = "USER_SESSION";

    public static Short User_Normal = Short.valueOf((short) 1);
    public static Short User_Forbidden = Short.valueOf((short) 0);
    public static Short User_ADMIN = Short.valueOf( (short) -1);

    public static Short Log_Leavel_INFO = Short.valueOf((short) 1);
    public static Short Log_Leavel_WARRING = Short.valueOf((short) 2);
    public static Short Log_Leavel_ERROR = Short.valueOf((short) 3);

    public static String Log_Type_LOGIN = "1";//登录
    public static String Log_Type_EXIT = "2";//退出
    public static String Log_Type_INSERT = "3";//保存
    public static String Log_Type_DEL = "4";//删除
    public static String Log_Type_UPDATE = "5";//修改
    public static String Log_Type_UPLOAD = "6";//上传
    public static String Log_Type_AUDIT = "7";//注销
    public static String Log_Type_EXPORT = "8";//导出
    public static String Log_Type_SEND = "9";//发送
    public static String Log_Type_OTHER = "10";//其他

    public static String TypeGroup_Database = "database";

    public static Short Function_Leave_ONE = Short.valueOf((short) 0);
    public static Short Function_Leave_TWO = Short.valueOf((short) 1);

    public static String Function_Order_ONE = "ofun";
    public static String Function_Order_TWO = "tfun";

    public static String Menu_Level_ONE = "0";

    public static boolean BUTTON_AUTHORITY_CHECK = false;
}
