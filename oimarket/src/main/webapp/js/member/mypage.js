// index.jsp 로 이동 ( 홈으로 이동 )

function logo(){
	location.href="/oimarket/main.jsp"
}

console.log(memberInfo)

// [최성아] 회원 정보 호출
getLogin();
function getLogin() {
	$.ajax({
		url : "/oimarket/login" ,
		method : "get" ,
		async : false ,
		success : (r) => {
			memberInfo = r;
			let html = '';
			
			if ( r.mid != null){
				html += `
					<div class="updateLine">
						<h3> 회원 정보 출력 </h3>
						<div>
							<button class="updatebtn" onclick="modal_Update()" type="button"> 회원 정보 수정 </button>
						</div>
					</div>
					<div class="mypageInfo"> <div class="mypageH">아이디</div>   ${r.mid} </div>
					<div class="mypageInfo"> <div class="mypageH">프로필</div>   ${r.mimg} </div>
					<div class="mypageInfo"> <div class="mypageH">닉네임</div>   ${r.mname} </div>
					<div class="mypageInfo"> <div class="mypageH">핸드폰</div>   ${r.mphone} </div>
					<div class="mypageInfo"> <div class="mypageH">거주지</div>   ${r.mresidence} </div>
					

					
					<div class="deleteLine">
						<div class="delete_input">
							<input class="deleteinput" type="text" placeholder="비밀번호 입력 후 탈퇴 버튼 누를시 탈퇴됩니다!"> 
						</div>
						<div>
							<button class="deletebtn" onclick="setDelete();" type="button"> 회원 탈퇴 </button> 
						</div>
					</div>
					
					
					`
			}else if ( r.mid == null ){
				alert('로그인 후 이용가능합니다.')
				location.href = "/oimarket/member/login.jsp";
			}	
			document.querySelector('.mypageInfobox').innerHTML = html;
			
		} // success end
	}) // ajax end
}

// [최성아] 회원 탈퇴
function setDelete() {
	
	$.ajax({
		url : "/oimarket/login" ,
		method : "delete" ,
		data : {"mpwd" : document.querySelector('.deleteinput').value } ,
		success : (r) => {
			if( r == 'true'){
				alert('회원 탈퇴 성공')
				location.href="/oimarket/index.jsp"
			}else{
				alert('입력 비밀번호가 옳지 않습니다.')
			}
			
		}
	}) // ajax end
}
// [최성아] 수정창으로 이동
function modal_Update() {
	location.href = "/oimarket/member/update.jsp"
}

// [최성아] mypage 내용 출력
let type = 0;

// 1. 내가 등록한 물품 출력
getRegistProduct();
function getRegistProduct(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 1 , "mno" : memberInfo.mno  } ,
		async : false ,
		success : (r) => { console.log('등록한제품통신성공'); console.log(r);
			let html = '';
			
			r.forEach( (p) => {
				console.log(p);
				html += `<div class="product_box">
							<div> 
								<img src="/oimarket/img/${p.mainImg}">
							</div> 
							<div class="product_textbox">
							
								<div class="product_category"> 
									${p.pcno == 1 ? '생활가전' : p.pcno == 2 ? '의류' : p.pcno == 3 ? '뷰티미용' : p.pcno == 4 ? '가공식품' : '식물'}
								</div> 
								
								<div class="product_title"> ${p.ptitle} </div>
								<div class="product_price"> ${p.pprice} 원 </div> 
								<div class="product_date"> ${p.divideDate} </div>
							</div>
							
							<div class="p_state"> ${p.pstate == 1 ? '판매중' : '판매완료'  }  </div>

						</div>`
			})
			
			document.querySelector('.mypageRegistbox').innerHTML = html;
		}
	})	
}

// 2. 내가 판매중인 물품 출력
getSellProduct();
function getSellProduct(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 2 , "mno" : memberInfo.mno  } ,
		async : false ,
		success : (r) => { console.log('판매중통신성공'); console.log(r);
			let html = ``;
			
			r.forEach( (p) => {
				html += `<div class="product_box">
							<div> 
								<img src="/oimarket/img/${p.mainImg}">
							</div> 
							
							<div class="product_textbox">
							
								<div class="product_category"> 
									${p.pcno == 1 ? '생활가전' : p.pcno == 2 ? '의류' : p.pcno == 3 ? '뷰티미용' : p.pcno == 4 ? '가공식품' : '식물'}
								</div> 
								
								<div class="product_title"> ${p.ptitle} </div>
								<div class="product_price"> ${p.pprice} 원 </div> 
								<div class="product_date"> ${p.divideDate} </div>
							</div>
							
							<div class="p_state"> ${p.pstate == 1 ? '판매중' : '판매완료'  }  </div>

						</div>`
			})
			
			document.querySelector('.mypageSellbox').innerHTML = html;
		}
	})	
}

// 3. 내가 구매한 물품 출력
getBuyProduct();
function getBuyProduct(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 3 , "mno" : memberInfo.mno  } ,
		async : false ,
		success : (r) => { console.log('구매한제품통신성공'); console.log(r);
			let html = '';
			
			r.forEach( (p) => {
				console.log(p);
				html += `<div class="product_box">
							<div> 
								<img src="/oimarket/img/${p.mainImg}">
							</div> 
							<div class="product_textbox">
								<div class="product_category"> 
									${p.pcno == 1 ? '생활가전' : p.pcno == 2 ? '의류' : p.pcno == 3 ? '뷰티미용' : p.pcno == 4 ? '가공식품' : '식물'}
								</div> 
								<div class="product_title"> ${p.ptitle} </div>
								<div class="product_price"> ${p.pprice} 원 </div> 
								<div class="product_date"> ${p.pdate} </div>
							</div>
							<div class="p_state"> ${p.pstate == 1 ? '판매중' : '판매완료'  }  </div>

						</div>`
			})
			
			document.querySelector('.mypageBuybox').innerHTML = html;
		}
	})	
}



