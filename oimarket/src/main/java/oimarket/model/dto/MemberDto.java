package oimarket.model.dto;

public class MemberDto {
	private int mno;
	private String mname;
	private String mid;
	private String mpwd ;
	private String mresidence; 
	private String mmw;						
	private String mphone;
    private String mimg;		
    
    
    public MemberDto() {//깡통
		// TODO Auto-generated constructor stub
	}


	public MemberDto(int mno, String mname, String mid, String mpwd, String mresidence, String mmw,
			String mphone, String mimg) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mid = mid;
		this.mpwd = mpwd;
		this.mresidence = mresidence;
		this.mmw = mmw;
		this.mphone = mphone;
		this.mimg = mimg;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getMpwd() {
		return mpwd;
	}


	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}


	public String getMresidence() {
		return mresidence;
	}


	public void setMresidence(String mresidence) {
		this.mresidence = mresidence;
	}



	public String getMmw() {
		return mmw;
	}


	public void setMmw(String mmw) {
		this.mmw = mmw;
	}


	public String getMphone() {
		return mphone;
	}


	public void setMphone(String mphone) {
		this.mphone = mphone;
	}


	public String getMimg() {
		return mimg;
	}


	public void setMimg(String mimg) {
		this.mimg = mimg;
	}

	
	// 4. 회원정보호출용 생성자
	public MemberDto(int mno, String mname, String mid, String mresidence, String mmw, String mphone, String mimg) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.mid = mid;
		this.mresidence = mresidence;
		this.mmw = mmw;
		this.mphone = mphone;
		this.mimg = mimg;
	}	
	

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", mname=" + mname + ", mid=" + mid + ", mpwd=" + mpwd + ", mresidence="
				+ mresidence + ", mmw=" + mmw + ", mphone=" + mphone + ", mimg=" + mimg + "]";
	}




	
}
