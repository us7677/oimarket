let pno=document.querySelector('.pno').value;

//김은영//물품수정
function Updatebtn(){
   console.log('수정함수');
   let productform=document.querySelectorAll('.productform')[0]
   let productformDate=new FormData(productform);
   productformDate.set("pno",pno);console.log("pno!!번호:"+pno);
   productformDate.set("plat",plat);
   productformDate.set("plng",plng);

   $.ajax({
      url:"/oimarket/product",
      method:"put",
      data:productformDate,
      contentType:false,
      processData:false,
      success:(r)=>{
         console.log('통신통신');
         console.log(r)
         if(r=='true'){
			 alert('수정완료 했습니다.')
			 location.href="/oimarket/main.jsp"
		 }else{
			 alert('수정실패 했습니다.')
		 }
      }
   })

}
//