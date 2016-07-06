<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login</title>

<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"
	type="text/javascript"></script>
<!-- 	
<script type="text/javascript">
	function check() {
		var file1 = document.loginForm.identity.value;
		var file2 = document.loginForm.user.value;
		var file3 = document.loginForm.password.value;
		if (file1 == "") {
			alert("LoginIdentityを入力してください");
			return false;
		} else if (file2 == "") {
			alert("UserNameを入力してください");
			return false;
		} else if (file3 == "") {
			alert("Passwordを入力してください");
			return false;
		}
	}
</script>

</head>

<body>
    <h1>Welcome to the Login:</h1>
	<form action="${pageContext.request.contextPath}/userlogin"
		method="post" id="loginForm" name="loginForm">
		LoginIdentity:<select name="identity">
			<option value="admin">管理員</option>
			<option value="user">ユーザー</option>
		</select><br /> 
		UserName:<input type="text" ID="txtUserName" name="username"> <br />
		Password:<input type="password" ID="txtPassword" name="password"> <br /> 
		<input type="submit" value="Login" onclick="return check();"> 
		<input type="reset" value="Reset" />
	</form>
	<hr />

</body>

 -->


<script type="text/javascript">
<!--
window.onload=function onLoginLoaded() {
if(isPostBack == "False") {
GetLastUser();
}
}
-->
function check() {
	var file1 = document.loginForm.identity.value;
	var file2 = document.loginForm.user.value;
	var file3 = document.loginForm.password.value;
    if (file1 == "") {
		alert("请选择登录身份");
		return false;
	} else if (file2 == "") {
		alert("请输入用户名");
		return false;
	} else if (file3 == "") {
		alert("请输入密码");
		return false;
	}
}
<!--
function GetLastUser() {
var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";//GUID标识符
var usr = GetCookie(id);
if (usr != null) {
document.getElementById('txtUserName').value = usr;
} 
else {
document.getElementById('txtUserName').value = "001";
}
GetPwdAndChk();
} -->
//点击登录时触发客户端事件
function SetPwdAndChk() {
<!--
if(!check()){
	return false;
}-->
//取用户名
var usr = document.getElementById('txtUserName').value;
//将最后一个用户信息写入到Cookie
SetLastUser(usr);
//取密码值
var pwd = document.getElementById('txtPassword').value;
//如果记住密码选项被选中
if(document.getElementById('chkRememberPwd').checked == true) {
var expdate = new Date();
expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
//将用户名和密码写入到Cookie
SetCookie(usr, pwd, expdate);
} else {
//如果没有选中记住密码,则立即过期
ResetCookie();
}
//return true;
}
function SetLastUser(usr) {
var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
var expdate = new Date();
//当前时间加上两周的时间
expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
SetCookie(id, usr, expdate);
}
//用户名失去焦点时调用该方法
function GetPwdAndChk() {
var usr = document.getElementById('txtUserName').value;
var pwd = GetCookie(usr);
if (pwd != null) {
document.getElementById('chkRememberPwd').checked = true;
document.getElementById('txtPassword').value = pwd;
} 
else {
document.getElementById('chkRememberPwd').checked = false;
document.getElementById('txtPassword').value = "";
}
}
//取Cookie的值
function GetCookie(name) {
var arg = name + "=";
var alen = arg.length;
var clen = document.cookie.length;
var i = 0;
while (i < clen) {
var j = i + alen;
//alert(j);
if (document.cookie.substring(i, j) == arg) return getCookieVal(j);
i = document.cookie.indexOf(" ", i) + 1;
if (i == 0) break;
}
return null;
}

function getCookieVal(offset) {
var endstr = document.cookie.indexOf(";", offset);
if (endstr == -1) endstr = document.cookie.length;
return unescape(document.cookie.substring(offset, endstr));
}
//写入到Cookie
function SetCookie(name, value, expires) {
var argv = SetCookie.arguments;
//本例中length = 3
var argc = SetCookie.arguments.length;
var expires = (argc > 2) ? argv[2] : null;
var path = (argc > 3) ? argv[3] : null;
var domain = (argc > 4) ? argv[4] : null;
var secure = (argc > 5) ? argv[5] : false;
document.cookie = name + "=" + escape(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" : ("; path=" + path)) + ((domain == null) ? "" : ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
}
function ResetCookie() {
var usr = document.getElementById('txtUserName').value;
var expdate = new Date();
SetCookie(usr, null, expdate);
}
</script>
</head>
<body>
<h1>富士施乐云印量管理平台</h1>
<form action="${pageContext.request.contextPath}/userlogin" method="post" id="loginForm" name="loginForm">
<div>
登录身份：<select name="identity">
<option value="admin">管理员</option>
<option value="user">用户</option>
</select><br /> 
用户名:<input type="text" ID="txtUserName" name="username" onblur="GetPwdAndChk()"><br /> 
密码：<input type="password" ID="txtPassword" name="password"><br /> 
<input type="checkbox" ID="chkRememberPwd" />
记住密码 
<input type="submit" value="Login" onclick="return SetPwdAndChk();"/>
<input type="reset" value="Reset" />
</div>
</form>
</body>


</html>