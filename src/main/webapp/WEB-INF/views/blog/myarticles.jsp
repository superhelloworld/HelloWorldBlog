<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-22
  Time: 下午2:37
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
    <title>我的文章</title>
</head>
<body>
<ul>
    <c:forEach items="${requestScope.pageModel.items}" var="item">
        <li>
            <a href="${item.getArticleLink()}">${item.title}</a>
                ${item.date}
            <br>
            <a href="./blog/delete?aid=${item.aId}">删除</a>
            <a href="./blog/updatePage?aid=${item.aId}">修改</a>
            <br>
            <p>${item.detail}</p>
            <br>
            <c:forEach items="${item.getTagsArray()}" var="tag">
                ${tag}
            </c:forEach>
            浏览量:${item.readCount}
        </li>
    </c:forEach>
</ul>
<a href="./blog/myarticles.action?index=1">首页</a>
<c:forEach begin="1" end="${requestScope.pageModel.getAllPageCount()}" step="1" var="i">
    <a href="./blog/myarticles.action?index=${i}">${i}</a>
</c:forEach>
<a href="./blog/myarticles.action?index=${requestScope.pageModel.getAllPageCount()}">末页</a>
</body>
</html>
