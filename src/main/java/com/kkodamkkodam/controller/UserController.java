package com.kkodamkkodam.controller;

import java.io.IOException;

import com.kkodamkkodam.user.service.UserService;
import com.kkodamkkodam.user.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.user") 
public class UserController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); 
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		System.out.println(command);
		
		UserService service;
		
		if (command.equals("/user/join.user")) {
		    request.getRequestDispatcher("join.jsp").forward(request, response);
		} else if (command.equals("/user/joinForm.user")) {
		    service = new UserServiceImpl();
		    service.signUp(request, response); // 회원 가입 처리
		} else if (command.equals("/user/login.user")) {
		    request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (command.equals("/user/loginForm.user")) {
		    service = new UserServiceImpl();
		    service.login(request, response); // 로그인 처리
		} else if(command.equals("/user/mypage.user")) { // 회원 페이지
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
		}
	}
}
