package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class SignUpDto {
	
	private int sig_no;
	private int pet_no;
	private String user_id;
	private String sig_content;
	private Date sig_date;
	
	public SignUpDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUpDto(int sig_no, int pet_no, String user_id, String sig_content, Date sig_date) {
		super();
		this.sig_no = sig_no;
		this.pet_no = pet_no;
		this.user_id = user_id;
		this.sig_content = sig_content;
		this.sig_date = sig_date;
	}

	public int getSig_no() {
		return sig_no;
	}

	public void setSig_no(int sig_no) {
		this.sig_no = sig_no;
	}

	public int getPet_no() {
		return pet_no;
	}

	public void setPet_no(int pet_no) {
		this.pet_no = pet_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSig_content() {
		return sig_content;
	}

	public void setSig_content(String sig_content) {
		this.sig_content = sig_content;
	}

	public Date getSig_date() {
		return sig_date;
	}

	public void setSig_date(Date sig_date) {
		this.sig_date = sig_date;
	}
	
	
	
}
