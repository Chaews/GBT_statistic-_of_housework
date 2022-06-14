<%@page import="Dto.Group"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>가사분담통계 GBT</title>
</head>
<body class="bg">
<%@include file="header.jsp" %>
<% 	if(loginid==null){
		response.sendRedirect("index.jsp");
	}
	ArrayList<Group> grouplist = GroupDao.getgroupdao().grouplist(loginmnum); 
	
%>

<div class="col-lg-6 offset-lg-3 px-3 mainframe">
	<div class="row">
		<h1 class="homeh1 text-center"><%=(String)session.getAttribute("loginid")%>님 반갑습니다</h1>
	</div>
<div class="row my-5">

<div class="col-lg-6">
	<div class="col-lg-6 offset-lg-3">
		<select id="homegroupname" class="form-select" >
		<%
			if(grouplist==null){%>
				<option selected value="groupnameswitch">그룹이 없습니다.</option>
		<%}else{ %>
			<option selected value="groupnameswitch">그룹을 선택해주세요</option>
			<%for(Group temp : grouplist){ %>
			<option value="<%=temp.getGnum()%>"><%=temp.getGname()%></option>
			<%} %>
		<%} %>	
		</select>
		
		<div id="checkarea" class="my-2">
		</div>
	</div>	
</div>

<div class="col-lg-6">
	
		<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-4 homebox">
			<div class="col-lg-6 offset-lg-3"><img id="homeimg" src="img/group.png"></div>
			<div class="homemenutext">그룹을 만들거나 소속된 그룹이 있으면 그룹원을 초대할 수 있습니다. 그룹을 만들어보세요!</div>
			<button class="form-control btnbox" type="button" onclick="movetomg()">그룹 관리</button>
		</div>
	
		<div class="col-lg-1"></div>
		<div class="col-lg-4 homebox">
			<div class="col-lg-6 offset-lg-3"><img id="homeimg" src="img/graph.png"></div>
			<div class="homemenutext">소속된 그룹원들간 통계내보고 싶은 일들을 자유롭게 추가, 삭제하고 점수를 설정하는 메뉴입니다.</div>
			<button class="form-control btnbox" type="button" onclick="movetoaddhw()">가사일 등록</button>
		</div>
		</div>

	
		<div class="row">
		<div class="col-lg-1"></div>
		<div class="col-lg-4 homebox">
			<div class="col-lg-6 offset-lg-3"><img id="homeimg" src="img/statistics.png"></div>
			<div class="homemenutext">소속된 그룹원들이 했던 일들을 등록된 점수에 기반하여 통계로 보여주는 메뉴입니다.</div>
			<button class="form-control btnbox" type="button" onclick="movetostat()">통계보기</button>
		</div>
		<div class="col-lg-1"></div>
		<div class="col-lg-4 homebox">
			<div class="text-center"><a href="https://www.facebook.com"><i class="fab fa-facebook fa-3x"></i></a>
			<a href="https://www.twitter.com"><i class="fab fa-twitter fa-3x"></i></a>
			<a href="https://www.google.com"><i class="fab fa-google fa-3x"></i></a>
			<a href="https://www.instagram.com"><i class="fab fa-instagram fa-3x"></i></a>
			<a href="https://www.github.com"><i class="fab fa-github fa-3x"></i></a>
			</div>
		</div>
		</div>
</div>


</div>


</div>




<br><br><br><br><br><br>
<script src="js/home.js"></script>
</body>
</html>