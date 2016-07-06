<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Company Info Management web Services</title>

<div id="show"></div>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	function checkRegisterFile() {
		var file1 = document.registerForm.ID.value;
		var file2 = document.registerForm.name.value;
		var file3 = document.registerForm.code.value;
		if (file1 == "") {
			alert("IDを入力してください");
			return false;
		} else if (file2 == "") {
			alert("Company nameを入力してください");
			return false;
		} else if (file3 == "") {
			alert("Company codeを入力してください");
			return false;
		}
	}
	
</script>

</head>

<body>

		<li><h1>用户管理:</h1></li>
		<form action="${pageContext.request.contextPath}/userManagement" method="post" id="userForm" name="userForm">
                   登录身份：<select name="identity">
        <option value="admin">管理员</option>
        <option value="user">用户</option>
        </select><br /> 
                   用户名:<input type="text" ID="txtUserName" name="username"><br /> 
                   密码：<input type="password" ID="txtPassword" name="password"><br /> 
			<input id="submit" type="submit" name="submit" value="提交" onclick="return checkRegisterFile();">
			<input type="reset" value="重置"/>
		</form>
		<hr />

</body>

</html>