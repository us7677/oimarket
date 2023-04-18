<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<link href="/oimarket/css/main.css" rel="stylesheet">
	<link href="/oimarket/css/board/write.css" rel="stylesheet">
</head>
<body>

	<%@include file="/footer.jsp" %>
	<div class="wrap">
	<%
		Object o = request.getSession().getAttribute("login");
		if(o==null){
			response.sendRedirect("/oimarket/member/login.jsp");
		}
		String bno = request.getParameter("bno");
	%>
	<input type="hidden" class="bno" value="<%=bno %>">
	<div class="container">
		<h3 class="updatetitle"> 글수정페이지 </h3>
		<form class="updateForm">
			<div>
			카테고리 : <select name="bcno" class="bcno">
						<option value="1">커뮤니티</option>
						<option value="2">QnA</option>
						<option value="3">노하우</option>
					</select>
			</div>
			<div>
				글 제목 : <input name="btitle" class="btitle" type="text">
			</div>
			<div>
				글 내용 : <textarea id="summernote" name="bcontent" class="bcontent"></textarea>
			</div>
			<div class="bfilebox">
			
			</div>
			<div class="writeboard">
				<button class="writeboardbtn" onclick="bupdate()" type="button">수정</button>
				<button class="writeboardbtn" onclick="bupdatecancel()" type="button">취소</button>
			</div>
		</form>
	</div>
	</div>
	
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>

	<script src="/oimarket/js/board/update.js"></script>
</body>
</html>