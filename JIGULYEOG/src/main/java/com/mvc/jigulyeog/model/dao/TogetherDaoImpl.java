package com.mvc.jigulyeog.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.TogetherDto;
import com.mvc.jigulyeog.model.dto.UserDto;



@Repository
public class TogetherDaoImpl implements TogetherDao{
	Logger logger = LoggerFactory.getLogger(TogetherDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	@Override
	public TogetherDto selectOne(int tog_no) {
		TogetherDto dto = null;
		try {
			dto = sqlSession.selectOne(NAMESPACE+"selectOne",tog_no);
		} catch(Exception e) {
			logger.info("Together - selectOne error");
			e.printStackTrace();
		} 
		return dto;
	}

	@Override
	public int update(TogetherDto dto) {
		int res=0;
		try {
			res = sqlSession.update(NAMESPACE+"update",dto);
		}catch(Exception e) {
			logger.info("Together - update error");
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int insert(TogetherDto dto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"insert",dto);
		} catch(Exception e) {
			logger.info("Together - insert error");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(int tog_no) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE+"delete",tog_no);
		}catch(Exception e) {
			logger.info("Together - delete error");
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 일반적인 List의 총 게시글 수 구하기(paging
	@Override
	public int totalArticle() {
		logger.info("Together - totalArticle");
		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE+"totalArticle");
			System.out.println("TogetherTotalArticle"+res);
		}catch (Exception e) {
			logger.info("Together - totalArticle error");
		}
		logger.info("[ totalArticle : "+res+"]");
		return res;
	}
	
	//일반 (paging
	@Override
	public List<TogetherDto> selectList(int startRow, int endRow) {
		List<TogetherDto> list = new ArrayList<TogetherDto>();
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		try {
			list = sqlSession.selectList(NAMESPACE+"getArticleList",map);
		} catch(Exception e) {
			logger.info("Together - selectList error");
			e.printStackTrace();
		} 
		return list;
	}

	
	@Override
	public int totalArticleSearch(String keyword) {
		int totalArticle = 0;
		
		try {
			totalArticle = sqlSession.selectOne(NAMESPACE+"totalArticleSearch",keyword);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalArticle;
	}

	@Override
	public List<TogetherDto> getArticleListSearch(int startRow, int endRow, String keyword) {
		
		List<TogetherDto> list = new ArrayList<TogetherDto>();
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("startRow",startRow);
		map.put("endRow",endRow);
		map.put("keyword",keyword);
		
		try {
			list = sqlSession.selectList(NAMESPACE+"getArticleListSearch",map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public List<TogetherDto> selectVolunteerList(int startRow, int endRow) {
		logger.info("봉사 카테고리 게시글 목록 보여주기");
		List<TogetherDto> list = new ArrayList<TogetherDto>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startRow",startRow);
		map.put("endRow",endRow);
		try {
			list = sqlSession.selectList(NAMESPACE+"selectVolunteerList",map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TogetherDto> selectFundingList(int startRow, int endRow) {
		logger.info("모금 카테고리 게시글 목록 보여주기");
		List<TogetherDto> list = new ArrayList<TogetherDto>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startRow",startRow);
		map.put("endRow",endRow);
		try {
			list = sqlSession.selectList(NAMESPACE+"selectFundingList",map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<TogetherDto> selectShareList(int startRow, int endRow) {
		logger.info("모금 카테고리 게시글 목록 보여주기");
		List<TogetherDto> list = new ArrayList<TogetherDto>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startRow",startRow);
		map.put("endRow",endRow);
		try {
			list = sqlSession.selectList(NAMESPACE+"selectShareList",map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int totalFundingArticle() {
		
		int totalFundingArticle=0;
		
		try {
			totalFundingArticle = sqlSession.selectOne(NAMESPACE+"totalFundingArticle");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalFundingArticle;
	}

	@Override
	public int totalShareArticle() {
		
		int totalShareArticle=0;
		
		try {
			totalShareArticle = sqlSession.selectOne(NAMESPACE+"totalShareArticle");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalShareArticle;
	}

	@Override
	public int totalVolunteerArticle() {
		
		int totalVolunteerArticle=0;
		
		try {
			totalVolunteerArticle = sqlSession.selectOne(NAMESPACE+"totalVolunteerArticle");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return totalVolunteerArticle;
	}

	@Override
	public UserDto selectWriteUser(String user_id) {
		UserDto writeUser = new UserDto();
		try {
			writeUser = sqlSession.selectOne(NAMESPACE+"selectWriteUser",user_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return writeUser;
	}

}
