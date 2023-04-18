package oimarket.control.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oimarket.model.dao.ProductDao;

/**
 * Servlet implementation class Productview
 */
@WebServlet("/productview")
public class Productview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Productview() {
        super();
       
    }

	//조회수
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno=Integer.parseInt(request.getParameter("pno")) ;System.out.println("pno:"+pno);
		boolean result= ProductDao.getInstance().view(pno);
		response.getWriter().print(result);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
