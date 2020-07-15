package com.mvc.jigulyeog.model.dto;

public class UserDto {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nick;
	private String user_phone;
	private String user_addr;
	private String user_pic;
	private int user_status;
	private SubscribeDto subscribe;
	
	public UserDto() {
		super();
	}
	public UserDto(String user_id, String user_pw, String user_name, String user_nick, String user_phone,
			String user_addr, String user_pic, int user_status) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_nick = user_nick;
		this.user_phone = user_phone;
		this.user_addr = user_addr;
		this.user_pic = user_pic;
		this.user_status = user_status;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public int getUser_status() {
		return user_status;
	}
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	
	@Override
	public String toString() {
		return "UserDto [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_nick="
				+ user_nick + ", user_phone=" + user_phone + ", user_addr=" + user_addr + ", user_pic=" + user_pic
				+ ", user_status=" + user_status + "]";
	}
	public SubscribeDto getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(SubscribeDto subscribe) {
		this.subscribe = subscribe;
	}
}
