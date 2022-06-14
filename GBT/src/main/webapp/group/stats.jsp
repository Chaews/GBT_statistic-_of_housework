<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가사분담통계 GBT</title>
</head>
<body class="bg">
<%@include file="../header.jsp" %>
<%
	if(loginid==null){
		response.sendRedirect("/GBT/index.jsp");
	}
%>
<br>
<div class="col-lg-6 offset-lg-3 px-3 mainframe">
<br><br>

<h1 class="text-center">통계 보기</h1>

<br><br>
	<div class="row statsvp">
		<div class="col-lg-2 offset-lg-3 text-center">	
		<button type="button" class="form-control btnboxst" id="daybtn">일간 통계</button>
		</div>
		<div class="col-lg-2 text-center">	
		<button type="button" class="form-control btnboxst" id="weekbtn">주간 통계</button>
		</div>
		<div class="col-lg-2 text-center">	
		<button type="button" class="form-control btnboxst" id="monthbtn">월간 통계</button>
		</div>
	</div>
	
	<br><br><br>
	
		<div class="row">
		<div class="col-12">
		<div id="statsbar"></div>
		</div>
		</div>
		
		<br><br><br><br>
		
<div class="row my-5">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 d-flex justify-content-center"><a href="../home.jsp">뒤로가기</a></div>
</div>

<br><br>
</div><br><br><br><br><br><br>
	<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
	<script src="../js/statsbar.js"></script>
</body>
</html>