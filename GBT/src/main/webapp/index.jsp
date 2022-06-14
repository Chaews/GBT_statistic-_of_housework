<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가사분담통계 GBT</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
	if((String)session.getAttribute("loginid")!=null){
		response.sendRedirect("home.jsp");
	}
	String result = request.getParameter("result");
	if(result!=null){
		if(result.equals("success")){ %>
			<script>
			alert("회원가입 되셨습니다!");
			</script>
		<% }else{%>
			<script>
			alert("가입실패");
			</script>
		<% }
	}%>
<%@include file="main.jsp" %>
</body>
</html>