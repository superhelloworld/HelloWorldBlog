<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-12
  Time: 下午9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value="/home"/>">To IndexPage</a>
<a href="<c:url value="/blog/addArticleType.action"/> ">添加文章类型</a>
<a href="<c:url value="/blog/addArticle.action"/> ">添加文章</a><br>
<a href="<c:url value="/blog/articles.action?type=Java&index=1"/> ">文章列表</a>
<a href="<c:url value="/blog/myarticles.action?index=1"/> ">我的文章列表</a>
</body>
</html>
