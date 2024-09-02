package com.kkodamkkodam.mini.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import com.kkodamkkodam.mini.model.MiniDTO;
import com.kkodamkkodam.mini.model.VoteDTO;
import com.kkodamkkodam.user.model.UserDTO;
import com.kkodamkkodam.mini.model.MiniMapper;
import com.kkodamkkodam.util.mybatis.MybatisUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MiniServiceImpl implements MiniService {

	// 멤버변수에 세션팩토리 하나 선언
	private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

	//미니 게시판 신청
	@Override
	public void miniWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDTO userdto = new UserDTO();
		HttpSession session = request.getSession(); // 세션값에 저장되어 있는 걸 뽑음
		userdto = (UserDTO) session.getAttribute("user");

		String boardCategory = request.getParameter("boardCategory");
		String boardType = request.getParameter("boardType");
		String content = request.getParameter("content");
		
		MiniDTO dto = new MiniDTO(null, boardType, boardCategory, content, null, null, userdto.getuserNo(), null,null,null);
		SqlSession sql = sqlSessionFactory.openSession(true);
		MiniMapper mapper = sql.getMapper(MiniMapper.class);
		mapper.miniWrite(dto);
		sql.close();

		request.getRequestDispatcher("postMiniList.mini").forward(request, response);
	}
	
	
	//미니 게시판 신청 목록
	@Override
	public void getMiniList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SqlSession sql = sqlSessionFactory.openSession(true);
		MiniMapper mapper = sql.getMapper(MiniMapper.class);
		ArrayList<MiniDTO> Minilist = mapper.getMiniList();
		sql.close();

		request.setAttribute("Minilist", Minilist);
		request.getRequestDispatcher("mini_post_list.jsp").forward(request, response);
	}

	//
	@Override
	public void voteContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		String state = request.getParameter("state"); 
		
		SqlSession sql = sqlSessionFactory.openSession(true);
		MiniMapper mapper = sql.getMapper(MiniMapper.class);

		mapper.miniListView(postNo); // 조회수 증가
		MiniDTO dto = mapper.voteContent(postNo); // 결과 반환
		sql.close();
		request.setAttribute("state", state);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("mini_vote.jsp").forward(request, response); // forward 경로 확인
	}

	@Override
	public void increaseVoteLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long postNo = Long.parseLong(request.getParameter("postNo"));

		MiniDTO dto = new MiniDTO(postNo, null, null, null, null, null, null, null, null, null);

		SqlSession sql = sqlSessionFactory.openSession(true);
		MiniMapper mapper = sql.getMapper(MiniMapper.class);
		mapper.increaseVoteLike(dto);

		sql.close(); // 마이바티스 세션 종료

		request.getRequestDispatcher("voteContent.board").forward(request, response);
	}

	@Override
	public void addVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        Long postNo = Long.parseLong(request.getParameter("postNo"));
	        String voteOption = request.getParameter("voteOption");
	        String boardCategory = request.getParameter("boardCategory");
	        String boardType = request.getParameter("boardType");
	        
	        // 세션에서 사용자 번호를 가져옵니다.
	        HttpSession session = request.getSession();
	        UserDTO udto = new UserDTO();
			udto = (UserDTO) session.getAttribute("user");
			Long userNo = udto.getuserNo();

	        SqlSession sql = sqlSessionFactory.openSession(true);
	        MiniMapper mapper = sql.getMapper(MiniMapper.class);
	        
	        Map<String, Object> params = new HashMap<>();
			params.put("postNo", postNo);
			params.put("userNo", userNo);
			int x=mapper.hasUserVoted(params);
	        // 사용자가 이미 투표했는지 확인
	        if (x>0) {
	            sql.close();
	            String redirectUrl = "voteContent.board?postNo=" + postNo + "&state=" + 1 ;
	            request.getRequestDispatcher(redirectUrl).forward(request, response);
	            return;
	        }
	        // 현재 투표 상황을 가져옵니다.
	        MiniDTO dto = new MiniDTO();
	        dto.setPostNo(postNo);
	        VoteDTO votodto=new VoteDTO(null,postNo,null,userNo);
	        if (voteOption.equals("yes")) {
	        	mapper.insertVoteYes(dto);
	        	mapper.insertVote(votodto);
	        }else {
	        	mapper.insertVoteNo(dto);
	        	mapper.insertVote(votodto);
	        }
	        MiniDTO resultDTO = mapper.getMiniVote(dto);
	        sql.close();
	        
	        // 총 투표 수와 찬성 비율을 계산합니다.
	        Long totalVotes = resultDTO.getYes()+ resultDTO.getNo();
	        double yesPercentage = (resultDTO.getYes().doubleValue() / totalVotes.doubleValue()) * 100;

	        if (totalVotes >= 20 && yesPercentage >= 60) {
	            // 조건이 충족되면 새로운 미니 게시판으로 리다이렉트
	            response.sendRedirect("mini" + boardType + ".jsp");
	            return;
	        }
	        request.getRequestDispatcher("voteContent.mini?postNo=" + request.getParameter("postNo")).forward(request, response);
	        return;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
