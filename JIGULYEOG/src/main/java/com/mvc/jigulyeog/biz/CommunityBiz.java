package com.mvc.jigulyeog.biz;

import java.util.List;

import com.mvc.jigulyeog.model.dto.CheerMessageDto;
import com.mvc.jigulyeog.model.dto.CommunityGuestbookDto;
import com.mvc.jigulyeog.model.dto.CommunityNoticeDto;
import com.mvc.jigulyeog.model.dto.FundingListDto;
import com.mvc.jigulyeog.model.dto.PageMaker;
import com.mvc.jigulyeog.model.dto.Paging;
import com.mvc.jigulyeog.model.dto.UserDto;

public interface CommunityBiz {

	Boolean createCommunity(Integer pro_num);

	Integer checkCommnunity(Integer pro_num);

	List<UserDto> fundingUserList(Integer pro_num);

	int getCommunityNo(int pro_num);

	Boolean commNoticeWrite(CommunityNoticeDto notice);

	Paging noticePaging(Integer nPage,int com_num);

	List<CommunityNoticeDto> noticeList(Paging paging, int commNo);

	PageMaker getPageMaker(Paging paging);

	CommunityNoticeDto getNoticeOne(Integer notice_num);

	Boolean commNoticeUpdate(CommunityNoticeDto notice);

	Boolean commNoticeDelete(Integer notice_num);

	void countViewNotice(Integer notice_num);

	List<CommunityGuestbookDto> guestbookList(Paging gPaging,int com_num);

	Paging guestbookPaging(Integer gPage,int com_num);

	Boolean commGuestbookWrite(CommunityGuestbookDto guestbook);

	Boolean commGuestbookUpdate(CommunityGuestbookDto guestbook);

	Boolean commGuestbookDelete(int cation_num);

	
}
