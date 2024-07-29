package com.kkodamkkodam.board.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BoardService {
	//목록조회
	void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 상세내용
	void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//댓글 작성
	void commentWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 작성 화면
	void postWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 삭제
	void postDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 작성하여 등록
	void postRegi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}



