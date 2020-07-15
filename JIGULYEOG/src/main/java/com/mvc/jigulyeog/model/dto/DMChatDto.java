package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class DMChatDto {
	private int dm_no; 
	private String dm_key; 
	private String dm_host;
	private String send_id;
	private String receive_id;
	private String dm_content;
	private Date dm_date;
	
	public DMChatDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DMChatDto(int dm_no, String dm_key, String dm_host, String send_id, String receive_id, String dm_content,
			Date dm_date) {
		super();
		this.dm_no = dm_no;
		this.dm_key = dm_key;
		this.dm_host = dm_host;
		this.send_id = send_id;
		this.receive_id = receive_id;
		this.dm_content = dm_content;
		this.dm_date = dm_date;
	}



	public int getDm_no() {
		return dm_no;
	}



	public void setDm_no(int dm_no) {
		this.dm_no = dm_no;
	}



	public String getDm_key() {
		return dm_key;
	}



	public void setDm_key(String dm_key) {
		this.dm_key = dm_key;
	}



	public String getDm_host() {
		return dm_host;
	}



	public void setDm_host(String dm_host) {
		this.dm_host = dm_host;
	}



	public String getSend_id() {
		return send_id;
	}



	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}



	public String getReceive_id() {
		return receive_id;
	}



	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}



	public String getDm_content() {
		return dm_content;
	}



	public void setDm_content(String dm_content) {
		this.dm_content = dm_content;
	}



	public Date getDm_date() {
		return dm_date;
	}



	public void setDm_date(Date dm_date) {
		this.dm_date = dm_date;
	}



	@Override
	public String toString() {
		return "DMChatDto [dm_no=" + dm_no + ", dm_key=" + dm_key + ", dm_host=" + dm_host + ", send_id=" + send_id
				+ ", receive_id=" + receive_id + ", dm_content=" + dm_content + ", dm_date=" + dm_date + "]";
	}
}
