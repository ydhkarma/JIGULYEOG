package com.mvc.jigulyeog.model.dto;



public class ChungwonDto {

	private int pet_no;
	private String pet_title;
	private String pet_content;
	private String pet_photo;
	private String pet_link;
	private String pet_date;
	private String pet_dead;
	private String user_id;
	private int pet_person;
	
	public ChungwonDto() {
		super();
	}

	public ChungwonDto(int pet_no, String pet_title, String pet_content, String pet_photo, String pet_link,
			String pet_date, String pet_dead, String user_id,int pet_person) {
		super();
		this.pet_no = pet_no;
		this.pet_title = pet_title;
		this.pet_content = pet_content;
		this.pet_photo = pet_photo;
		this.pet_link = pet_link;
		this.pet_date = pet_date;
		this.pet_dead = pet_dead;
		this.user_id = user_id;
		this.pet_person = pet_person;
	}

	public int getPet_no() {
		return pet_no;
	}

	public void setPet_no(int pet_no) {
		this.pet_no = pet_no;
	}

	public String getPet_title() {
		return pet_title;
	}

	public void setPet_title(String pet_title) {
		this.pet_title = pet_title;
	}

	public String getPet_content() {
		return pet_content;
	}

	public void setPet_content(String pet_content) {
		this.pet_content = pet_content;
	}

	public String getPet_photo() {
		return pet_photo;
	}

	public void setPet_photo(String pet_photo) {
		this.pet_photo = pet_photo;
	}

	public String getPet_link() {
		return pet_link;
	}

	public void setPet_link(String pet_link) {
		this.pet_link = pet_link;
	}

	public String getPet_date() {
		return pet_date;
	}

	public void setPet_date(String pet_date) {
		this.pet_date = pet_date.substring(0, 11);
	}

	public String getPet_dead() {
		return pet_dead;
	}

	public void setPet_dead(String pet_dead) {
		this.pet_dead = pet_dead;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPet_person() {
		return pet_person;
	}

	public void setPet_person(int pet_person) {
		this.pet_person = pet_person;
	}

	
	

	

	
	
	
	
	

}
