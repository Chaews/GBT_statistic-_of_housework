<%@page import="Dao.MemberDao"%>
<%@page import="Dto.Groupmember"%>
<%@page import="Dto.Group"%>
<%@page import="Dto.Member"%>
<%@page import="java.util.ArrayList"%>
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
	if(loginid==null){
		response.sendRedirect("/GBT/index.jsp");
	}
	request.setCharacterEncoding("UTF-8");
	String searchresult = (String)request.getAttribute("searchresult");
	
%>

<div class="col-lg-6 offset-lg-3 px-3 mainframe">

<br><br>

<h1 class="text-center">그 룹 관 리</h1>

<br><br>
<div class="row">

<div class="col-lg-4 offset-lg-2" id="makegrouparea">
<%
	ArrayList<Group> grouplist = GroupDao.getgroupdao().grouplist(loginmnum); 
	if(grouplist==null){
%>
<p>그룹만들기 버튼을 눌러 그룹을 만들어보세요</p>
<%}else{ %>
	<select name="groupname" id="groupname" class="form-select">
	<option selected value="groupnameswitch">그룹을 선택해주세요</option>
	<%for(Group temp : grouplist){ %>
	<option value="<%=temp.getGnum()%>"><%=temp.getGname()%></option>
	<%} %>
	</select>
	
<%}%>

<div id="groupmembertext"></div>
<div id="groupmemberbtn"></div>

<button class='btn btnbox my-3' type="button" onclick="mgtext(<%=loginmnum%>)" id="makegroupbtn">그룹만들기</button>

<div id="makegrouptext"></div>
</div>

<div class="col-lg-4" id="searcharea" style="display: none;">
	<div class="row">
    	<input class="form-control" type="text" name="search" id="searchid" placeholder="전화번호를 입력하세요 (010-****-****)">
	</div>
	<div class="row">
	<div class="col-lg-6 offset-lg-3" id="searchresult"></div>
	</div>

	<br><br>

	<div class="row">
		<div class="col-lg-6">
			<input type="button" class="btn btnboxconfirm" value="그룹요청!" onclick="sendask()">
		</div>
    </div>    
</div>


</div>




<div class="col-lg-6 offset-lg-3">
<br><br><br><br>
<%if(askedlist!=null) {%>
<form>
	<h5>내가 받은 그룹요청</h5><hr>
	<%for(Asked temp : askedlist){ %>
	<div class="row">
			<div class="col-lg-8">
			요청한 회원 : <%=temp.getFromid()%><br>그룹명 : <%=temp.getGname()%>
			</div>
			<input type="hidden" value="<%=temp.getAgnum()%>" id="agnum" name="agnum">
			<input type="hidden" value="<%=temp.getFromid()%>" id="fromid" name="fromid">
			<input type="hidden" value="<%=temp.getFrommnum()%>" id="frommnum" name="frommnum">
			<input type="hidden" value="<%=temp.getGname()%>" id="gname" name="gname">
			<input type="hidden" value="<%=temp.getGnum()%>" id=gnum name="gnum">
			<input type="hidden" value="<%=temp.getTomnum()%>" id="tomnum" name="tomnum">	
			<div class="col-lg-4 text-end">
				<button type="button" class="btn btnboxconfirm" onclick="accept()">수락</button>
				<button type="button" class="btn btnboxcancel" onclick="reject()">거절</button>
			</div>
	</div>
	<hr>
</form>
<%}} %>
</div><br><br>

<div class="d-flex justify-content-center my-5"><a href="../home.jsp">뒤로가기</a></div>
</div><br><br><br><br><br><br>
<script src="../js/group.js"></script>
</body>
</html>