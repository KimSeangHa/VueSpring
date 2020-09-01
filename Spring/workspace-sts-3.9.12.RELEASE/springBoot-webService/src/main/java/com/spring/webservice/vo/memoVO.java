package com.spring.webservice.vo;

public class memoVO {
	private String content;
	private String id;
	private int num;
	private String regDate;
	private String modifyDate;
	private String curDate;
	private int member_num;
	private String member_token;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		System.out.println(regDate);
		this.regDate = regDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getCurDate() {
		return curDate;
	}
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getMember_token() {
		return member_token;
	}
	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}
}
