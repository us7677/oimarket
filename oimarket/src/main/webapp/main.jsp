<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- css 부트스트립 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/oimarket/css/main.css" rel="stylesheet"> 
	
</head>
<body>
		<div class="wrap main">
			<div class="topcontent">
				<select class="form-select " aria-label="Default select example" onchange="category(this.value)">
					<option value="0" >전체보기</option>
					<option value="1">생활가전</option>
					<option value="2">의류</option>
					<option value="3">뷰티미용</option>
					<option value="4">가공식품</option>
					<option value="5">식물</option>
					
				</select>
				<div >
					<input class="keyword" type="text">
					<img width="25px" src="/oimarket/img/favicon.ico" onclick="search()" ></img>
					
				</div>
			</div>
			<div class="contentbox">
				
				
			</div>
		
		</div>
	<%@include file="/footer.jsp" %>
	
<!-- 카카오지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=302a2d5c43d29cb22e0d5d10d6434665"></script>
<!-- 부트스트랩-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<!-- 사용자 -->
<script src="/oimarket/js/main.js" type="text/javascript"></script>
</body>
</html>









