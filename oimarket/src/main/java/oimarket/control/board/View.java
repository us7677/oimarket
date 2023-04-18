package oimarket.control.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oimarket.model.dao.BoardDao;

/**
 * Servlet implementation class View
 */
@WebServlet("/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 타입과 게시물 번호요청
		int type = Integer.parseInt(request.getParameter("type"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		// PC 네트워크 식별자 [ip]
			// 현재 요한 클라이언트의 ip확인
		String ip = request.getRemoteAddr();
			System.out.println("ip :" +ip);
		// 만약 기존 세션기록이 없으면 증가 가능 아니면 불가능
		Object o = request.getSession().getAttribute(type + ip + bno);
		if(o==null) { // 세션[ bno+type+ip]가 생성된 적이 없다. ]
			
			// 세션 생성[bno + type + ip]
			request.getSession().setAttribute(type + ip + bno, 1 ); // 각각 타입마다 , ip마다 , 게시물마다 1개씩
			// 세션에 생명주기[생명주기 지나면 자동으로 메모리 삭제 / 초 단위]
			request.getSession().setMaxInactiveInterval(60*60*24);	//60*60*24 → 하루
		
			BoardDao.getInstance().bIncrease(type,bno);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
