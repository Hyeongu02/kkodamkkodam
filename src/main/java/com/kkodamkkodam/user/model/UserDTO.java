package com.kkodamkkodam.user.model;

public class UserDTO {

	private int userNo;
	private String id;
	private String pw;
	private String name;
	private String deleted;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(String id, String pw, String name, String deleted) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.deleted = deleted;
	}

	public UserDTO(int userNo, String id, String pw, String name, String deleted) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.deleted = deleted;
	}

	public int getuserNo() {
		return userNo;
	}

	public void setuserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}


