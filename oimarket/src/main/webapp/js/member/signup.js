console.log('연동')

let checkconfirm=document.querySelectorAll('.checkconfirm');

function logo(){
	location.href="/oimarket/index.jsp"
}

//아이디 존재 여부 확인 특수문자 문자길이 체크 함수 
function namecheck(){//이름 유효성(한글만가능)
	let mid=document.querySelector('.mname').value;
	let testname=/^[ㄱ-힣]{2,5}$/
	if(testname.test(mid)){
			checkconfirm[0].innerHTML='OK'
			checkconfirm[0].style.color='gray';
	}else{
			checkconfirm[0].innerHTML='한글로입력해주세요'
			checkconfirm[0].style.color='red';
		
	}
}

function idcheck(){//아이디 유효성(정규식 표현 및 존재여부)

	let mid=document.querySelector('.mid').value;
	console.log(mid.length)
	//특수문자검사할것
	if(mid.length<1){//아무런글자도없으면 => 아무것도안썼는데 자꾸 ok뜨길래..
		checkconfirm[1].innerHTML='';
		return;
	}
	
	let testid= /^[a-z]+[a-z0-9]{5,19}$/g;
	if(testid.test(mid)){//정규표현식대로 아이디 기재를 했으면!
		
		$.ajax({//아이디가 있는지 없는지 두번째 유효성 검사
			url:"/oimarket/findmember",
			method:"get",
			data:{"mid":mid,"type":1}, 
			success:(r)=>{
				console.log('아이디 체크 연공성공')
				console.log(r)
				if(r=='true'){
					checkconfirm[1].innerHTML='OK'
					checkconfirm[1].style.color='gray';
					
				}else{
					checkconfirm[1].innerHTML='같은 아이디가 존재합니다'
					checkconfirm[1].style.color='red';
					
				}
			}
		})
	}else{
					checkconfirm[1].innerHTML='아이디는 영소문자로 시작하는 6~19로 기재해주세요'
					checkconfirm[1].style.color='red';
		
	}
		
}

function pwdcheck(){//비밀번호 유효성 검사
	let mpwd=document.querySelector('.mpwd').value;
	
	let testpwd=/^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
	
	if( testpwd.test(mpwd) ) {//정규식 통과시
					checkconfirm[2].innerHTML='OK'
					checkconfirm[2].style.color='gray';
	}else{
		
					checkconfirm[2].innerHTML='비밀번호는 8 ~ 16자 영문, 숫자 조합 해주세요'
					checkconfirm[2].style.color='red';
	}
}


function repwdcheck(){//비밀번호 재확인 유효성
	let repwdcheck=document.querySelector('.repwdck').value;
	let mpwd=document.querySelector('.mpwd').value;
	if(repwdcheck==mpwd){
					checkconfirm[3].innerHTML='OK'
					checkconfirm[3].style.color='gray';
					
	}else{
					checkconfirm[3].innerHTML='비밀번호가 서로 다릅니다'
					checkconfirm[3].style.color='red';
	}
}


let mphone=''; // 나중에 회원가입할때 따로 가져가야해서 전역변수로뺌
function phone(){//핸드폰 번호 유효성 검사
	//1.핸드폰 번호 받기
	let phone1 = document.querySelector('.phone1').value
	let phone2 = document.querySelector('.phone2').value
	let phone3 = document.querySelector('.phone3').value
	 mphone=phone1+phone2+phone3
	console.log(mphone)
	
	//유효성검사하기 준비물
	let testphone1=/^[0-9]{3,3}$/g;
	let testphone2=/^[0-9]{4,4}$/g;
	let testphone3=/^[0-9]{4,4}$/g;
	
	
	if( testphone1.test(phone1) ){//첫번째 010 구간제대로 썼으면 아래 실행
		
		if(testphone2.test(phone2)&&testphone3.test(phone3) ){//두번째와 세번째구간 4자리이면
			//동일한 핸드폰이 있는지 확인할것 , 통과시 인증번호시작
			
			
			$.ajax({// aj s
				url:"/oimarket/findmember",
				method:"get",
				data:{"mphone":mphone,"type":2},
				success:(r)=>{//s s
					console.log(r)
					if(r=='true'){//if  s
						console.log('같은 번호의 회원은없다')//등록 가능상태 이면 인증번호 칸 쓸수있음
						
						document.querySelector('.confirmphone').style.display='flex';//동일한 핸드폰 번호가 없으면 인증번호칸 flex
						
						
					//if  e
					}else{
						checkconfirm[4].innerHTML='이미 존재하는 핸드폰 번호입니다'
						checkconfirm[4].style.color='red';
						}
					
				}//s e
			})//aj e
			
			console.log('성공')
		//2번째 if e	
		}else{
			console.log('두번째 세번째칸은 4글자로 기제해주세요')
		}
			
	//1번째 if e
	}else {
			console.log('첫번째칸은 세글자로 기제해주세요')
			}
	
	 
	console.log(phone)
	
	
}


function confirmphone(){//인증번호 확인
	
	//인증번호받기
	let confirmphoneNo=document.querySelector('.confirmphoneNo').value
	
	if(confirmphoneNo=='1234'){//if  s 인증번호 인풋창에기재값이 1234이면
							
						document.querySelector('.confirmphone').style.display='none'; //다시숨기기
						console.log('인증성공!')
						checkconfirm[4].innerHTML='OK';
						checkconfirm[4].style.color='gray';
						
					//if  e
					}else{console.log('인증번호가 틀렸습니다.')}
}

let mmw=null;
function getmmw(event){
	 
  mmw=event.target.value; //성별검사 
    
}

function premimg(object){//첨부파일 미리보기
	console.log(object.files[0]	);
	console.log(document.querySelector('.mimg').files[0]);
	let file=new FileReader();
	
	file.readAsDataURL(object.files[0]);
	file.onload= (e)=>{
		document.querySelector('.premimg').src=e.target.result;
	}
	
	
	
}


function signup(){
	console.log('회원가입연동')
	
	let count=0;
	console.log(mmw)
	
	for(let i=0;i<checkconfirm.length;i++){
		if(checkconfirm[i].innerHTML=='OK'){count++;}
		}
		console.log(count);
		
		if(count==5 && mmw!=null ){ // ok가 5개이고 성별을 찍었으면 회원가입 성공
			
			let signupForm=document.querySelectorAll('.signupForm')[0];
			let signupFormData=new FormData(signupForm);
			signupFormData.set("mphone",mphone);//인풋 3개 합쳐진거는 따로 가져가기
			
			$.ajax({
				url:"/oimarket/member/info",
				method:"post",
				data:signupFormData,
				contentType:false,
				processData:false,
				success:(r)=>{
					console.log('연동성공')
					console.log(r)
					if(r=='true'){
						alert('가입을 축하합니다.')
						location.href="/oimarket/index.jsp" //로그인창으로 이동
					}
				}
				
			});
		}else{
			alert('빈칸을 모두 채워주세요')
		}
	
	
	
	
	
};
