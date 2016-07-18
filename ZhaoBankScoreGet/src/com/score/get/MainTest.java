package com.score.get;

import java.sql.Date;
import java.util.Scanner;

import net.sf.json.JSONObject;

public class MainTest {

	public static void main(String[] args) {
		ScoreGet socre = new ScoreGet();
		
		String refer="http://easternmiles.ceair.com/mpf/";
//		socre.getConn(refer, "", "");
//		socre.printHeader();
//		String referCookie = socre.getCookie();
//		String referCo[]=referCookie.split(" ");
//		String BC_HA=referCo[0];
//		System.out.println("BC_HA = "+BC_HA);
		
		System.out.println("[==================================================]");
		
		String verifycode = "http://easternmiles.ceair.com/mpf/sign/captchaCreate";
//		String timestamp = Long.toString(System.currentTimeMillis());
		socre.getConnVerifycode(verifycode, "", "",verifycode);
		String verifycodeCookie = socre.getCookie();
		//String vcCookie[]=verifycodeCookie.split(" Path=/");
		//String verifycodeUUID=vcCookie[1].substring(0, vcCookie[1].indexOf(" "));
		socre.printHeader();
		socre.getVerifyCode();
		System.out.println("[====]"+verifycodeCookie);
//		System.out.println(socre.getResponse());
		
		String loginUrl = "http://easternmiles.ceair.com/mpf/sign/signIn_CN?locale=cn";
		System.out.print("input coode:");
		Scanner scan = new Scanner(System.in);
		String code = scan.nextLine();
		System.out.println("code is : "+code);
		scan.close();
		
		JSONObject obj = new JSONObject();
        obj.element("memberId", "13166059082");
        obj.element("password", "748B80590626FC2B3D83ED40D40542C7");
        obj.element("code", code);
        
		//String parames = "{\"memberId\":\"13166059082\",\"password\":\"748B80590626FC2B3D83ED40D40542C7\",\"code\":\""+code+"\"}";
		
		// String parames = "memberId=13166059082&password=748B80590626FC2B3D83ED40D40542C7&code="+code;
		
		String inputCookie =verifycodeCookie;
		System.out.println(inputCookie);
		socre.getConn(loginUrl, "", inputCookie,refer,obj);
		socre.printHeader();
		//cookie = socre.getCookie();
		System.out.println(socre.getResponse());
		
		
		
		
		
		

	}

}
