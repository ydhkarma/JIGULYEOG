package com.mvc.jigulyeog.biz;

import java.util.List;

import com.mvc.jigulyeog.model.dto.CheerMessageDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;

public interface CheerMessageBiz {
	int totalArticle(int pro_num);
	Paging cheerMsgPaging(Integer page,int pro_num);
	List<CheerMessageDto> cheerMsgList(Paging paging,int pro_num);
	PageMaker getPageMaker(Paging paging);
	Boolean cheerMsgWrite(CheerMessageDto msg);
	Boolean cheerMsgUpdate(CheerMessageDto msg);
	Boolean cheerMsgDelete(int cheer_num);
	List<String> getUserImgList(List<CheerMessageDto> cMList);
}
