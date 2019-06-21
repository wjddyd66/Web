<%@page import="pack.business.DataDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="processDao" class="pack.business.ProcessDao"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
상품자료(MyBatis) <p>
<a href="ins.html">상품추가</a><br>
<%--  1.전통적
<table border="1">
	<tr>
		<th>코드</th><th>품명</th><th>수량</th><th>단가</th>
	</tr>
	<%
	ArrayList<DataDto> slist = (ArrayList)processDao.selectDataAll();
	for(DataDto dto:slist){
	%>
	<tr>
		<td><%=dto.getCode()%></td>
		<td><%=dto.getSang()%></td>
		<td><%=dto.getSu()%></td>
		<td><%=dto.getDan()%></td>
	</tr>	
	<%
	}
	%>
</table>
--%>
<%ArrayList<DataDto> slist = (ArrayList)processDao.selectDataAll(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table border="1">
	<tr>
		<th>코드</th><th>품명</th><th>수량</th><th>단가</th>
	</tr>
	<c:forEach var="s" items="<%=slist %>">
	<tr>
		<td><a href="delete.jsp?code=${s.code}">${s.code}</a></td>
		<td><a href="update.jsp?code=${s.code}">${s.sang}</a></td>
		<td>${s.su}</td>
		<td>${s.dan}</td>
	</tr>	
	</c:forEach>
</table>
<b style="color: red">코드를 클릭하면 삭제, 품명을 클릭하면 수정 작업</b>
</body>
</html>