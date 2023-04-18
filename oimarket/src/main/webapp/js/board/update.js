console.log('켜짐');

let bno = document.querySelector('.bno').value;
console.log("bno : " +bno);

// 수정버튼 함수
function bupdate(){
	let updateForm = document.querySelectorAll('.updateForm')[0];
	console.log(updateForm)
	let updateFormData = new FormData(updateForm);
		updateFormData.set('bno' , bno);
		
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"put",
		data : updateFormData,
		contentType:false,
		processData : false,
		success:(r)=>{
			console.log(r)
			if(r=='true'){
				alert('수정완료');
				location.href="/oimarket/board/view.jsp?bno="+bno;
			}else{
				alert('수정실패');
			}
		}
	})
}// end

// 수정할 게시물 출력
getBoard()
function getBoard(){
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"get",
		data:{"type" : 2 , "bno" : bno},
		success:(r)=>{
			console.log(r)
			
			document.querySelector('.btitle').value=r.btitle;
			document.querySelector('.bcontent').value=r.bcontent;
			
			let bcnoSelect = document.querySelector('.bcno');
				console.log(bcnoSelect);
				console.log(bcnoSelect.options[0]);
			for(let i = 0 ; i<bcnoSelect.options.length; i++){
				if(bcnoSelect.options[i].value == r.bcno){
					bcnoSelect.options[i].selected = true;
				}
			}// for end
			
			let html =''
			if(r.bfile == null){ // 첨부파일 없으면
				html +=`<div>첨부파일 없음</div>`
			}else{
				html +=
				`<div>
				기존 첨부파일 : <span class="oldbfile"> </span>
				<button onclick="bfiledelete()" type="button">삭제</button>
				</div>
				`
			}
				html += `<div class="writefile">새로운 첨부파일 : <input name="bfile" type="file"></div>`
				document.querySelector('.bfilebox').innerHTML=html;
				document.querySelector('.oldbfile').innerHTML=r.bfile;
		}
	})
}// end

$(document).ready(function() {
        $('#summernote').summernote(
			{   height : 400 , 
	  lang: 'ko-KR'   } 
		);
});

// 수정시 첨부 파일만 삭제
function bfiledelete(){
	alert('첨부파일 삭제합니다.');getBoard();
	
	$.ajax({
		url:"/oimarket/boardinfo",
		method: "delete",
		data:{"type" : 2 , "bno" : bno},
		success:(r)=>{
			if(r=="true"){
				$(".bfilebox").load(location.href+' .bfilebox');
			}else{
				
			}
		}
	})
}
// 수정 취소버튼
function bupdatecancel(){
	location.href="/oimarket/board/view.jsp?bno="+bno;
}


























