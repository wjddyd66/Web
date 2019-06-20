<%@page import="java.util.Date"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	JSPL Test
	<br> **변수**
	<br>
	<c:set var="irum" value="홍길동" scope="page" />
	<c:out value="${irum}" />
	<br>

	<c:set var="bb" value="star" />
	<c:if test="${bb=='star' }">
		<c:out value="${bb }" />
	</c:if>

	<hr>
	<c:choose>
		<c:when test="${bb=='star'}">
			<c:out value="${bb }" />
		</c:when>
		<c:when test="${bb=='moon'}">
			<c:out value="${bb }" />
		</c:when>
		<c:otherwise>
			<c:out value="${bb }" />
		</c:otherwise>
	</c:choose>

	<c:forEach var="cc" begin="1" end="10" step="2">
		<c:out value="${cc*3}" />&nasp;
	</c:forEach>
	<hr>
	<c:forEach var="cc" begin="1" end="9" step="1">
		3*${cc}=${3*cc}<br>
	</c:forEach>
	<hr>
	<br>
	<%
		HashMap map = new HashMap();
		map.put("name", "신기해");
		map.put("today", new Date());
	%>
	<c:set var="arr" value="<%=new int[] { 1, 2, 3, 4, 5 }%>" />
	<c:set var="m" value="<%=map%>" />

	<c:forEach var="a" items="${arr}">
	${a}&nbsp;
	</c:forEach>

	<c:forEach var="a" items="${arr}" begin="2" end="4">
	${a}&nbsp;
	</c:forEach>

	<c:forEach var="a" items="${m}">
	${a.key}&nbsp;${a.value}
	</c:forEach>
	
	<hr>
	**문자열 분할**<br>
	<c:forTokens var="animal" items="horse,cat,dog,lion" delims="," begin="0" end="3">
	${animal}
	</c:forTokens>
	<hr>
	<c:forTokens var="animal" items="horse/cat/dog/lion" delims="/" begin="0" end="3" step="1" varStatus="index">
	${index.begin}:${index.end} 
	</c:forTokens>
</body>
</html>