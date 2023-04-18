
// 검색에 필요한 정보+ 출력에필요한 정보들 한번에 보낼 객체 (카테고리별 출력)
let boardObject={
	key:"",
	keyword:"",
	type:1,
	bcno : document.querySelector('.bcno').value
}
// 검색에 필요한 정보+ 출력에필요한 정보들 한번에 보낼 객체 (전체 출력)
let boardObject2={
	key:"",
	keyword:"",
	type:3
}

// 카테고리별 전체 출력
function getBoardList(){
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"get",
		data:boardObject,
		success:(r)=>{
			console.log(r)
			// 	 게시물 프로필 이미지 나중에 화긴
			let html = ``
			r.forEach( ( o , i ) => {
				html += `<div class="contentbox">
							<div class="subcontentbox"> 
								<div class="contentbc">${o.bcno==1?"커뮤니티":o.bcno==2?"QnA":"노하우"}</div>
								<div class="contenttitle"> <a href="/oimarket/board/view.jsp?bno=${ o.bno }">${ o.btitle }</a></div>
								<img alt="" src="/oimarket/img/${o.mimg==null?'default.webp':o.mimg}">							
								<span class="contentmid"> ${ o.mid } </span>
								<span class="contentdate"> ${ o.bdate } </span>
							</div>
							<div class="contentinfo">
								<span class="contentinfoitem"><i class="far fa-eye"></i>${ o.bview } </span>
								<span class="contentinfoitem"><i class="far fa-thumbs-up"></i>${ o.bup } </span> 
								<span class="contentinfoitem"><i class="far fa-thumbs-down"></i>${ o.bdown } </span> 
								<span class="contentinfoitem"> <i class="far fa-comment-dots"></i> ${ o.rcount } </span>
							</div>
						</div>`
			})
			document.querySelector('.boardTable').innerHTML = html;
		}
			
	})
}
// 전체 게시물 출력
getBoardListAll();
function getBoardListAll(){
	$.ajax({
		url:"/oimarket/boardinfo",
		method:"get",
		data:boardObject2,
		success:(r)=>{
			console.log(r)
			// 	 게시물 프로필 이미지 나중에 화긴
			let html = ``
			r.forEach( ( o , i ) => {
				html += `<div class="contentbox">
							<div> 
								<div class="contentbc">${o.bcno==1?"커뮤니티":o.bcno==2?"QnA":"노하우"}</div>
								<div class="contenttitle"> <a href="/oimarket/board/view.jsp?bno=${ o.bno }">${ o.btitle }</a></div>
								<img alt="" src="/oimarket/img/${o.mimg==null?'default.webp':o.mimg}">						
								<span class="contentmid"> ${ o.mid } </span>
								<span class="contentdate"> ${ o.bdate } </span>
							</div>
							<div class="contentinfo">
								<span class="contentinfoitem"><i class="far fa-eye"></i>${ o.bview } </span>
								<span class="contentinfoitem"><i class="far fa-thumbs-up"></i>${ o.bup } </span> 
								<span class="contentinfoitem"><i class="far fa-thumbs-down"></i>${ o.bdown } </span> 
								<span class="contentinfoitem"> <i class="far fa-comment-dots"></i>${ o.rcount } </span>
							</div>
						</div>`
			})
			document.querySelector('.boardTable').innerHTML = html;
		}
			
	})
}
// 글쓰기버튼 함수
function boardUpload(){
	location.href="write.jsp"
}

// 검색
function getsearch(){
	console.log("검색 실행")
	boardObject2.key = document.querySelector('.key').value;
	boardObject2.keyword = document.querySelector('.keyword').value;
	getBoardListAll();
	console.log(boardObject2)
}

// 각 카테고리별 출력 리모콘(전체보기 포함)
function setsearch(){
	location.href="list.jsp"
}

function bcbtn1(){
	boardObject.bcno = 1;
	getBoardList();
	
}
function bcbtn2(){
	boardObject.bcno = 2;
	getBoardList();
}
function bcbtn3(){
	boardObject.bcno = 3;
	getBoardList();
}