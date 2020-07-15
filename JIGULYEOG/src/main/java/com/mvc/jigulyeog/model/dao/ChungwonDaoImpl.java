package com.mvc.jigulyeog.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.PageNavigator;

@Repository
public class ChungwonDaoImpl implements ChungwonDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ChungwonDto> selectList(PageNavigator navi,String searchWord) {
		List<ChungwonDto> list = new ArrayList<ChungwonDto>();
		
		try {
			//new RowBounds(offset, limit)
			//offset : 건너뛸 갯수
			//limit : 몇개 잘라올지
//			RowBounds rb = new RowBounds(navi.getStartRecord(),5);
			RowBounds rb = new RowBounds(navi.getStartRecord(),6);
			
			list = sqlSession.selectList(NAMESPACE+"selectList",searchWord,rb);
			
		}catch(Exception e) {
			System.out.println("[error] : select list");
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<ChungwonDto> newestList(PageNavigator navi,String searchWord) {
		List<ChungwonDto> list = new ArrayList<ChungwonDto>();
		
		try {
			RowBounds rb = new RowBounds(navi.getStartRecord(),6);
			list = sqlSession.selectList(NAMESPACE+"newestList",searchWord,rb);
		}catch(Exception e) {
			System.out.println("[error] : newest list");
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public ChungwonDto selectOne(int pet_no) {
		ChungwonDto dto = null;
		
		try {
			dto = sqlSession.selectOne(NAMESPACE+"selectOne",pet_no);
		}catch(Exception e) {
			System.out.println("[error] : select one");
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int insert(ChungwonDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE+"insert",dto);
		}catch(Exception e) {
			System.out.println("[error] : insert");
			e.printStackTrace();
		}
		
		return res;
	}

	
	//페이징
	@Override
	public int selectCount(String searchWord) {
		int result = 0;
		
		try {
			result = sqlSession.selectOne(NAMESPACE+"selectCount",searchWord);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int chungDelete(int pet_no) {
		
		int res = 0;
		
		try {
			res = sqlSession.delete(NAMESPACE+"delete",pet_no);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int chungUpdate(ChungwonDto dto) {
		
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"update",dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	


	
	
	
	
	
	
	
	
	
	
}
