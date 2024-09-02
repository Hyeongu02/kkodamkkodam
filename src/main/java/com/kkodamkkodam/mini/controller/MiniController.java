package com.kkodamkkodam.mini.controller;

import java.io.IOException;

import com.kkodamkkodam.mini.service.MiniService;
import com.kkodamkkodam.mini.service.MiniServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.mini")
public class MiniController extends HttpServlet {
	public MiniController() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//요청 분기
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); //ip, port번호 제외된 주소
		String path = request.getContextPath(); //프로젝트 식별 이름
		String command = uri.substring( path.length() );
		
		
		MiniService service =new MiniServiceImpl();
		
		if(command.equals("/mini/miniWrite.mini")) {  // 미니 게시판 신설 요청
	        request.getRequestDispatcher("/board/post_write_mini.jsp").forward(request, response);
		}
		else if(command.equals("/mini/postMiniList.mini")) { //  미니 요청 목록
		    service.getMiniList(request, response);
		}
		else if(command.equals("/mini/miniWriteForm.mini")) { // 미니 신설 요청 등록
	        service.miniWrite(request, response);
		}    
		else if(command.equals("/mini/voteContent.mini")) { // 신청글 내용
			service.voteContent(request, response);
		}
		else if(command.equals("/mini/increaseVoteLike.mini")) { // 신청글 좋아요
			service.increaseVoteLike(request, response);
		}
		/////
		else if(command.equals("/mini/voteForm.mini")) { // 투표 결과
			service.addVote(request, response);
		}
	}
}
