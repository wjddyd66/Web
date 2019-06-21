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
		request.setCharacterEncoding("utf-8");
		//redirect Method
		String[] data = (String[]) request.getAttribute("data");
		out.println("방식은"+data[0]+"자료는" + data[1]);
	%>
</body>
</html>