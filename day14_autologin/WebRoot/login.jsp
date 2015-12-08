<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h1>用户登录</h1>
    <form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
    	用户名<input type="text" name="name"/>
    	密码<input type="password" name="password"/>
    	<input type="checkbox" name="autologin" value="true"/>30天自动登录<hr>
    	<input type="submit" value="登录"/>
    </form>
  </body>
</html>
