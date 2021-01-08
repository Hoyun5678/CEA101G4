package com.replyreport.model;

import java.sql.Date;

public class ReplyReportVO implements java.io.Serializable{
	private String reportId;
	private String empId;
	private String memId;
	private String replyId;
	private Integer reportResult;
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public Integer getReportResult() {
		return reportResult;
	}
	public void setReportResult(Integer reportResult) {
		this.reportResult = reportResult;
	}
	
	
}
