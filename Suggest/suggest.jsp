<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Jsp������ DB�� �����Ͽ� Query���� ���������� ����� ��ȯ�Ѵ�. -->
<!-- Query: select jikwon_name from jikwon where jikwon_name like ? -->
<!-- ?�� suggest.js���� ���� keyword�̴�. -->
<%
	request.setCharacterEncoding("utf-8");
	String keyword = request.getParameter("keyword");

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String result = "";

	try {
		Class.forName("org.mariadb.jdbc.Driver");
	} catch (Exception e) {
		System.out.println("Connection Error: " + e);
		return;
	}

	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123");
		pstmt = conn.prepareStatement("select jikwon_name from jikwon where jikwon_name like ?");
		pstmt.setString(1, keyword + "%");
		rs = pstmt.executeQuery();

		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
			//System.out.println(rs.getString(1));
		}

		out.print(list.size());
		out.print("|");
		for (int i = 0; i < list.size(); i++) {
			String data = list.get(i);
			out.print(data);
			if (i < list.size() - 1) {
				out.print(",");
			}
		}
		
		rs.close();
		pstmt.close();
		conn.close();
	} catch (Exception e) {
		System.out.println("Process Error: " + e);
		return;
	}
%>