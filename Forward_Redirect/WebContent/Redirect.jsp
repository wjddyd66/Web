<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Called File by Servlet
	<br>
	<%
		//redirect Method
		String name = request.getParameter("name");
		out.println("방식은 Redirect 자료는 " + name);
		
	%>
</body>
</html>