<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/oimarket/css/main.css" rel="stylesheet">
<link href="/oimarket/css/board/list.css" rel="stylesheet">
</head>
<body>

	<%
		Object o = request.getSession().getAttribute("login");
		if(o==null){
			response.sendRedirect("/oimarket/member/login.jsp");
		}
	%>
	<%@include file="/footer.jsp" %>	
	<div class="wrap main">
	 <%
	 	// 1. HTTP GET <a href="URL경로?변수명=값"> 전달된 매개변수 가져오기 
	 	String bcno = request.getParameter("bcno");
	 	// 2. 표현식을 이용한 input , div 등등 대입 
	 %>
	 <!-- cno 숨겨서 js에게 전달  -->
	<input type="hidden" class="bcno" value="<%=bcno%>">
		<div class="container">
			<div>
			<ul class="mainmenu">
				<li><button class="bcbtn all" onclick="setsearch()" type="button">전체보기</button></li>
				<li> <button class="bcbtn" type="button" onclick="bcbtn1()"> 커뮤니티 </button> </li>
				<li> <button class="bcbtn" type="button" onclick="bcbtn2()"> QnA </button> </li>
				<li> <button class="bcbtn" type="button" onclick="bcbtn3()"> 노하우 </button> </li>
			</ul>
			</div>
			<span class="searchbox">
				<select class="key item">
					<option value="btitle">제목</option>
					<option value="mid">작성자</option>
				</select>
				<span><input class="keyword item" type="text"></span>
				<span><button class="bcbtn item" onclick="getsearch()" type="button">검색</button></span>			
				<span><button class="bcbtn item" onclick="boardUpload()" type="button">글쓰기</button> </span>
			</span>
			
			<div class="boardTable table">

			</div>			
		</div>
	</div>
	
	<script src="/oimarket/js/board/list.js" type="text/javascript"></script>
</body>
</html>