package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.CheerMessageDto;

public interface CheerMessageDao {
	String NAMESPACE = "mapper.cheerMessage.";
	int totalArticle(int pro_num);
	List<CheerMessageDto> getArticleList(int startRow, int endRow,int pro_num);
	Boolean cheerMsgWrite(CheerMessageDto msg);
	Boolean cheerMsgUpdate(CheerMessageDto msg);
	Boolean cheerMsgDelete(int cheer_num);
	String getUserImg(String user_id);
	
}
