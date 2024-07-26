package com.kkodamkkodam.user.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.kkodamkkodam.user.model.UserDTO;
import com.kkodamkkodam.user.model.UserMapper;
import com.kkodamkkodam.util.mybatis.MybatisUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
	
	private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();


	@Override
	public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		UserDTO dto = new UserDTO(id, pw, name, null);
		
		SqlSession sql = sqlSessionFactory.openSession(true);
		
		UserMapper user = sql.getMapper(UserMapper.class);
		
		user.signUp(dto);
		
		sql.close();
		
		response.sendRedirect("mypage.user");
		
		
		
		

	}


	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 사용자로부터 입력 받은 로그인 정보
	    String id = request.getParameter("id");
	    String pw = request.getParameter("pw");
	    
	    // 로그인 인증 결과를 저장할 변수
	    UserDTO userDTO = null;

	    // SqlSession을 try-with-resources로 열어 리소스 자동 관리를 보장
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        // UserMapper 인터페이스의 구현체를 얻음
	        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        
	        // 로그인 인증을 위한 쿼리 실행
	        userDTO = userMapper.login(id, pw);
	        
	        if (userDTO != null) {
	            // 로그인 성공
	            HttpSession session = request.getSession();
	            session.setAttribute("user", userDTO); // 세션에 사용자 정보 저장
	            response.sendRedirect("welcome.jsp"); // 로그인 성공 후 리다이렉트
	        } else {
	            // 로그인 실패
	            request.setAttribute("loginError", "Invalid ID or Password");
	            request.getRequestDispatcher("login.jsp").forward(request, response); // 로그인 페이지로 포워드
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 에러 처리
	        request.setAttribute("loginError", "An error occurred during login.");
	        request.getRequestDispatcher("login.jsp").forward(request, response);
	    }
	}
		

	@Override
	public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


}
