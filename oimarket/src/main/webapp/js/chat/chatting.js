	let pno=document.querySelector('.pno').value;
	let cno=document.querySelector('.cno').value;
	let tomno=document.querySelector('.tomno').value;
	
	let mno=memberInfo.mno
	let chattingbox=document.querySelector('.chattingbox')
	let rmno='';
메세지출력();

function 보내기(){
	let textbox =document.querySelector('.textbox').value
	console.log(textbox)
	console.log(pno)
	console.log(mno)
	$.ajax({
		url:"/oimarket/chat/db",
		data:{textbox:textbox,tomno:tomno,frommno:mno , cno : cno,pno:pno},
		method:"post",
		async : false ,
		success:(r)=>{
			console.log(r);
			document.querySelector('.textbox').value = '';
			메세지출력()
		}
	})
}

function getrmno(){

	$.ajax({
		url:"/oimarket/chat/db",
		data:{pno:pno,type:3},
		method:"get",
		async : false ,
		success:(r)=>{
			console.log(r);
			rmno=r
		}
	})
}




function 메세지출력(){
		$.ajax({
		url:"/oimarket/chat/db",
		method:"get",
		async : false ,
		data:{pno:pno,type:1 , cno : cno },
		success:(r)=>{
			console.log(r);
			getrmno()
		let html='';
		chattingbox.innerHTML='';
		console.log(rmno);
		if(rmno!=memberInfo.mno ){
		 
		 let pstate  = 0;
		 $.ajax({
			url:"/oimarket/product",
			data:{pno:pno,type:4},
			async : false ,
			method:"get",
			success:(r2)=>{//r에는 상세페이지의 작성자의 정보가 담겨잇다
				console.log(r2);
				pstate = r2.pstate;
				
				
				
				
				}
			});
			
		 //alert( pstate );
		  if(pstate==1){
			  chattingbox.innerHTML+=`      
            <div  class="state">
               <button onclick="state(${pno})" type="button">구매하기</button>
            </div>
            `
		  }else if(pstate==2){
			  chattingbox.innerHTML+=`      
            <div  class="state">
               <div>판매완료</div>
            </div>
            `
			  
		  }
		
		
			}
			

		r.forEach( (o)=>{
			
		if(o.frommno==memberInfo.mno){//내가 보낸 메세지
			chattingbox.innerHTML+=
			`	
			<div class="sendmessage">
				<div class="sendtime">${o.ndate}</div>
				<div class="sendtext">${o.ncontent}</div>
			<div>
			`
		}else{//내가 받은 메세지
			chattingbox.innerHTML+=
			`
			<div >
				<div>
					<div class="frommember"> ${o.frommname==memberInfo.mid ? o.tomname : o.frommname} </div>
				</div>
				<div class="receivemessage">
					<div class="receivetext">${o.ncontent}</div>
					<div class="sendtime">${o.ndate}</div>
				</div>
			<div>`
			}
		})
		
			
			
		}
		
		
	})
	
}

function state(pno){
   console.log('상태변경')
   
   
   $.ajax({
      url:"/oimarket/productstate",
      method:"get",
      data:{"pno":pno},
      success:(r)=>{
         console.log('통신됐나영');
         console.log(r);
         if(r==2){
            alert('구매완료 되었습니다');
            location.href="/oimarket/main.jsp"
         }
      }
   })
}

