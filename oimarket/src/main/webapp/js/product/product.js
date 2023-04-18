console.log('열림라라라라');


let plat=0;
let plng=0;
//1.물품등록
function productbtn(){
	console.log('함수실행')
	//let mno=memberInfo.mno
	//console.log(mno)
	//1. 폼전송
	let productform =document.querySelectorAll('.productform')[0];
	let productformDate= new FormData(productform);//2.폼데이터 객체선언하기
	console.log(productformDate);//확인용
	productformDate.set("plat",plat);
	productformDate.set("plng",plng);

	//productformDate.set("mno",mno);
	//productformDate.set("mno",);
	//서블릿으로 보내기
		
	$.ajax({
		url:"/oimarket/product",
		method:"post",
		data:productformDate,
		contentType:false,
		processData:false,
		success:(r)=>{
			console.log(' 통신완료');
			console.log(r);
			if(r=='true'){
				alert('제품을 등록하였습니다')
				location.href="/oimarket/main.jsp"
			}else{
				alert('제품등록이 실패했습니다')
			}
			
			
			
		}
	})

	
}

	//---------------------------카카오 지도를 표시할 div 객체----------------------
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
//---------------------------- 지도를 클릭한 위치에 표출할 마커입니다------------------
var marker = new kakao.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
// 지도에 마커를 표시합니다
marker.setMap(map);
//---------------------------- 지도에 클릭 이벤트를 등록합니다-------------------------
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
   
    var latlng = mouseEvent.latLng;  // 클릭한 위도, 경도 정보를 가져옵니다 
    
  
    marker.setPosition(latlng);  // 마커 위치를 클릭한 위치로 옮깁니다
    plat = latlng.getLat();
      console.log("위도:"+latlng.getLat())
    plng= latlng.getLng();
  	  console.log("경도:"+latlng.getLng())
    
});


