package com.connxun.ttdj.constants;


import android.os.Environment;

import com.blankj.utilcode.util.SDCardUtils;

/**
 * Created by wushange on 2017/7/12.
 */

public class Constants {
//    public static String SERVER_IP    = "192.168.1.19";
    public static String SERVER_IP    = "47.93.32.136";
    public static String SERVER_PORT = "8087";  //8087
    public static String HOST = "http://" + SERVER_IP + ":" + SERVER_PORT ;//服务器地址
    public static String BASE_URL = HOST + "/ltcx/";  // /
    public static String BASE_API_URL = BASE_URL + "api/";
    public static String BASE_IMAGE_URL = BASE_URL + "upload";
    public static String BASE_FILE_URL = BASE_URL + "course_part";

    //验证码等待时间
    public static int WAITETIME = 60;
    //基础请求分页
    public static int PAGE_SIZE=15;
    //高德定位
    public static String AMAP_LOCATION="AMAP_LOCATION";


    public static String BUGLY_APPID = "a5de514a0f";//buglyid
    public static String DCS_KEY = "http://dcsapi.com/?k=181751184&url=";//永中云转换
    public static int ROLE_MANAGER = 4;//管理人员
    public static int ROLE_WORKER = 3;//巡检员

    public static String NIGHT_THEME = "THEME";
    public static String USER_NAME = "USER_NAME";
    public static String USER_PWD = "USER_PWD";
    public static String USER_ID = "USER_ID";


    /**
     * 项目根SD卡目录
     **/
    public static final String PROJECT_ROOT = "ltcx";
    public static final String SDPATH = SDCardUtils.getSDCardPaths()
            + PROJECT_ROOT + "/";
    public static final String DOWNLOAD_PATH = SDPATH + "download" + "/";
    //图片压缩地址
    public static String SAVED_IMAGE_DIR_PATH= Environment.getExternalStorageDirectory().getPath()
            + "/ltcx/compressImage/";
}
