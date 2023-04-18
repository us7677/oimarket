<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/oimarket/css/main.css" rel="stylesheet"> 
<link href="/oimarket/css/product/viewProduct.css" rel="stylesheet"> 
<!-- css 부트스트립 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
	<%@include file="/footer.jsp" %>
	<%int pno =Integer.parseInt(request.getParameter("pno")) ;%>

			<input class="pno" type="hidden" value="<%=pno%>">
			<div class="wrap main" id="viewproduct" style="padding: 0px 0px 200px 0px">		
					
	<div class="chatdiv">
		
	</div>
				
				
				<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
				  <div class="carousel-indicators">
				  	<!-- 밑에 버튼 들어가는 구역-->
				  </div>
				 
				  <div class="carousel-inner">
				  	<!-- 사진들어가는 구역 -->
				  </div>
				  <div>
					  <!-- 왼쪽버튼 -->
					  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Previous</span>
					  </button> 
					  <!-- 오른쪽버튼 -->
					  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
					    <span class="carousel-control-next-icon" aria-hidden="true"></span>
					    <span class="visually-hidden">Next</span>
					  </button>
				  </div>
				</div>
				
				<div class="productbox">
				
				</div>
				<div class="btns"></div>
							
				<div class="maptitle"> 거래 희망 장소 </div>
				<div id="map" style="width:100%;height:250px;"></div>
				
			</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=302a2d5c43d29cb22e0d5d10d6434665"></script>
<!-- 부트스트랩-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<!-- 사용자 js -->
<script src="/oimarket/js/product/viewProduct.js" type="text/javascript"></script>

</body>
</html>