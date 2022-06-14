<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="bg">
<%@include file="../header.jsp" %>
<%
	if(loginid==null){
		response.sendRedirect("/GBT/index.jsp");
	}
%>

<div class="col-lg-6 offset-lg-3 px-3 mainframe">

<br><br>

<h1 class="text-center">가사일 등록</h1>
<br><br>
	<div class="row">
	<div class="col-lg-2"></div>
	<div class="col-lg-8">
	<div id="regpie"></div>
	</div>
	</div>
	
	<div class="row">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 text-center">
	<div id="hwrange"></div>
	</div>
	<div class="row">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 d-flex justify-content-end mt-5 mb-3 me-2">	
	<button class="btn btnboxconfirm" type="button" onclick="saveratio()">이대로 저장</button></div>
	</div>
	</div>
	
<div class="row my-5">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 d-flex justify-content-center"><a href="addhomework.jsp?gnum=<%=Integer.parseInt(request.getParameter("gnum"))%>">뒤로가기</a></div>
</div>	


</div><br><br><br>
	<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
	<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
	<script src="../js/regpie.js"></script>

</body>
</html>