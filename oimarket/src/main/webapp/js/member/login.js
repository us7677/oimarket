
// 1. [최성아] 로그인
function login() {
	let mid = document.querySelector('.mid').value;
	let mpwd = document.querySelector('.mpwd').value;	
	
	$.ajax({
		url : "/oimarket/login" ,
		method : "post" ,
		data : { "mid" : mid , "mpwd" : mpwd } ,
		success : (r) => {

			if ( r == 'true' ){
				alert('로그인 성공')
				location.href = "/oimarket/member/memberInfo.jsp"	
				location.href="/oimarket/main.jsp";
			} else { 
				alert('아이디나 비밀번호가 옳지 않습니다.')
			}

		} // success end
	}) // ajax end
	
} // login end


// [김은영] 아이디찾기
let type=0;
function openModal(){//입력받은값 가져오기

	let mname=document.querySelector('.mname').value;
	let mphone=document.querySelector('.mphone').value;
	
	$.ajax({
		url:"/oimarket/find",
		method:"get",
		data:{"mname":mname,"mphone":mphone,"type":1},
		success:(r)=>{
			console.log(r); 
			if(r=='false'){
				dalert('회원정보가 없습니다')
			}else{
				document.querySelector('.modal_wrap').style.display='flex';//열기
				document.querySelector('.content').innerHTML =`회원님의 아이디는 ${r} 입니다.로그인페이지로 이동합니다.`
					//location.href="/oimarket/member/login.jsp";			
			}//
		}//success e
	})//ajax e
};//m e

// [김은영] 비밀번호찾기
function openModal2(){
	console.log('실행');//확인용
	let mid=document.querySelector('.mid').value;
	let mphone=document.querySelector('.mphone').value;
	 $.ajax({
		 url:"/oimarket/find",
		 method:"get",
		 data:{"mid":mid,"mphone":mphone,"type":2},
		 success:(r)=>{
			 console.log(r);
			 if(r=='false'){
				 alert('회원정보가 없습니다')
			 }else{document.querySelector('.modal_wrap').style.display='flex';//열기
				document.querySelector('.content').innerHTML=`휴대폰으로 임시비밀번호를 전송하였습니다. 로그인페이지로 이동합니다.`
			 }
		 }
	 })	
}

function closeModal(){
	document.querySelector('.modal_wrap').style.display='none';//닫기
}
