package com.mvc.jigulyeog.biz;

import com.mvc.jigulyeog.model.dto.TogetherApplyDto;

public interface TogetherApplyBiz {
	
	
	public int insert(TogetherApplyDto dto);
	public int delete(TogetherApplyDto dto);
	public TogetherApplyDto select(int tog_no, String user_id);
	
	
}
