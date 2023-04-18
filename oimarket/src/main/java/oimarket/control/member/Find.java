package oimarket.control.member;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oimarket.model.dao.MemberDao;


@WebServlet("/find")
public class Find extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Find() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type=Integer.parseInt(request.getParameter("type"));
		
		String result=null;
		if(type==1) {//아이디 찾기
			String mname=request.getParameter("mname");
			String mphone=request.getParameter("mphone");
			 result=MemberDao.getInstance().findid(mname,mphone);
			
			
		}else if(type==2) {//비밀번호찾기
			String mid=request.getParameter("mid");
			String mphone=request.getParameter("mphone");
			
			String ranStr="abcdefghijklmnopqystuvwxyz1234567890";
			String newpwd="";
			//비밀번호랜덤
			for(int i=0; i<10; i++) {
				Random random =new Random();
				int ran=random.nextInt(ranStr.length());
				newpwd+=ranStr.charAt(ran);
			}//for end
			
			System.out.println("newpwd:"+newpwd);
			result=MemberDao.getInstance().findpwd(mid,mphone,newpwd);
		}
		
		response.getWriter().print(result);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
	}

}
