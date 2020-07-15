package com.mvc.jigulyeog.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.MainDao;
import com.mvc.jigulyeog.model.dto.ChungwonDto;
import com.mvc.jigulyeog.model.dto.ProjectDto;
import com.mvc.jigulyeog.model.dto.TogetherDto;

@Service
public class MainBizImpl implements MainBiz{
	Logger logger = LoggerFactory.getLogger(MainBizImpl.class);
	
	@Autowired
	MainDao dao;
	
	@Override
	public int countOrg() {
		logger.info("[ MainBiz : countOrg ]");
		return dao.countOrg();
	}
	
	@Override
	public ProjectDto popularProject() {
		logger.info("[ MainBiz : popularProject ]");
		return dao.popularProject();
	}

	@Override
	public List<ProjectDto> ongoingProject() {
		logger.info("[ MainBiz : ongoingProject ]");
		return dao.ongoingProject();
	}

	@Override
	public List<ChungwonDto> ongoingChungwon() {
		logger.info("[ MainBiz : ongoingChungwon ]");
		return dao.ongoingChungwon();
	}

	@Override
	public List<TogetherDto> ongoingTogether() {
		logger.info("[ MainBiz : ongoingTogether ]");
		return dao.ongoingTogether();
	}

}
