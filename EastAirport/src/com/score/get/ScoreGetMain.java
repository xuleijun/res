package com.score.get;

import java.util.Scanner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ScoreGetMain {

	public static void main(String[] args) {
//		String scoreQueryIndexUrl = "http://jf.ccb.com/exchangecenter/account/viewscore.jhtml";
//		String scoreVerifyCodeUrl ="http://jf.ccb.com/exchangecenter/account/viewScoreVerifyCode.jhtml";
//		String scoreQueryUrl ="http://jf.ccb.com/exchangecenter/account/viewScoreResult.jhtml";
//		
//		String cookie = "";
//		
//		String bankCard = "6259+6508+0255+0076";
//		String mobildLastNum = "5611";
//		String verifyCode = "";
//		
//		String VerifyCodeparam = "accoumt=" + bankCard + "&mobilelastNum=" + mobildLastNum +"&state=1&accessCode=";
//		String Queryparam = "&mobildlastNum=" + mobildLastNum +"&hidden_banckCard=" + bankCard + "&hidden_mcode=";
//		
//		ScoreGet socre = new ScoreGet();
//		
//		// 获得AccessCode
//		System.out.println("======从登陆页面获得AccessCode和Cookie======");
//		socre.getConn(scoreQueryIndexUrl, "", "");
//		cookie = socre.getCookie();
//		System.out.println(cookie);
//		String accessCode = socre.getAccessCode(socre.getResponse());
//		VerifyCodeparam += accessCode;
//		
//		// 发送短信验证码
//		System.out.println("======根据AccessCode，发出手机短息验证码======");
//		socre.getConn(scoreVerifyCodeUrl, VerifyCodeparam, cookie);
//		String response = socre.getResponse();
//		System.out.println("[Mobile Code Send State] "+response);
//		
//		// 积分查询请求
//		
//		// 等待输入手机验证码
//		System.out.println("======输入验证码后查询积分======");
//		
//		System.out.print("Please Input VerifyCode：");
//		Scanner scan = new Scanner(System.in);
//		verifyCode = scan.nextLine();
//		Queryparam +=verifyCode;
//		scan.close();
//		socre.getConn(scoreQueryUrl, Queryparam, cookie);
//		
//		socre.getScore(socre.getResponse(), bankCard);
		
		ScoreGet socre = new ScoreGet();
		
		String refer="http://easternmiles.ceair.com/mpf/";
		socre.getConn(refer, "", "");
		
		String eastAirLoginUrl="http://easternmiles.ceair.com/mpf/#/sign/signin";
		socre.getConn(eastAirLoginUrl, "", "");
		//String  loginRes=socre.getResponse();
		String loginCookie=socre.getCookie();
		socre.printHeader();
		System.out.print(loginCookie);
		
		String captchaCreateUrl ="http://easternmiles.ceair.com/mpf/sign/captchaCreate";
		socre.getConn(captchaCreateUrl, "", "");
		System.out.println(socre.getVerifyCode());
		
		String captchaCookie=socre.getCookie();
		System.out.println(captchaCookie);
		String signIn[]=captchaCookie.split("; ");
		String signInCookie=signIn[0]+"; "+signIn[4];
		System.out.println(signInCookie);
		
		String memberId ="13166059082";
		String password ="748B80590626FC2B3D83ED40D40542C7";
		String jsonPw=password;
		// 等待输入验证码
		System.out.println("======输入验证码后查询积分======");
		System.out.println("Please Input VerifyCode：");
		Scanner scan = new Scanner(System.in);
		String code = scan.nextLine();
		System.out.println("code is : "+code);
	
		String signInUrl ="http://easternmiles.ceair.com/mpf/sign/signIn_CN?locale=cn";
		
		//String resourceCode ="locale=cn";
		String resourceCode="{\"memberId\":\"13166059082\",\"password\":\"748B80590626FC2B3D83ED40D40542C7\",\"code\":\""+code+"\"}";
		System.out.println(resourceCode);
		//JSONObject rootObject = JSONObject.fromObject(json);
		socre.getConnPost(signInUrl, resourceCode, signInCookie, refer);
		
		System.out.println(socre.getResponse());
	}

}
