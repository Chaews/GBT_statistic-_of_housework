<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가사분담통계 GBT</title>
</head>
<body class="bg">
<%@include file="../header.jsp"%>
<%
	if((String)session.getAttribute("loginid")!=null){
		response.sendRedirect("home.jsp");
	}
%>
<div class="col-lg-4 offset-lg-4 px-3 mainframe">	

<br><br>
<h1 class="text-center">회 원 가 입</h1>

	<br><br><br>
	
	<div class="col-lg-10 offset-lg-1 px-1">
		<div class="row">
			<div class="col-lg-3"></div>
				<div class="col-lg-6">
					<form action="../signup" id="signupform" class="vstack gap-3" method="post">
						<input class="form-control form-control-lg textbox border-primary" type="text" id="mid" name="mid" placeholder="아이디 (4자리 이상)">
		        		<span id="idcheck"></span>
						<input class="form-control form-control-lg textbox border-primary" type="text" id="mname" name="mname" placeholder="이름">
						<span id="namecheck"></span>					
						<input class="form-control form-control-lg textbox border-primary" type="password" id="mpassword" name="mpassword" placeholder="비밀번호 (4자리 이상)">
		        		<span></span>
						<input class="form-control form-control-lg textbox border-primary" type="password" id="mpasswordcheck" name="mpasswordcheck" placeholder="비밀번호 재입력">
		        		<span id="passwordcheck"></span>
						<input class="form-control form-control-lg textbox border-primary" type="text" id="mphone" name="mphone" placeholder="전화번호 01*-****-****">
		        		<span id="phonecheck"></span>
						<div class="row"><div class="d-flex justify-content-end"><button type="button" class="btn btn-lg btnbox" onclick="signup()">가입하기</button></div></div>	
					</form>
				</div>
			<div class="col-lg-3"></div>
		</div>
	</div>

<br><br>
	
<div class="d-flex justify-content-center"><a href="login.jsp">뒤로가기</a></div>

<br><br>

</div>

<br><br><br><br><br><br><br><br>

<script src="../js/signup.js"></script>
</body>
</html>