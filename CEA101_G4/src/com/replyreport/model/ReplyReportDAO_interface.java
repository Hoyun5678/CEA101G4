package com.replyreport.model;
import java.util.List;

public interface ReplyReportDAO_interface {
	public void insert(ReplyReportVO replyReportVO);
    public void update(ReplyReportVO replyReportVO);
    public void delete(String reportId);
    public ReplyReportVO findByPrimaryKey(String reportId);
    public List<ReplyReportVO> getAll();
	public List<ReplyReportVO> findByMemId(String memId);
}
