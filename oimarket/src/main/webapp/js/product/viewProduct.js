
let pno=document.querySelector('.pno').value;
getproduct();
let productInfo='';

let chatdiv=document.querySelector('.chatdiv');


//[장민정]제품 하나 상세 페이지
function viewProductPrint(){
	console.log(pno) //pno로 네츄럴 조인을 해서 글쓴 사람과 카테고리를 뽑아내야함
		let html='';
		let pimglistbox='';
		let btn='';	
		
		$.ajax({
			url:"/oimarket/product",
			data:{pno:pno,type:2},
			method:"get",
			success:(r)=>{//r에는 상세페이지의 작성자의 정보가 담겨잇다
				console.log(r);
				view(pno);
		html=
			`
				<div class="rmnobox">
					<div class="rmnoinfo">
						<!-- member 정보 -->
						<img alt="" src="/oimarket/img/${r.mimg==null?'기본.png':r.mimg}">
						<div class="infotop">
							<div class="mname">${r.mname}</div>
							<div class="residence">${r.mresidence}</div>
						</div>
					</div>
					
						<!-- product 정보 -->
					<div class="chatbox">
						<button class="likebtn" type="button" onclick="setlike(${pno})"><img src="/oimarket/img/likeoff.png"style="width:30px;height:30px;" ></button>
					</div>
				</div>																						
				
				<div class="rmnocontent">
						<div class="pinfo">
							<div class="pinfotop">
								<span class="pcname"> ${productInfo.pcname} </span>
								<span class="pdate"> ${productInfo.pdate}  </span>
								<div class="ptitle">${productInfo.ptitle}</div>
								<div class="pcontent">${productInfo.pcontent}</div>
								<div class="pprice">${(productInfo.pprice).toLocaleString()} 원</div>
							</div>
							<div class="pinfobottom">
								<span class="icon"> <i class="far fa-eye"></i> <span class="bview">${ productInfo.pview }</span> </span>
								<span class="icon"> <i class="far fa-heart"></i> <span class="bup">${ productInfo.plikecount}</span> </span>
								<span class="icon"> <i class="far fa-comment-dots"></i> <span class="rcount">30 </span> </span>
							</div>
						</div>
				</div>`
		document.querySelector('.productbox').innerHTML=html;		
				
					
		
				//<!--[김은영]//수정버튼,상태수정버튼,삭제버튼  -->
			let html1='';
		if(memberInfo.mno!=r.mno){//제품등록한 사람과 사는사람이 같지 않으면 구매하기버튼 보이기
			/*
			html1+=`		
				<div>
					<button onclick="state(${pno})" type="button">구매하기</button>
				</div>`
				*/
				
			}else if(memberInfo.mno==r.mno){//제품등록한 사람과 제품사는 사람이 같으면 수정/삭제 보이기 
				html1+=`
				<button onclick="Updateproduct(${pno})" type="button">수정</button>
				<button onclick="Deleteproduct(${pno})" type="button">삭제</button>`
			}
				document.querySelector('.btns').innerHTML=html1;


			
			
			
	
				
		//카카오지도 
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(productInfo.plat, productInfo.plng), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var imageSrc = '/oimarket/img/maker.png', // 마커이미지의 주소입니다    
		    imageSize = new kakao.maps.Size(40, 43), // 마커이미지의 크기입니다
		    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
		      
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
		    markerPosition = new kakao.maps.LatLng(productInfo.plat, productInfo.plng); // 마커가 표시될 위치입니다
		
	
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition,
		    image: markerImage // 마커이미지 설정
		});
		
		
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
				
		
		
	
		
	 	getlike(pno);
	 	/*사진첩 포문돌려서 꺼내기*/
	 	console.log("사진수량:"+productInfo.pimglist.length)
	 	productInfo.pimglist.forEach( (o,j)=>{
			 console.log(j)
			 console.log(o)
			 if(j==0){
			pimglistbox+=`
					 <div class="carousel-item active" data-bs-interval="10000">
				      <img src="/oimarket/img/${o}" class="w-100" alt="...">
				    </div>`
			
			btn+=
				`
			 <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
				`	    
				    
			 }else{
			 pimglistbox+=
			 	`
				  <div class="carousel-item" data-bs-interval="2000">
				      <img src="/oimarket/img/${o}" class="w-100" alt="...">
				    </div>`
				    
			
			btn+=
			`
			 <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${j}" aria-label="Slide ${j+1}"></button>
			`	    
				
			 }
		
		
		 })
	 	
	 	
	 	document.querySelector('.carousel-inner').innerHTML=pimglistbox;
	 	document.querySelector('.carousel-indicators').innerHTML=btn;
	 	
	 	
	 	// 내거 작성한 제품이 아니면 채팅 버튼 보이기 
	 	if( productInfo.rmno != memberInfo.mno ){
			 chatdiv.innerHTML = `<button type="button"  onclick="chatting(${pno},${productInfo.rmno})">채팅하기</button>`
		 }
		 
	 
	 	}
			
	})
	
}

