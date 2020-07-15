package com.mvc.jigulyeog.model.dto;

public class SubscribeDto {
	private int org_num;
	private String user_id;
	
	public SubscribeDto() {
		super();
	}

	public SubscribeDto(int org_num, String user_id) {
		super();
		this.org_num = org_num;
		this.user_id = user_id;
	}

	public int getOrg_num() {
		return org_num;
	}

	public void setOrg_num(int org_num) {
		this.org_num = org_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "SubscribeDto [org_num=" + org_num + ", user_id=" + user_id + "]";
	}
}
