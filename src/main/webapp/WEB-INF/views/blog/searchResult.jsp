<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-17
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>搜索结果</title>
</head>
<body>
<h1 align="center">搜索结果</h1>
<label for="order">排序：</label>
<select name="order" id="order" onchange="change(this)">
    <option value="0">最新</option>
    <option value="1">热门</option>
</select>
<ul>
    <c:forEach items="${requestScope.result.items}" var="item">
        <li>
            <img width="80px" height="80px" src="${item.getUserIconUrl()}">
                ${item.publisher}<br>
            <a href="${item.getArticleLink()}">${item.title}</a>
                ${item.date}
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
<button onclick="turnpage(1)">首页</button>
<c:forEach begin="1" end="${requestScope.result.getAllPageCount()}" step="1" var="i">
    <button onclick="turnpage(${i})">${i}</button>
</c:forEach>
<button onclick="turnpage(${requestScope.result.getAllPageCount()})">末页</button>
<script>
    window.onload=function () {
        var orderNumber=${requestScope.result.order};
        var s=document.getElementById("order");
        s[orderNumber].selected=true;
    }
    function change(s) {
        var item=s.selectedIndex;
        var url="./blog/search?type=${requestScope.result.pageType}&index=${requestScope.result.index}&keyWord=${requestScope.result.keyWord}&order="+item;
        window.location.href=encodeURI(url);
    }
    function turnpage(index) {
        var url="./blog/search?type=${requestScope.result.pageType}"+
            "&index="+index+"&keyWord=${requestScope.result.keyWord}&order="+
            "${requestScope.result.order}";
            //alert(encodeURI(url));
        window.location.href=encodeURI(url);
    }
</script>
</body>
</html>
