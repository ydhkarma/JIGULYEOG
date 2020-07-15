package com.mvc.jigulyeog.model.dao;


import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.TogetherApplyDto;

@Repository
public class TogetherApplyDaoImpl implements TogetherApplyDao{

	 @Autowired
	 private SqlSessionTemplate sqlSession;
	
	@Override
	public int insert(TogetherApplyDto dto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"insert",dto);
		} catch(Exception e) {
			System.out.println("Apply insert 오류");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(TogetherApplyDto dto) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE+"delete",dto);
		} catch(Exception e) {
			System.out.println("Apply delete 오류");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public TogetherApplyDto select(int tog_no, String user_id) {
		TogetherApplyDto dto = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			System.out.println(tog_no);
			System.out.println(user_id);
			
			map.put("tog_no", tog_no);
			map.put("user_id",user_id);
			
			dto=sqlSession.selectOne(NAMESPACE+"selectCount",map);
			System.out.println("check dto on ApplyDaoImpl"+dto); 
		} catch(Exception e) {
			System.out.println("Apply 신청판별 오류");
			e.printStackTrace();
		}
		return dto;
	}

}
