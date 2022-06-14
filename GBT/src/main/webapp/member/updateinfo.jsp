<%@page import="Dao.MemberDao"%>
<%@page import="Dto.Member"%>
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
	<h1 class="text-center">회원정보수정</h1>
	
	<br><br><br>
	
	<div class="col-lg-10 offset-lg-1 px-1">	
	<%
		if(loginid==null){
			response.sendRedirect("/GBT/index.jsp");
		}
		int mnum = (Integer)session.getAttribute("loginmnum");
		Member member = MemberDao.getmemberdao().updateinfo(mnum);	
	%>
		<div class="row">
			<div class="col-lg-3"></div>
				<div class="col-lg-6">
					<form class="vstack gap-3">
						<input class="form-control form-control-lg textbox border-primary" type="text" name="mname" id="mname" value="<%=member.getMname()%>">
						<span id="namecheck"></span>
						<input class="form-control form-control-lg textbox border-primary" type="text" name="mid" id="mid" disabled="disabled" placeholder="아이디는 수정 불가능합니다.">
						<span></span>
						<input class="form-control form-control-lg textbox border-primary" type="password" name="mpassword" id="mpassword" value="<%=member.getMpassword()%>">
						<span id="passwordcheck"></span>
						<input class="form-control form-control-lg textbox border-primary" type="text" name="mphone" id="mphone" value="<%=member.getMphone()%>">
						<span id="phonecheck"></span>
						<div class="d-flex justify-content-end"><input type="submit" class="btn btn-lg btn-secondary btnbox" value="수정하기" onclick="update()"></div>
					</form>
				</div>
			<div class="col-lg-3"></div>
		</div>	
		<div class="row my-5">
			<div class="col-lg-3"></div>
			<div class="col-lg-6 d-flex justify-content-center"><a href="#" onclick="deleteac()" >회원탈퇴</a><span style="margin: 0 10px"> | </span><a href="../home.jsp">뒤로가기</a></div>
			</div>
	</div>
	
<br><br><br><br>

</div>
	
<br><br><br><br><br><br><br><br>
<script src="../js/updateinfo.js"></script>	
</body>
</html>