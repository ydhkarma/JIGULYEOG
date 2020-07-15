package com.mvc.jigulyeog.biz;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mvc.jigulyeog.model.dao.SignUpDao;
import com.mvc.jigulyeog.model.dto.SignUpDto;

@Service
public class SignUpBizImpl implements SignUpBiz{
	
	@Autowired
	private SignUpDao dao;

	@Override
	public int insert(SignUpDto dto) {
		return dao.insert(dto);
	}

	@Override
	public List<SignUpDto> sigList(int pet_no) {
		return dao.sigList(pet_no);
	}

	@Override
	public SignUpDto signUpOne(int sig_no) {
		return dao.signUpOne(sig_no);
	}

	@Override
	public int sigUpdate(SignUpDto dto) {
		return dao.sigUpdate(dto);
	}

	@Override
	public int sigDelete(SignUpDto dto) {
		return dao.sigDelete(dto);
	}

	@Override
	public boolean insertChk(SignUpDto dto) {
		
		return dao.insertChk(dto);
	}

	


	
}
