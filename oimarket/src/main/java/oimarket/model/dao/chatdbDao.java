package oimarket.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oimarket.model.dto.MessageDto;
import oimarket.model.dto.chatcategoryDto;

public class chatdbDao extends Dao{
	
	private static chatdbDao dao=new chatdbDao();
	

	private chatdbDao() {
		// TODO Auto-generated constructor stub
	}


	public static chatdbDao getInstance() {
		return dao;
	}
	//[장민정] 채팅하기
	public synchronized boolean chatting(String ncontent,int pno,int frommno,int tomno) {
		String sql="select * from chatcategory where pno = ? and ( mno1 = ? or mno2 = ? );"; 
					//해당제품에 내가 채팅한적이 있는지 확인 !cno찾기 방개설을위해 (frommno = mno)
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno); ps.setInt(2, frommno); ps.setInt(3, frommno); rs = ps.executeQuery();
			
			int cno = 0;
			
			if( rs.next() ) { // 첫 채팅이 아니다. 이미 채팅방이 존재한다. 
				// 검색된 cno
				 cno = rs.getInt(1);
				
			}else { // 첫 채팅이다 . 채팅방 부터 만들자 
				sql = " insert into chatcategory( pno , mno1 , mno2 ) values( ? , ? , ? );";
				ps=con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS );
				ps.setInt(1, pno); ps.setInt(2, frommno); ps.setInt(3, tomno); ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				if( rs.next() ) {
					 cno = rs.getInt(1); // 새로 만들어진 채팅방 
					// 첫 내용 작성 
				}
			}
			sql = "insert into chat( ncontent , cno , frommno , tomno ) values( ? , ? , ? , ? )";
						//메세지적기
			ps=con.prepareStatement(sql);
			ps.setString(1, ncontent); ps.setInt(2, cno); 
			ps.setInt(3, frommno); ps.setInt(4, tomno); 
			ps.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return false;
		
	}
	//[장민정] 채팅출력하기
	public synchronized ArrayList<MessageDto>getchat(int pno,int frommno,int tomno , int cno ){
		ArrayList<MessageDto>list=new ArrayList<>();
		try {
			String sql = "";
			if( cno == 0 ) { // 제품 상세에서 채팅버튼을 클릭했을때,... cno가 없으니까 채팅방 찾아라 
				
				sql="select * from chatcategory where pno = ? and ( mno1 = ? or mno2 = ? )"; 
				// 방안에있는 내용을 출력하기 위해 cno를 찾는다
				ps=con.prepareStatement(sql);
				ps.setInt(1, pno);
				ps.setInt(2, frommno );
				ps.setInt(3, frommno );
				rs = ps.executeQuery();
				if( rs.next() ) {
					cno = rs.getInt(1);
				}	
			}
			 // 쪽지 목록 에서 해당 쪽지를 클릭했을때,... cno가 있으니까 바로 모든 메시지 검색  
				sql = "select * from chat where cno =?";
				ps=con.prepareStatement(sql);
				ps.setInt( 1 , cno );
				rs=ps.executeQuery();
				while (rs.next()) {
					MessageDto dto=new MessageDto(
							rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getInt(4), rs.getInt(5), rs.getInt(6));
					
					
					String tomid=MemberDao.getInstance().getMid(dto.getTomno());
					String frommname=MemberDao.getInstance().getMid(dto.getFrommno());
					dto.setTomname(tomid);
					dto.setFrommname(frommname);
					list.add(dto);
				}
			
			/*
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.setInt(2, frommno );
			ps.setInt(3, tomno );
			ps.setInt(4, tomno );
			ps.setInt(5, frommno );
			*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;
	}
	
	//[장민정]pno로 판매자 rmno찾기
	public int  getrmno(int pno) {
		String sql="select rmno from product where pno=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return 0;
	}
	
	
	

	//[장민정]채팅리스트출력하기
	public synchronized ArrayList<chatcategoryDto>getallchat(int mno){
		ArrayList<chatcategoryDto>list=new ArrayList<>();
		String sql="select * from chatcategory where mno1 = ? or mno2 = ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, mno );
			rs=ps.executeQuery();
			while (rs.next()) {
				chatcategoryDto dto=new chatcategoryDto(
						rs.getLong(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5));
				
				// 1. mno는 있으니까 mid 찾아서 dto 추가하자 
					// mno1 --> mid1 
				String mid1 = MemberDao.getInstance().getMid( dto.getMno1() ) ;
					// mno2 --> mid2 
				String mid2 = MemberDao.getInstance().getMid( dto.getMno2() ) ;
					// 
				dto.setMid1(mid1); dto.setMid2(mid2);
				
				// 2. 대표 이미지 찾자
				sql = "select * from product_img where pno=? limit 1 " ;
				ps=con.prepareStatement(sql);
				ps.setInt(1, dto.getPno() );
				ResultSet rs2=ps.executeQuery();
				if( rs2.next() ) { 
					String mpimg = rs2.getString(2);
					dto.setMpimg(mpimg);
				}
				
				// 3. 최신글 과 최신글의 날짜 도 찾자 
				sql = "select * from chat where cno = ? order by ndate desc limit 1;";
				ps=con.prepareStatement(sql);
				ps.setLong( 1 , dto.getCno() );
				rs2=ps.executeQuery();
				if( rs2.next() ) { 
					// 최신글 
					String ccontent = rs2.getString(2);
					String cdate = rs2.getString(3);
					dto.setCcontent(ccontent);
					// 날짜
					dto.setCdate(cdate);
					
				}
				
				
				
				
				list.add(dto);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return list;
	}





}




// 현재 select에서 검색된 레코드들은 회원이 채팅에 포함된 모두 레코드 이니까
// 최근 채팅 순서[정렬] 기준으로 제품별 하나씩만 리스트에 담자
// 조건식 만들기 : 만약에 리스트에 담은 제품중에 이미 제품번호가 존재하면 리스트에 저장하지 않는다.
//boolean check = false; // 중복검사 
/*for( MessageDto msg : list ) {
					if( msg.getPno() == dto.getPno() ) {
						// 리스트에 존재하는 dto의 제품번호와 내가 저정할 dto의 제품번호와 일치하면
						check = true; // 이미 리스트에 동일한 제품이 있다.
					}
				}
				// 위에서 동일한 제품번호가 없을경우 리스트에 추가 
				if( check == false ) list.add(dto);*/
