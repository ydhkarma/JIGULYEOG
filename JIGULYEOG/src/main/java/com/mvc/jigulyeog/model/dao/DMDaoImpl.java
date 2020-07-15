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

import com.mvc.jigulyeog.biz.DMBizImpl;
import com.mvc.jigulyeog.model.dto.DMChatDto;
import com.mvc.jigulyeog.model.dto.DMListDto;

@Repository
public class DMDaoImpl implements DMDao{
	Logger logger = LoggerFactory.getLogger(DMBizImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<DMListDto> getDMList(String user_id) {
		logger.info("[ DMDao : getDMList ]");
		List<DMListDto> DMList = new ArrayList<DMListDto>();
		
		try {
			DMList = sqlSession.selectList(NAMESPACE+"dmList",user_id);
		} catch (Exception e) {
			logger.info("[ DMDao error : getDMList ]");
			e.getStackTrace();
		}
		return DMList;
	}
	
	@Override
	public List<DMListDto> checkUnreadDM(String user_id) {
		logger.info("[ DMDao : checkUnreadDM ]");
		List<DMListDto> unreadDMList = new ArrayList<DMListDto>();
		try {
			unreadDMList = sqlSession.selectList(NAMESPACE+"checkUnreadDM",user_id);
		} catch (Exception e) {
			logger.info("[ DMDao error : checkUnreadDM ]");
			e.getStackTrace();
		}
		
		return unreadDMList;
	}


	@Override
	public DMListDto checkDMList(String user_id, String dm_id) {
		logger.info("[ DMDao : checkDMList ]");
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);
		param.put("dm_id", dm_id);
		
		DMListDto dto = new DMListDto();
		
		try {
			dto = sqlSession.selectOne(NAMESPACE+"checkDMList",param);
		} catch (Exception e) {
			logger.info("[ DMDao error : checkDMList ]");
			e.getStackTrace();
		}
		
		return dto;
	}

	@Override
	public Boolean addDM(String user_id, String dm_id,String dm_key) {
		logger.info("[ DMDao : addDM ]");
		HashMap<String, String> param = new HashMap<String, String>();
		int res = 0;
		
		param.put("user_id", user_id);
		param.put("dm_id", dm_id);
		param.put("dm_key", dm_key);
		
		try {
			res = sqlSession.insert(NAMESPACE+"addDM",param);
			
		} catch (Exception e) {
			logger.info("[ DMDao error : addDM ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public List<DMChatDto> getDMChatLog(DMListDto dto) {
		logger.info("[ DMDao : getDMChatLog ]");
		List<DMChatDto> chatList = new ArrayList<DMChatDto>();
		logger.info(dto.toString());
		try {
			chatList = sqlSession.selectList(NAMESPACE+"getDmChatLog",dto);
			
		} catch (Exception e) {
			logger.info("[ DMDao error : getDMChatLog ]");
			e.printStackTrace();
		}
		return chatList;
	}
	
	@Override
	public Boolean deleteDMList(String user_id, String dm_id) {
		logger.info("[ DMDao : deleteDMList ]");
		int res = 0;
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("user_id",user_id);
		param.put("dm_id",dm_id);
		
		try {
			res = sqlSession.delete(NAMESPACE+"deleteDMList",param);
		} catch (Exception e) {
			logger.info("[ DMDao error : deleteDMList ]");
			e.printStackTrace();
		}

		return (res>0)?true:false;
	}

	@Override
	public Boolean deleteDMChat(String user_id,String dm_key) {
		logger.info("[ DMDao : deleteDMChat ]");
		int res = 0;
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("user_id",user_id);
		param.put("dm_key",dm_key);
		
		try {
			res = sqlSession.delete(NAMESPACE+"deleteDMChat",param);
		} catch (Exception e) {
			logger.info("[ DMDao error : deleteDMChat ]");
			e.printStackTrace();
		}

		return (res>0)?true:false;
	}

	@Override
	public Boolean insertDMChat(DMChatDto chat) {
		logger.info("[ DMDao : insertDMChat ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"insertDMChat",chat);
		} catch (Exception e) {
			logger.info("[ DMDao error : insertDMChat ]");
			e.printStackTrace();
		}

		return (res>0)?true:false;
	}


}
