// [최성아] 마이페이지 버튼 클릭시 마이페이지로 이동하기
function mypageGo() {
	location.href="/oimarket/member/mypage.jsp"
}
function homeGo() {
	location.href="/oimarket/main.jsp"
}
function productrigster(){
	location.href="/oimarket/product/product.jsp"
}
function boardGo(){
	location.href="/oimarket/board/list.jsp";
	getBoardListAll()
}
function chattingListGo(){
	location.href="/oimarket/chat/mychattingList.jsp";
	
}

let memberInfo = null;

// [최성아] 회원 수정 , 탈퇴 위한 로그인 정보 호출 
getLogin();
function getLogin() {
	$.ajax({
		url : "/oimarket/login" ,
		method : "get" ,
		async : false ,
		success : (r) => {
			memberInfo = r;
			console.log(r);
} // success end
	}) // ajax end
}

// ------------------------------- 
let chatSocket = null;
	if(memberInfo.mid==null){
		alert('회원공간입니다 로그인해주세요')
		location.href="/oimarket/member/login.jsp"
	}else{
		//소켓과 연결
		chatSocket=new WebSocket('ws://localhost:8080/oimarket/chatting/'+memberInfo.mid);console.log(chatSocket)
		chatSocket.onopen=function(e){console.log("들어왔다.")}
		chatSocket.onmessage=function(e){onalarm(e)}
		chatSocket.onclose=function(e){console.log("나갔다")}
		
		}



function onalarm( e ){
	 let alarm = document.querySelector('.alarm')
	 alarm.style.top = "0px";
	 
	 // * 4초 후에 자동 내려가기 
	 // n초 후에 이벤트 실행  setTimeout( ()=>{} , 밀리초 )
	 // n초 마다 이벤트 실행  setInterval( ()=>{} , 밀리초 )
	setTimeout( ()=>{  
		 alarm.style.top = "-50px"; 
	} , 4000)
	
	// 여러명이 채팅 요청 하면 Dao 메소드 충돌 발생
		// Dao 메소드에 synchronized 키워드 사용 
		// 스레드1 해당 메소드를 사용하고 있을떄[ return 전] 다른 스레드2 해당 메소드에 대기상태 
		// 멀티스레드 : HttpServlet 에 포함
	
	메세지출력();
}


