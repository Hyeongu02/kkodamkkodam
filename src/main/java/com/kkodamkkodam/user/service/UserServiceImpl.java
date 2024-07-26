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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {
	
	private SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();


	// 가입
	@Override
	public void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);

            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String name = request.getParameter("name");

            String rePw = request.getParameter("rePw");
            if (!pw.equals(rePw)) {
                request.setAttribute("message", "비밀번호가 일치하지 않습니다.");
                request.getRequestDispatcher("join.jsp").forward(request, response);
                return;
            }

            if (mapper.checkId(id) != null) {
                request.setAttribute("message", "이미 사용 중인 아이디입니다.");
                request.getRequestDispatcher("join.jsp").forward(request, response);
                return;
            }

            UserDTO dto = new UserDTO();
            dto.setId(id);
            dto.setPw(pw);
            dto.setName(name);

            mapper.join(dto);
            session.commit();

            response.sendRedirect("login.jsp"); 
        }

	}

	// 로그인
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		
		String id = request.getParameter("id");
	    String pw = request.getParameter("pw");
	    String check =  request.getParameter("check");
	    
	    UserDTO dto = new UserDTO();
	    
	    dto.setId(id);
	    dto.setPw(pw);
	    
	    UserDTO resultDto = null;
	    
	    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
	        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	        
	        resultDto = userMapper.login(id, pw);
	        
	        if (resultDto != null) { // 로그인 성공 시
	            HttpSession session = request.getSession();
	            session.setAttribute("id", resultDto.getId());
	            session.setAttribute("pw", resultDto.getPw());
	            
	            // 쿠키
	            if(check != null) {
	            	Cookie getCookie = new Cookie("userId", id);
	            	getCookie.setMaxAge(20);
	            	response.addCookie(getCookie);
	            } else {
	            	Cookie noCookie = new Cookie("userId", null);
	            	noCookie.setMaxAge(0);
	            	response.addCookie(noCookie);
	            }
	           
	            response.sendRedirect("mypage.jsp");
	            
	            
	        } else { // 로그인 실패 시
	            request.setAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
	            request.getRequestDispatcher("login.jsp").forward(request, response); 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "다시 시도해 주세요.");
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
