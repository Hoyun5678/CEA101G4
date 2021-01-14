package com.reply.model;

import java.util.List;

public class ReplyService {
	private ReplyDAO_interface dao;

	public ReplyService() {
		dao = new ReplyDAO();
	}

	public ReplyVO addReply(String actId, String memId, String replyContent, Integer replyVisible) {

		ReplyVO replyVO = new ReplyVO();

		replyVO.setActId(actId);
		replyVO.setMemId(memId);
		replyVO.setReplyContent(replyContent);
		replyVO.setReplyVisible(replyVisible);
		dao.insert(replyVO);

		return replyVO;
	}

	public ReplyVO updateReply(String replyId, String actId, String memId, String replyContent,
			java.sql.Timestamp replyTime, Integer replyVisible) {
//		 java.sql.Date replyTime
		ReplyVO replyVO = new ReplyVO();

		replyVO.setReplyId(replyId);
		replyVO.setActId(actId);
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

	public List<ReplyVO> getReplyByActId(String actId) {
		return dao.findByActId(actId);
	}
	
	public List<ReplyVO> getReplyByMemId(String memId) {
		return dao.findByMemId(memId);
	}

	public List<ReplyVO> getAll() {
		return dao.getAll();
	}
}
