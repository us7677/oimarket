package oimarket.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oimarket.model.dto.MemberDto;
import oimarket.model.dto.ProductDto;

public class ProductDao extends Dao{
	
	
	private static ProductDao dao=new ProductDao();
	

	private ProductDao() {
		// TODO Auto-generated constructor stub
	}


	public static ProductDao getInstance() {
		return dao;
	}
	
	
	//물픔등록
	public  boolean productPrint(ProductDto dto) {//제품 우선등록하고 이미지는 나중에 추가
		String sql="insert into product(ptitle,pcontent,pprice,plat,plng,rmno,pcno) values(?,?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getPtitle());
			ps.setString(2, dto.getPcontent());
			ps.setInt(3, dto.getPprice());
			ps.setString(4, dto.getPlat());
			ps.setString(5, dto.getPlng());
			ps.setInt(6, dto.getRmno());
			ps.setInt(7, dto.getPcno());
			ps.executeUpdate();
			//제품우선 등록한 후에 pk번호를 받음
			rs=ps.getGeneratedKeys();
			if(rs.next()) {//존재하면 productdto내에 리스트에서 하나씩 ?//물어보기 
				for(String pimgname: dto.getPimglist()) {
					
					sql="insert into product_img(pimgname,pno) values(?,?) ";
					ps=con.prepareStatement(sql);
					ps.setString(1, pimgname);
					ps.setInt(2, rs.getInt(1));
					ps.executeUpdate();
				}
			}return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}
	
	
	
	
	//[장민정]등록된 물품전체출력 and 카테고리별 출력
	  public ArrayList<ProductDto> getproductlist(int pcno,int pcount){
		 ArrayList<ProductDto>list=new ArrayList<>(); 
		 String sql=null;
		 if(pcno==0) { //전체출력 db에 없는아무런 값으로 지정
			 sql="select p.*,c.pcname from product p natural join product_category c where pstate=1 order by p.pdate desc limit " + pcount+" "; //상태가 1(판매중)인것만 출력
			 
		 }else {//카테고리 선택시 마다 0이상의 값들이 넘어옴
			 sql="select p.*,c.pcname from product p natural join product_category c where pstate=1 and pcno= " + pcno+" order by p.pdate desc limit "  + pcount+" ";;//카테고리별 출력
		 }
		  try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				ArrayList<String>pimglist=new ArrayList<>();
				sql="select * from product_img where pno=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, rs.getInt(1));
				ResultSet rs2=ps.executeQuery();
				while (rs2.next()){
					pimglist.add(rs2.getString(2));
				}
				
			ProductDto dto=new ProductDto(
					rs.getInt(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4), rs.getInt(5), rs.getString(6),
					rs.getString(7), rs.getString(8), rs.getInt(9),
					rs.getString(10), rs.getInt(11), rs.getInt(12),
					rs.getInt(13), pimglist, rs.getString(14));	
			
				// 찜수 구하기 
				sql ="select count(*) from product_like where pno = "+ dto.getPno();
				ps=con.prepareStatement(sql);
				ResultSet rs3=ps.executeQuery();
				if( rs3.next() ) { dto.setPlikecount( rs3.getInt(1) ); }
				// ----------
				
				list.add(dto); 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}return list;
	 }
	 
	 //[장민정]개별출력:판매상품 pk번호로 판매자의 정보를 호출하는 함수
	  public MemberDto getproductmember(int pno) {
		 
		  String sql="select m.*  from product p natural join member m  where pno=?  and p.rmno=m.mno;";
		  try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			rs=ps.executeQuery();
			if(rs.next()) {
				MemberDto dto=new MemberDto(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				return dto;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		  
	  }
	  //[장민정]개별출력:판매상품 pk번호로 product의 정보를 호출하는 함수
	  public ProductDto getproduct(int pno){
		
			 String sql="select p.*,c.pcname from product p natural join product_category c where pno=?"; 
			  try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, pno);
				rs=ps.executeQuery();
				if (rs.next()) {
					ArrayList<String>pimglist=new ArrayList<>();
					sql="select * from product_img where pno=?";
					ps=con.prepareStatement(sql);
					ps.setInt(1, rs.getInt(1));
					ResultSet rs2=ps.executeQuery();
					while (rs2.next()){
						pimglist.add(rs2.getString(2));
					}
				ProductDto dto=new ProductDto(
						rs.getInt(1), rs.getString(2), rs.getString(3)
						, rs.getInt(4), rs.getInt(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getInt(11), rs.getInt(12),
						rs.getInt(13), pimglist, rs.getString(14));
				return dto;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e+"                         111");
			}
			return null;
		 }
	  
	  
	 //[장민정]검색해서 제목에 키워드가 존재하는 물품만 출력하기
	  public ArrayList<ProductDto>search(String keyword){
		  ArrayList<ProductDto>list=new ArrayList<>();
		  String sql="select p.*,c.pcname from product p natural join product_category c where ptitle like '%"+keyword+"%'";
		  try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				ArrayList<String>pimglist=new ArrayList<>();
				sql="select * from product_img where pno=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, rs.getInt(1));
				ResultSet rs2=ps.executeQuery();
				while (rs2.next()){
					pimglist.add(rs2.getString(2));
				}
			ProductDto dto=new ProductDto(
					rs.getInt(1), rs.getString(2), rs.getString(3)
					, rs.getInt(4), rs.getInt(5), rs.getString(6),
					rs.getString(7), rs.getString(8), rs.getInt(9),
					rs.getString(10), rs.getInt(11), rs.getInt(12),
					rs.getInt(13), pimglist, rs.getString(14));
				list.add(dto); 
			}
		} catch (SQLException e) {
			System.out.println(e);
		}return list;
	  }
	//[김은영] 제품삭제
	  public boolean Deleteproduct(int pno) {
		  String sql="delete  from product where pno=?;";
		  try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			int count=ps.executeUpdate();
			if(count==1) {return true;}
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	  }
	//[김은영] 제품수정
		public  boolean updatebtn(ProductDto dto) {//제품 우선등록하고 이미지는 나중에 추가
			System.out.println("이미지2:"+dto.getPimglist());
			String sql=
			"update product set ptitle=?, pcontent=?, pprice=? ,plat=?,plng=?,pcno=? where pno=?;";
			try {
				ps=con.prepareStatement(sql);
				ps.setString(1, dto.getPtitle());
				ps.setString(2, dto.getPcontent());
				ps.setInt(3, dto.getPprice());
				ps.setString(4, dto.getPlat());
				ps.setString(5, dto.getPlng());
				ps.setInt(6, dto.getPcno());
				ps.setInt(7, dto.getPno());
				ps.executeUpdate();
				//제품우선 등록한 후에 pk번호를 받음
				System.out.println("이미지:"+dto.getPimglist());
				
				//기존에 있던 이미지 삭제
				sql="delete from product_img where pno="+dto.getPno();
				ps=con.prepareStatement(sql);ps.executeUpdate();
				
				//새로운 이미지 
					for(String pimgname: dto.getPimglist()) {
						sql="insert into product_img(pimgname,pno) values(?,?) ";
						ps=con.prepareStatement(sql);
						ps.setString(1, pimgname);
						ps.setInt(2, dto.getPno());
						ps.executeUpdate();
						System.out.println("이미지3:"+dto.getPimglist());
					}
			return true;
			} catch (Exception e) {
				System.out.println(e);
			}return false;
		}
		
		
		
		
	  
	//[김은영]제품 찜하기
	  public boolean setlike(int pno, int mno) {
		  String sql="select *from product_like where pno=? and mno=? ";
		  try {
			  ps=con.prepareStatement(sql);
			  ps.setInt(1,pno);
			  ps.setInt(2,mno);
			  rs=ps.executeQuery();
			  if(rs.next()) {//레코드행 있으면 취소해야함
				  sql="delete from product_like  where pno=? and mno=? ";
				  ps=con.prepareStatement(sql);
				  ps.setInt(1,pno);
				  ps.setInt(2,mno);
				  ps.executeUpdate();return false;//취소되었을때 
				  }else {//레코드행 없으면 ! 등록해야함
					  sql="insert into product_like(pno,mno) values(?,?) ";
					  ps=con.prepareStatement(sql);
					  ps.setInt(1,pno);
					  ps.setInt(2,mno);
					  ps.executeUpdate();return true;
				  }
		} catch (Exception e) {System.out.println(e);}return false;
	  }
	  
	  
	//김은영[찜하기 상태] 
	public boolean getlike(int pno, int mno) {
		 String sql="select *from product_like where pno=? and mno=? ";
		  try {
			  ps=con.prepareStatement(sql);
			  ps.setInt(1,pno);
			  ps.setInt(2,mno);
			  rs=ps.executeQuery();
			  if(rs.next()) {//레코드행 있으면 등록을해서 나온 상태니깐 true
				  return true;}
		  }catch (SQLException e) {
			  System.out.println(e);
		  }return false;
		}
	  
	  //김은영[조회수]
	public boolean view(int pno) {
		String sql="update product set pview=pview+1 where pno=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}
	
	
	  //김은영[제품상태변경]
		public int productstate(int pno,int buymno) {
			String sql="update product set pstate= '2' ,buymno=? , buydate=now()  where pno=?";
			try {
				ps=con.prepareStatement(sql);
				ps.setInt(1, buymno);
				ps.setInt(2, pno);
				ps.executeUpdate();
				return 2;
				
			} catch (Exception e) {
				System.out.println(e);
			}return 1;
			
			
			
			
		}

		
	
}
