package com.mvc.jigulyeog.biz;

import com.mvc.jigulyeog.model.dto.FundingListDto;

public interface FundingBiz {

	Boolean checkFundingUser(String user_id, Integer pro_num);
	Boolean fundingProcess(FundingListDto sponser);
	Boolean fundingProcessOverlap(FundingListDto sponser);
	Boolean checkAndUpdateSuccessStatus(int pro_num);
	
}
