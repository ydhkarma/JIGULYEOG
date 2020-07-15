package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class CheerMessageDto {
	private int cheer_num;
	private int pro_num;
	private String user_id;
	private String cheer_content;
	private Date cheer_date;
	
		public CheerMessageDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CheerMessageDto(int cheer_num, int pro_num, String user_id, String cheer_content, Date cheer_date) {
			super();
			this.cheer_num = cheer_num;
			this.pro_num = pro_num;
			this.user_id = user_id;
			this.cheer_content = cheer_content;
			this.cheer_date = cheer_date;
		}
		public int getCheer_num() {
			return cheer_num;
		}
		public void setCheer_num(int cheer_num) {
			this.cheer_num = cheer_num;
		}
		public int getPro_num() {
			return pro_num;
		}
		public void setPro_num(int pro_num) {
			this.pro_num = pro_num;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getCheer_content() {
			return cheer_content;
		}
		public void setCheer_content(String cheer_content) {
			this.cheer_content = cheer_content;
		}
		public Date getCheer_date() {
			return cheer_date;
		}
		public void setCheer_date(Date cheer_date) {
			this.cheer_date = cheer_date;
		}
		
		@Override
		public String toString() {
			return "CheerMessageDto [cheer_num=" + cheer_num + ", pro_num=" + pro_num + ", user_id=" + user_id
					+ ", cheer_content=" + cheer_content + ", cheer_date=" + cheer_date + "]";
		}
	

	
	
	
}
