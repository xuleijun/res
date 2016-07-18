package com.score.get;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScoreGet {
	private URLConnection connection;
	
	
	/**
	 * 建立链接
	 */
	public void getConn(String url, String param, String Cookie) {
		String urlNameString = url;
        if (param.length() > 0) {
        	urlNameString += "?" + param;
        }
        try {
        	
        	Utils.SysInit();
	        URL realUrl = new URL(urlNameString);
	        // 打开和URL之间的连接
	        connection = realUrl.openConnection();
	        // 设置通用的请求属性
	        connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        if (Cookie!= null && Cookie.length() > 0){
	        	connection.setRequestProperty("Cookie", Cookie);
	        }
	        // 建立实际的连接
	        connection.connect();
	        System.out.println("[Target] "+ urlNameString);
	        System.out.println("[Result] Connect OK!");
	        
        } catch(Exception e) {
        	System.out.println("[Target] "+ urlNameString);
        	System.out.println("[Result] Connect Faild!");
        	e.printStackTrace();
        }
	}
		
	public void getConnPost(String url, String param, String Cookie, String refer) {
		String urlNameString = url;
        if (param.length() > 0) {
        	urlNameString += "?" + param;
        }
        try {
        	
        	//  Utils.SysInit();
	        URL realUrl = new URL(urlNameString);
	        // 打开和URL之间的连接
	        connection = realUrl.openConnection();
	        connection.setDoOutput(true);
	        // 设置通用的请求属性
	        connection.setRequestProperty("User-Agent", "");
	        connection.setRequestProperty("accept", "application/json;charset=utf-8");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        if (Cookie!= null && Cookie.length() > 0){
	        	connection.setRequestProperty("Cookie", Cookie);
	        }
	        // 建立实际的连接
	        connection.setRequestProperty("refer", refer);
	        
            if(null!=param&&param.length()>0){
            	connection.getOutputStream().write(param.getBytes());
            	connection.getOutputStream().flush();
            	connection.getOutputStream().close();
            }
	        
	        connection.connect();
	        System.out.println("[Target] "+ urlNameString);
	        System.out.println("[Result] Connect OK!");
	        
        } catch(Exception e) {
        	System.out.println("[Target] "+ urlNameString);
        	System.out.println("[Result] Connect Faild!");
        	e.printStackTrace();
        }
	}
	
	/**
	 * 返回Cookie
	 * @return
	 */
	public String getCookie() {
		String cookie = "";
		// 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
        	if (key != null && key.contains("Cookie")) {
        		for(int i =0; i< map.get(key).size()-1;i++){
        			cookie += map.get(key).get(i).toString()+"; ";
        		}
        		cookie += map.get(key).get(map.get(key).size()-1).toString();
        	}
            // System.out.println("[Cookie]" + cookie);
        }
        
        return cookie;
	}
	
	
	public void printHeader(){
		Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
		
        for (String key : map.keySet()) {
        	String value = "";
        	for(int i =0; i< map.get(key).size();i++){
        		value += map.get(key).get(i).toString();
    		}
            System.out.println(key + "-->" + value);
        }
	}
	
	/**
	 * 返回Response内容
	 * @return
	 */
	public String getResponse(){
		StringBuffer result = new StringBuffer();
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String line;
	        while ((line = in.readLine()) != null) {
	        	result.append(line);
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
        return result.toString();
		
	}
	
	/**
	 * 获取AccessCode
	 * @param html
	 * @return
	 */
	public String getAccessCode(String html) {
		Document doc = Jsoup.parse(html);
		
		Element el = doc.getElementsByAttributeValue("name", "accessCode").get(0);
		
		String result = el.attr("value");
		System.out.println("[AccessCodeElement] "+ el.toString());
		System.out.println("[AccessCode] "+ result);
		return result;
	}
	
	
	public String getScore(String html, String bankCard){
		Document doc = Jsoup.parse(html);
		String element_id = "score_";
		
		String cardNos[] = bankCard.split("\\+");
		element_id += cardNos[0];
		element_id += cardNos[1];
		element_id +="****";
		element_id += cardNos[3];
		
		Element el = doc.getElementsByAttributeValue("id", element_id).get(0);
		
		System.out.println(el.toString());
		return el.toString();
		
	}
	
	/* get verify code image */
	public String getVerifyCode(){
		try{
			BufferedImage bi = ImageIO.read(connection.getInputStream());
	        File f = new File("check_img.jpg");
            ImageIO.write(bi, "jpg", f);

            return f.getAbsolutePath();
            
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	
	
}
