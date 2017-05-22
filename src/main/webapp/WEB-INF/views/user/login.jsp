 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>Insert title here</title>

<!-- <script type="text/javascript">
// 切换验证码
 function change() {
	
	//document.getElementById("mydiv").src = "./user/cCode.action?"+ new Date().getTime();
} 
</script>-->

</head>
<body>
    <form action="./user/Login.action" method="post">
    	用户名<input type="text" name="username" /><br>
    	密码 <input type="password" name="password" /><br>
    	验证码<input type="text" name="checkcode" /><span boder="2px">${checkCode}</span><br>
    	<!-- <img id="myimg"
		src="./user/cCode.action" style="cursor: pointer;" onclick="change();" /><br> -->
    <input type="submit" value="登陆"> 

    </form>
</body>
</html>