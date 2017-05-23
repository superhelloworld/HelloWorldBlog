<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xdzy
  Date: 17-5-13
  Time: 下午4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>博客列表-${requestScope.pageModel.pageName}</title>
</head>
<body>
<div style="border: solid 1px #000;width: 400px">
    <h3>分类浏览文章</h3>
    <a href="./blog/articles.action?type=all&index=1">全部</a>
    <c:forEach items="${requestScope.typeList}" var="at">
        <a href="<c:url value="${at.getUrl()}"/>">${at.typeName}</a>
    </c:forEach>
</div>
<label for="order">排序：</label>
<select name="order" id="order" onchange="change(this)">
    <option value="0">最新</option>
    <option value="1">热门</option>
</select>
<ul>
    <c:forEach items="${requestScope.pageModel.items}" var="item">
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
<a href="./blog/articles.action?type=${requestScope.pageModel.pageType}&index=1&order=${requestScope.pageModel.order}">首页</a>
<c:forEach begin="1" end="${requestScope.pageModel.getAllPageCount()}" step="1" var="i">
    <a href="./blog/articles.action?type=${requestScope.pageModel.pageType}&index=${i}&order=${requestScope.pageModel.order}">${i}</a>
</c:forEach>
<a href="./blog/articles.action?type=${requestScope.pageModel.pageType}&index=${requestScope.pageModel.getAllPageCount()}&order=${requestScope.pageModel.order}">末页</a>

</body>
<script>
    window.onload=function () {
        var orderNumber=${requestScope.pageModel.order};
        var s=document.getElementById("order");
        s[orderNumber].selected=true;
    }
    function change(s) {
        var item=s.selectedIndex;
        window.location.href="./blog/articles.action?type=${requestScope.pageModel.pageType}&index=${requestScope.pageModel.index}&order="+item;
    }
</script>
</html>
