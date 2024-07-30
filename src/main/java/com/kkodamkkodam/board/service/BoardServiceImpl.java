package com.kkodamkkodam.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.kkodamkkodam.board.model.BoardDTO;
import com.kkodamkkodam.board.model.CommentDTO;
import com.kkodamkkodam.user.model.UserDTO;
import com.kkodamkkodam.board.model.BoardMapper;
import com.kkodamkkodam.util.mybatis.MybatisUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BoardServiceImpl implements BoardService {
	
	//멤버변수에 세션팩토리 하나 선언
	private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
	
	@Override
	public void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long boardId=Long.parseLong(request.getParameter("boardId"));
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);
		ArrayList<BoardDTO> list = mapper.getList(boardId);
		String boardType=mapper.getboardType(boardId);
		sql.close();
		
		request.setAttribute("list", list);
		request.setAttribute("boardType", boardType);
		request.getRequestDispatcher("post_list.jsp").forward(request, response);
	}
	
	@Override
	public void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo=Integer.parseInt(request.getParameter("postNo"));
		int boardId=Integer.parseInt(request.getParameter("boardId"));
		Map<String, Object> params = new HashMap<>();
        params.put("postNo", postNo);
        params.put("boardId", boardId);
        
		//마이바티스 실행
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);

		mapper.increaseView(params); //조회수 증가
		BoardDTO dto = mapper.getContent(params); //결과 반환
		ArrayList<CommentDTO> commentList = mapper.getComment(params);
		sql.close(); //마이바티스 세션 종료
		
		//dto를 request에 담고 forward 화면으로 이동
		request.setAttribute("dto", dto);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("post_view.jsp").forward(request, response);			
	}
	
	@Override
	public void increasePostLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		Long boardId=Long.parseLong(request.getParameter("boardId"));
		BoardDTO dto=new BoardDTO(postNo, null, boardId, null, null, null, null, null, null, null, null, null);
		//마이바티스 실행
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);
		mapper.increasePostLike(dto);
		
		sql.close(); //마이바티스 세션 종료
		
		//dto를 request에 담고 forward 화면으로 이동
		request.getRequestDispatcher("getContent.board").forward(request, response);	
	}
	
	@Override
	public void commentWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		int userNo=(int)session.getAttribute("userNo");

		UserDTO udto = new UserDTO();
		udto = (UserDTO) session.getAttribute("user");
		Long userNo=udto.getuserNo();
		Long boardId=Long.parseLong(request.getParameter("boardId"));
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		String commentContent=request.getParameter("commentContent");
		CommentDTO dto = new CommentDTO(null, userNo, boardId, postNo, commentContent, null, null, null, null,null);
		//마이바티스 실행
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);
		mapper.commentWrite(dto);
		
		sql.close(); //마이바티스 세션 종료
		
		//dto를 request에 담고 forward 화면으로 이동
		request.getRequestDispatcher("getContent.board").forward(request, response);	
		
	}

	@Override
	public void replyWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		int userNo=(int)session.getAttribute("userNo");
		UserDTO udto = new UserDTO();
		udto = (UserDTO) session.getAttribute("user");
		Long userNo=udto.getuserNo();
		Long boardId=Long.parseLong(request.getParameter("boardId"));
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		String commentContent=request.getParameter("commentContent");
		Long parentId=Long.parseLong(request.getParameter("parentId"));
		
		CommentDTO dto = new CommentDTO(null, userNo, boardId, postNo, commentContent, null, null, parentId, null,null);
		//마이바티스 실행
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);
		mapper.replyWrite(dto);
		
		sql.close(); //마이바티스 세션 종료
		
		//dto를 request에 담고 forward 화면으로 이동
		request.getRequestDispatcher("getContent.board").forward(request, response);
		
	}
	
	@Override
	public void increaseCommentLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void postWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long boardId = Long.parseLong(request.getParameter("boardId"));
		request.setAttribute("boardId", boardId);
		request.getRequestDispatcher("post_write.jsp").forward(request, response);
	}

	@Override
	public void postDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	@Override
	public void postRegi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long boardId = Long.parseLong(request.getParameter("boardId"));

		HttpSession session = request.getSession();
		UserDTO udto = new UserDTO();
		udto = (UserDTO) session.getAttribute("user");
		Long userNo = udto.getuserNo();

//		TIMESTAMP regdate = new TIMESTAMP(Date.getCurrentDate());
//		System.out.println(regdate);
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setUserNo(userNo);
		dto.setBoardId(boardId);
//		dto.setRegdate(regdate);
		
		SqlSession sql = sqlSessionFactory.openSession(true);
		BoardMapper mapper = sql.getMapper(BoardMapper.class);
		mapper.postRegi(dto);
		
		sql.close();
		
		request.setAttribute("boardId", boardId);
		request.getRequestDispatcher("postList.board").forward(request, response);
	}
	
	@Override
	public void miniWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  String boardCategory = request.getParameter("boardCategory");
      String boardType = request.getParameter("boardType");
      String content = request.getParameter("content");
      
      BoardDTO dto = new BoardDTO(null, null, null, null, null, null, null, content, null, null, boardType, boardCategory);
      
      SqlSession sql = sqlSessionFactory.openSession(true);
      BoardMapper mapper = sql.getMapper(BoardMapper.class);
      mapper.miniWrite(dto);
      sql.close();

      response.sendRedirect(request.getContextPath() + "/board/list.board");
	}
  
	@Override
	public void voteContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardCategory=request.getParameter("boardCategory");
		
	    BoardDTO dto = new BoardDTO(null, null, null, null, null, null, null, null, null, null, null, boardCategory);
	    
	    SqlSession sql = sqlSessionFactory.openSession(true);
	    BoardMapper mapper = sql.getMapper(BoardMapper.class);
	    mapper.voteContent(dto);
	    sql.close();

		
		//dto를 request에 담고 forward 화면으로 이동
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("post_view.jsp").forward(request, response);		
	}

	
	@Override
	public void getPostsByUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO udto = new UserDTO();
		udto = (UserDTO) session.getAttribute("user");
//		System.out.println(udto.getuserNo());

        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            ArrayList<BoardDTO> posts = mapper.getPostsByUser(udto.getuserNo());
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("../user/mypage.jsp").forward(request, response);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
		
	}

	@Override
	public void getCommentsByUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserDTO udto = new UserDTO();
		udto = (UserDTO) session.getAttribute("user");
		System.out.println(udto.getuserNo());

        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            ArrayList<CommentDTO> comments = mapper.getCommentsByUser(udto.getuserNo());
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("../user/mypage.jsp").forward(request, response);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
	}

	@Override
	public void getUserActivityLog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
        UserDTO udto = (UserDTO) session.getAttribute("user");

        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

            ArrayList<BoardDTO> posts = mapper.getPostsByUser(udto.getuserNo());
            ArrayList<CommentDTO> comments = mapper.getCommentsByUser(udto.getuserNo());

            request.setAttribute("posts", posts);
            request.setAttribute("comments", comments);

            request.getRequestDispatcher("../user/mypage.jsp").forward(request, response);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
		
	}

	


	
}
