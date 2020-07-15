package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.CommunityGuestbookDto;
import com.mvc.jigulyeog.model.dto.CommunityNoticeDto;
import com.mvc.jigulyeog.model.dto.UserDto;

public interface CommunityDao {
	String NAMESPACE = "mapper.community.";
	Boolean createCommunity(Integer pro_num);
	Integer checkCommunity(Integer pro_num);
	List<UserDto> fundingUserList(int pro_num);
	int getCommunityNo(int pro_num);
	Boolean commNoticeWrite(CommunityNoticeDto notice);
	Boolean commNoticeDelete(Integer notice_num);
	Boolean commNoticeUpdate(CommunityNoticeDto notice);
	int totalNotice(int com_num);
	List<CommunityNoticeDto> getNoticeList(int startRow, int endRow, int com_num);
	CommunityNoticeDto getNoticeOne(Integer notice_num);
	void countViewNotice(Integer notice_num);
	List<CommunityGuestbookDto> getGuestbookList(int startRow, int endRow,int com_num);
	int totalGuestbook(int com_num);
	Boolean commGuestbookWrite(CommunityGuestbookDto guestbook);
	Boolean commGuestbookUpdate(CommunityGuestbookDto guestbook);
	Boolean commGuestbookDelete(int cation_num);
}
