<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
	<h1>30天自动登录案例</h1>    
	<c:if test="${sessionScope.user==null }">
		游客    
		<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
	</c:if>
	<c:if test="${sessionScope.user!=null }">
		欢迎回来,   ${sessionScope.user.name }    
		<a href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a>
	</c:if>
  </body>
</html>
