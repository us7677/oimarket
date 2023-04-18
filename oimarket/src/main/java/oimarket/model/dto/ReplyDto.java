package oimarket.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyDto {

	private int rno			;
	private String  rcontent;	
	private String  rdate	;	
	private int  rindex		;
	private int  mno		;	
	private int  bno		;
	// 추가 필드
	private String mid;
	private String mimg;
	
	public ReplyDto() {
		// TODO Auto-generated constructor stub
	}

	public ReplyDto(int rno, String rcontent, String rdate, int rindex, int mno, int bno, String mid, String mimg) {
		super();
		this.rno = rno;
		this.rcontent = rcontent;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = sdf.format(date);
		if(now.split(" ")[0].equals(rdate.split(" ")[0] ) ) {
			this.rdate=rdate.split(" ")[1];
		}else {
			this.rdate = rdate.split(" ")[0];
		}
		this.rindex = rindex;
		this.mno = mno;
		this.bno = bno;
		this.mid = mid;
		this.mimg = mimg;
	}

	public ReplyDto(String rcontent, int mno, int bno) {
		super();
		this.rcontent = rcontent;
		this.mno = mno;
		this.bno = bno;
	}

	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", rcontent=" + rcontent + ", rdate=" + rdate + ", rindex=" + rindex + ", mno="
				+ mno + ", bno=" + bno + ", mid=" + mid + ", mimg=" + mimg + "]";
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public int getRindex() {
		return rindex;
	}

	public void setRindex(int rindex) {
		this.rindex = rindex;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMimg() {
		return mimg;
	}

	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	
	
}
