package oimarket.control.chat;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import oimarket.model.dao.MemberDao;
import oimarket.model.dao.ProductDao;
import oimarket.model.dao.chatdbDao;
import oimarket.model.dto.MessageDto;
import oimarket.model.dto.chatcategoryDto;

/**
 * Servlet implementation class chatDB
 */
@WebServlet("/chat/db")
public class chatDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public chatDB() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper mapper=new ObjectMapper();
		String jsonArray=null;
		String type=request.getParameter("type");
		
		
		String mid=(String)request.getSession().getAttribute("login");
		int frommno=MemberDao.getInstance().getMno(mid);//로그인한 회원의 mno
		
		
		
		if(type.equals("1")) {//채팅창에 내용 뽑기
			int cno=Integer.parseInt(request.getParameter("cno"));
			
			int pno=Integer.parseInt(request.getParameter("pno"));
			int tomno= ProductDao.getInstance().getproductmember(pno).getMno(); //판매자의 mno
			ArrayList<MessageDto> result=chatdbDao.getInstance().getchat(pno,frommno,tomno,cno);
			jsonArray=mapper.writeValueAsString(result);
		}
		
		else if(type.equals("2")) {// 채팅 목록 뽑기
			
			ArrayList<chatcategoryDto> result=chatdbDao.getInstance().getallchat(frommno);
			jsonArray=mapper.writeValueAsString(result);
		}
		else if(type.equals("3")) {// 채팅 목록 뽑기
			
			int pno=Integer.parseInt(request.getParameter("pno"));
			int result=chatdbDao.getInstance().getrmno(pno);
			jsonArray=mapper.writeValueAsString(result);
		}
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray); 
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ncontent=request.getParameter("textbox");
		int pno=Integer.parseInt(request.getParameter("pno"));
		int frommno=Integer.parseInt(request.getParameter("frommno"));
		int tomno=Integer.parseInt(request.getParameter("tomno"));
		
		boolean result=chatdbDao.getInstance().chatting(ncontent, pno, frommno , tomno );
		
		if( result ) {
			// 서버소켓에게 채팅을 받은 유저의 번호와 내용 을 전달 
			try {chatting.onMessage( null ,  tomno+","+ncontent );}
			catch (Exception e) { e.printStackTrace(); }
		}
		
		response.getWriter().print(result);
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
