<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/oimarket/css/index.css" rel="stylesheet">
	<link href="/oimarket/css/member/login.css" rel="stylesheet">

</head>
<body>

	<div class="indexwrap">
		<div class="mainbox">
			<div class="id_pwd_loc">
				<a href="/oimarket/index.jsp"><img onclick="logo()" class="logo" alt="" src="/oimarket/img/오이마켓.png"></a>
				<div class="idinput">
					<div> <input class="mid" type="text" placeholder="아이디"> </div>
				</div>	
				
				<div class="pwdinput">
					<div> <input class="mpwd"  type="password" placeholder="패스워드"> </div>
				</div>			
			</div>			 
				<div class="login_btn">
					<button class="login_btn1" onclick="login()" type="button"> 로그인 </button>
				</div>
				
					<div class="idpwpage">
						<div><a href="/oimarket/member/findid.jsp"> 아이디 찾기 </a></div>
						<div><a href="/oimarket/member/findpwd.jsp"> 비밀번호 찾기 </a></div>
					</div>
		</div>			
				
			
	</div>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>	
	<script src="/oimarket/js/member/login.js" type="text/javascript"></script>

</head>

</html>