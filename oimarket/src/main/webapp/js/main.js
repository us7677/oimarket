
// ----------------- 스크롤막대가 바닥이면 --------------- //

let pcount = 5;

$(window).scroll(function() {
   if($(window).scrollTop() + $(window).height() == $(document).height()) {
	   pcount +=5;
      getproduct();
   }
});



// ------------------------------------------------


getproduct();//기본 메인페이지에 전체출력부터 시작
let productInfo=""; //전체 물품들의 리스트들이 들어있음


function getproduct(){//등록된 물품전체출력 and 카테고리별 출력 !기본은 전체출력
	let html='';
	let pcno=document.querySelector('.form-select').value
	
	$.ajax({
		url:"/oimarket/product",
		data:{type:1,pcno:pcno , pcount : pcount },
		method:"get",
		success:(r)=>{
			console.log('성공')
			console.log(r);	productInfo=r;
			
			r.forEach( (o)=>{
				
				html+=
				` 
					<div class="content" onclick="oneproduct( ${o.pno}  )">
						<img  src="/oimarket/img/${o.pimglist[0]==null?'기본.png':o.pimglist[0]}">
						<div class="pinfo">
							<div class="pinfotop">
								<span class="pcname"> ${o.pcname} </span>
								<span class="pdate"> ${o.pdate}  </span>
								<div class="ptitle">${o.ptitle}</div>
								<div class="pprice">${(o.pprice).toLocaleString()} 원</div>
							</div>
							<div class="pinfobottom">
								<span class="icon"> <i class="far fa-eye"></i> <span class="bview">${ o.pview }</span> </span>
								<span class="icon"> <i class="far fa-heart"></i> <span class="bup">${ o.plikecount}</span> </span>
								<span class="icon"> <i class="far fa-comment-dots"></i> <span class="rcount">30 </span> </span>
							</div>
						</div>
					</div>
					`	
			})
			
			document.querySelector('.contentbox').innerHTML=html;
			
		}
		
	})
}


function oneproduct(pno  ){ // 제품 하나 클릭하면 상세 페이지로 전환
	if(memberInfo==null){
		alert('회원전용입니다 로그인해주세요!')
		location.href="/oimarket/member/login.jsp"
		
	}else{
		location.href="/oimarket/product/viewProduct.jsp?pno="+pno;
	}
	
}




function category(pcno){ //카테고리선택 변경했을때 
	getproduct(); //카테고리별 출력
}

function search(){//제목검색했을때
	let keyword = document.querySelector('.keyword').value;
	console.log("keyword:"+keyword)
	if(keyword==""){
		alert("검색창에 찾으실 제목을 써주세요!")
	}else{//키워드가 있을경우에 아작트실행
		let html='';
		
		$.ajax({
			url:"/oimarket/product",
			method:"get",
			data:{keyword:keyword,type:3},
			success:(r)=>{
				console.log(r)
				if(r.length>0){
					
					r.forEach( (o,i)=>{
				
				html+=
				`
					<div class="content" onclick="oneproduct(${i},${o.pno})">
						<img  src="/oimarket/img/${o.pimglist[0]==null?'기본.png':o.pimglist[0]}">
						<div class="pinfo">
							<h3 class="ptitle">${o.ptitle}</h3>
							<h5 class="pdate">${o.pdate}</h5>
							<h3 class="pprice">${(o.pprice).toLocaleString()} 원</h3>
						</div>
					</div>
				
					`	
					})
			
				}else{
					html+='<h3>검색결과가 없습니다.</h3>'
				}
				
			document.querySelector('.keyword').value="";
			document.querySelector('.contentbox').innerHTML=html;
			}
			
		})
	} 
	
}

function searchToggle(obj, evt){
    var container = $(obj).closest('.search-wrapper');
        if(!container.hasClass('active')){
            container.addClass('active');
            evt.preventDefault();
        }
        else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
            container.removeClass('active');
            // clear input
            container.find('.search-input').val('');
        }
}








