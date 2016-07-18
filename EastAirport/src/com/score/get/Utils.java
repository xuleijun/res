package com.score.get;

import java.util.Properties;

public class Utils {
	public static void SysInit(){
		Properties prop = System.getProperties(); 
        // 设置http访问要使用的代理服务器的地址 
        prop.setProperty("http.proxyHost", "proxy.chn.fujixerox.com"); 
        // 设置http访问要使用的代理服务器的端口 
        prop.setProperty("http.proxyPort", "8000");
        // 设置http访问要使用的代理服务器的用户名 
        prop.setProperty("http.proxyUser", "cnzhoujx1");
        // 设置http访问要使用的代理服务器的密码 
        prop.setProperty("http.proxyPassword", "xxxxxx");
	}
}
