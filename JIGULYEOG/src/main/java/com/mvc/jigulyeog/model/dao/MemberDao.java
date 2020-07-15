package com.mvc.jigulyeog.model.dao;

import com.mvc.jigulyeog.model.dto.UserDto;

public interface MemberDao {
	String NAMESPACE = "mapper.mymember.";
	
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
	/*솔지추가부분*/
	public int updateOrgImg(UserDto user);
}
