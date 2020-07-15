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

import com.mvc.jigulyeog.model.dto.CheerMessageDto;

@Repository
public class CheerMessageDaoImpl implements CheerMessageDao{
	Logger logger = LoggerFactory.getLogger(CheerMessageDaoImpl.class);
	
	@Autowired
	SqlSessionTemplate sqlSession;

	@Override
	public int totalArticle(int pro_num) {
		logger.info("[ CheerMessageDao : totalArticle ]");
		int totalArticle = 0;
		try {
			totalArticle = sqlSession.selectOne(NAMESPACE+"totalArticle",pro_num);
		}catch (Exception e) {
			logger.info("[ CheerMessageDao error : totalArticle ]");
		}
		logger.info("[ totalArticle : "+totalArticle+"]");
		return totalArticle;
	}

	@Override
	public List<CheerMessageDto> getArticleList(int startRow, int endRow, int pro_num) {
		logger.info("[ CheerMessageDao : getArticlelist ]");

		List<CheerMessageDto> getArticleList = new ArrayList<CheerMessageDto>();
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		
		param.put("startRow", startRow);
		param.put("endRow", endRow);
		param.put("pro_num", pro_num);
		
		
		try {
			getArticleList = sqlSession.selectList(NAMESPACE+"getArticleList",param);
		}catch (Exception e) {
			logger.info("[ CheerMessageDao error : getArticlelist ]");
			e.getStackTrace();
		}
		
		logger.info("[ getArticleList : "+getArticleList.size()+" ]");
		
		return getArticleList;
	}

	@Override
	public Boolean cheerMsgWrite(CheerMessageDto msg) {
		logger.info("[ CheerMessageDao : cheerMsgWrite ]");
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"writeCheerMessage",msg);
			
		} catch (Exception e) {
			logger.info("[ CheerMessageDao error : cheerMsgWrite ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public Boolean cheerMsgUpdate(CheerMessageDto msg) {
		logger.info("[ CheerMessageDao : cheerMsgUpdate ]");
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"updateCheerMessage",msg);
			
		} catch (Exception e) {
			logger.info("[ CheerMessageDao error : cheerMsgUpdate ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public Boolean cheerMsgDelete(int cheer_num) {
		logger.info("[ CheerMessageDao : cheerMsgDelete ]");
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"deleteCheerMessage",cheer_num);
			
		} catch (Exception e) {
			logger.info("[ CheerMessageDao error : cheerMsgDelete ]");
			e.getStackTrace();
		}
		
		return (res>0)?true:false;
	}

	@Override
	public String getUserImg(String user_id) {
		logger.info("[ CheerMessageDao : getUserImg ]");
		String imgName = "";
		try {
			imgName = sqlSession.selectOne(NAMESPACE+"getUserImg",user_id);
		}catch (Exception e) {
			logger.info("[ CheerMessageDao error : getUserImg ]");
			e.getStackTrace();
		}
		return imgName;
	}
	
	
}
