package com.mvc.jigulyeog.model.dto;

public class DMListDto {
	private String user_id;
	private String dm_id;
	private String dm_key;
	
	public DMListDto() {
		super();
	}

	public DMListDto(String user_id, String dm_id, String dm_key) {
		super();
		this.user_id = user_id;
		this.dm_id = dm_id;
		this.dm_key = dm_key;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDm_id() {
		return dm_id;
	}

	public void setDm_id(String dm_id) {
		this.dm_id = dm_id;
	}

	public String getDm_key() {
		return dm_key;
	}

	public void setDm_key(String dm_key) {
		this.dm_key = dm_key;
	}

	@Override
	public String toString() {
		return "DMListDto [user_id=" + user_id + ", dm_id=" + dm_id + ", dm_key=" + dm_key + "]";
	}
	
	
	
	
}
