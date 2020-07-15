package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class ProjectDto {
	private int pro_num; // 기본키
	private int org_num; // 해당 환경단체 번호
	private String user_id; // 작성자(환경단체)
	private String pro_title; // 프로젝트 제목
	private int pro_nowmoney; // 지금 기부액
	private int pro_goalmoney; // 목표 기부액
	private Date pro_start_date; // 시작일
	private Date pro_due_date; // 마감일
	private Date pro_write_date; // 프로젝트 작성 일
	private String pro_detail; //내용
	private char pro_success; // 성공여부
	private String pro_image; // 이미지 경로
	
	public ProjectDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectDto(int pro_num, int org_num, String user_id, String pro_title, int pro_nowmoney, int pro_goalmoney,
			Date pro_start_date, Date pro_due_date, Date pro_write_date, String pro_detail, char pro_success,
			String pro_image) {
		super();
		this.pro_num = pro_num;
		this.org_num = org_num;
		this.user_id = user_id;
		this.pro_title = pro_title;
		this.pro_nowmoney = pro_nowmoney;
		this.pro_goalmoney = pro_goalmoney;
		this.pro_start_date = pro_start_date;
		this.pro_due_date = pro_due_date;
		this.pro_write_date = pro_write_date;
		this.pro_detail = pro_detail;
		this.pro_success = pro_success;
		this.pro_image = pro_image;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
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
	public String getPro_title() {
		return pro_title;
	}
	public void setPro_title(String pro_title) {
		this.pro_title = pro_title;
	}
	public int getPro_nowmoney() {
		return pro_nowmoney;
	}
	public void setPro_nowmoney(int pro_nowmoney) {
		this.pro_nowmoney = pro_nowmoney;
	}
	public int getPro_goalmoney() {
		return pro_goalmoney;
	}
	public void setPro_goalmoney(int pro_goalmoney) {
		this.pro_goalmoney = pro_goalmoney;
	}
	public Date getPro_start_date() {
		return pro_start_date;
	}
	public void setPro_start_date(Date pro_start_date) {
		this.pro_start_date = pro_start_date;
	}
	public Date getPro_due_date() {
		return pro_due_date;
	}
	public void setPro_due_date(Date pro_due_date) {
		this.pro_due_date = pro_due_date;
	}
	public Date getPro_write_date() {
		return pro_write_date;
	}
	public void setPro_write_date(Date pro_write_date) {
		this.pro_write_date = pro_write_date;
	}
	public String getPro_detail() {
		return pro_detail;
	}
	public void setPro_detail(String pro_detail) {
		this.pro_detail = pro_detail;
	}
	public char getPro_success() {
		return pro_success;
	}
	public void setPro_success(char pro_success) {
		this.pro_success = pro_success;
	}
	public String getPro_image() {
		return pro_image;
	}
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}
	@Override
	public String toString() {
		return "ProjectDto [pro_num=" + pro_num + ", org_num=" + org_num + ", user_id=" + user_id + ", pro_title="
				+ pro_title + ", pro_nowmoney=" + pro_nowmoney + ", pro_goalmoney=" + pro_goalmoney
				+ ", pro_start_date=" + pro_start_date + ", pro_due_date=" + pro_due_date + ", pro_write_date="
				+ pro_write_date + ", pro_detail=" + pro_detail + ", pro_success=" + pro_success + ", pro_image="
				+ pro_image + "]";
	}
	
	
	
	
}
