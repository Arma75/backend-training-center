package com.example.usu1.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("TodoItemDto")
public class TodoItemDto {
	private Long seq;
	private String title;
	private String content;
	private boolean completed;
	private Date startDt;
	private Date createDt;
	private Date updateDt;

	private int page;
	private int size;
	private int offset;

	public int getPage() {
		return this.page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getOffset() {
		return this.offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public Long getSeq() {
		return this.seq;
	}
	
	public void setSeq(Long seq) {
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

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreateDt() {
        return this.createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return this.updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
