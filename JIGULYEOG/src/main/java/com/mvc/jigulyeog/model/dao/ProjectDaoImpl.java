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

import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;

@Repository
public class ProjectDaoImpl implements ProjectDao{
	Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public boolean projectWrite(ProjectDto project) {
		logger.info("[ ProjectDao : projectWrite ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"writeProject",project);			
		} catch (Exception e) {
			logger.info("[ error : projectWrite ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public int totalArticle() {
		logger.info("[ ProjectDao : totalArticle ]");
		int totalArticle = 0;
		try {
			totalArticle = sqlSession.selectOne(NAMESPACE+"totalArticle");
		}catch (Exception e) {
			logger.info("[ error : totalArticle ]");
			e.getStackTrace();
		}
		logger.info("[ totalArticle : "+totalArticle+"]");
		return totalArticle;
	}
	
	@Override
	public List<ProjectDto> getArticleList(int startRow,int endRow) {
		logger.info("[ ProjectDao : getArticlelist ]");

		List<ProjectDto> getArticleList = new ArrayList<ProjectDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			getArticleList = sqlSession.selectList(NAMESPACE+"getArticleList",param);
		}catch (Exception e) {
			logger.info("[ error : getArticlelist ]");
			e.getStackTrace();
		}
		
		logger.info("[ getArticleList : "+getArticleList.size()+" ]");
		
		
		return getArticleList;
	}
	

	@Override
	public List<ProjectDto> getArticleOldList(int startRow, int endRow) {
		logger.info("[ ProjectDao :  getArticleOldList ]");

		List<ProjectDto> getArticleList = new ArrayList<ProjectDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			getArticleList = sqlSession.selectList(NAMESPACE+"getArticleListOld",param);
		}catch (Exception e) {
			logger.info("[ error : getArticleOldList ]");
			e.getStackTrace();
		}
		
		logger.info("[ getArticleList : "+getArticleList.size()+" ]");
		
		
		return getArticleList;
	}

	@Override
	public List<ProjectDto> getArticleManyList(int startRow, int endRow) {
		logger.info("[ ProjectDao : getArticleManyList ]");

		List<ProjectDto> getArticleList = new ArrayList<ProjectDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			getArticleList = sqlSession.selectList(NAMESPACE+"getArticleListMany",param);
		}catch (Exception e) {
			logger.info("[ error : getArticleManyList ]");
			e.getStackTrace();
		}
		
		logger.info("[ getArticleList : "+getArticleList.size()+" ]");
		
		
		return getArticleList;
	}

	@Override
	public ProjectDto getProjectOne(int pro_num) {
		logger.info("[ ProjectDao : getProjectOne ]");
		ProjectDto project = new ProjectDto();
		
		try {
			project = sqlSession.selectOne(NAMESPACE+"getProjectOne",pro_num);
		} catch (Exception e) {
			logger.info("[ error : getProjectOne ]");
			e.getStackTrace();
		}
		
		return project;
	}

	@Override
	public OrgDto getProjectOneOrg(String user_id) {
		logger.info("[ ProjectDao : getProjectOneOrg ]");
		OrgDto org = new OrgDto();
		
		try {
			org = sqlSession.selectOne(NAMESPACE+"getProjectOneOrg",user_id);
		} catch (Exception e) {
			logger.info("[ error : getProjectOneOrg ]");
			e.getStackTrace();
		}
		
		return org;
	}

	@Override
	public boolean projectUpdate(ProjectDto project) {
		logger.info("[ ProjectDao : projectUpdate ]");
		int res = 0;
		//logger.info(project.toString());
		
		try {
			res = sqlSession.update(NAMESPACE+"updateProject",project);			
		} catch (Exception e) {
			logger.info("[ error : projectUpdate ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public boolean projectDelete(int pro_num) {
		logger.info("[ ProjectDao : projectDelete ]");
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE+"deleteProject",pro_num);			
		} catch (Exception e) {
			logger.info("[ error : projectDelete ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public List<ProjectDto> getArticleListSearch(int startRow, int endRow, String keyword) {
		logger.info("[ ProjectDao : getArticleListSearch ]");

		List<ProjectDto> getArticleList = new ArrayList<ProjectDto>();
		
		Map<String,Object> param = new HashMap<String,Object>();
		
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		param.put("keyword", keyword);
		
		try {
			getArticleList = sqlSession.selectList(NAMESPACE+"getArticleListSearch",param);
		}catch (Exception e) {
			logger.info("[ error : getArticleListSearch ]");
			e.getStackTrace();
		}
		
		logger.info("[ getArticleList : "+getArticleList.size()+" ]");
		
		
		return getArticleList;
	}

	@Override
	public int totalArticleSearch(String keyword) {
		logger.info("[ ProjectDao : totalArticleSearch ]");
		int totalArticle = 0;
		try {
			totalArticle = sqlSession.selectOne(NAMESPACE+"totalArticleSearch",keyword);
		}catch (Exception e) {
			logger.info("[ error : totalArticleSearch ]");
		}
		logger.info("[ totalArticle : "+totalArticle+"]");
		return totalArticle;
	}

}
