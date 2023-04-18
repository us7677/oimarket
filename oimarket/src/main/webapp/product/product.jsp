<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/oimarket/css/product/product.css" rel="stylesheet">
<link href="/oimarket/css/main.css" rel="stylesheet"> 

</head>
<body>
	
	<div class="wrap main">
			<form class="productform">
				<div>카테고리:
					<select name="pcno" class="pcno">
						<option value="1">생활가전</option>
						<option value="2">의류</option>
						<option value="3">뷰티미용</option>
						<option value="4">가공식품</option>
						<option value="5">식물</option>
					</select>
				</div>
				<div>제목:
					<input type="text"  name="ptitle" class="ptitle">
				</div>
				
				<div>내용:
					<input type="text" name="pcontent" class="pcontent" >
				</div>
				
				<div>가격:
					<input type="text" name="pprice" class="pprice">
				</div>
				장소:
				<div id="map" style="width:300px;height:300px;"></div>
				<div>
					<input type="file" name="pfiles" class="pfiles"  multiple="multiple" accept="image/*">
					
				</div> 
				<div>
					<button class="btn" onclick="productbtn()" type="button">물품 등록</button>
				
				
				</div>
			</form>	
		
	</div>
<!-- 카카오지도 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=302a2d5c43d29cb22e0d5d10d6434665"></script>

<!-- 사용자 -->
<script src="/oimarket/js/product/product.js" type="text/javascript"></script>


<%@include file="/footer.jsp" %>


</body>
</html>