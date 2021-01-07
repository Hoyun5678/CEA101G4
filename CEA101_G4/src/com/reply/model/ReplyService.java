package com.reply.model;

import java.util.List;

public class ReplyService {
	private ReplyDAO_interface dao;
	
	public ReplyService() {
		dao = new ReplyJDBCDAO();
	}
	
	public ReplyVO addReply(String actPeriodId, String memId, 
			String replyContent, Integer replyVisible) {

		ReplyVO replyVO = new ReplyVO();

		replyVO.setActPeriodId(actPeriodId);
		replyVO.setMemId(memId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyVisible(replyVisible);
		dao.insert(replyVO);

		return replyVO;
	}

	public ReplyVO updateReply(String replyId, String actPeriodId, String memId, 
			String replyContent, java.sql.Timestamp replyTime, Integer replyVisible) {
//		 java.sql.Date replyTime
		ReplyVO replyVO = new ReplyVO();

		replyVO.setReplyId(replyId);
		replyVO.setActPeriodId(actPeriodId);
		replyVO.setMemId(memId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyTime(replyTime);
		replyVO.setReplyVisible(replyVisible);
		dao.update(replyVO);

		return replyVO;
	}

	public void deleteReply(String replyId) {
		dao.delete(replyId);
	}

	public ReplyVO getOneReply(String replyId) {
		return dao.findByPrimaryKey(replyId);
	}

	public List<ReplyVO> getAll() {
		return dao.getAll();
	}
}
