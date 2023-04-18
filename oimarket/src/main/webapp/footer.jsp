<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> oi Market</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 개인 css -->
	<link href="/oimarket/css/footer.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	

</head>
<body>

   <div class="alarm">
      오이마켓! 메시지가 도착했어요.
   </div>

	<div class="wrap">
		<div class="footerbox">
			<div class="menubox">
				<div onclick="homeGo()"><i class="fas fa-home"></i> </div>
				<div class="menuname">홈</div>
			</div>
			<div class="menubox">
				<div onclick="boardGo()"><i class="fas fa-newspaper"></i>   </div>
				<div class="menuname">게시판</div>
			</div>
			<div onclick="productrigster()" class="menubox">
				<div><i class="fas fa-edit"></i>   </div>
				<div class="menuname">물품등록</div>
			</div>
			<div class="menubox" onclick="chattingListGo()">
				<div ><i class="fas fa-comments"></i>   </div>
				<div class="menuname">쪽지함</div>
			</div>
			<div  onclick="mypageGo()" class="menubox">
				<div><i class="fas fa-carrot"></i>   </div>
				<div class="menuname"> 나의정보 </div>
			</div>
			
		</div>
	</div>
	
	



	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
	<script src="/oimarket/js/footer.js" type="text/javascript"></script>
</body>
</html>