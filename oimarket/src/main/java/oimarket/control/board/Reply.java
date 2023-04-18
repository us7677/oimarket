package oimarket.control.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import oimarket.model.dao.BoardDao;
import oimarket.model.dao.MemberDao;
import oimarket.model.dto.ReplyDto;

/**
 * Servlet implementation class Reply
 */
@WebServlet("/reply")
public class Reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno")); 		System.out.println("bno : " +bno);
		int type = Integer.parseInt(request.getParameter("type"));		System.out.println("type : " +type);
		int rindex = 0;
		
		if(type == 1) {
			
		}else if(type==2) {
			rindex = Integer.parseInt(request.getParameter("rindex"));
		}
		
		ArrayList<ReplyDto> result = BoardDao.getInstance().getReplyList(bno,rindex);
		System.out.println("result : " +result);
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		String rcontent = request.getParameter("rcontent");		
		int type = Integer.parseInt(request.getParameter("type"));
		
		ReplyDto dto = new ReplyDto(rcontent, mno, bno);
		if(type==1) {		
		}else if(type == 2) {
			int rindex = Integer.parseInt(request.getParameter("rindex"));
			dto = new ReplyDto(rcontent, mno, bno);
			dto.setRindex(rindex);
		}
		
			System.out.println("ReplyDto : " +dto);
			
		boolean result = BoardDao.getInstance().rwrite(dto);
		
		response.getWriter().print(result);
	}

}
