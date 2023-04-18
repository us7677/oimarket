<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/oimarket/css/member/mypage.css" rel="stylesheet">
	<link href="/oimarket/css/main.css" rel="stylesheet">
</head>
<body>

	<%@include file="/footer.jsp" %>
	
	
	
	<div class="wrap main asd">
		<div class="">
			<div class="mypageLogo" >
				<img class="mypage_logo" onclick="logo()" alt="" src="/oimarket/img/아이콘.png"> 
			</div>
			
			<!-- 개인정보 호출 -->
			<div class="container mypageInfobox"></div>
			<a class="logoutbtn" href="/oimarket/member/logout.jsp">로그아웃</a>
			
			<div class="mypage_mainbox">
				<div class="mypage_Box_name">
					<h3> < 등록한 제품 > </h3>	
				</div>
						
				<!-- 1. 등록제품 출력 창  -->	
				<div  class="mypageRegistbox"> </div>
			
			</div>
			
			
			<div class="mypage_mainbox">
				<div class="mypage_Box_name">
					<h3> < 판매 중인 제품 > </h3>	
				</div>
						
				<!-- 2. 판매제품 출력 창  -->	
				<div  class="mypageSellbox"> </div>


			</div>
			
			
			<div class="mypage_mainbox">
				<div class="mypage_Box_name">
					<h3> < 구매한 제품 > </h3>	
				</div>
						
				<!-- 3. 구매한 제품 출력 창  -->	
				<div  class="mypageBuybox"> </div>

			
			</div>
			
			
			
			<div class="mypage_mainbox">
				<div class="mypage_Box_name">
					<h3> < 찜한 제품 > </h3>	
				</div>
						
				<!-- 4. 찜한 제품 출력 창  -->	
				<div  class="mypageLikebox"> </div>

			
			</div>
					
				
			<div class="mypage_mainbox">
				<div class="mypage_Box_name">
					<h3> < 게시판 등록글 > </h3>	
				</div>
						
				<!-- 5. 게시판 출력 창  -->	
				
				<table border="1" class="mypageBoardbox">
	
				</table>
			
			</div>
			
			<!-- 6. 오늘 방문자 수 (파일 처리) -->
			<div>
				오늘 방문자 수 : <span class="viewCount"></span>
			</div>
			
			<!-- 7. 총 물품 개수 출력 -->
			<div>
				<span class="ProductCount"></span> <br>
			</div>
			
			<!-- 8. 4월 물품 총 거래 가격 -->
			<div>
				<span class="ProductPriceCount"></span>
			</div>
			
			<!-- 9. 카테고리별 물품 거래 개수 (오늘 기준) -->
			
			<!-- 			
			<div>
				<div> 카테고리별 물품 거래 개수 (당일 기준)</div>
				<table border="1" class="ProductCategoryCount"></table>
			</div> 
			-->

			<!-- 9. 카테고리별 물품 거래 개수(당일 기준) 차트화 -->
			<div>
				  <canvas id="myChart"></canvas>
			</div>
			
			
		</div>

		
	
	</div>	


	<script src="/oimarket/js/member/mypage.js" type="text/javascript"></script>
	
	
	<!-- 차트 js -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</body>
</html>