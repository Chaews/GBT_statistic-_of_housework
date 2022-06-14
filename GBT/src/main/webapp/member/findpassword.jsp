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

<div class="col-lg-4 offset-lg-4 px-3 mainframe">	

<br><br>
<h1 class="text-center">비 밀 번 호 찾 기</h1>

	<br><br><br>
	
	<div class="col-lg-10 offset-lg-1 px-1">
		<div class="row">
			<div class="col-lg-3"></div>
		    <div class="col-lg-6">
			<form>
				<input class="form-control form-control-lg textbox border-primary" type="text" id="name" name="name" placeholder="이름"><br>
				<input class="form-control form-control-lg textbox border-primary" type="text" id="mid" name="id" placeholder="아이디"><br>
				<input class="form-control form-control-lg textbox border-primary" type="text" id="phone" name="phone" placeholder="전화번호"><br>
				<div class="d-flex justify-content-end"><button type="button" class="btn btn-lg btn-secondary btnbox"  onclick="findpw()">비밀번호 찾기</button></div>
		     </form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>

<br><br>

<div class="d-flex justify-content-center"><a href="login.jsp">뒤로가기</a></div>

<br><br>

</div>

<script src="../js/find.js" type="text/javascript"></script>
</body>
</html>