package com.mvc.jigulyeog.biz;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.OrgDao;
import com.mvc.jigulyeog.model.dto.OrgDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.ProjectDto;

@Service
public class OrgBizImpl implements OrgBiz{
	Logger logger = LoggerFactory.getLogger(OrgBizImpl.class);
	
	@Autowired
	OrgDao dao;

	@Override
	public Paging orgPaging(Integer orgPage) {
		Paging paging = new Paging();
		paging.setPage(orgPage);
		int totalOrg = dao.getTotalOrg();
		
		paging.setTotalArticle(totalOrg);
		paging.setTotalPage(totalOrg);
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public List<OrgDto> orgList(Paging paging) {
		List<OrgDto> orgList = dao.orgList(paging.getStartRow(), paging.getEndRow());
		
		return orgList;
	}
	
	@Override
	public PageMaker getPageMaker(Paging paging) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPaging(paging);
		return pageMaker;
	}

	@Override
	public Paging orgPagingSearch(Integer orgPage, String keyword) {
		Paging paging = new Paging();
		paging.setPage(orgPage);
		int totalArticle = dao.totalOrgSearch(keyword);
		paging.setTotalArticle(totalArticle);
		paging.setTotalPage(totalArticle);
		paging.setStartRow();
		paging.setEndRow();
		
		return paging;
	}

	@Override
	public List<OrgDto> orgListSearch(Paging paging, String keyword) {
		List<OrgDto> orgList = dao.getOrgListSearch(paging.getStartRow(),paging.getEndRow(),keyword);
		return orgList;
	}

	@Override
	public OrgDto selectOne(Integer org_num) {
		OrgDto org = dao.selectOne(org_num);
		
		return org;
	}

	@Override
	public List<ProjectDto> getPList(Integer org_num) {
		List<ProjectDto> pList = dao.getPList(org_num);
		
		return pList;
	}

	@Override
	public List<ProjectDto> getNowPList(int org_num) {
		List<ProjectDto> nowPList = dao.getNowPList(org_num);
		return nowPList;
	}

	@Override
	public List<ProjectDto> getEndPList(int org_num) {
		List<ProjectDto> endPList = dao.getEndPList(org_num);
		return endPList;
	}

	@Override
	public int subscribe(int org_num, String user_id) {
		
		return dao.subscribe(org_num,user_id);
	}

	@Override
	public int subscribeCancle(int org_num, String user_id) {
		
		return dao.subscribeCancle(org_num,user_id);
	}

	@Override
	public boolean subChk(Integer org_num, String user_id) {

		return dao.subChk(org_num,user_id);
	}

	@Override
	public List<Object> getSubList(int org_num) {
		return dao.getSubList(org_num);
	}

}
