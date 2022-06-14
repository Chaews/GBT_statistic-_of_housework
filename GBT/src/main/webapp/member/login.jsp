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
<%	if((String)session.getAttribute("loginid")!=null){
		response.sendRedirect("/GBT/home.jsp");
	}%>
<div class="col-lg-4 offset-lg-4 px-3 mainframe">	

<br><br>
<h1 class="text-center">로 그 인</h1>

	<br><br><br>

	<div class="col-lg-10 offset-lg-1 px-1">		
		<div class="row">
			<div class="col-lg-3"></div>
		    <div class="col-lg-6"> 		
		        	<input class="form-control form-control-lg textbox border-primary" type="text" name="id" id="loginid" placeholder="아이디"><br>
					<input class="form-control form-control-lg textbox border-primary" type="password" name="password" id="password" placeholder="비밀번호"><br>
		   			<div class="d-flex justify-content-end"><input class="btn btn-lg btnbox" onclick="login()" type="button" value="GBT !"></div>
		    </div>
		    <div class="col-lg-3"></div>
		</div>
		
		<br><br>	
		
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6 d-flex justify-content-center">
				<h5><a href="signup.jsp">회원가입</a></h5>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
<br><br>
<div class="d-flex justify-content-center"><a href="findid.jsp">아이디 찾기</a><span style="margin: 0 10px"> | </span><a href="findpassword.jsp">비밀번호 찾기</a></div>

<br><br>

</div>


<br><br><br><br><br><br><br><br>
<script src="/GBT/js/member.js"></script>
</body>
</html>