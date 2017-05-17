<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  entity.User: yuyufeng
  Date: 2017/5/17
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/include/head.jsp" %>
</head>
<body>
<h1>用户列表<font color="red">${message}</font></h1>
<form action="/user/add.do" method="post">
    用户ID:<input type="text" name="userId">
    <br>用户名:<input type="text" name="userName">
    <br>备注信息:<input type="text" name="note">
    <br><input type="submit" value="提交">
</form>
<c:forEach items="${users}" var="u">
    <p>${u.userId} ${u.userName}</p>
</c:forEach>
</body>
</html>
