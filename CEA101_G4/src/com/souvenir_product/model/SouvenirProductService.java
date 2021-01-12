package com.souvenir_product.model;

import java.sql.Timestamp;
import java.util.List;

public class SouvenirProductService {
	private SouvenirProductDAO_interface dao;

	public SouvenirProductService() {
		dao = new SouvenirProductDAO();
	}

	public SouvenirProductVO addSou(String sou_type_id, String sou_name, Integer sou_price,
			Timestamp sou_on_date,Timestamp sou_off_date, Integer sou_like_count, String sou_des,
			Integer sou_status) {
		SouvenirProductVO soupVO = new SouvenirProductVO();
		soupVO.setSou_type_id(sou_type_id);
		soupVO.setSou_name(sou_name);
		soupVO.setSou_price(sou_price);
		soupVO.setSou_on_date(sou_on_date);
		soupVO.setSou_off_date(sou_off_date);
		soupVO.setSou_like_count(sou_like_count);
		soupVO.setSou_des(sou_des);
		soupVO.setSou_status(sou_status);

		dao.insert(soupVO);
		return soupVO;

	}

	public SouvenirProductVO updateSou(String sou_id, String sou_type_id, String sou_name, Integer sou_price,
			Timestamp sou_on_date,Timestamp sou_off_date, Integer sou_like_count, String sou_des,
			Integer sou_status) {
		SouvenirProductVO soupVO = new SouvenirProductVO();
		soupVO.setSou_id(sou_id);
		soupVO.setSou_type_id(sou_type_id);
		soupVO.setSou_name(sou_name);
		soupVO.setSou_price(sou_price);
		soupVO.setSou_on_date(sou_on_date);
		soupVO.setSou_off_date(sou_off_date);
		soupVO.setSou_like_count(sou_like_count);
		soupVO.setSou_des(sou_des);
		soupVO.setSou_status(sou_status);
		dao.update(soupVO);
		return soupVO;

	}

	public SouvenirProductVO getOneSou(String sou_id) {
		return dao.findByPrimaryKey(sou_id);
	}

	public void deleteSou(String sou_id) {
		dao.delete(sou_id);
	}

	public List<SouvenirProductVO> getAll() {
		return dao.getAll();
	}

}
