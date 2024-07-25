package com.kkodamkkodam.board.model;

import java.util.Map;

public interface BoardMapper {
	public BoardDTO getContent(int post_no, int board_id); //글 보기
	public void increaseView(Map<String, Object> params); //조회수
	
} 

