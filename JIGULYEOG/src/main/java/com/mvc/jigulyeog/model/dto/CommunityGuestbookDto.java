package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class CommunityGuestbookDto {
	private int cation_num;
	private int com_num;
	private String user_id;
	private String cation_content;
	private Date cation_date;
	
	public CommunityGuestbookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunityGuestbookDto(int cation_num, int com_num, String user_id, String cation_content, Date cation_date) {
		super();
		this.cation_num = cation_num;
		this.com_num = com_num;
		this.user_id = user_id;
		this.cation_content = cation_content;
		this.cation_date = cation_date;
	}

	public int getCation_num() {
		return cation_num;
	}

	public void setCation_num(int cation_num) {
		this.cation_num = cation_num;
	}

	public int getCom_num() {
		return com_num;
	}

	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCation_content() {
		return cation_content;
	}

	public void setCation_content(String cation_content) {
		this.cation_content = cation_content;
	}

	public Date getCation_date() {
		return cation_date;
	}

	public void setCation_date(Date cation_date) {
		this.cation_date = cation_date;
	}

	@Override
	public String toString() {
		return "CommunityGuestbookDto [cation_num=" + cation_num + ", com_num=" + com_num + ", user_id=" + user_id
				+ ", cation_content=" + cation_content + ", cation_date=" + cation_date + "]";
	}
	
	
}
