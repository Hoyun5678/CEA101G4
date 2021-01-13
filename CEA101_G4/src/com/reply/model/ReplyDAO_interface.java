package com.reply.model;
import java.util.*;

public interface ReplyDAO_interface {
		public void insert(ReplyVO replyVO);
	    public void update(ReplyVO replyVO);
	    public void delete(String replyId);
	    public ReplyVO findByPrimaryKey(String replyId);
	    public List<ReplyVO> getAll();
	    public List<ReplyVO> findByActId(String actId);
		public List<ReplyVO> findByMemId(String memId); 
}

