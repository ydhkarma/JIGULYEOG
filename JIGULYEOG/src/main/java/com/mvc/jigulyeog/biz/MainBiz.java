package com.mvc.jigulyeog.biz;

import java.util.List;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;

public interface MainBiz {
	public int countOrg();
	public ProjectDto popularProject();
	public List<ProjectDto> ongoingProject();
	public List<ChungwonDto> ongoingChungwon();
	public List<TogetherDto> ongoingTogether();
}
