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

	function checkConvertFile() {
		var file = document.convertForm.code.value;
		if (file == "") {
			alert("Company codeを入力してください");
			return false;
		}
	}
	
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

		<li><h1>Convert Company code :</h1></li>
		<form action="${pageContext.request.contextPath}/convert" method="post" id="convertForm" name="convertForm">
			Company code:<input type="text" name="code" > <br /> 
			<input id="convert" type="submit" name="convert" value="Convert" onclick="return checkConvertFile();">
			<input type="reset" value="Reset"/>
		</form>
		<hr />

		<li><h1>Register Company record :</h1></li>
		<form action="${pageContext.request.contextPath}/register" method="post" id="registerForm" name="registerForm">
			ID:<input type="text" name="ID" > <br /> 
			Company name:<input type="text" name="name" > <br /> 
			Company code:<input type="text" name="code" > <br /> 
			<input id="register" type="submit" name="register" value="Register" onclick="return checkRegisterFile();">
			<input type="reset" value="Reset"/>
		</form>
		<hr />

</body>

</html>