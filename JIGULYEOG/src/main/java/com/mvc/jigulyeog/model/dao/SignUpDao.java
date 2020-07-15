package com.mvc.jigulyeog.model.dao;


import java.util.List;

import com.mvc.jigulyeog.model.dto.SignUpDto;

public interface SignUpDao {
	
	String NAMESPACE = "mapper.signup.";
	
	public int insert(SignUpDto dto);
	public List<SignUpDto> sigList(int pet_no);
	public SignUpDto signUpOne(int sig_no);
	public int sigUpdate(SignUpDto dto);
	public int sigDelete(SignUpDto dto);
	public boolean insertChk(SignUpDto dto);
}
