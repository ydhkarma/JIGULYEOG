package com.mvc.jigulyeog.model.dto;

public class MakeDMKey {
	private String id1;
	private String id2;
	
	private String dm_key;

	public MakeDMKey() {
		super();
	}
	
	public MakeDMKey(String id1, String id2) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		int chk = id1.compareTo(id2);
		
		if(chk>0) {
			this.dm_key = id2.concat(id1);
		}
		
		if(chk<0) {
			this.dm_key = id1.concat(id2);
		}
		
		if(chk==0) {
			this.dm_key = id1 + id2;
		}
		
	}
	
	

	public String getId1() {
		return id1;
	}
	

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}


	public String getDm_key() {
		return dm_key;
	}

	public void setDm_key(String dm_key) {
		this.dm_key = dm_key;
	}
	
	
}
