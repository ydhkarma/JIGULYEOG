package com.mvc.jigulyeog.model.dao;

import java.util.List;

import com.mvc.jigulyeog.model.dto.DMChatDto;
import com.mvc.jigulyeog.model.dto.DMListDto;

public interface DMDao {
	String NAMESPACE = "mapper.dm.";
	
	List<DMListDto> getDMList(String user_id);

	DMListDto checkDMList(String user_id, String dm_id);

	Boolean addDM(String user_id, String dm_id,String dm_key);

	List<DMChatDto> getDMChatLog(DMListDto dto);

	Boolean deleteDMList(String user_id, String dm_id);

	Boolean deleteDMChat(String user_id, String dm_key);

	List<DMListDto> checkUnreadDM(String user_id);

	Boolean insertDMChat(DMChatDto chat);

}
