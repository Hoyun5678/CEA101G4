package com.reply.model;

import java.sql.*;

public class ReplyVO implements java.io.Serializable {
	private String replyId;
	private String actPeriodId;
	private String memId;
	private String replyContent;
	private Timestamp replyTime;
	private Integer replyVisible;

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getActPeriodId() {
		return actPeriodId;
	}

	public void setActPeriodId(String actPeriodId) {
		this.actPeriodId = actPeriodId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Timestamp getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Timestamp date) {
		this.replyTime = date;
	}

	public Integer getReplyVisible() {
		return replyVisible;
	}

	public void setReplyVisible(Integer replyVisible) {
		this.replyVisible = replyVisible;
	}

}
