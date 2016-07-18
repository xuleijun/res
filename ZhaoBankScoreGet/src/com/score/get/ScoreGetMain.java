package com.score.get;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Element;

public class ScoreGetMain {

	public static void main(String[] args) throws IOException {
//		String scoreQueryIndexUrl = "http://easternmiles.ceair.com/mpf/#/sign/signin";
//		String scoreVerifyCodeUrl ="http://easternmiles.ceair.com/mpf/#/home/index";
//		String scoreQueryUrl ="http://easternmiles.ceair.com/mpf/#/acc/accindex";
//		
//		String cookie = "";
//		
//		String bankCard = "13166059082";
//		String mobildLastNum = "12345678";
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
//		
			
//		String zhaohangLoginUrl = "https://ssl.jf.cmbchina.com/Customer/Login.aspx";
//		
//		ScoreGet socre = new ScoreGet();
//		socre.getConn(zhaohangLoginUrl, "", "");
//		
//		String respon = socre.getResponse();
//		
//		String zhaohangEWinLoginUrl=socre.getIframeSrc(respon);
//		//System.out.println(zhaohangDefaultUrl);
//		
//		socre.getConn(zhaohangEWinLoginUrl, "", "");
//		respon=socre.getResponse();
//		
//		ArrayList<Element> inputEleList = socre.getInputList(respon);
//		System.out.println(inputEleList);
//		
		ScoreGet socre = new ScoreGet();
		
		System.out.println("----------------------------------");
		String zhaoBankLogin ="https://ssl.jf.cmbchina.com/Customer/Login.aspx";
		// String easternAirportLogin ="http://easternmiles.ceair.com/mpf/#/sign/signin";
		socre.getConn(zhaoBankLogin, "", "");
		String cookie = socre.getCookie();
		String respon = socre.getResponse();
		socre.printHeader();
		System.out.println("-----------------cookie---start-------------");
		String zhaohangEWinLoginUrl=socre.getIframeSrc(respon);
		// socre.printHeader();
		System.out.println(cookie);
		System.out.println("-----------------cookie------end-----------");
		System.out.println(respon);
//		
//		
//		System.out.println("----------------------------------");
		socre.getConn(zhaohangEWinLoginUrl, "", cookie);
		respon=socre.getResponse();
		socre.printHeader();
		System.out.println(respon);
		
		String Merchant=socre.getElementValue(respon, "Merchant");
		String GuidKey=socre.getElementValue(respon, "GuidKey");
		String Timestamp=socre.getElementValue(respon, "Timestamp");
		String Code=socre.getElementValue(respon, "Code");
		String MerchantData=socre.getElementValue(respon, "MerchantData");
		String Account=socre.getElementValue(respon, "Account");
		String IsAccountError=socre.getElementValue(respon, "IsAccountError");
		
		String resourceCode="Merchant='"+Merchant+"'&GuidKey='"+GuidKey+"'&Timestamp='"+Timestamp+"'&Code='"+Code+"'&MerchantData='"+MerchantData;
		resourceCode += "'&Account='"+Account+"'&IsAccountError='"+IsAccountError+"'";
		
		System.out.println("----------------------------------");
		String showUrl="https://trip.cmbchina.com/SSO/Common/Login/Show.html";
		socre.getConn(showUrl, resourceCode, cookie,zhaohangEWinLoginUrl);
		respon=socre.getResponse();
		System.out.println(respon);
//		
		
	}

}
