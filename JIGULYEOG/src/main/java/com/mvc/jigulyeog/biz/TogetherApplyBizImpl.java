package com.mvc.jigulyeog.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.TogetherApplyDao;
import com.mvc.jigulyeog.model.dto.TogetherApplyDto;

@Service
public class TogetherApplyBizImpl implements TogetherApplyBiz{

	@Autowired
	private TogetherApplyDao dao;
	
	
	@Override
	public int insert(TogetherApplyDto dto) {
		return dao.insert(dto);
	}

	@Override
	public int delete(TogetherApplyDto dto) {
		return dao.delete(dto);
	}

	@Override
	public TogetherApplyDto select(int tog_no, String user_id) {
		return dao.select(tog_no, user_id);
	}


}
