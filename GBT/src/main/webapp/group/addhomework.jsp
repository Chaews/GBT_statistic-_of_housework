<%@page import="Dao.HomeworkDao"%>
<%@page import="Dto.Homework"%>
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
	int gnum = Integer.parseInt(request.getParameter("gnum"));
	ArrayList<Homework> homeworklist = HomeworkDao.gethomeworkdao().gethomework2(gnum);
%>

<div class="col-lg-6 offset-lg-3 px-3 mainframe">

<br><br>

<h1 class="text-center">가사일 등록</h1>

<br><br>

<div class="col-lg-6 offset-lg-3 mt-5" id="addhomeworkarea">
<%	if(homeworklist!=null){%>
	<table class="table">	
		<%for(Homework temp : homeworklist){%>
		<tr>
		<td width="55%"><input type="text" class="form-control" value="<%=temp.getHwork()%>" id="<%=temp.getHnum()%>" name="<%=temp.getHnum()%>"></td>
		<td width="45%" class="buttontable"><button class="btn btnbox" type="button" onclick="changename(<%=temp.getGnum()%>,<%=temp.getHnum()%>)">이름수정</button> <button class="btn btnbox" type="button" onclick="deletehw(<%=temp.getGnum()%>,<%=temp.getHnum()%>)">삭제</button></td>
		</tr>
	<%}%>
		</table>
	<%}%>

</div>
<div class="col-lg-6 offset-lg-3">
<p>집안일을 등록하세요</p>
<div class="row">
<div class="col-lg-7"><input class="form-control" id="addhomework" name="addhomework" type="text"></div>
<div class="col-lg-5 buttontable"><button class="btn btnbox" type="button" onclick="addhomeworkbtn()">추가</button></div>
</div>
</div>

<div class="row">		
<div class="col-lg-3"></div>	
<div class="col-lg-6 d-flex justify-content-end mt-5 mb-3 me-2">		
<button class="btn btnboxconfirm mx-3"  type="button" id="reghomework" name="reghomework" onclick="reghomeworkbtn()">다음</button>
</div>
</div>

<div class="row my-5">
	<div class="col-lg-3"></div>
	<div class="col-lg-6 d-flex justify-content-center"><a href="../home.jsp">뒤로가기</a></div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
<script src="../js/addhomework.js" ></script>
</div>
</body>
</html>