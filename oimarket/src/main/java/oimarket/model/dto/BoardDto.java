package oimarket.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bfile;
	private String bdate;
	private int bview;
	private int bup;
	private int bdown;
	private int mno;
	private int bcno;
	// 추가 필드
	private String mid;
	private String mimg;
	private int rcount; // 댓글개수
	
	public String getMimg() {
		return mimg;
	}

	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	

	public int getRcount() {
		return rcount;
	}

	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	// 글쓰기용 생성자 [ 게시물제목 , 내용 , 첨부파일 , 회원번호 , 카테고리번호 ]
	public BoardDto(String btitle, String bcontent, String bfile, int mno, int bcno) {
		super();
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
		this.mno = mno;
		this.bcno = bcno;
	}
	
	
	public BoardDto(int bno, String btitle, String bcontent, String bfile, String bdate, int bview, int bup, int bdown,
			int mno, int bcno) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
		this.bdate = bdate;
		this.bview = bview;
		this.bup = bup;
		this.bdown = bdown;
		this.mno = mno;
		this.bcno = bcno;
	}

	// 게시글 출력용 생성자
		public BoardDto(int bno, String btitle, String bcontent, String bfile, String bdate, int bview, int bup, int bdown,
			int mno, int bcno, String mid) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
		// 오늘 날짜와 작성일이 동일하면 시간 표기 / 아니면 날짜 표기
					// 1. 오늘날짜[ Date클래스 : java.util ]
				Date date = new Date();
					// 2. DB에서 사용하고 있는 날짜형식과 동일하게 형변환
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String now = sdf.format(date);
					// 2. 만약에 오늘날짜와 작성일과 동일하면 [ split 문자열 자르기 ]
							// "yyyy-MM-dd hh:mm:ss".split(" ")-->  날짜 와 시간 분해
							// now.split(" ")[0] : 날짜		now.split(" ")[1] : 시간 
				if( now.split(" ")[0].equals( bdate.split(" ")[0] ) ) {
					this.bdate = bdate.split(" ")[1];	// 오늘과 같으면 시간 
				}else {
					this.bdate = bdate.split(" ")[0];	// 오늘과 다르면 날짜
				}
		this.bview = bview;
		this.bup = bup;
		this.bdown = bdown;
		this.mno = mno;
		this.bcno = bcno;
		this.mid = mid;
	}
	
	// [김동혁] 업데이트용 생성자
		public BoardDto(int bno, String btitle, String bcontent, String bfile, int bcno) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
		this.bcno = bcno;
	}

	// [최성아] mypage 게시물 출력용 생성자
		public BoardDto( int bcno , String btitle, String bdate, int bview, int bup, int bdown) {
			super();
			this.bcno = bcno;
			this.btitle = btitle;
			this.bdate = bdate;
			this.bview = bview;
			this.bup = bup;
			this.bdown = bdown;
		}		
		
	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bfile=" + bfile
				+ ", bdate=" + bdate + ", bview=" + bview + ", bup=" + bup + ", bdown=" + bdown + ", mno=" + mno
				+ ", bcno=" + bcno + "]";
	}





	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBfile() {
		return bfile;
	}

	public void setBfile(String bfile) {
		this.bfile = bfile;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBview() {
		return bview;
	}

	public void setBview(int bview) {
		this.bview = bview;
	}

	public int getBup() {
		return bup;
	}

	public void setBup(int bup) {
		this.bup = bup;
	}

	public int getBdown() {
		return bdown;
	}

	public void setBdown(int bdown) {
		this.bdown = bdown;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getBcno() {
		return bcno;
	}

	public void setBcno(int bcno) {
		this.bcno = bcno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	



}
