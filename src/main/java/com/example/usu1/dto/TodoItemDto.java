package com.example.usu1.dto;

import java.util.Date;

public class TodoItemDto {
	private long seq;
	private String title;
	private String content;
	private Date startDt;
	private Date regDt;
	private Date modiDt;
	
	public long getSeq() {
		return this.seq;
	}
	
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDt() {
		return this.startDt;
	}
	
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	
	public Date getModiDt() {
		return this.modiDt;
	}
	
	public void setMiduDt(Date modiDt) {
		this.modiDt = modiDt;
	}
	public Date getRegDt() {
		return this.regDt;
	}
	
	public void setRegtDt(Date regDt) {
		this.regDt = regDt;
	}
}
