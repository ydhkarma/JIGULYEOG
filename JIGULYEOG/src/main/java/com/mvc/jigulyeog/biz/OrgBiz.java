package com.mvc.jigulyeog.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;


public interface OrgBiz {
	
	Paging orgPaging(Integer orgPage);
	List<OrgDto> orgList(Paging paging);
	PageMaker getPageMaker(Paging paging);
	Paging orgPagingSearch(Integer orgPage, String keyword);
	List<OrgDto> orgListSearch(Paging paging, String keyword);
	OrgDto selectOne(Integer org_num);
	List<ProjectDto> getPList(Integer org_num);
	List<ProjectDto> getNowPList(int org_num);
	List<ProjectDto> getEndPList(int org_num);
	int subscribe(int org_num, String user_id);
	int subscribeCancle(int org_num, String user_id);
	boolean subChk(Integer org_num, String user_id);
	List<Object> getSubList(int org_num);

}
