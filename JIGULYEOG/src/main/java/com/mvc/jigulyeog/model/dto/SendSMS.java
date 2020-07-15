package com.mvc.jigulyeog.model.dto;

import java.util.UUID;

public class SendSMS {
	private String msg; //보낼 메세지
	private String rphone;
	private String sphone1;
	private String sphone2;
	private String sphone3;
	private String smsType;
	private String testflag;
	private String returnurl;
	private String certiNumber; // 인증번호


	public SendSMS() {
		super();
	}
	
	

	public String getTestflag() {
		return testflag;
	}



	public void setTestflag(String testflag) {
		this.testflag = testflag;
	}



	public SendSMS(String msg, String rphone, String sphone1, String sphone2, String sphone3, String smsType,
			String certiNumber) {
		super();
		this.msg = msg;
		this.rphone = rphone;
		this.sphone1 = sphone1;
		this.sphone2 = sphone2;
		this.sphone3 = sphone3;
		this.smsType = smsType;
		this.certiNumber = certiNumber;
	}
	
	public String getReturnurl() {
		return returnurl;
	}



	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getRphone() {
		return rphone;
	}



	public void setRphone(String rphone) {
		this.rphone = rphone;
	}



	public String getSphone1() {
		return sphone1;
	}



	public void setSphone1(String sphone1) {
		this.sphone1 = sphone1;
	}



	public String getSphone2() {
		return sphone2;
	}



	public void setSphone2(String sphone2) {
		this.sphone2 = sphone2;
	}



	public String getSphone3() {
		return sphone3;
	}



	public void setSphone3(String sphone3) {
		this.sphone3 = sphone3;
	}



	public String getSmsType() {
		return smsType;
	}



	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}



	public void setCertiNumber(String certiNumber) {
		this.certiNumber = certiNumber;
	}



	public String getCertiNumber() {
		this.certiNumber= UUID.randomUUID().toString().substring(0, 5);
		return certiNumber;
	}



	@Override
	public String toString() {
		return "SendSMS [msg=" + msg + ", rphone=" + rphone + ", sphone1=" + sphone1 + ", sphone2=" + sphone2
				+ ", sphone3=" + sphone3 + ", smsType=" + smsType + ", testflag=" + testflag + ", returnurl="
				+ returnurl + ", certiNumber=" + certiNumber + "]";
	}


	
	
	
	
}
