<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-22
  Time: 下午4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改文章</title>
</head>
<body>
<form action="./blog/update.action" method="post">
    标题：<input type="text" name="title" value="${sessionScope.articleTemp.title}"><br>
    分类:
    <c:forEach items="${requestScope.typeList}" var="at">
        <input type="radio" name="type" id="type${at.typeNumber}" value="${at.typeNumber}">${at.typeName}
    </c:forEach><br>
    标签(多个标签，用 ; 号隔开):<input type="text" name="tags" value="${sessionScope.articleTemp.tags}"><br>
    原创： <input type="radio" name="original" value="1" id="o1">是
    <input type="radio" name="original" value="0" id="o2">否(请尊重原创，在文章中注明出处)<br>
    内容:<br>
    <textarea name="content" cols="80" rows="20">${sessionScope.articleTemp.content}</textarea><br>
    <button>提交</button>
</form>
</body>
<script src="resources/js/jquery-3.1.0.min.js"></script>
<script>
$("document").ready(function () {
    $("#type${sessionScope.articleTemp.type}").attr("checked", true);
    $("#o${sessionScope.articleTemp.original}").attr("checked", true);
})
</script>
</html>
