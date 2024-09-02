package com.kkodamkkodam.board.service;

import java.io.IOException;
import java.util.ArrayList;

import com.kkodamkkodam.board.model.BoardDTO;
import com.kkodamkkodam.board.model.CommentDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BoardService {

	// 메인페이지
	void getIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 목록조회
	void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 작성 화면
	void postWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 작성하여 등록
	void postRegi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 코드 리뷰 게시글 작성화면
	void postCodeWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 코드 질문글 등록
	void postCodeRegi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 상세내용
	void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 수정 페이지 이동
	void updatePostPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 수정 페이지 이동
	void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 삭제
	void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 글 좋아요
	void increasePostLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	// 댓글 작성
	void commentWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 대댓글 작성
	void replyWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 댓글 좋아요
	void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 댓글 삭제
	void increaseCommentLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	// 내가 쓴 글 목록 조회
	void getPostsByUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 내가 쓴 댓글 목록 조회
	void getCommentsByUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	// 내가 쓴 글, 댓글 한번에 조회
	void getUserActivityLog(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	// 글 검색
	void searchPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	// 메인페이지 글 검색
	void searchPostIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
