<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<script>
	//书写更换图片的js函数
	function changeImg(obj){
		//使用obj表示获得当前对象
		obj.src="/web_response/checkImg?time="+new Date().getTime();
	}
</script>
</head>
<body>
	<form action="/web_servlet/login" method="post">
		用户名:<input type="text" name="username"><br/>
		密码:<input type="password" name="password"><br/>
		验证码:<input type="text" name="checkImg"><span><img onclick="changeImg(this)" src="/web_response/checkImg"/></span><br/>
		
		<input type="submit" value="登录"><br/>
	</form>
</body>
</html>