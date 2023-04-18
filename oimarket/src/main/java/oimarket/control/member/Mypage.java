package oimarket.control.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import oimarket.model.dao.MemberDao;
import oimarket.model.dao.MypageDao;
import oimarket.model.dto.BoardDto;
import oimarket.model.dto.ProductDto;

@WebServlet("/member/mypage")
public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Mypage() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 각 type 받기 [ 1 : 등록 / 2 : 판매 / 3 : 구매 / 4 : 찜하기 / 5 : 게시물 ]
		int type = Integer.parseInt(request.getParameter("type") );
		// mypage 출력부 전부 mno 필요해서 따로 빼놓음
		int mno=MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login")) ;
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// String result = null;
		
		// mypage 등록한 물품 출력
		if ( type == 1 ) {
			int rmno = mno ;
						
			ArrayList<ProductDto> result = MypageDao.getInstance().MypageRegisterProductList( rmno );						
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
		}
		
		// mypage 판매한 물품 출력
		else if ( type == 2 ) {
			int rmno = mno;
						
			ArrayList<ProductDto> result = MypageDao.getInstance().MypageSellProductList( rmno  );						
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
		}
		
		// mypage 구매한 물품 출력
		else if ( type == 3 ) {
			int buymno = mno;
						
			ArrayList<ProductDto> result = MypageDao.getInstance().MypageBuyProductList( buymno );						
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
		}
		
		// mypage 찜한 물품 출력
		else if ( type == 4 ) { 			
			ArrayList<ProductDto> result = MypageDao.getInstance().MypageLikeProductList( mno );						
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);	
		}
		
		
		// mypage 게시물 출력
		else if ( type == 5 ) { // 게시물 출력 start
			
			ArrayList<BoardDto> result = MypageDao.getInstance().MypageBoardList( mno );			
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
			
		} // type : 5 , 게시물 출력 end
		
		// type 6 : 방문자 수 체크
		else if ( type == 6 ) {
			int result = MypageDao.getInstance().viewCount();
			System.out.println("asd" + result);
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
		}
				
		// type 7 : 등록되어있는 물품 총 개수 
		else if ( type == 7 ) {
			int result = MypageDao.getInstance().ProductCount();
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);

		}
		
		// type 8 : 4월 물품 총 거래가격 
		else if ( type == 8 ) {
			int result = MypageDao.getInstance().ProductPriceCount();
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);			
		}
		
		// type 9 : 카테고리별 물품 거래 개수(당일 기준) -> 차트화시켜놨음
		/*
		else if ( type == 9 ) {
			
			HashMap<String, Integer> result = MypageDao.getInstance().getProductCategoryCount();
			
			mapper = new ObjectMapper();
			jsonArray = mapper.writeValueAsString(result);
			response.getWriter().print(jsonArray);
	
		}		
		*/
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
