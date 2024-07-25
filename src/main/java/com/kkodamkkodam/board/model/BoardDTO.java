package com.kkodamkkodam.board.model;

import java.sql.Timestamp;

public class BoardDTO {
	
	//테이블의 컬럼명과 동일하게 멤버변수 선언합니다.
	private int post_no;
	private int user_no;
	private int board_id;
	private String title;
	private int view_count;
	private int like_count;
	private String content;
	private Timestamp regdate;
	private String code_language;
	private String code_content;
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardDTO(int post_no, int user_no, int board_id, String title, int view_count, int like_count,
			String content, Timestamp regdate, String code_language, String code_content) {
		super();
		this.post_no = post_no;
		this.user_no = user_no;
		this.board_id = board_id;
		this.title = title;
		this.view_count = view_count;
		this.like_count = like_count;
		this.content = content;
		this.regdate = regdate;
		this.code_language = code_language;
		this.code_content = code_content;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getCode_language() {
		return code_language;
	}

	public void setCode_language(String code_language) {
		this.code_language = code_language;
	}

	public String getCode_content() {
		return code_content;
	}

	public void setCode_content(String code_content) {
		this.code_content = code_content;
	}
}
