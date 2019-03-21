<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登陆</title>
 </head>
<body>
	
	<form id="form1" method='post'>
		<input type='text' class='name' name='userName' placeholder="请输入账户"/>
		<input type='password' class='pwd' name='passWord' placeholder="请输入密码"/>
	</form>
	<p><input class='login' type="button" value='登录'/>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/main.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
<script type="text/javascript">
	$(function(){
		$('a').each(function(){
			$(this).append('中国');
		});
	});
</script>
</html>