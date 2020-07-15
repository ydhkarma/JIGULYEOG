package com.mvc.jigulyeog.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.SignUpDto;

@Repository
public class SignUpDaoImpl implements SignUpDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(SignUpDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE+"signInsert",dto);
		}catch(Exception e) {
			System.out.println("sign insert error");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public List<SignUpDto> sigList(int pet_no) {
		List<SignUpDto> sigList = new ArrayList<SignUpDto>();
		
		try {
			sigList = sqlSession.selectList(NAMESPACE+"sigList",pet_no);
		}catch(Exception e) {
			System.out.println("sigList error");
			e.printStackTrace();
		}
		
		return sigList;
	}



	@Override
	public SignUpDto signUpOne(int sig_no) {
		SignUpDto dto = null;
		
		try {
			dto = sqlSession.selectOne(NAMESPACE+"signUpOne",sig_no);
		}catch(Exception e) {
			System.out.println("[error] : select one");
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int sigUpdate(SignUpDto dto) {
		
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE+"sigUpdate",dto);
		}catch(Exception e) {
			System.out.println("error : update sig");
			e.printStackTrace();
		}
		
		
		return res;
	}

	@Override
	public int sigDelete(SignUpDto dto) {
		
		int res = 0;
		
		try {
			res = sqlSession.delete(NAMESPACE+"sigDelete",dto);
		}catch(Exception e) {
			System.out.println("error : delete sig");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean insertChk(SignUpDto dto) {
		SignUpDto res = null;
		System.out.println(dto.getUser_id());
		System.out.println(dto.getPet_no());
		try {
			res = sqlSession.selectOne(NAMESPACE+"insertChk",dto);
		} catch (Exception e) {
			System.out.println("error : insertChk sig");
			e.printStackTrace();
		}
		System.out.println(res);
		
		return (res!=null)?true:false;
	}

	
	
	
	
	
	
	
	
	
}
