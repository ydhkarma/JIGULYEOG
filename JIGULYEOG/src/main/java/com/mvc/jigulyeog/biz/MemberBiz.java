package com.mvc.jigulyeog.biz;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dto.UserDto;

public interface MemberBiz {
	
	// 이미지 파일 전송
	String userfileUpload(MultipartFile file,HttpServletRequest request, UserDto user);
	
	public UserDto login(UserDto user);
	public UserDto snslogin(String user_id);
	public int regist_user(UserDto user);
	public int regist_org(UserDto user);
	public UserDto searchId(UserDto user);
	public UserDto searchPw(UserDto user);
	public int idCheck(String user_id);
	public int nickCheck(String user_nick);
	public int phoneCheck(String user_phone);
	public int insertSNS(UserDto user);
}
