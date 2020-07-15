package com.mvc.jigulyeog.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.jigulyeog.model.dao.FundingDao;
import com.mvc.jigulyeog.model.dto.FundingListDto;

@Transactional
@Service
public class FundingBizImpl implements FundingBiz{
	Logger logger = LoggerFactory.getLogger(FundingBizImpl.class);
	
	@Autowired
	FundingDao dao;
	
	@Override
	public Boolean checkFundingUser(String user_id, Integer pro_num) {
		logger.info("[ FundingBiz : checkFundingUser ]");

		return dao.checkFundingUser(user_id,pro_num);
	}

	@Override
	public Boolean fundingProcess(FundingListDto sponser){
		logger.info("[ FundingBiz : fundingProcess ]");
		// fundList
		Boolean is1 = dao.insertFundingList(sponser);
		Boolean is2 = false;
		if(is1) {
			is2 = dao.updatePrjectNowMoney(sponser);			
		}	
		return (is1&&is2)?true:false;
	}

	@Override
	public Boolean fundingProcessOverlap(FundingListDto sponser) {
		logger.info("[ FundingBiz : fundingProcessOverlap ]");
		// fundList
		Boolean is1 = dao.updateFundingList(sponser);
		Boolean is2 = false;
		if(is1) {
			is2 = dao.updatePrjectNowMoney(sponser);			
		}	
		return (is1&&is2)?true:false;
	}

	@Override
	public Boolean checkAndUpdateSuccessStatus(int pro_num) {
		logger.info("[ FundingBiz : checkAndUpdateSuccessStatus ]");
		Integer res = dao.checkSuccessStatus(pro_num);
		Boolean res1 = false;
		if(res!=null) {
			res1 = dao.updateSuccessStatus(pro_num);
		}
		return res1;
	}
	
	

}
