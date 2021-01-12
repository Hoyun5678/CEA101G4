package com.faq.model;

import java.util.List;

import com.foodspot.model.FoodSpotVO;

public class FAQService {
	private FAQDAO_interface dao;
	public FAQService() {
		dao = new FAQDAO();
	}
	public FAQVO addFAQ(String faq_question, String faq_answer) {

		FAQVO faqVO = new FAQVO();
		
		faqVO.setFaq_question(faq_question);
		faqVO.setFaq_answer(faq_answer);
		
		dao.insert(faqVO);
		
		return faqVO;
	}
	public FAQVO updateFAQ(String faq_id, String faq_question, String faq_answer) {

		FAQVO faqVO = new FAQVO();
		
		faqVO.setFaq_id(faq_id);
		faqVO.setFaq_question(faq_question);
		faqVO.setFaq_answer(faq_answer);
				
		dao.update(faqVO);

		return faqVO;
	}
	public void deleteFAQ(String faq_id) {
		dao.delete(faq_id);
	}

	public FAQVO getOneFAQ(String faq_id) {
		return dao.findByPrimaryKey(faq_id);
	}

	public List<FAQVO> getAll() {
		return dao.getAll();
	}

}
