
let bno = document.querySelector('.bno').value;	// 게시물번호 가져오기

// 게시물 개별 출력 함수
getBoard();
function getBoard(){
	
	console.log("bno:" +bno)
	
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"get",
		data:{"type" : 2 , "bno" : bno},
		success:(r)=>{
			console.log(r)
			let html = `
						<i class="far fa-eye"></i>${r.bview} 
						<i class="far fa-thumbs-up"></i><button class="increasebtn" onclick="bIncrease(2)" type="button">${ r.bup }</button>
						 <i class="far fa-thumbs-down"></i><button class="increasebtn" onclick="bIncrease(3)" type="button">${ r.bdown }</button>          
						`
			let htmldate=`${r.bdate}`			

			document.querySelector('.infoboxdate').innerHTML = htmldate;
			document.querySelector('.infobox').innerHTML = html;
			document.querySelector('.mimg').src=`/oimarket/img/${r.mimg==null ? 'default.webp' : r.mimg}`
			document.querySelector('.mid').innerHTML = r.mid;
			document.querySelector('.btitle').innerHTML = r.btitle;
			document.querySelector('.bcontent').innerHTML = r.bcontent;

			if( r.bfile == null ){ // 첨부파일 없을때 
				document.querySelector('.bfile').innerHTML = '첨부파일없음';
			}else{ // 첨부파일 있을때 
				html = ` ${ r.bfile } <button onclick="bdownload( '${ r.bfile }' )" type="button"> 다운로드 </button>`
				document.querySelector('.bfile').innerHTML = html;
			}
			if(memberInfo.mid==r.mid){
				html =`
					<button class="budbtn" onclick="bdelete(${bno} , ${r.bcno})" type="button" class="bbtn">삭제</button>
					<button class="budbtn" onclick="bupdate(${bno})" type="button" class="bbtn">수정</button>
					`;
				document.querySelector('.bbtnbox').innerHTML=html;
			}
			getReplyList();
		}
	})
}// function end

// 첨부파일 다운로드
function bdownload(bfile){
	console.log("선택한 파일명 : " +bfile)
	/*$.ajax({
		url:"/oimarket/filedownload",
		method : "get",
		data:{"bfile" : bfile},
		success:(r)=>{
			console.log(r)
		}
	})*/
	location.href="/oimarket/filedownload?bfile="+bfile;
}

// 삭제
function bdelete(bno,bcno){
	$.ajax({
		url:"/oimarket/boardinfo",
		method : "delete",
		data:{"bno" : bno , "type" : 1},
		success:(r)=>{
			console.log(r)
			if(r=='true'){
				alert('삭제성공');
				location.href="/oimarket/board/list.jsp?bcno="+bcno;
			}else{
				alert('삭제실패');
			}
		}
	})
}

// 수정페이지로 이동
function bupdate(bno){
	location.href="/oimarket/board/update.jsp?bno="+bno;
}

// 조회수 / 좋아요 / 싫어요 기능 함수
bIncrease(1);
function bIncrease(type){
	let bno=document.querySelector('.bno').value;
	$.ajax({
		url:"/oimarket/view",
		method:"get",
		data:{"type" : type , "bno" : bno},
		success:(r)=>{
			console.log(r)
			getBoard();
		}
	})
}

// 댓글쓰기
function rwrite(){
	console.log("댓글 작성!!!!!")
	$.ajax({
		url:"/oimarket/reply",
		method:"post",
		data:{
			"type" : 1 ,
			"bno"  : bno ,
			"rcontent" : document.querySelector('.rcontent').value},
		success:(r)=>{
			console.log(r)
			if(r=='true'){
				alert('댓글작성성공');
				document.querySelector('.rcontent').value=''
				location.reload();
			}
			else{alert('댓글작성 실패...');}
		}
	})
}

function rrwirte(rno){
	$.ajax({
		url:"/oimarket/reply",
		method:"post",
		data:{"type" : 2 , "bno" : bno , "rindex" : rno , "rcontent" : document.querySelector('.rrcontent'+rno).value},
		success:(r)=>{
			console.log(r)
			if(r=='true'){
				alert('대댓작성완료')
				location.reload();
			}
		}
	})
}

// 댓글 출력
function getReplyList(){
	$.ajax({
		url:"/oimarket/reply",
		method:"get",
		data:{"type" : 1 , "bno" : bno},
		success:(r)=>{
			console.log(r)			
			let html =''
			r.forEach((o,i)=>{
				html+=`
					<div>
						<div  class="reply">
							<span class="replyitem"><img class="hpimg" alt="" src="/oimarket/img/${o.mimg==null?'default.webp':o.mimg}"></span>
							<span class="reremid replyitem">${o.mid}</span>
							<span class="reredate replyitem">${o.rdate}</span>
						<span>${o.rcontent}</span>
						</div>
						<div class="rereviewbox">
							<button class="rereview" onclick="rereplyview(${o.rno})" type="button">대댓글보기</button>
						</div>
						<div class="rereplybox${o.rno}"></div>
					</div>
				`
			})
			document.querySelector('.replylistbox').innerHTML=html;
		}
	})
}
// 대댓 출력
function rereplyview(rno){
	$.ajax({
		url:"/oimarket/reply",
		async : 'false',
		method:"get",
		data : {"type" : 2 , "rindex" : rno , "bno" : bno},
		success:(r)=>{
			console.log(r)			
			let html=''
			r.forEach((o)=>{
				
				html+=`
				<div class="rereply">
					<span ><img class="hpimg" alt="" src="/oimarket/img/${o.mimg==null?'default.webp':o.mimg}"></span>
					<span class="reremid rereitem">${o.mid}</span>
					<span class="reredate rereitem">${o.rdate}</span>
					<span class="rereitem">${o.rcontent}</span>
				</div>
				`
			})
			html +=`
				<div class="rereplybottom">
					<textarea id="rrcontent" class="rrcontent${rno}"> </textarea>
					<button class="rrbtn" onclick="rrwirte(${rno})" type="button"> 대댓글달기 </button>
					<button class="rrbtn" onclick="replynoview(${rno})" type="button">대댓글가리기</button>
				</div?
			`
			document.querySelector('.rereplybox'+rno).innerHTML = html;
		}
	})
}

function replynoview(rno){
	console.log(rno)
	let html = ''
	document.querySelector(".rereplybox"+rno).innerHTML = html;
}