//[장민정]product의 정보 한줄레코드 불러오는 함수
function getproduct(){
	$.ajax({
			url:"/oimarket/product",
			data:{pno:pno,type:4},
			method:"get",
			success:(r)=>{
			console.log(r);
			productInfo=r
			viewProductPrint();
				
		}
	});
}

//[장민정] 채팅하기
function chatting(pno , tomno ){
	location.href="/oimarket/chat/chatting.jsp?pno="+pno+"&tomno="+tomno;
}














/*---------------------------------------------------------------------------------------------------*/

//김은영// 조회수

function view(pno){
	$.ajax({
		url:"/oimarket/productview",
		method:"get",
		data:{"pno":pno},
		success:(r)=>{
			console.log('조회수 통신');
			console.log(r);
		}
	})
}



//김은영//판매상태
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

//김은영 //찜하기 상태
function getlike(pno){
	
	$.ajax({
		url:"/oimarket/productlike",
		method:"get",
		async:'false',
		data:{"pno":pno},
		success:(r)=>{
			console.log('통신완료?');
			console.log(r);
			if(r=='true'){
				
				document.querySelector('.likebtn').innerHTML='<img src="/oimarket/img/likeon.png"style="width:30px;height:30px;">'
			}else{
				
				document.querySelector('.likebtn').innerHTML='<img src="/oimarket/img/likeoff.png"style="width:30px;height:30px;">'
			}
		}
	})
}


//김은영//삭제 
 Deleteproduct();
function Deleteproduct(pno){
	console.log(pno);
	console.log('삭제함수열라아')
	$.ajax({
		url:"/oimarket/product",
		method:"delete",
		data:{"pno":pno,"type":1},
		success:(r)=>{
			console.log('삭제통신');
			console.log(r);
			if(r=='true'){
				alert('삭제완료')
				location.href="/oimarket/main.jsp"
			}else{alert('삭제불가')}
		}//success e
	})//ajax e
}//m e
//김은영//수정창 이동
function Updateproduct(pno){
location.href="/oimarket/product/update.jsp?pno="+pno;
}





//[김은영]찜하기 버튼
function setlike(pno){
	console.log('하트함수')
	if(memberInfo==null){
		alert('회원기능입니다. 로그인후 사용해주세요'); return;
	}
	$.ajax({
		url:"/oimarket/productlike",
		method:"post",
		data:{"pno":pno},
		success:(r)=>{
			console.log('하트 통신');
			console.log(r);
			if(r=='true'){
				alert('찜 등록');
				document.querySelector('.likebtn').innerHTML='<img src="/oimarket/img/likeon.png"style="width:30px;height:30px;">'
			}else{
				alert('찜 취소');
				document.querySelector('.likebtn').innerHTML='<img src="/oimarket/img/likeoff.png"style="width:30px;height:30px;">'
			}
		}
	})
}





















