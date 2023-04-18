package oimarket.model.dto;

public class chatcategoryDto {
	private long cno; 
	private int pno ; 
	private int cstate ;										
	private int mno1 ;
	private int mno2;
	
	private String mid1 ; // mno1 에 해당 하는 아이디 
	private String mid2 ; // mno2 에 해당 하는 아이디 
	
	private String ccontent; // 최신 글 1개 
	private String mpimg; // 대표 이미지 
	private String cdate; // 대표 이미지 
	
	
	
	
	
	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getMid1() {
		return mid1;
	}

	public void setMid1(String mid1) {
		this.mid1 = mid1;
	}

	public String getMid2() {
		return mid2;
	}

	public void setMid2(String mid2) {
		this.mid2 = mid2;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getMpimg() {
		return mpimg;
	}

	public void setMpimg(String mpimg) {
		this.mpimg = mpimg;
	}

	public chatcategoryDto() {
		// TODO Auto-generated constructor stub
	}

	public chatcategoryDto(long cno, int pno, int cstate, int mno1, int mno2) {
		super();
		this.cno = cno;
		this.pno = pno;
		this.cstate = cstate;
		this.mno1 = mno1;
		this.mno2 = mno2;
	}

	public long getCno() {
		return cno;
	}

	public void setCno(long cno) {
		this.cno = cno;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int getCstate() {
		return cstate;
	}

	public void setCstate(int cstate) {
		this.cstate = cstate;
	}

	public int getMno1() {
		return mno1;
	}

	public void setMno1(int mno1) {
		this.mno1 = mno1;
	}

	public int getMno2() {
		return mno2;
	}

	public void setMno2(int mno2) {
		this.mno2 = mno2;
	}

	@Override
	public String toString() {
		return "chatcategoryDto [cno=" + cno + ", pno=" + pno + ", cstate=" + cstate + ", mno1=" + mno1 + ", mno2="
				+ mno2 + "]";
	}
	

}
