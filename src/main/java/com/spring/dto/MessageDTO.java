package com.spring.dto;

/**
 * 
 * @author 56949
 *
 */
public class MessageDTO {
	
	private String msgDetail;

	public String getMsgDetail() {
		return msgDetail;
	}

	public void setMsgDetail(String msgDetail) {
		this.msgDetail = msgDetail;
	}

	public MessageDTO(String msgDetail) {
		super();
		this.msgDetail = msgDetail;
	}


}
