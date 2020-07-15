package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;

public interface MainDao {
	String NAMESPACE = "mapper.main.";
	public int countOrg();
	public ProjectDto popularProject();
	public List<ProjectDto> ongoingProject();
	public List<ChungwonDto> ongoingChungwon();
	public List<TogetherDto> ongoingTogether();
}
