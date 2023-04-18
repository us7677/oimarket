console.log('글쓰기 열림여')

function bwrite(){
	let writeForm = document.querySelectorAll('.writeForm');
	console.log(writeForm)
	
	let writeFormData = new FormData(writeForm[0]);
	console.log(writeFormData)
	
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"post",
		data:writeFormData,
		contentType : false,
		processData : false,
		success:(r)=>{
			console.log(r)
			if(r=='true'){
				alert('글쓰기성공')
				location.href="/oimarket/board/list.jsp";
			}else{
				alert('글쓰기 실패')
			}
		}
	})
}

$(document).ready(function() {
        $('#summernote').summernote(
			{   height : 400 , 
	  lang: 'ko-KR'   } 
		);
});

function mainPage(){
	location.href="/oimarket/main.jsp"
}
