package com.mvc.jigulyeog.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;



public interface TogetherBiz {
	
	String TogetherfileUpload(MultipartFile file, HttpServletRequest request);
	
	//CRUD
	public TogetherDto selectOne(int tog_no);
	public int insert(TogetherDto dto);
	public int update(TogetherDto dto);
	public int delete(int tog_no);
	
	// project page maker
	PageMaker getPageMaker(Paging paging);
	
	//paging(basicList)
	Paging togetherPaging(Integer page);
	public List<TogetherDto> selectList(Paging paging);

	//paging + search(keyword)
	Paging togetherSearchPaging(Integer page, String keyword);
	public List<TogetherDto> togetherSearch(Paging paging, String keyword);
	
	// sorting by category
	Paging togetherFundingPaging(Integer page);
	Paging togetherVolunteerPaging(Integer page);
	Paging togetherSharePaging(Integer page);
	
	List<TogetherDto> selectVolunteerList(Paging paging);
	List<TogetherDto> selectFundingList(Paging paging);
	List<TogetherDto> selectShareList(Paging paging);

	UserDto selectWriteUser(String user_id);
	 
	
	
}
