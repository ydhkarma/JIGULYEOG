package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class FundingListDto {
	private int pro_num;
	private String user_id;
	private int funded_money;
	private Date funded_date;
	
	public FundingListDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FundingListDto(int pro_num, String user_id, int funded_money, Date funded_date) {
		super();
		this.pro_num = pro_num;
		this.user_id = user_id;
		this.funded_money = funded_money;
		this.funded_date = funded_date;
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

	public int getFunded_money() {
		return funded_money;
	}

	public void setFunded_money(int funded_money) {
		this.funded_money = funded_money;
	}

	public Date getFunded_date() {
		return funded_date;
	}

	public void setFunded_date(Date funded_date) {
		this.funded_date = funded_date;
	}

	@Override
	public String toString() {
		return "FundingListDto [pro_num=" + pro_num + ", user_id=" + user_id + ", funded_money=" + funded_money
				+ ", funded_date=" + funded_date + "]";
	}
	
	
}
