package oimarket.control.product;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import oimarket.model.dao.BoardDao;
import oimarket.model.dao.MemberDao;
import oimarket.model.dao.ProductDao;
import oimarket.model.dto.MemberDto;
import oimarket.model.dto.ProductDto;


@WebServlet("/product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Product() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//제품 출력 [type 1 : 전체출력 / type 2 : 개별출력]
		
		String type=request.getParameter("type");
		ObjectMapper mapper=new ObjectMapper();
		String jsonArray=null;
		if(type.equals("1")) { //판매등록된 모든 상품을 출력한다
			int pcno=Integer.parseInt(request.getParameter("pcno") );
			int pcount=Integer.parseInt(request.getParameter("pcount") );
			ArrayList<ProductDto> result= ProductDao.getInstance().getproductlist(pcno,pcount); 
			 jsonArray=mapper.writeValueAsString(result);
			
			 
			
		}else if (type.equals("2")) {//pno를 가지고 판매한 맴버를 출력하기
			int pno=Integer.parseInt(request.getParameter("pno")) ;
			MemberDto result= ProductDao.getInstance().getproductmember(pno);
			jsonArray=mapper.writeValueAsString(result);
			
			
		}else if (type.equals("3")) { //셀렉트값으로(카테고리) 출력하기
			String keyword=request.getParameter("keyword") ;System.out.println(keyword);
			ArrayList<ProductDto> result= ProductDao.getInstance().search(keyword);
			jsonArray=mapper.writeValueAsString(result);
			
			
		}else if (type.equals("4")) { //pno를 가지고 판매한 product 정보 출력하기
			int pno=Integer.parseInt(request.getParameter("pno")) ;
			ProductDto result= ProductDao.getInstance().getproduct(pno);
			jsonArray=mapper.writeValueAsString(result);
			
			
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray); 
	}

	//제품등록 //
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//첨부파일 여러개 하기위해 commons.jar 사용
		request.setCharacterEncoding("UTF-8");
		String path=request.getSession().getServletContext().getRealPath("/img");//다운로드할 서버경로
		System.out.println("경로:"+path);
		//해당 파일을 객체화시키기
		File setpath=new File(path);
		DiskFileItemFactory fileset= new DiskFileItemFactory(); 
		fileset.setRepository(setpath);	//파일저장소 위치 대입
		fileset.setSizeThreshold(1024*1024*10);	//파일저장소에 저장할수 있는 최대용량 범위
		fileset.setDefaultCharset("UTF-8"); //파일저장소 한글 인코딩 타입
		
		ServletFileUpload fileupload=new ServletFileUpload(fileset);
		try {//5.매개변수 요청해서 리스트에 담기 [무조건 예외처리 발생]
			List<FileItem> fileList = fileupload.parseRequest(request);
			//*DB에 저장할 데이터를 분류
			List<String> fieldlist= new ArrayList<>();//일반필드 첨부파일 없는거
			List<String> filefieldlist= new ArrayList<>();//첨부파일 목록필드
			
			for(FileItem item:fileList) {//요청된 모든 매개변수들을 반복문 돌려서 확인
				if(item.isFormField()) {
					System.out.println("[첨부파일 아닌 필드명:]"+item.getFieldName());
					System.out.println("[첨부파일 아닌 필드명의 값]:"+item.getString());//필드의 입력값
					fieldlist.add(item.getString());//첨부파일이 아니면 여기에다가 저장
					
				}else {
					System.out.println("첨부파일 인 필드명:"+item.getFieldName());
					System.out.println("첨부파일 인 필드의 파일명:"+item.getName());
				
					String filename=UUID.randomUUID()+" "+( item.getName().replaceAll(" ", "-"));//첨부파일 식별이름
					filefieldlist.add(filename);//식별되어진 첨부파일을 리스트에서 저장
					File upload= new File(path+"/"+filename);
					item.write(upload);
					
				
				}
			}
			System.out.println(fieldlist.toString());
			System.out.println(filefieldlist.toString());
			
			int mno=MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")) ;
			//int mno= Integer.parseInt(request.getParameter("mno"));
			//int pcno=Integer.parseInt(request.getParameter("pcno")) ;
			System.out.println("mno!!!!!:"+mno);
			
			//dto 구성하기: 입력한것을 담아서 dao에 보낸다
			ProductDto dto=new ProductDto(fieldlist.get(1), fieldlist.get(2), Integer.parseInt(fieldlist.get(3)) ,
					fieldlist.get(4), fieldlist.get(5), mno, Integer.parseInt(fieldlist.get(0)) ,filefieldlist);
					System.out.println("dto!!!!!!!!!!!:"+dto);
				
					
			
			//dao 구성
			boolean result=ProductDao.getInstance().productPrint(dto);
			response.getWriter().print(result);
			
		} catch (Exception e) {
		System.out.println("파일저장 실패");
		}
		
		
		
	}

	//제품수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String path=request.getSession().getServletContext().getRealPath("/img");
	 System.out.println("경로:"+path);
		//해당 파일을 객체화시키기
		File setpath=new File(path);
		DiskFileItemFactory fileset= new DiskFileItemFactory(); 
		fileset.setRepository(setpath);	//파일저장소 위치 대입
		fileset.setSizeThreshold(1024*1024*10);	//파일저장소에 저장할수 있는 최대용량 범위
		fileset.setDefaultCharset("UTF-8"); //파일저장소 한글 인코딩 타입
		
		ServletFileUpload fileupload=new ServletFileUpload(fileset);
		try {//5.매개변수 요청해서 리스트에 담기 [무조건 예외처리 발생]
			List<FileItem> fileList = fileupload.parseRequest(request);
			//*DB에 저장할 데이터를 분류
			List<String> fieldlist= new ArrayList<>();//일반필드 첨부파일 없는거
			List<String> filefieldlist= new ArrayList<>();//첨부파일 목록필드
			
			
			
			for(FileItem item:fileList) {//요청된 모든 매개변수들을 반복문 돌려서 확인
				if(item.isFormField()) {
					System.out.println("[첨부파일 아닌 필드명:]"+item.getFieldName());
					System.out.println("[첨부파일 아닌 필드명의 값]:"+item.getString());//필드의 입력값
					fieldlist.add(item.getString());//첨부파일이 아니면 여기에다가 저장
					
				}else {
					System.out.println("첨부파일 인 필드명:"+item.getFieldName());
					System.out.println("첨부파일 인 필드의 파일명:"+item.getName());
				
					
					String filename=UUID.randomUUID()+" "+( item.getName().replaceAll(" ", "-"));//첨부파일 식별이름
					filefieldlist.add(filename);//식별되어진 첨부파일을 리스트에서 저장
					File upload= new File(path+"/"+filename);
					item.write(upload);
					
				
				}
			}
			System.out.println(fieldlist.toString());
			System.out.println(filefieldlist.toString());
			
			//int mno=MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")) ;
			//int pno=Integer.parseInt(request.getParameter("pno")) ;System.out.println("pno들어와:"+pno);
			//int mno= Integer.parseInt(request.getParameter("mno"));
			//int pcno=Integer.parseInt(request.getParameter("pcno")) ;
			//System.out.println("mno!!!!!:"+mno);
			
			//dto 구성하기: 입력한것을 담아서 dao에 보낸다
			ProductDto dto= new ProductDto(Integer.parseInt(fieldlist.get(4)), fieldlist.get(1), fieldlist.get(2), 
					Integer.parseInt(fieldlist.get(3)), fieldlist.get(5), fieldlist.get(6),Integer.parseInt(fieldlist.get(0)), filefieldlist);
					System.out.println("dto:"+dto);
			
			//dao 구성
			boolean result=ProductDao.getInstance().updatebtn(dto);
			response.getWriter().print(result);
			
		} catch (Exception e) {
			System.out.println("파일저장 실패"+e);
			}
			
	}
	

	//제품삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno=Integer.parseInt(request.getParameter("pno")); System.out.println("pno:"+pno);
		//dao에게 보내서 결과값 받기
		boolean result=ProductDao.getInstance().Deleteproduct(pno);
		//응답
		response.getWriter().print(result);
		
		
	}

}
