package com.kkodamkkodam.board.service;

import java.io.IOException;
import java.util.ArrayList;

import com.kkodamkkodam.board.model.BoardDTO;
import com.kkodamkkodam.board.model.CommentDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BoardService {
	//목록조회
	void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  
  //글 작성 화면
	void postWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 작성하여 등록
	void postRegi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 상세내용
	void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 수정 페이지 이동
	void updatePostPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//글 수정 페이지 이동
	void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;	
	//글 삭제
	void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;	
	//글 좋아요
	void increasePostLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//댓글 작성
	void commentWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//대댓글 작성
	void replyWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//댓글 좋아요
	void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//댓글 삭제
	void increaseCommentLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;	
	// 미니 게시판 신설 요청
	void miniWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//신설 요청 글목록 
	void getMiniList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	// 투표
	void voteContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  // 신청글 좋아요 증가
	void increaseVoteLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	void getAllList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;;
	// 내가 쓴 글 목록 조회
	void getPostsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  // 내가 쓴 댓글 목록 조회
  void getCommentsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  // 내가 쓴 글, 댓글 한번에 조회
  void getUserActivityLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

