package oimarket.model.dto;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ProductDto {

	private int pno;				//물품 고유번호
    private String ptitle	;		//물품 제목
    private String pcontent	;		//물품 내용
    private int pprice	;	 		//물품 가격
    private int pview	;			//조회수
    private String plat	;			//위도
    private String plng	;			//경도
    private String pdate;			//등록일
    private int pstate	;			//물품상태	[ 1 : 판매 가능 / 2 : 판매 완료 ]
    private String buydate	;		//구매일
    private int rmno;				//판매등록한 회원번호
    private int buymno;				//구매한 회원번호
    private int pcno;				//카테고리번호
	
    //추가필드
    private List<String>pimglist;	//등록한 사진목록들
    private String pcname;			//등록한 카테고리의 이름
    
    // [최성아 추가필드]
    private String divideDate;		// 판매현황 상태로 date 구분하기
    private String mainImg;			// 모든 물품 출력 관한 대표 img 
    
    // 추가필드 [ 찜수 ] 
    private int plikecount;
    
    private int ProductCategoryCount;	// 오늘 거래된 물품 별 카테고리 개수

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}//깡통생성자


	public ProductDto(int pno, String ptitle, String pcontent, int pprice, int pview, String plat, String plng,
			String pdate, int pstate, String buydate, int rmno, int buymno, int pcno, List<String> pimglist,
			String pcname) { //풀생성자
		super();
		this.pno = pno;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.pprice = pprice;
		this.pview = pview;
		this.plat = plat;
		this.plng = plng;
		
				// -- 날짜 형식 
			
				LocalDateTime 현재날짜시간 = LocalDateTime.now(); // 현재 날짜/시간 
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 날짜형식 
				LocalDateTime 제품등록날짜 = LocalDateTime.parse(pdate, formatter); // 문자형태[DB온 날짜] 의 날짜를 날짜형식으로 변환
			
				// 현재날짜/시간 과 제품등록날짜의 분 차이 구하기 
				Duration diff = Duration.between( 제품등록날짜.toLocalTime() , 현재날짜시간.toLocalTime() ) ;
				long minute = diff.toMinutes();
				long hour = diff.toHours();
				
				// 현재날짜/시간 과 제품등록날짜의 일 차이 구하기 
				Period diff2 = Period.between(제품등록날짜.toLocalDate() , 현재날짜시간.toLocalDate() ) ;
				long day = diff2.getDays();
				
				
				// 1. 오늘이면서 한시간 전 
				if( day == 0 && minute <= 60 ) { this.pdate = minute+"분 전"; }
				// 2. 오늘이면서 한시간 이후 이면서 하루 전이면 
				else if( day == 0 && minute >=60  ) {  
					this.pdate = ( hour )+"시간 전"; 
				}
				// 2. 하루 지났을떄 
				else {  this.pdate = ( day )+"일 전"; }
				
				
		this.pstate = pstate;
		this.buydate = buydate;
		this.rmno = rmno;
		this.buymno = buymno;
		this.pcno = pcno;
		this.pimglist = pimglist;
		this.pcname = pcname;
	}





	//제품 등록용
    public ProductDto(String ptitle, String pcontent, int pprice, String plat, String plng, int rmno, int pcno,
    		List<String> pimglist) {
    	super();
    	this.ptitle = ptitle;
    	this.pcontent = pcontent;
    	this.pprice = pprice;
    	this.plat = plat;
    	this.plng = plng;
    	this.rmno = rmno;
    	this.pcno = pcno;
    	this.pimglist = pimglist;
    }
 
    
    
    
    
    
    //수정용
    public ProductDto(int pno, String ptitle, String pcontent, int pprice, String plat, String plng,  int pcno,
    		List<String> pimglist) {
    	super();
    	this.pno = pno;
    	this.ptitle = ptitle;
    	this.pcontent = pcontent;
    	this.pprice = pprice;
    	this.plat = plat;
    	this.plng = plng;
	
    	this.pcno = pcno;
    	this.pimglist = pimglist;
    }
	




	// [최성아] 1. mypage 등록한 제품 출력용 + 4. mypage 찜한 제품 출력용 
	public ProductDto(int pno, int pcno , String ptitle, int pprice, String divideDate , int pstate) {
		super();
		this.pno = pno;
		this.pcno = pcno;
		this.ptitle = ptitle;
		this.pprice = pprice;
		this.divideDate = divideDate;
		this.pstate = pstate;
	}		
    
	





	// [최성아] 2. mypage 판매 제품 출력용 + 3. mypage 구매 제품 출력용
	public ProductDto(int pno , int pcno ,String ptitle, int pprice, String pdate ) {
		super();
		this.pno = pno;
		this.pcno = pcno;
		this.ptitle = ptitle;
		this.pprice = pprice;
		this.pdate = pdate;	
	}		
	
	// [최성아] 3. 오늘 거래된 물품 카테고리별 개수 출력
	public ProductDto(int productCategoryCount , String pcname) {
		super();
		this.ProductCategoryCount = productCategoryCount;
		this.pcname = pcname;
	}	
	

	public int getPno() {
		return pno;
	}


	public void setPno(int pno) {
		this.pno = pno;
	}


	public String getPtitle() {
		return ptitle;
	}


	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}


	public String getPcontent() {
		return pcontent;
	}


	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}


	public int getPprice() {
		return pprice;
	}


	public void setPprice(int pprice) {
		this.pprice = pprice;
	}


	public int getPview() {
		return pview;
	}


	public void setPview(int pview) {
		this.pview = pview;
	}


	public String getPlat() {
		return plat;
	}


	public void setPlat(String plat) {
		this.plat = plat;
	}


	public String getPlng() {
		return plng;
	}


	public void setPlng(String plng) {
		this.plng = plng;
	}


	public String getPdate() {
		return pdate;
	}


	public void setPdate(String pdate) {
		this.pdate = pdate;
	}


	public int getPstate() {
		return pstate;
	}


	public void setPstate(int pstate) {
		this.pstate = pstate;
	}


	public String getBuydate() {
		return buydate;
	}


	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}


	public int getRmno() {
		return rmno;
	}


	public void setRmno(int rmno) {
		this.rmno = rmno;
	}


	public int getBuymno() {
		return buymno;
	}


	public void setBuymno(int buymno) {
		this.buymno = buymno;
	}


	public int getPcno() {
		return pcno;
	}


	public void setPcno(int pcno) {
		this.pcno = pcno;
	}


	public List<String> getPimglist() {
		return pimglist;
	}


	public void setPimglist(List<String> pimglist) {
		this.pimglist = pimglist;
	}


	public String getPcname() {
		return pcname;
	}


	public void setPcname(String pcname) {
		this.pcname = pcname;
	}

	public String getDivideDate() {
		return divideDate;
	}


	public void setDivideDate(String divideDate) {
		this.divideDate = divideDate;
	}
	
    public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public int getPlikecount() {
		return plikecount;
	}





	public void setPlikecount(int plikecount) {
		this.plikecount = plikecount;
	}


	
	public int getProductCategoryCount() {
		return ProductCategoryCount;
	}

	public void setProductCategoryCount(int productCategoryCount) {
		ProductCategoryCount = productCategoryCount;
	}
	

	
	@Override
	public String toString() {
		return "ProductDto [pno=" + pno + ", ptitle=" + ptitle + ", pcontent=" + pcontent + ", pprice=" + pprice
				+ ", pview=" + pview + ", plat=" + plat + ", plng=" + plng + ", pdate=" + pdate + ", pstate=" + pstate
				+ ", buydate=" + buydate + ", rmno=" + rmno + ", buymno=" + buymno + ", pcno=" + pcno + ", pimglist="
				+ pimglist + ", pcname=" + pcname + "]";
	}
	
	

	
	
	
	
	
	
}
