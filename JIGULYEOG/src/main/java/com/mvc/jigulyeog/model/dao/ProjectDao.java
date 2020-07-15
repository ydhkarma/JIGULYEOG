package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;

public interface ProjectDao {

	String NAMESPACE = "mapper.project.";
	boolean projectWrite(ProjectDto project);
	
	int totalArticle();
	
	List<ProjectDto> getArticleList(int startRow,int endRow);
	List<ProjectDto> getArticleOldList(int startRow,int endRow);	
	List<ProjectDto> getArticleManyList(int startRow,int endRow);	
	
	ProjectDto getProjectOne(int pro_num);
	OrgDto getProjectOneOrg(String user_id);
	
	boolean projectUpdate(ProjectDto project);
	boolean projectDelete(int pro_num);
	
	List<ProjectDto> getArticleListSearch(int startRow, int endRow, String keyword);
	int totalArticleSearch(String keyword);

}
