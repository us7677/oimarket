package oimarket.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Session;

import oimarket.control.chat.chatting;

public class MessageDto {
	private int nno	;
	private String ncontent;					
	private String ndate;			
	private int cno;					
	private int frommno;
	private int tomno;	
	
	//추가 
	private String tomname;
	private String frommname;
	
	
	public String getFrommname() {
		return frommname;
	}


	public void setFrommname(String frommname) {
		this.frommname = frommname;
	}


	public String getTomname() {
		return tomname;
	}


	public void setTomname(String tomname) {
		this.tomname = tomname;
	}


	public MessageDto() {
		// TODO Auto-generated constructor stub
	}


	public MessageDto(int nno, String ncontent, String ndate, int cno, int frommno, int tomno) {
		super();
		this.nno = nno;
		this.ncontent = ncontent;
		this.ndate = ndate;
		this.cno = cno;
		this.frommno = frommno;
		this.tomno = tomno;
	}


	public int getNno() {
		return nno;
	}


	public void setNno(int nno) {
		this.nno = nno;
	}


	public String getNcontent() {
		return ncontent;
	}


	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}


	public String getNdate() {
		return ndate;
	}


	public void setNdate(String ndate) {
		this.ndate = ndate;
	}


	public int getcno() {
		return cno;
	}


	public void setcno(int cno) {
		this.cno = cno;
	}


	public int getFrommno() {
		return frommno;
	}


	public void setFrommno(int frommno) {
		this.frommno = frommno;
	}


	public int getTomno() {
		return tomno;
	}


	public void setTomno(int tomno) {
		this.tomno = tomno;
	}


	@Override
	public String toString() {
		return "MessageDto [nno=" + nno + ", ncontent=" + ncontent + ", ndate=" + ndate + ", cno=" + cno + ", frommno="
				+ frommno + ", tomno=" + tomno + "]";
	}
	
	
	
	

}
