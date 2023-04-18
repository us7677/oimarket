package oimarket.control.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oimarket.model.dao.BoardDao;
import oimarket.model.dao.MemberDao;
import oimarket.model.dto.BoardDto;


@WebServlet("/boardinfo")
public class Boardinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Boardinfo() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		if(type==1) { // 카테고리별 게시물 출력
		
		// 카테고리 번호 가져오기
		int bcno = Integer.parseInt(request.getParameter("bcno"));	System.out.println("카테고리 번호 : " +bcno);
		
		// 검색에 필요한 매개변수 가져오기
		String key = request.getParameter("key");			System.out.println("검색 키(제목 등) :" +key);
		String keyword = request.getParameter("keyword");	System.out.println("검색 내용 : " +keyword);
		// ------------------------------------------------------ //
		ArrayList<BoardDto> result = BoardDao.getInstance().getBoardList(key , keyword , bcno); // 카테고리 별로 가져오기 위해 카테고리번호도 인수로 가져감
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);}
		// ------------------------------------------------------ //
		else if(type==2) { // 게시물 개별 출력
			int bno = Integer.parseInt(request.getParameter("bno"));	System.out.println("bno : " + bno); // bno 입력받은 값 가져오기
			BoardDto result = BoardDao.getInstance().getBoard(bno);					// Dao에 bno넣어서 응답 값 가져오기
			// 형변환
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(result);
			// js로 반환
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(json);}
		// ------------------------------------------------------ //
		else if(type==3) { // 게시물 전체 출력
			// 검색에 필요한 매개변수 가져오기
			String key = request.getParameter("key");			System.out.println("검색 키(제목 등) :" +key);
			String keyword = request.getParameter("keyword");	System.out.println("검색 내용 : " +keyword);
			// ------------------------------------------------------ //
			ArrayList<BoardDto> result = BoardDao.getInstance().getBoardListAll(key , keyword);
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업로드 한 파일 저장할 서버의 경로 찾기
		String path = request.getSession().getServletContext().getRealPath("/board/bfile");
			System.out.println("path : " +path);

		MultipartRequest multi = new MultipartRequest(
				request, path , 1024*1024*10 , "UTF-8" , new DefaultFileRenamePolicy());
			System.out.println("파일복사 : " + multi);
			
		int bcno = Integer.parseInt(multi.getParameter("bcno"));	System.out.println("카테고리번호 : " +bcno);
		String btitle = multi.getParameter("btitle");				System.out.println("게시물제목 : " +btitle);
		String bcontent = multi.getParameter("bcontent");			System.out.println("게시물내용 : " +bcontent);
		String bfile = multi.getFilesystemName("bfile");			System.out.println("파일이름 : " +bfile);
		
		String mid = (String)request.getSession().getAttribute("login"); // 로그인세션에서 로그인중인 아이디 가져와서
		int mno = MemberDao.getInstance().getMno(mid);					 // MemberDao에 있는 getMno에 아이디를 넣어서 회원번호 꺼내고
		if(mno<1) {response.getWriter().print("false");}				 // 회원번호가 1보다 작으면(모든 회원번호는 1이상) 글쓰기 불가
		
		BoardDto dto = new BoardDto(btitle, bcontent, bfile, mno, bcno);
			System.out.println("Dto : " +dto);
			
		boolean result = BoardDao.getInstance().bwrite(dto);
		
		response.getWriter().print(result);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/board/bfile");
		MultipartRequest multi = new MultipartRequest(request, path , 1024*1024*10 , "UTF-8" , new DefaultFileRenamePolicy());
		
		int bno = Integer.parseInt(multi.getParameter("bno"));
		int bcno = Integer.parseInt(multi.getParameter("bcno"));
		String btitle = multi.getParameter("btitle");
		String bcontent = multi.getParameter("bcontent");
		String bfile = multi.getFilesystemName("bfile");
		
		String oldfile = BoardDao.getInstance().getBoard(bno).getBfile();
		
		if(bfile==null) {
			bfile = oldfile;
		}else {
			String filepath = request.getSession().getServletContext().getRealPath("/board/bfile/"+oldfile);
			File file = new File(path); if(file.exists())file.delete();			
		}
		
		BoardDto updatedto = new BoardDto(bno, btitle, bcontent, bfile, bcno);
			System.out.println("update dto : " +updatedto);
		boolean result = BoardDao.getInstance().bupdate(updatedto);
		
		response.getWriter().print(result);
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));	System.out.println("bno : " +bno);
		int type = Integer.parseInt(request.getParameter("type"));	System.out.println("type : " +type);
		// 게시글만 삭제하면 서버에 올려둔 파일은 그대로 남아있기 때문에 해당게시물에 파일 있는지 화긴
		String bfile = BoardDao.getInstance().getBoard(bno).getBfile();
		
		boolean result = true; // 파일이 존재한다면?
		if(type == 1) { // db 레코드 삭제 + 파일도 삭제
			// [ 1번은 ] db 삭제처리
			result = BoardDao.getInstance().bdelete(bno);
		}else if(type == 2) { // 수정시 파일만 삭제
			result = BoardDao.getInstance().bfiledelete(bno);
		}
		if(result) {
			String path = request.getSession().getServletContext().getRealPath("/board/bfile/"+bfile);
			File file = new File(path); // 객체화
			if(file.exists()) { // 만약 파일이 있다면
				file.delete(); // 파일 삭제
			}
		}
		response.getWriter().print(result);
	}

}



















