<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

<h2>2nd commit</h2>


<h3>Welcome ${user.email}</h3>
<h3><a href="<c:url value="/secure/login.jsp"/>">Login</a></h3>
<h3><a href="<c:url value="/pages/product.jsp"/>">Product</a></h3>
Hello!! 你好嘛!!
<p>

請簽到<br>
=========================================<br>
李松翰第一次簽到
楊英龍

<%-- <h3><a href="<c:url value="/secure/login.jsp"/>">Login</a></h3> --%>
<%-- <h3><a href="<c:url value="/pages/product.jsp"/>">Product</a></h3> --%>
<%-- <h3><a href="<c:url value="/pages/product.jsp"/>">Product</a></h3> --%>
廖書賢第一次簽
李松翰第二次簽到
李松翰第二次簽到
李松翰第二次簽到
李松翰第二次簽到
李松翰第二次簽到
李松翰第二次簽到
</body>
</html>
