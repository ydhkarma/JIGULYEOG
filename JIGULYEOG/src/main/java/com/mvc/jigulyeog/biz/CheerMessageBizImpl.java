package com.mvc.jigulyeog.biz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.jigulyeog.model.dao.CheerMessageDao;
import com.mvc.jigulyeog.model.dto.CheerMessageDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;

@Service
public class CheerMessageBizImpl implements CheerMessageBiz{
	private static final Logger logger = LoggerFactory.getLogger(CheerMessageBizImpl.class);
	@Autowired
	CheerMessageDao dao;
	
	@Override
	public int totalArticle(int pro_num) {
		logger.info("[ CheerMessageBiz : totalArticle ]");
		return dao.totalArticle(pro_num);
	}

	@Override
	public Paging cheerMsgPaging(Integer page,int pro_num) {
		logger.info("[ CheerMessageBiz : projectPaging ]");
		Paging paging = new Paging();
		
		paging.setPage(page);
		int totalArticle = dao.totalArticle(pro_num); // 전체 댓글 수 가져오기.
		
		paging.setTotalArticle(totalArticle); // setting
		
		// 댓글 페이지 개수
		paging.setTotalPage(totalArticle); // 전체 페이지 개수 setting
		
		// 댓글 시작 행 및 끝 행 설정
		paging.setStartRow();
		paging.setEndRow();
		
		logger.info("[ startRow : "+paging.getStartRow()+", endRow : "+paging.getEndRow()+" ]");
		
		return paging;
	}

	@Override
	public List<CheerMessageDto> cheerMsgList(Paging paging,int pro_num) {
		logger.info("[ CheerMessageBiz : cheerMsgList ]");
		List<CheerMessageDto> CMList = dao.getArticleList(paging.getStartRow(),paging.getEndRow(),pro_num);
		return CMList;
	}

	@Override
	public PageMaker getPageMaker(Paging paging) {
		logger.info("[ CheerMessageBiz : getPageMaker ]");
		PageMaker maker = new PageMaker();
		maker.setPaging(paging);
		return maker;
	}

	@Override
	public Boolean cheerMsgWrite(CheerMessageDto msg) {
		logger.info("[ CheerMessageBiz : cheerMsgWrite ]");
		return dao.cheerMsgWrite(msg);
	}

	@Override
	public Boolean cheerMsgUpdate(CheerMessageDto msg) {
		logger.info("[ CheerMessageBiz : cheerMsgUpdate ]");
		return dao.cheerMsgUpdate(msg);
	}

	@Override
	public Boolean cheerMsgDelete(int cheer_num) {
		logger.info("[ CheerMessageBiz : cheerMsgDelete ]");
		
		return dao.cheerMsgDelete(cheer_num);
	}

	@Override
	public List<String> getUserImgList(List<CheerMessageDto> cMList) {
		logger.info("[ CheerMessageBiz : getUserImgList ]");
		
		List<String> userImgList = new ArrayList<String>();
		
		for(CheerMessageDto dto:cMList) {
			String imgName = dao.getUserImg(dto.getUser_id());
			if(imgName==null) {imgName="";}
			userImgList.add(imgName);
			
		}
		
		return userImgList;
	}

}
