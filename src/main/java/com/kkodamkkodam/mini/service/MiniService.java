package com.kkodamkkodam.mini.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MiniService {
	// 미니 게시판 신설 요청
	void miniWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 신설 요청 글목록
	void getMiniList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void voteContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 신청글 좋아요 증가
	void increaseVoteLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	// 투표 결과 반영
	void addVote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
