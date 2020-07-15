package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class OrgDto {
	private int org_num;
	private String org_name;
	private String org_addr;
	private String org_ceo;
	private Date org_est_date;
	private String org_role;
	private String org_dept;
	private String org_pic;
	
	public OrgDto() {
		super();
	}

	public OrgDto(int org_num, String org_name, String org_addr, String org_ceo, Date org_est_date, String org_role,
			String org_dept, String org_pic) {
		super();
		this.org_num = org_num;
		this.org_name = org_name;
		this.org_addr = org_addr;
		this.org_ceo = org_ceo;
		this.org_est_date = org_est_date;
		this.org_role = org_role;
		this.org_dept = org_dept;
		this.org_pic = org_pic;
	}

	public int getOrg_num() {
		return org_num;
	}

	public void setOrg_num(int org_num) {
		this.org_num = org_num;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_addr() {
		return org_addr;
	}

	public void setOrg_addr(String org_addr) {
		this.org_addr = org_addr;
	}

	public String getOrg_ceo() {
		return org_ceo;
	}

	public void setOrg_ceo(String org_ceo) {
		this.org_ceo = org_ceo;
	}

	public Date getOrg_est_date() {
		return org_est_date;
	}

	public void setOrg_est_date(Date org_est_date) {
		this.org_est_date = org_est_date;
	}

	public String getOrg_role() {
		return org_role;
	}

	public void setOrg_role(String org_role) {
		this.org_role = org_role;
	}

	public String getOrg_dept() {
		return org_dept;
	}

	public void setOrg_dept(String org_dept) {
		this.org_dept = org_dept;
	}

	public String getOrg_pic() {
		return org_pic;
	}

	public void setOrg_pic(String org_pic) {
		this.org_pic = org_pic;
	}

	@Override
	public String toString() {
		return "OrgDto [org_num=" + org_num + ", org_name=" + org_name + ", org_addr=" + org_addr + ", org_ceo="
				+ org_ceo + ", org_est_date=" + org_est_date + ", org_role=" + org_role + ", org_dept=" + org_dept
				+ ", org_pic=" + org_pic + "]";
	}
}
