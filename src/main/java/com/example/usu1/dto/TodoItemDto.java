package com.example.usu1.dto;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("TodoItemDto")
public class TodoItemDto {
	private List<Long> seqList;
	private Long seq;
	private String title;
	private String content;
	private Boolean completed;
	private Date startDt;
	private Date createDt;
	private Date updateDt;

	private Date startFromDt;
	private Date startToDt;

	// 페이징 처리를 위한 parameters
	private int page;
	private int size;
	private int offset;

	// 정렬 처리를 위한 parameters
    private String sort;
    private List<String> sortList;

	public List<Long> getSeqList() {
		return this.seqList;
	}

	public void setSeqList(List<Long> seqList) {
		this.seqList = seqList;
	}

	public Date getStartFromDt() {
		return this.startFromDt;
	}
	
	public void setStartFromDt(Date startFromDt) {
		this.startFromDt = startFromDt;
	}

	public Date getStartToDt() {
		return this.startToDt;
	}
	
	public void setStartToDt(Date startToDt) {
		this.startToDt = startToDt;
	}

    public String getSort() {
        return this.sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<String> getSortList() {
        return this.sortList;
    }
    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }

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

    public Boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
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
