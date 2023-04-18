console.log(memberInfo)

// 로고 클릭시 mypage.jsp로 이동
function logo(){
	location.href="/oimarket/member/mypage.jsp"
}

if( memberInfo.mid == null ){
	alert('로그인하고 오세요');
	location.href="/oimarket/member/login.jsp"
}

document.querySelector('.mid').innerHTML = memberInfo.mid
document.querySelector('.mimg').src = `/oimarket/img/${ memberInfo.mimg == null ? 'default.webp' :  memberInfo.mimg }`
document.querySelector('.mname').value = memberInfo.mname
document.querySelector('.mphone').value = memberInfo.mphone
document.querySelector('.mresidence').value = memberInfo.mresidence

function setUpdate(){
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData ( updateForm );
	
	let defaultimg = document.querySelector('.defaultimg').checked
	updateFormData.set( "defaultimg" , defaultimg );	
	
	$.ajax({
		url : "/oimarket/login" ,
		method : "put" ,
		data : updateFormData ,
		contentType : false ,
		processData : false ,
		success : (r) => {
			if(r == 'true' ){
				alert('수정성공')
			} else{
				alert('비밀번호가 틀립니다.');
			}
		}
	})
}