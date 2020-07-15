package com.mvc.jigulyeog.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;

public interface ProjectBiz {
	// << 이미지 파일 전송 >>
	String projectfileUpload(MultipartFile file,HttpServletRequest request);
	
	// << project wirte >>
	boolean projectWrite(ProjectDto project);
	
	// << project paging >>
	Paging projectPaging(Integer page);
	List<ProjectDto> projectList(Paging paging);
	List<ProjectDto> getArticleOldList(Paging paging);	
	List<ProjectDto> getArticleManyList(Paging paging);
	
	// << project page maker >>
	PageMaker getPageMaker(Paging paging);
	
	// << get project one >>
	ProjectDto getProjectOne(int pro_num);
	
	// << get org one >>
	OrgDto getProjectOneOrg(String user_id);
	
	// << project update >>
	boolean projectUpdate(ProjectDto project);
	
	// << project delete >>
	boolean projectDelete(int pro_num);
	
	// << project search >>
	Paging projectPagingSearch(Integer page,String keyword);
	List<ProjectDto> projectSearch(Paging paging,String keyword);

}
