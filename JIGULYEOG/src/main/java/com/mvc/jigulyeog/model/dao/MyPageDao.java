package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.SignUpDto;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;


public interface MyPageDao {
	String NAMESPACE = "mapper.mypage.";
	
	List<ProjectDto> getMyDonateProjectList(String user_id);

	List<OrgDto> getMySubscribeOrgList(String user_id);

	List<ChungwonDto> getMySignUpList(String user_id);

//	List<ChungwonDto> getMyChungwonList(String user_id);

	List<TogetherDto> getMyTogetherApplyList(String user_id);

//	List<TogetherDto> getMyTogetherList(String user_id);

	int getTotalMyChungwon(String user_id);

	List<ChungwonDto> getMyChungwonList(int startRow, int endRow, String user_id);

	int getTotalMyTogether(String user_id);

	List<TogetherDto> getMyTogetherList(int startRow, int endRow, String user_id);

	List<SignUpDto> getSignUpList(Integer pet_no);

	List<TogetherApplyDto> getTogetherApplyList(Integer tog_no);

	int secession(String user_id);

	int update_member(UserDto user);

	int secession_org_update(int org_num);


}
