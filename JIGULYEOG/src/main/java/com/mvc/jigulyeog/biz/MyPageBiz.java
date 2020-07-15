package com.mvc.jigulyeog.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.MyPagePageMaker;
import com.mvc.jigulyeog.model.dto.MyPagePaging;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.SignUpDto;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;

public interface MyPageBiz {

	public List<ProjectDto> getMyDonateProjectList(String user_id);
	public List<OrgDto> getMySubscribeOrgList(String user_id);
	public List<ChungwonDto> getMySignUpList(String user_id);
//	public List<ChungwonDto> getMyChungwonList(String user_id);
	public List<TogetherDto> getMyTogetherApplyList(String user_id);
//	public List<TogetherDto> getMyTogetherList(String user_id);
	public MyPagePaging myChungwonPaging(Integer CWPage, String user_id);
	public List<ChungwonDto> getMyChungwonList(MyPagePaging CWPaging, String user_id);
	public MyPagePageMaker getPageMaker(MyPagePaging CWPaging);
	public MyPagePaging myTogetherPaging(Integer TGPage, String user_id);
	public List<TogetherDto> getMyTogetherList(MyPagePaging TGPaging, String user_id);
	public List<SignUpDto> getSignUpList(Integer pet_no);
	public void SUExcelCreate(HttpServletRequest request, List<SignUpDto> SUList, String filename);
	public List<TogetherApplyDto> getTogetherApplyList(Integer tog_no);
	public void TGAExcelCreate(HttpServletRequest request, List<TogetherApplyDto> tGAList, String filename);
	public int secession(String user_id);
	public int update_member(UserDto user);
	public int secession_org_update(int org_num);
}



