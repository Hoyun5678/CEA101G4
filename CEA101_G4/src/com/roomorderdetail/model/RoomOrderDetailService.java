package com.roomorderdetail.model;

import java.util.ArrayList;
import java.util.List;

public class RoomOrderDetailService {
	private RoomOrderDetailDAO_interface dao;
	public RoomOrderDetailService() {
		dao = new RoomOrderDetailDAO();
	}
	public RoomOrderDetailVO addRoomOrderDetail(String room_order_id, String room_id,Integer room_cur_price,String room_guest_name,String room_guest_mail,String room_guest_tel) {
		
		RoomOrderDetailVO rodVO = new RoomOrderDetailVO();
		
		rodVO.setRoom_order_id(room_order_id);
		rodVO.setRoom_id(room_id);
		rodVO.setRoom_cur_price(room_cur_price);
		rodVO.setRoom_guest_name(room_guest_name);
		rodVO.setRoom_guest_mail(room_guest_mail);
		rodVO.setRoom_guest_tel(room_guest_tel);
		dao.insert(rodVO);
		
		return rodVO;
	}

public RoomOrderDetailVO updateRoomOrderDetail(String room_order_id, String room_id, Integer room_cur_price,String room_guest_name,String room_guest_mail,String room_guest_tel){

	RoomOrderDetailVO rodVO = new RoomOrderDetailVO();

	rodVO.setRoom_order_id(room_order_id);
	rodVO.setRoom_id(room_id);
	rodVO.setRoom_cur_price(room_cur_price);
	rodVO.setRoom_guest_name(room_guest_name);
	rodVO.setRoom_guest_mail(room_guest_mail);
	rodVO.setRoom_guest_tel(room_guest_tel);
	dao.update(rodVO);

	return rodVO;
}

public void deleteRoomOrderDetail(String room_order_id, String room_id) {
	dao.delete(room_order_id,room_id);
}
public RoomOrderDetailVO getOneRoomOrderDetail(String room_order_id) {
	return dao.findByPrimaryKey(room_order_id);
}

public List<RoomOrderDetailVO> getAll() {
	List<RoomOrderDetailVO> list = new ArrayList<>();
	list = dao.getAll();
	if(list.isEmpty()) {
		list = new ArrayList<>();
	}
	return list;
}
}
