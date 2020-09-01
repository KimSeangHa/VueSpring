package com.spring.webservice.vo;

public class botVO {
	private int member_num;
	private int bot_num;
	private String bot_token;
	private String chat_id;
	private String bot_regdate;
	private String member_token;

	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getBot_num() {
		return bot_num;
	}
	public void setBot_num(int bot_num) {
		this.bot_num = bot_num;
	}
	public String getBot_token() {
		return bot_token;
	}
	public void setBot_token(String bot_token) {
		this.bot_token = bot_token;
	}
	public String getChat_id() {
		return chat_id;
	}
	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}
	public String getBot_regdate() {
		return bot_regdate;
	}
	public void setBot_regdate(String bot_regdate) {
		this.bot_regdate = bot_regdate;
	}
	public String getMember_token() {
		return member_token;
	}
	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}
}
