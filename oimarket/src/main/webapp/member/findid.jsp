<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/oimarket/css/member/login.css" rel="stylesheet">
<link href="/oimarket/css/index.css" rel="stylesheet">

</head>
<body>
	
		
	<div class="indexwrap">
		<div class="mainbox">
			<div class="findid_mphone_loc">
				<a href="/oimarket/index.jsp"><img onclick="logo()" class="logo" alt="" src="/oimarket/img/오이마켓.png"></a>
		
				<div class="title">
					<input  type="text" name="mname"class="mname" placeholder="이름">	
				</div>			
				<div class="title">
					<input  type="text" name="mphone"class="mphone"  placeholder="휴대폰번호">				
				</div>
			</div>
			 	<div class="findid_btn">
					<button class="findid"onclick="openModal()"type="button" >아이디 찾기</button>
				</div>
		</div>				
	</div>
	
	<div class="modal_wrap">
		<div class="modal_box">
			
			<div class="modal_content">
				
				<div class="content">
				</div>
				
			</div>
			<div class="modal_btns">
				<a href="/oimarket/member/login.jsp"><button onclick="closeModal()"class="modal_check" 	type="button">확인</button></a>
				
			</div>
		</div>
	</div>
	
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/oimarket/js/member/login.js" type="text/javascript"></script>

</body>
</html>