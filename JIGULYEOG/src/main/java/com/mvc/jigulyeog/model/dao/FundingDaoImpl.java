package com.mvc.jigulyeog.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.jigulyeog.model.dto.FundingListDto;

@Repository
public class FundingDaoImpl implements FundingDao{
	Logger logger = LoggerFactory.getLogger(FundingDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public Boolean checkFundingUser(String user_id, Integer pro_num) {
		logger.info("[ FundingDao : checkFundingUser ]");
		Boolean fundingCheck = false;
		Map<String,Object> param = new HashMap<String, Object>();
		
		param.put("user_id", user_id);
		param.put("pro_num", pro_num);
		
		try {
			String chk = sqlSession.selectOne(NAMESPACE+"checkFundingUser",param);
			// 기부한 유저라면,
			if(user_id != null && user_id.equals(chk)) {fundingCheck = true;}
			
		} catch (Exception e) {
			logger.info("[ error : checkFundingUser ]");
			e.getStackTrace();
		}
		
		
		return fundingCheck;
	}

	@Override
	public Boolean insertFundingList(FundingListDto sponser) {
		logger.info("[ FundingDao : insertFundingList ]");
		
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE+"insertFundingList",sponser);
		
		} catch (Exception e) {
			logger.info("[ error : insertFundingList ]");
			e.getStackTrace();
		}
		
		
		return (res>0)?true:false;
	}

	@Override
	public Boolean updatePrjectNowMoney(FundingListDto sponser) {
		logger.info("[ FundingDao : updatePrjectNowMoney ]");
		
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE+"updateProjectNowMoney",sponser);
		
		} catch (Exception e) {
			logger.info("[ error : updatePrjectNowMoney ]");
			e.getStackTrace();
		}
		
		
		return (res>0)?true:false;
	}

	@Override
	public Boolean updateFundingList(FundingListDto sponser) {
		logger.info("[ FundingDao : updateFundingList ]");
		
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE+"updateFundingList",sponser);
		
		} catch (Exception e) {
			logger.info("[ error : updateFundingList ]");
			e.getStackTrace();
		}
		
		
		return (res>0)?true:false;
	}

	@Override
	public Integer checkSuccessStatus(int pro_num) {
		logger.info("[ FundingDao : checkSuccessStatus ]");
		Integer res = 0;	
		try {
			res = sqlSession.selectOne(NAMESPACE+"checkSuccessStatus",pro_num);
		
		} catch (Exception e) {
			logger.info("[ error : checkSuccessStatus ]");
			e.getStackTrace();
		}
		
		return res;
	}

	@Override
	public Boolean updateSuccessStatus(int pro_num) {
		logger.info("[ FundingDao : updateSuccessStatus ]");
		int res = 0;	
		try {
			res = sqlSession.update(NAMESPACE+"updateSuccessStatus",pro_num);
		
		} catch (Exception e) {
			logger.info("[ error : updateSuccessStatus ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

}
