<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>convert to html</title>

<script type="text/javascript">
function checkFile(){
 	var file1 = document.form.file1.value;
	var file2 = document.form.file2.value;
	var file3 = document.form.file3.value;
	var obj = document.getElementsByName("changefile");
	if(file1 == "" && file2 == "" && file3 == ""){
	    alert("ファイルを選択してください");
	    return false;
	}
}

/* function getExt(file_name){
	var result =/\.[^\.]+/.exec(file_name);
	return result;
} */
</script>
</head>
<body>
   <form name="form" action="${pageContext.request.contextPath}/ConvertServlet" method="post" enctype="multipart/form-data">                    
   <fieldset style="width:730px; text-align:center;  margin:0px auto">
       <legend>
           ファイル変換
       </legend>
           ファイル：
           <input type="file" name="file1" id="file1">
           <br><br>
           ファイル：
           <input type="file" name="file2" id="file2">
           <br><br>
           ファイル：
           <input type="file" name="file3" id="file3">
           <br><br>
           <input type="radio" name="changefile" value="pdf2html"  checked="checked" >PDF2Html
           <input type="radio" name="changefile" value="word2html" >Word2Html
           <input type="radio" name="changefile" value="dcuworks2html" >Dcuworks2Html
           <input type="radio" name="changefile" value="excel2html" >Excel2Html
           <br><br>
           <input type="submit" value="変換" onclick="return checkFile();">
   </fieldset>
   </form>
</body>
</html>