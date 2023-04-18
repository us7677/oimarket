package oimarket.control.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oimarket.model.dao.MemberDao;
import oimarket.model.dao.MypageDao;

@WebServlet("/mview")
public class Mview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Mview() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login"));
		Object o = request.getSession().getAttribute(mno + ip);
		if ( o == null ) {
			request.getSession().setAttribute(mno + ip, 1);
			request.getSession().setMaxInactiveInterval(60*60*24);
			// 60*60*24
			
			MypageDao.getInstance().viewIncrease(mno);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
