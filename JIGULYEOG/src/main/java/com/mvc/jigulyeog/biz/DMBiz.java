package com.mvc.jigulyeog.biz;

import java.util.List;

import com.mvc.jigulyeog.model.dto.DMChatDto;
import com.mvc.jigulyeog.model.dto.DMListDto;

public interface DMBiz {

	List<DMListDto> getDMList(String user_id);

	// [1] DMList에 있는지 확인 and [2] 상대방 DMList에 있는지 확인
	DMListDto checkDMList(String user_id, String dm_id);

	// [3]
	Boolean addDM(String user_id, String dm_id);

	List<DMChatDto> getDMCHatLog(DMListDto dto);

	Boolean deleteDM(String user_id, String dm_id, String dm_key);

	List<DMListDto> checkUnreadDM(String user_id);

	Boolean insertDMChat(DMChatDto chat);

}
