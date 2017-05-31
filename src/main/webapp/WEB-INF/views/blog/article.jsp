<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-15
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>文章</title>
</head>
<body>
<h2 align="center">${requestScope.article.title}<small>--${requestScope.article.type}</small></h2>
标签:
    <c:forEach items="${requestScope.article.getTagsArray()}" var="tag">
        ${tag}
    </c:forEach>
<br>
作者：${requestScope.article.publisher}-${requestScope.article.original}<br>
日期：${requestScope.article.date}    阅读量:${requestScope.article.readCount}<br>
<p>${requestScope.article.content}</p>

<h3>评论</h3>
<c:forEach items="${requestScope.comments}" var="comment">
    ${comment.commenter}:<br>
    ${comment.date}<br>
    <p style="color: #9b4449">${comment.content}</p>
</c:forEach>

评论文章：
<form>
    <textarea cols="40" rows="10" id="content" name="content"></textarea>
    <button type="button" id="commit">提交</button>
</form>
<script src="resources/js/jquery-3.1.0.min.js"></script>
<script>
    $("document").ready(function(){
        $("#commit").click(function () {
            var content=$("#content").val();
            $.ajax({
                type:"POST",
                url:"blog/addComment",
                data:{
                    "content":content,
                    "aid":${requestScope.aid}
                },
                dataType:"text",
                success:function (data) {
                    var result=JSON.parse(data)
                    if(result=="success"){
                        location.reload()
                    }else{
                        location.href="./user/toLogin.action"
                    }
                },
                error:function () {
                    alert("请求错误")
                }
            })
        })
    })
</script>
</body>
</html>
