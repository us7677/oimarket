<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="width: 100%">
<head>
<meta charset="UTF-8">

	<link href="/oimarket/css/member/update.css" rel="stylesheet">
</head>
<body style="width: 100%">
	<%@ include file="/footer.jsp" %>
	
	<div class="wrap Updatewrap">

		<div class="mypageMainbox">
			<div class="updateLogo" >
				<img onclick="logo()" alt="" src="/oimarket/img/아이콘.png"> 
			</div>			
			
			<form class="updateForm">
				<div class="updateMainbox">
					
					<div>
						<div> 아이디 </div>
						<div class="mid">  </div>
					</div>
					
					<div>
						<img class="mimg" style="width: 30%;" src="/oimarket/img/default.webp"> <br>
						프로필변경 : <input type="file" name="newmimg"> <br>
						<input class="defaultimg" type="checkbox"> 기본프로필 사용
					</div>					
					
					<div>
						<div> 닉네임 </div> <input class="mname" type="text" name="newmname">
						
					</div>

					<div>
						<div> 핸드폰 </div> <input class="mphone"  type="text" name="newmphone">
					</div>
					
					<div>
						<div> 거주지 </div> <input class="mresidence" type="text" name="newmresidence">
					</div>
					
					<div>
						<div> 현재비밀번호 </div>
						<input class="nowpwd" type="text"  name="mpwd">
					</div>
					<div>
						<div> 새 비밀번호 </div>
						<input class="newPwd" type="text"  name="newmpwd">
					</div>

					

					<button class="updatebtn" onclick="setUpdate()" type="button"> 회원 수정 </button>
				</div>
			</form>		
			
			
		</div>
	
	</div>
	
	
	<script src="/oimarket/js/member/update.js" type="text/javascript"></script>
</body>
</html>