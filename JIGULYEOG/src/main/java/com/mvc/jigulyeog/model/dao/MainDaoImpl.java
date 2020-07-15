package com.mvc.jigulyeog.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;

@Repository
public class MainDaoImpl implements MainDao{
	Logger logger = LoggerFactory.getLogger(MainDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int countOrg() {
		logger.info("[ MainDao : countOrg ]");
		int count = 0;
		try {
			count = sqlSession.selectOne(NAMESPACE+"countOrg");
		} catch (Exception e) {
			logger.info("[ MainDao : error ]");
		}
		return count;
	}
	
	@Override
	public ProjectDto popularProject() {
		logger.info("[ MainDao : popularProject ]");
		ProjectDto project = new ProjectDto();
		try {
			project = sqlSession.selectOne(NAMESPACE+"popularProject");
		} catch (Exception e) {
			logger.info("[ MainDao : error ]");
		}
		return project;
	}

	@Override
	public List<ProjectDto> ongoingProject() {
		logger.info("[ MainDao : popularProject ]");
		List<ProjectDto> pList = new ArrayList<ProjectDto>();
		try {
			pList = sqlSession.selectList(NAMESPACE+"ongoingProject");
		} catch (Exception e) {
			logger.info("[ MainDao : error ]");
		}
		return pList;
	}

	@Override
	public List<ChungwonDto> ongoingChungwon() {
		logger.info("[ MainDao : ongoingChungwon ]");
		List<ChungwonDto> cList = new ArrayList<ChungwonDto>();
		try {
			cList = sqlSession.selectList(NAMESPACE+"ongoingChungwon");
		} catch (Exception e) {
			logger.info("[ MainDao : error ]");
		}
		return cList;
	}

	@Override
	public List<TogetherDto> ongoingTogether() {
		logger.info("[ MainDao : ongoingTogether ]");
		List<TogetherDto> tList = new ArrayList<TogetherDto>();
		try {
			tList = sqlSession.selectList(NAMESPACE+"ongoingTogether");
		} catch (Exception e) {
			logger.info("[ MainDao : error ]");
		}
		return tList;
	}

}
