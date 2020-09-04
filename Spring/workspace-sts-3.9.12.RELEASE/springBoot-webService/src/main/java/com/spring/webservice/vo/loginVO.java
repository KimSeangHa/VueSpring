package com.spring.webservice.vo;

public class loginVO {
	private int member_num;
	private String member_name;
	private String member_id;
	private String member_password;
	private String member_regDate;
	private String member_token;
	private String login_result;
	private int member_level;
	
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_regDate() {
		return member_regDate;
	}
	public void setMember_regDate(String member_regDate) {
		this.member_regDate = member_regDate;
	}
	public String getMember_token() {
		return member_token;
	}
	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}
	public String getLogin_result() {
		return login_result;
	}
	public void setLogin_result(String login_result) {
		this.login_result = login_result;
	}
	public int getMember_level() {
		return member_level;
	}
	public void setMember_level(int member_level) {
		this.member_level = member_level;
	}

	
}
