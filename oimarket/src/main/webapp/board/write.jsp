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
	<%
		Object o = request.getSession().getAttribute("login");
		if(o==null){
			response.sendRedirect("/oimarket/member/login.jsp");
		}
	%>
	<%@include file="/footer.jsp" %>
	<div class="wrap">
		<div class="container">
			<img onclick="mainPage()" class="logo" style="width:100px; height:100px; margin: 0 auto;" src="/oimarket/img/아이콘.png">
			<form class="writeForm">
				카테고리 : <select name="bcno">
							<option value="1"> 커뮤니티 </option>
							<option value="2"> QnA </option>
							<option value="3"> 노하우 </option>
						</select>
				제목 : <input name="btitle" type="text">
				<textarea id="summernote" name="bcontent"></textarea>
				<div class="writefile">첨부파일 : <input name="bfile" type="file"></div>
			</form>
			<div class="writeboard"><button class="writeboardbtn" onclick="bwrite()">글쓰기</button></div>
		</div>
 	</div>
 	
 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
 	
	<script src="/oimarket/js/board/write.js" type="text/javascript"></script>
</body> 
</html>