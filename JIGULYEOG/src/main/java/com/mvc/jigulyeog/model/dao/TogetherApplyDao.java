package com.mvc.jigulyeog.model.dao;


import com.mvc.jigulyeog.model.dto.TogetherApplyDto;

public interface TogetherApplyDao {
	String NAMESPACE = "mapper.TogetherApply.";
	
	public int insert(TogetherApplyDto dto);
	public int delete(TogetherApplyDto dto);
	public TogetherApplyDto select(int tog_no, String user_id);

}