// 4. 내가 찜한 물품 출력
getLikeProduct();
function getLikeProduct(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 4 , "mno" : memberInfo.mno  } ,
		async : false ,
		success : (r) => { console.log('찜한제품통신성공'); console.log(r);
			let html = '';
			
			r.forEach( (p) => {
				console.log(p);
				html += `<div class="product_box">
							<div> 
								<img src="/oimarket/img/${p.mainImg}">
							</div> 
							<div class="product_textbox">
								<div class="product_category"> 
									${p.pcno == 1 ? '생활가전' : p.pcno == 2 ? '의류' : p.pcno == 3 ? '뷰티미용' : p.pcno == 4 ? '가공식품' : '식물'}
								</div> 
								<div class="product_title"> ${p.ptitle} </div>
								<div class="product_price"> ${p.pprice} 원 </div> 
								<div class="product_date"> ${p.divideDate} </div>
							</div>
							<div class="p_state"> ${p.pstate == 1 ? '판매중' : '판매완료'  }  </div>

						</div>`
			})
			
			document.querySelector('.mypageLikebox').innerHTML = html;
		}
	})	
}

// 5. 내가 게시한 게시글 출력

getBoard();
function getBoard(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 5 , "mno" : memberInfo.mno } ,
		async : false ,
		success : (r) => { console.log('게시물통신성공'); console.log(r);

			let html = `<tr>
							<th>Category</th> <th>Title</th> <th> Date </th>
							<th><i class="far fa-eye"></i></th> 
							<th><i class="far fa-thumbs-up"></i></th> 
							<th><i class="far fa-comment-dots"></i></th>
						</tr>`;
			
			r.forEach( (b) => {
					html += `<tr>
								<td>${b.bcno == 1 ? '공지사항' : b.bcno == 2 ? '자유게시판' : 'QnA'  } </td>
								<td>${b.btitle}</td><td>${b.bdate}</td>
								<td>${b.bview}</td><td>${b.bup}</td><td>${b.bdown}</td>
							</tr>`
			})
			document.querySelector('.mypageBoardbox').innerHTML = html;
		}
	})
}

// 6. 오늘 방문자 수 증가 
function viewIncrease(){
	$.ajax({
		url : "/oimarket/mview" ,
		data : { "mno" : memberInfo.mno } ,
		success : (r) => {
			console.log('오늘 방문자 수 증가 성공')
			console.log(r);
		}
	})
}

// 6.1 오늘 방문자 수 출력 [ ip + mno ]
viewCount();
function viewCount(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 6} ,
		async : false ,
		success : (r) => {
			console.log('오늘 방문자 수 카운트 통신 성공')
			console.log(r);
			let html = `${r}`;
			document.querySelector('.viewCount').innerHTML = html;
		}
	})	
}



// 7. 총 물품개수 출력
getProductCount();
function getProductCount(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 7 } ,
		async : false ,
		success: (r) => {
			console.log('총 물품개수 통신'); console.log(r);
			let html = `총 물품 개수 : <span class="ProductCount">${r}</span>`;
			document.querySelector('.ProductCount').innerHTML = html;
			
		}
	})
} // 총 물품개수 end

// 8. 4월 물품거래가격 출력
getProductPriceCount();
function getProductPriceCount(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 8 } ,
		async : false ,
		success: (r) => {
			console.log('4월 물품가격 통신'); console.log(r);
			let html = `4월 물품 총 거래가격 : <span class="ProductPriceCount">${r}</span>`;
			document.querySelector('.ProductPriceCount').innerHTML = html;
			
		}
	})
} // 4월 물품거래가격 end

// 9. 오늘 거래된 물품 카테고리별 개수 출력
/*getProductCategoryCount();
function getProductCategoryCount(){
	$.ajax({
		url : "/oimarket/member/mypage" ,
		data : { "type" : 9 } ,
		async : false ,
		success: (r) => {
			console.log('오늘 거래된 물품 카테고리별 개수 통신'); console.log(r);
			let html = `<th>물품 카테고리</th> <th> 거래된 개수 </th>`
			
			r.forEach( (p) => {
					html += `<tr>
								<td>${p.pcname} </td>
								<td>${p.productCategoryCount}</td>
							</tr>`
			})						
			document.querySelector('.ProductCategoryCount').innerHTML = html;
			
		}
	})
} // 오늘 거래된 물품 카테고리별 개수 end*/


const ctx = document.getElementById('myChart');

// 9. 오늘 거래된 물품 카테고리별 개수 출력 (차트화)
$.get ( "/oimarket/count" , (r) => {

  new Chart(ctx, {
    type: 'bar', // bar : 막대차트 vs line : 선차트
    data: {
      labels: Object.keys(r) ,
      datasets: [{
        label: '당일 기준 물품 카테고리별 거래 개수' ,		// 데이터 항목명
        data: Object.values(r) ,					// 해당 항목 데이터 
        borderWidth: 2,								// 막대 굵기
        borderColor: '#41A541',
		backgroundColor: '#41A541'
      }] ,
    },
    options: {
      scales: {
        y: {
			min : 0 ,
			max: 10 ,
          beginAtZero: true
        }
      }
    }
  });
	
})




































