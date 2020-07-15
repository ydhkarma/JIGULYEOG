package com.mvc.jigulyeog.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.DMDao;
import com.mvc.jigulyeog.model.dto.DMChatDto;
import com.mvc.jigulyeog.model.dto.DMListDto;
import com.mvc.jigulyeog.model.dto.MakeDMKey;

@Service
public class DMBizImpl implements DMBiz{
	Logger logger = LoggerFactory.getLogger(DMBizImpl.class);
	
	@Autowired
	DMDao dao;
	
	@Override
	public List<DMListDto> getDMList(String user_id) {
		logger.info("[ DMBiz : getDMList ]");

		return dao.getDMList(user_id);
	}
	
	@Override
	public List<DMListDto> checkUnreadDM(String user_id) {
		logger.info("[ DMBiz : checkUnreadDM ]");
		return dao.checkUnreadDM(user_id);
	}
	

	@Override
	public DMListDto checkDMList(String user_id, String dm_id) {
		logger.info("[ DMBiz : checkDMList ]");

		return dao.checkDMList(user_id,dm_id);
	}

	@Override
	public Boolean addDM(String user_id, String dm_id) {
		logger.info("[ DMBiz : addDM ]");
		
		String dm_key = (new MakeDMKey(user_id, dm_id)).getDm_key();
		logger.info("[ dm_key : "+dm_key+" ]");
		return dao.addDM(user_id,dm_id,dm_key);
	}

	@Override
	public List<DMChatDto> getDMCHatLog(DMListDto dto) {
		logger.info("[ DMBiz : getDMCHatLog ]");

		return dao.getDMChatLog(dto);
	}

	@Override
	public Boolean deleteDM(String user_id, String dm_id,String dm_key) {
		logger.info("[ DMBiz : deleteDM ]");
		
		Boolean is1 = dao.deleteDMList(user_id,dm_id);
		Boolean is2 = false;
		if(is1) {
			is2 = dao.deleteDMChat(user_id,dm_key);
		}
		
		return is2;
	}

	@Override
	public Boolean insertDMChat(DMChatDto chat) {
		logger.info("[ DMBiz : insertDMChat ]");
		Boolean is1 = dao.insertDMChat(chat);
		
		String dm_host = chat.getReceive_id();
		chat.setDm_host(dm_host);
		Boolean is2 = dao.insertDMChat(chat);
		
		return (is1&&is2)?true:false;
	}

}
