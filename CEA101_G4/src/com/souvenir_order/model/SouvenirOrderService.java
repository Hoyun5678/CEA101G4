package com.souvenir_order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.souvenir_order_detail.model.SouvenirOrderDetailVO;


public class SouvenirOrderService {
	private SouvenirOrderDAO_interface dao;

	public SouvenirOrderService() {
		dao = new SouvenirOrderJDBCDAO();
	}

	public SouvenirOrderVO addSouvenirOrder(String emp_id, String mem_id,
			String sou_receiver_name, String sou_receiver_address, String sou_receiver_phone, Integer sou_shipment_fee,
			Integer sou_order_sum_price, String sou_order_remarks, Integer sou_shipping_method,
			Integer sou_order_status, Integer sou_payment_status, Integer sou_shipment_status) {

		SouvenirOrderVO soVO = new SouvenirOrderVO();
		soVO.setEmp_id(emp_id);
		soVO.setMem_id(mem_id);
		soVO.setSou_receiver_name(sou_receiver_name);
		soVO.setSou_receiver_address(sou_receiver_address);
		soVO.setSou_receiver_phone(sou_receiver_phone);
		soVO.setSou_shipment_fee(sou_shipment_fee);
		soVO.setSou_order_sum_price(sou_order_sum_price);
		soVO.setSou_order_remarks(sou_order_remarks);
		soVO.setSou_shipping_method(sou_shipping_method);
		soVO.setSou_order_status(sou_order_status);
		soVO.setSou_payment_status(sou_payment_status);
		soVO.setSou_shipment_status(sou_shipment_status);
//		dao.insert(soVO);

		return soVO;
	}

	public SouvenirOrderVO updateSouvenirOrder(String emp_id, String mem_id, Timestamp sou_order_date,
			String sou_receiver_name, String sou_receiver_address, String sou_receiver_phone, Integer sou_shipment_fee,
			Integer sou_order_sum_price, String sou_order_remarks, Integer sou_shipping_method,
			Integer sou_order_status, Integer sou_payment_status, Integer sou_shipment_status,String sou_order_id) {

		SouvenirOrderVO soVO = new SouvenirOrderVO();
		soVO.setEmp_id(emp_id);
		soVO.setMem_id(mem_id);
		soVO.setSou_order_date(sou_order_date);
		soVO.setSou_receiver_name(sou_receiver_name);
		soVO.setSou_receiver_address(sou_receiver_address);
		soVO.setSou_receiver_phone(sou_receiver_phone);
		soVO.setSou_shipment_fee(sou_shipment_fee);
		soVO.setSou_order_sum_price(sou_order_sum_price);
		soVO.setSou_order_remarks(sou_order_remarks);
		soVO.setSou_shipping_method(sou_shipping_method);
		soVO.setSou_order_status(sou_order_status);
		soVO.setSou_payment_status(sou_payment_status);
		soVO.setSou_shipment_status(sou_shipment_status);
		soVO.setSou_order_id(sou_order_id);
		dao.update(soVO);

		return soVO;
	}

	public void deleteSouvenirOrder(String sou_order_id) {
		dao.delete(sou_order_id);
	}

	public SouvenirOrderVO getOneSouvenirOrder(String sou_order_id) {
		return dao.findByPrimaryKey(sou_order_id);
	}

	public List<SouvenirOrderVO> getAll() {
		return dao.getAll();
	}
	public List<SouvenirOrderVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public void insertWithSouvenirOrderDetail(SouvenirOrderVO soVO,List<SouvenirOrderDetailVO> list) {
		 dao.insertWithDetail(soVO, list);
	}
}
