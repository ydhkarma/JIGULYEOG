package com.mvc.jigulyeog.model.dao;

import java.util.Date;
import java.util.List;


import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;

public interface OrgDao {
	String NAMESPACE = "mapper.org.";
	
	List<OrgDto> orgList(int startRow, int endRow);
		
	int getTotalOrg();

	int totalOrgSearch(String keyword);

	List<OrgDto> getOrgListSearch(int startRow, int endRow, String keyword);

	OrgDto selectOne(Integer org_num);

	List<ProjectDto> getPList(Integer org_num);

	List<ProjectDto> getNowPList(int org_num);

	List<ProjectDto> getEndPList(int org_num);

	int subscribe(int org_num, String user_id);

	int subscribeCancle(int org_num, String user_id);

	boolean subChk(Integer org_num, String user_id);

	List<Object> getSubList(int org_num);

}
