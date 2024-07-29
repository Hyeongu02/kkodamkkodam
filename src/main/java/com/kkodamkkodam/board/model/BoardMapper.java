package com.kkodamkkodam.board.model;

import java.util.ArrayList;
import java.util.Map;

public interface BoardMapper {
	

	public ArrayList<BoardDTO> getList(int boardId); 					//글 목록 조회
	public String getboardType(int boardId);							//게시판 타입 조회
  
  public void postWrite(Map<String, Object> params); //글작성 화면
  public void postRegi(BoardDTO postregi); //글 작성 등록
  public void postDelete(Map<String, Object> params); //글삭제
	public BoardDTO getContent(Map<String, Object> params); 			//글 보기
	public ArrayList<CommentDTO> getComment(Map<String, Object> params);//댓글 보기
	public void increaseView(Map<String, Object> params); 				//조회수
	public void increasePostLike(BoardDTO dto); 						//글 좋아요
  
	public void commentWrite(CommentDTO dto); 							//댓글작성
	public void increaseCommentLike(CommentDTO dto); 					//댓글 좋아요
	public void replyWrite(CommentDTO dto); 							//대댓글작성
  
  public void miniWrite(BoardDTO dto); // 미니 게시판 신설 요청
	public void voteContent(BoardDTO dto); // 투표 
} 

