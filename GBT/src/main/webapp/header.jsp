<%@page import="Dao.GroupDao"%>
<%@page import="Dto.Asked"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>가사분담통계 GBT</title>
	<!-- 부트스트랩 css 포함 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- 사용자 정의한 css 포함 -->
	<link href="/GBT/css/main.css" rel="stylesheet">
	<link href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" rel="stylesheet" >
</head>
<body>
	<div class="container-lg fixed-top headerback">	
	<div class="py-3">
		 <div class="row">
		 	<div class="col-lg-2 logotext"><a href="/GBT/home.jsp">GBT</a></div>
			<div class="col-lg-6 offset-lg-4 d-flex justify-content-end"> <!-- 헤더 메뉴 -->
				<% 	
					request.setCharacterEncoding("UTF-8");
					int loginmnum = 0;
					if(session.getAttribute("loginmnum")!=null){
						int loginmnum2 = (Integer)session.getAttribute("loginmnum");
						loginmnum = loginmnum2;
					}
					String loginid = (String)session.getAttribute("loginid");
					ArrayList<Asked> askedlist = GroupDao.getgroupdao().asked(loginmnum);
				%>
				<ul class="nav">
				<%if(loginid==null){%>
					<li class="tophead"><a href="/GBT/member/login.jsp">로그인</a></li>
					<li class="tophead"><a href="/GBT/member/signup.jsp">회원가입</a></li>
				<%}else{
					if(askedlist!=null){ %>
					<li class="tophead position-relative"><a href="/GBT/group/managegroup.jsp">그룹요청이 있어요 <span style="font-size: 10px;" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					<%=askedlist.size()%><span class="visually-hidden">unread messages</span></span>
					</a></li>
					<%} %>					
					<li class="tophead"><a href="/GBT/member/updateinfo.jsp">회원정보수정</a></li>
					<li class="tophead"><a href="/GBT/member/logout.jsp">로그아웃</a> </li>	
				<%} %>						
				</ul>	
			</div>
		</div>
	</div>
	</div>
		<!-- jQuery 최신 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 js포함 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
</body>
</html>