package com.mvc.jigulyeog.model.dto;

public class TogetherApplyDto {
	private int apply_key;
	private int tog_no;
	private String user_id;
	
	public TogetherApplyDto() {
		super();
	}

	public TogetherApplyDto(int apply_key, int tog_no, String user_id) {
		super();
		this.apply_key = apply_key;
		this.tog_no = tog_no;
		this.user_id = user_id;
	}

	public int getApply_key() {
		return apply_key;
	}

	public void setApply_key(int apply_key) {
		this.apply_key = apply_key;
	}

	public int getTog_no() {
		return tog_no;
	}

	public void setTog_no(int tog_no) {
		this.tog_no = tog_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
