<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="/oimarket/css/index.css" rel="stylesheet">
	<link href="/oimarket/css/member/signup.css" rel="stylesheet">
	
</head>
<body>

	<div class="indexwrap">
		<img onclick="logo()" class="logo" alt="" src="/oimarket/img/오이마켓.png">
		<form class="signupForm">
			<div class="signtext">
				<h3>회원가입</h3>
				<div class="mtext">
					<h5>이름</h5>
					<input onkeyup="namecheck()" class="mname"  name="mname" type="text">
					<div class="checkconfirm" ></div>
				</div>
				<div class="mtext">
					<h5>아이디</h5>
					<input class="mid" name="mid" type="text">
					<button class="idcheck" onclick="idcheck()" type="button">중복확인</button>
					<div class="checkconfirm" ></div>
				</div>
				<div class="mtext">
					<h5>비밀번호</h5>
					<input onkeyup="pwdcheck()" class="mpwd" name="mpwd" type="password">
					<div class="checkconfirm"></div>
				</div>
				<div class="mtext">
					<h5>비밀번호확인</h5>
					<input onkeyup="repwdcheck()" class="repwdck" type="password">
					<div class="checkconfirm"></div>
				</div>
				<div class="mtext span">
					<div class="mresidence">
						<h5>사는지역</h5>
						<select name="mresidence">
							<option value="서울">서울</option>
							<option value="경기">경기</option>
							<option value="강원도">강원도</option>
							<option value="인천">인천</option>
							<option value="충청도">충청도</option>
							<option value="경상도">경상도</option>
							<option value="전라도">전라도</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="제주">제주</option>
							<option value="그외">그외</option>
						</select>
					</div>
					<div>
						<h5>성별</h5>
							<input onclick="getmmw(event)"  name="mmw" value="남" type="radio">남
							<input onclick="getmmw(event)"  name="mmw" value="여" type="radio">여
					</div>
				</div>
			
				<div class="mphone mtext">
					<h5>핸드폰번호</h5>
					<span ><input class="phone1" type="text">-<input class="phone2" type="text">-<input class="phone3" type="text">
								<button class="phone" onclick="phone()" type="button">인증하기</button></span>
					
					<div class="checkconfirm" ></div>
					<div class="confirmphone">
						<h5>인증번호 : </h5>
						<span><input class="confirmphoneNo" style="width: 40px;" type="text"><button class="confirmphonebtn" onclick="confirmphone()" type="button">확인</button></span>
					</div>
				</div>
				
				<div class="mtext ">
					<h5>프로필사진</h5>
					<img class="premimg" alt="" src="/oimarket/img/default.webp">
					<input onchange="premimg(this)" class="mimg" name="mimg" type="file">
				</div>
			</div>
			<div><button class="signup" onclick="signup()" type="button">가입하기</button></div>
		</form>
	</div>
	
	
	

	<script src="http://code.jquery.com/jquery-latest.min.js"></script> <!-- 아작트 쓰기위한용도 -->
	<script src="/oimarket/js/member/signup.js" type="text/javascript"></script>



</body>
</html>