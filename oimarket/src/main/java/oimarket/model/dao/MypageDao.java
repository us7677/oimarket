package oimarket.model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import oimarket.model.dto.BoardDto;
import oimarket.model.dto.ProductDto;

public class MypageDao extends Dao{

	private static MypageDao dao = new MypageDao();
	public MypageDao() {};
	public static MypageDao getInstance() { return dao; }

	// [최성아] 1. 로그인 된 회원의 등록한 물품 7개 출력
	public ArrayList<ProductDto> MypageRegisterProductList( int rmno ){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select p.pno , p.pcno, p.ptitle , p.pprice , "
				+ " if ( p.pstate = '1' , p.pdate , if ( p.pstate ='2' , p.buydate , '' ) ) as 등록혹은판매날짜, "
				+ " p.pstate "
				+ " from product p where rmno =? order by p.pdate desc limit 7 ";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, rmno);
				rs = ps.executeQuery();
				while ( rs.next() ) {
					ProductDto dto = new ProductDto(
							rs.getInt(1), rs.getInt(2), rs.getString(3) ,
							rs.getInt(4), rs.getString(5) , rs.getInt(6)  );
					
					sql = "select * from product_img where pno = ? limit 1";
					ps = con.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ResultSet rs2 = ps.executeQuery();
					while ( rs2.next() ) { dto.setMainImg(rs2.getString(2) );
					list.add(dto);
				}
			}
		}catch (Exception e) {System.out.println("등록물품오류 : " + e);}
		return list;
		
	} // 등록한 물품 end
	
	// [최성아] 2. 로그인 된 회원의 판매중 물품 5개 출력
	public ArrayList<ProductDto> MypageSellProductList( int rmno  ){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select p.pno, p.pcno , p.ptitle , p.pprice , p.pdate , p.pstate from product p  where rmno = ?   order by pdate desc ";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, rmno);
				rs = ps.executeQuery();
				while ( rs.next() ) {
					ProductDto dto = new ProductDto(
							rs.getInt(1), rs.getInt(2), 
							rs.getString(3), rs.getInt(4), rs.getString(5) , rs.getInt(6) );
					
					sql = "select * from product_img where pno = ? limit 1";
					ps = con.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ResultSet rs2 = ps.executeQuery();
					while ( rs2.next() ) { dto.setMainImg(rs2.getString(2) );
					list.add(dto);
				}
			}
		}catch (Exception e) {System.out.println("판매물품오류 : " + e);}
		return list;
		
	} // 판매중 물품 end

	// [최성아] 3. 로그인 된 회원의 구매한 물품 5개 출력
	public ArrayList<ProductDto> MypageBuyProductList( int buymno  ){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = " select p.pno , p.pcno , p.ptitle , p.pprice , p.buydate  "
				+ " from product p where buymno = ? order by p.buydate desc limit 5;";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, buymno);
				rs = ps.executeQuery();
				while ( rs.next() ) {
					ProductDto dto = new ProductDto(
							rs.getInt(1), rs.getInt(2), 
							rs.getString(3), rs.getInt(4), rs.getString(5) );
					
					sql = "select * from product_img where pno = ? limit 1";
					ps = con.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ResultSet rs2 = ps.executeQuery();
					while ( rs2.next() ) { dto.setMainImg(rs2.getString(2) );
					list.add(dto);
				}
			}
		}catch (Exception e) {System.out.println("구매물품오류 : " + e);}
		return list;
		
	} // 구매한 물품 end
	
	// [최성아] 4. 로그인 된 회원이 찜한 물품 5개 출력
	public ArrayList<ProductDto> MypageLikeProductList( int mno ){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = " select p.pno, p.pcno, p.ptitle , p.pprice , p.pdate , p.pstate "
				+ " from product_like pl natural join product p  where pl.mno = ? order by p.pdate desc";
			
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, mno);
				rs = ps.executeQuery();
				while ( rs.next() ) {
					ProductDto dto = new ProductDto(
							rs.getInt(1), rs.getInt(2), rs.getString(3) ,
							rs.getInt(4), rs.getString(5) , rs.getInt(6)  );
					
					sql = "select * from product_img where pno = ? limit 1";
					ps = con.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ResultSet rs2 = ps.executeQuery();
					while ( rs2.next() ) { dto.setMainImg(rs2.getString(2) );
					list.add(dto);
				}
			}
		}catch (Exception e) {System.out.println("찜한물품오류 : " + e);}
		return list;
		
	} // 찜한 물품 end
	
	// [최성아] 5. 로그인 된 회원의 게시물 5개까지 출력
	public ArrayList<BoardDto> MypageBoardList(int mno){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select b.bcno , b.btitle , b.bdate , b.bview , b.bup , b.bdown "
				+ " from board b where bdate between  '20230301'  and '20230430' and mno = ? order by bdate desc limit 5;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mno); 
			rs = ps.executeQuery();
			while( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5), rs.getInt(6) );
				list.add(dto);						
			}
			return list;
		}catch (Exception e) {System.out.println("게시물오류 : " + e);}
		return null;
	}// 게시물 출력 end
	
	// [최성아] 7. 등록되어있는 물품 개수 출력
	public int ProductCount(){
		String sql = "select count(p.pno) from product p";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) return rs.getInt(1);
		}catch (Exception e) {System.out.println(e);}
		return 0;
		
	}
	
	// [최성아] 8. 4월 물품 총 거래 가격
	public int ProductPriceCount(){
		String sql = "select sum(p.pprice) from product p where buydate between '2023-04-01' and '2023-04-30' and pstate = '2'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) return rs.getInt(1);
		}catch (Exception e) {System.out.println(e);}
		return 0;		
	}
	
	// [최성아] 9. 오늘 물품 카테고리별 거래 개수 
	/*
	public ArrayList<ProductDto> ProductCategoryCount(){
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select count(p.pno), pc.pcname from product p natural join product_category pc where p.pstate = \"2\" and buydate = CURDATE() group by pc.pcname order by count(p.pno) desc ";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				ProductDto dto = new ProductDto(
						rs.getInt(1) , rs.getString(2) );
				list.add(dto);
			}
			return list;
		}catch (Exception e) {System.out.println(e);}
		return null;		
	}	
	*/
	
	// [최성아] 9. 오늘 물품 카테고리별 거래 개수 (차트화)
	
	public HashMap<String , Integer> getProductCategoryCount(){
		HashMap<String, Integer> map = new HashMap<>();
		String sql = "select count(p.pno), pc.pcname from product p natural join product_category pc where p.pstate = \"2\" and buydate = CURDATE() group by pc.pcname order by count(p.pno) desc ;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next() ) {
				map.put(rs.getString(2), rs.getInt(1) );
			}
		}catch (Exception e) {System.out.println(e);}
		return map;
	}
	
	// [최성아] 6.1 오늘 방문자 수 증가
	public boolean viewIncrease(int mno) {
		String sql = "insert into mview(mno) values(?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// [최성아] 6.2 오늘 방문자 수 count
	public int viewCount() {
		String sql = "select count(mdate) from mview";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() )return rs.getInt(1);
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	
	
	
	
	
	
}
