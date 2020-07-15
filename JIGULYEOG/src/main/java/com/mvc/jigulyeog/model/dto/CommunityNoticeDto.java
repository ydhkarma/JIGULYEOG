package com.mvc.jigulyeog.model.dto;

import java.util.Date;

public class CommunityNoticeDto {
	private int notice_num;
	private int com_num;
	private String notice_title;
	private String notice_content;
	private Date notice_date;
	private int notice_view;
	
	public CommunityNoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunityNoticeDto(int notice_num, int com_num, String notice_title, String notice_content, Date notice_date,
			int notice_view) {
		super();
		this.notice_num = notice_num;
		this.com_num = com_num;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_date = notice_date;
		this.notice_view = notice_view;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public int getCom_num() {
		return com_num;
	}

	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_view() {
		return notice_view;
	}

	public void setNotice_view(int notice_view) {
		this.notice_view = notice_view;
	}

	@Override
	public String toString() {
		return "CommunityNoticeDto [notice_num=" + notice_num + ", com_num=" + com_num + ", notice_title="
				+ notice_title + ", notice_content=" + notice_content + ", notice_date=" + notice_date
				+ ", notice_view=" + notice_view + "]";
	}
	
	
	
	
	
}
