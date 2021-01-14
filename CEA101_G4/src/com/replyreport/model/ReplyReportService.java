package com.replyreport.model;

import java.util.List;

import com.reply.model.ReplyVO;

public class ReplyReportService {
	private ReplyReportDAO_interface dao;

	public ReplyReportService() {
		dao = new ReplyReportDAO();
	}

	public ReplyReportVO addReplyReportByMem(String memId, String replyId) {

		ReplyReportVO replyReportVO = new ReplyReportVO();

		replyReportVO.setMemId(memId);
		replyReportVO.setReplyId(replyId);
		replyReportVO.setReportResult(0);
		dao.insert(replyReportVO);

		return replyReportVO;
	}

	public ReplyReportVO addReplyReport(String empId, String memId, String replyId, Integer reportResult) {

		ReplyReportVO replyReportVO = new ReplyReportVO();

		replyReportVO.setEmpId(empId);
		replyReportVO.setMemId(memId);
		replyReportVO.setReplyId(replyId);
		replyReportVO.setReportResult(reportResult);
		dao.insert(replyReportVO);

		return replyReportVO;
	}

	public ReplyReportVO updateReplyReport(String reportId, String empId, String memId, String replyId,
			Integer reportResult) {

		ReplyReportVO replyReportVO = new ReplyReportVO();

		replyReportVO.setReportId(reportId);
		replyReportVO.setEmpId(empId);
		replyReportVO.setMemId(memId);
		replyReportVO.setReplyId(replyId);
		replyReportVO.setReportResult(reportResult);
		dao.update(replyReportVO);

		return replyReportVO;
	}

	public void deleteReplyReport(String reportId) {
		dao.delete(reportId);
	}

	public ReplyReportVO getOneReplyReport(String reportId) {
		return dao.findByPrimaryKey(reportId);
	}
	
	public List<ReplyReportVO> getReplyReportByMemId(String memId) {
		return dao.findByMemId(memId);
	}

	public List<ReplyReportVO> getAll() {
		return dao.getAll();
	}
}
