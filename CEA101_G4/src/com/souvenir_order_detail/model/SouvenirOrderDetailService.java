package com.souvenir_order_detail.model;

import java.util.List;
import java.util.Map;



public class SouvenirOrderDetailService {
	private  SouvenirOrderDetailDAO_interface dao;
	public  SouvenirOrderDetailService() {
		dao = new  SouvenirOrderDetailDAO();
	}

	public SouvenirOrderDetailVO addSouvenirOrderDetail(String sou_order_id, String sou_id, Integer sou_order_amount, Integer sou_price) {

		SouvenirOrderDetailVO sodVO = new SouvenirOrderDetailVO();
		sodVO.setSou_order_id(sou_order_id);
		sodVO.setSou_id(sou_id);
		sodVO.setSou_order_amount(sou_order_amount);
		sodVO.setSou_price(sou_price);
		dao.insert(sodVO);

		return sodVO;
	}

	public SouvenirOrderDetailVO updateSouvenirOrderDetail(String sou_id, Integer sou_order_amount,Integer sou_price, String sou_order_id) {

		SouvenirOrderDetailVO sodVO = new SouvenirOrderDetailVO();

		sodVO.setSou_id(sou_id);
		sodVO.setSou_order_amount(sou_order_amount);
		sodVO.setSou_price(sou_price);
		sodVO.setSou_order_id(sou_order_id);
		dao.update(sodVO);

		return sodVO;
	}

	public void deleteSouvenirOrderDetail(String sou_order_id, String sou_id) {
		dao.delete(sou_order_id, sou_id);
	}

	public SouvenirOrderDetailVO getOneSouvenirOrderDetail(String sou_order_id) {
		return dao.findByPrimaryKey(sou_order_id);
	}
	public List<SouvenirOrderDetailVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public List<SouvenirOrderDetailVO> getAll() {
		return dao.getAll();
	}
}
