package oimarket.control.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oimarket.model.dao.MemberDao;
import oimarket.model.dto.MemberDto;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() { super(); }

    // [최성아] 로그인 성공 후 정보 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = (String)request.getSession().getAttribute("login");
				
		MemberDto result = MemberDao.getInstance().getMember( mid );
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(json);
		
		
	}
	// [최성아] 로그인 성공 여부 체크
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String mpwd = request.getParameter("mpwd");
				
		boolean result = MemberDao.getInstance().login( mid , mpwd );
		
		System.out.println(result);
		
		if (result == true) {
			request.getSession().setAttribute("login", mid);
		}
		response.getWriter().print(result);}

	// [최성아] 회원 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getSession().getServletContext().getRealPath("/img");
		MultipartRequest multi = new MultipartRequest(
				request, path , 1024 * 1024 * 10 , "UTF-8" , new DefaultFileRenamePolicy() );
		
		String mid = (String)request.getSession().getAttribute("login");
		String newmimg = multi.getFilesystemName("newmimg"); 
		String newmname = multi.getParameter("newmname");
		String newmphone = multi.getParameter("newmphone");	
		String newmresidence = multi.getParameter("newmresidence");
		String mpwd = multi.getParameter("mpwd");
		String newmpwd = multi.getParameter("newmpwd");
		String defaultimg = multi.getParameter("defaultimg");
		
		// 첨부파일 if
		if ( newmimg == null ) { // 기존 이미지 파일 그대로 사용
			newmimg = MemberDao.getInstance().getMember( mid ).getMimg();
		} 
		// 3. 만약에 기본프로필 사용체크 했으면
		if ( defaultimg.equals("true") ) { // 기본프로필 사용
			newmimg = null;
		}
		
		boolean result = MemberDao.getInstance().update(mid, newmname, mpwd, newmpwd, newmresidence, newmphone, newmimg);
		
		System.out.println(result);
		
		response.getWriter().print(result);		
		
	}

	// [최성아] 회원 탈퇴
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = (String)request.getSession().getAttribute("login");
		String mpwd = request.getParameter("mpwd");
		
		boolean result = MemberDao.getInstance().delete(mid, mpwd);
		response.getWriter().print(result);
		
	}

}
