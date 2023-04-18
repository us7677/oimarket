let chatList = document.querySelector('.chatList');
console.log('gd')
getchatList();

//[장민정] 로그인한 회원의 모든 채팅 리스트
function getchatList(){
	$.ajax({
		url:"/oimarket/chat/db",
		data:{type:2},
		method:"get",
		success:(r)=>{
			console.log("연동");
			console.log(r);
	
			let html = ``;
			
			
			r.forEach( (o)=>{
				
				let tomno = o.mno1;
				let tomid = o.mid1;
				
				// 채팅방에 접속되어 있는 첫번째 회원과 로그인된 회원과 일치하면 
				if( o.mid1 == memberInfo.mid ){
					tomid = o.mid2;
					tomno = o.mno2;
					
				}
				
				
				
				html += `
						<div class="mychatbox" onclick="getchatting(${o.cno},${o.pno},${tomno})">
							<img class="mpimg" alt="" src="/oimarket/img/${o.mpimg==null?'기본.png':o.mpimg}">
							<div class="mychatinfo">
								<div class="tomno">${tomid} </div>
								<div> ${ o.ccontent } </div>
								<div class="cdate"> ${ o.cdate } </div>
							</div>
							
						</div>
						
				`;
				
				
				
				
			} )
			
			
			
			chatList.innerHTML = html;
			
		}
		
	})

}

function getchatting(cno,pno,tomno){
	console.log(cno,pno)
	location.href="/oimarket/chat/chatting.jsp?pno="+pno+"&cno="+cno+"&tomno="+tomno;
	
}