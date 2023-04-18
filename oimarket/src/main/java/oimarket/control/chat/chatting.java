package oimarket.control.chat;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import oimarket.model.dao.MemberDao;
import oimarket.model.dto.ClientDto;
import oimarket.model.dto.MessageDto;

@ServerEndpoint("/chatting/{mid}")
public class chatting {
	
	public static ArrayList<ClientDto> 접속명단 = new ArrayList<>();
	
	@OnOpen
	public void OnOpen(Session session,@PathParam("mid")String mid) throws Exception {
		
		ClientDto clientDto =new ClientDto(session, mid);
		접속명단.add(clientDto);
		System.out.println(접속명단.toString());
		
	}
	@OnClose
	public void onclose(Session session) throws Exception {
		
		for(ClientDto dto:접속명단) {
			if(dto.getSession()==session) {
				접속명단.remove(dto);
				break;
			}
		}
	}
	@OnMessage			//필수인수![누가,어떤내용을]
	public static void onMessage(Session session,String msg) throws Exception {
		// 메시지를 받는 회원번호 
		int tomno = Integer.parseInt( msg.split(",")[0] );
		// 메시지 내용 
		String tomsg = msg.split(",")[1];
		
		for( ClientDto c : 접속명단 ) {
			// 현재 소켓의 접속된 회원명단의 아이디로 회원번호 구하기 
			int cmno =  MemberDao.getInstance().getMno( c.getMid() );
			if( cmno == tomno ) { // 받는 회원번호가 알림명단에 존재하면
				c.getSession().getBasicRemote().sendText( tomsg );
			}
		}
	}

	
}
