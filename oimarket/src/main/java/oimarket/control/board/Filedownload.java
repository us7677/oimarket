package oimarket.control.board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/filedownload")
public class Filedownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Filedownload() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bfile = request.getParameter("bfile");
		System.out.println("bfile : " +bfile);
		
		// 다운받을 파일의 경로 찾기
		String path = request.getSession().getServletContext().getRealPath("/board/bfile/"+bfile);
			System.out.println("path:" +path);
		//
		File file = new File(path);
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(bfile , "UTF-8"));
		FileInputStream fin = new FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		fin.read(bytes);
		BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
		fout.write(bytes);
		fin.close();
		fout.flush();
		fout.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
