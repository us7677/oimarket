package oimarket.control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oimarket.model.dao.MemberDao;
import oimarket.model.dao.ProductDao;

/**
 * Servlet implementation class Productlike
 */
@WebServlet("/productlike")
public class Productlike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Productlike() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno=Integer.parseInt(request.getParameter("pno")) ;System.out.println("pno:"+pno);
		int mno=MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")) ; System.out.println("mno:"+mno);
		
		boolean result=ProductDao.getInstance().getlike(pno,mno);
		response.getWriter().print(result);
		
	}

	//하트 눌렀을때 안에 채우기 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno=Integer.parseInt(request.getParameter("pno")) ;System.out.println("pno:"+pno);
		int mno=MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")) ; System.out.println("mno:"+mno);
		
		boolean result=ProductDao.getInstance().setlike(pno,mno);
		response.getWriter().print(result);
	}

}
