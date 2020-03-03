package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

//用来装一些变量　application 对应的这些
public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String addUserUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;

    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore store;

}
