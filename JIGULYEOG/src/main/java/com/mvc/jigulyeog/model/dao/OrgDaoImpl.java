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
public class OrgDaoImpl implements OrgDao{
	Logger logger = LoggerFactory.getLogger(OrgDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<OrgDto> orgList(int startRow, int endRow) {
		List<OrgDto> orgList = new ArrayList<OrgDto>();
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		
		try {
			orgList = sqlSession.selectList(NAMESPACE+"getOrgList",param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orgList;
	}

	@Override
	public int getTotalOrg() {
		logger.info("[ getTotalOrg ]");
		
		int totalOrg = 0;
		try {
			totalOrg = sqlSession.selectOne(NAMESPACE+"getTotalOrg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("[ getTotalOrg : "+totalOrg+" ]");
		return totalOrg;
	}

	@Override
	public int totalOrgSearch(String keyword) {
		logger.info("[ OrgDao : totalArticleSearch]");
		int totalOrgSearch =0;
		try {
			totalOrgSearch = sqlSession.selectOne(NAMESPACE+"totalOrgSearch",keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("[ totalOrgSearch : "+totalOrgSearch+" ]");
		return totalOrgSearch;
	}

	@Override
	public List<OrgDto> getOrgListSearch(int startRow, int endRow, String keyword) {
		logger.info("[ OrgDao : getOrgListSearch]");
		List<OrgDto> orgList = new ArrayList<OrgDto>();
		Map<Object,Object> param = new HashMap<Object,Object>();
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		param.put("keyword", keyword);
		try {
			orgList = sqlSession.selectList(NAMESPACE+"getOrgListSearch",param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orgList;
	}

	@Override
	public OrgDto selectOne(Integer org_num) {
		OrgDto org = null;
		try {
			org = sqlSession.selectOne(NAMESPACE+"orgSelectOne",org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return org;
	}

	@Override
	public List<ProjectDto> getPList(Integer org_num) {
		List<ProjectDto> pList = new ArrayList<ProjectDto>();
		try {
			pList = sqlSession.selectList(NAMESPACE+"getPList",org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pList;
	}

	@Override
	public List<ProjectDto> getNowPList(int org_num) {
		List<ProjectDto> nowPList = new ArrayList<ProjectDto>();
		try {
			nowPList = sqlSession.selectList(NAMESPACE+"getNowPList",org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowPList;
	}

	@Override
	public List<ProjectDto> getEndPList(int org_num) {
		List<ProjectDto> endPList = new ArrayList<ProjectDto>();
		try {
			endPList = sqlSession.selectList(NAMESPACE+"getEndPList",org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endPList;
	}

	@Override
	public int subscribe(int org_num, String user_id) {
		int res = 0;
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("org_num", org_num);
		param.put("user_id", user_id);
		try {
			res = sqlSession.insert(NAMESPACE+"subscribe", param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int subscribeCancle(int org_num, String user_id) {
		int res = 0;
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("org_num", org_num);
		param.put("user_id", user_id);
		try {
			res = sqlSession.delete(NAMESPACE+"subscribeCancle", param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean subChk(Integer org_num, String user_id) {
		int res = 0;
		Map<Object, Object> param = new HashMap<Object,Object>();
		param.put("org_num", org_num);
		param.put("user_id", user_id);
		try {
			res = sqlSession.selectOne(NAMESPACE+"subChk", param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (res==1)?true:false;
	}

	@Override
	public List<Object> getSubList(int org_num) {
		List<Object> subList = null;
		try {
			subList = sqlSession.selectList(NAMESPACE+"getSubList", org_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subList;
	}
}
