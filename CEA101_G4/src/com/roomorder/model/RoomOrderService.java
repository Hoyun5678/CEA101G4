package com.roomorder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.roomorderdetail.model.RoomOrderDetailVO;

public class RoomOrderService {
	
	private RoomOrderDAO_interface dao;
	
	public RoomOrderService( ) {
		dao = new RoomOrderJDBCDAO();
	}
	
	public RoomOrderVO addRoomOrder(String sellMemId, String memId/*, Timestamp roomOrderTime*/, Date checkInDate,
			Date checkOutDate, Timestamp expectArrTime, String roomOrderRemarks, Integer roomOrderSum,
			Integer roomOrderStatus, Integer roomPaymentStatus, List<RoomOrderDetailVO> list) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		
		roomOrderVO.setSellMemId(sellMemId);
		roomOrderVO.setMemId(memId);
//		roomOrderVO.setRoomOrderTime(roomOrderTime);
		roomOrderVO.setCheckInDate(checkInDate);
		roomOrderVO.setCheckOutDate(checkOutDate);
		roomOrderVO.setExpectArrTime(expectArrTime);
		roomOrderVO.setRoomOrderRemarks(roomOrderRemarks);
		roomOrderVO.setRoomOrderSum(roomOrderSum);
		roomOrderVO.setRoomOrderStatus(roomOrderStatus);
		roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);
		dao.insertWithDetail(roomOrderVO, list);
		
		return roomOrderVO;
	}
	
//	public RoomOrderVO updateRoomOrder(String roomOrderId, String sellMemId, String memId, Timestamp roomOrderTime,
//			Date checkInDate, Date checkOutDate, Timestamp expectArrTime, String roomOrderRemarks,
//			Integer roomOrderSum, Integer roomOrderStatus, Integer roomPaymentStatus) {
//		
//		RoomOrderVO roomOrderVO = new RoomOrderVO();
//		
//		roomOrderVO.setRoomOrderId(roomOrderId);
//		roomOrderVO.setSellMemId(sellMemId);
//		roomOrderVO.setMemId(memId);
//		roomOrderVO.setRoomOrderTime(roomOrderTime);
//		roomOrderVO.setCheckInDate(checkInDate);
//		roomOrderVO.setCheckOutDate(checkOutDate);
//		roomOrderVO.setExpectArrTime(expectArrTime);
//		roomOrderVO.setRoomOrderRemarks(roomOrderRemarks);
//		roomOrderVO.setRoomOrderSum(roomOrderSum);
//		roomOrderVO.setRoomOrderStatus(roomOrderStatus);
//		roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);
//		dao.update(roomOrderVO);	
//		
//		return roomOrderVO;
//	}
	
	public RoomOrderVO updateRoomOrder(String roomOrderId, Integer roomOrderStatus, Integer roomPaymentStatus) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setRoomOrderId(roomOrderId);
		roomOrderVO.setRoomOrderStatus(roomOrderStatus);
		roomOrderVO.setRoomPaymentStatus(roomPaymentStatus);
		dao.updatePaymentStatus(roomOrderVO);
		
		return roomOrderVO;
	}
	
	public RoomOrderVO updateRoomOrder(String roomOrderId, Integer roomOrderStatus) {
		
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setRoomOrderId(roomOrderId);
		roomOrderVO.setRoomOrderStatus(roomOrderStatus);
		dao.updateOrderStatus(roomOrderVO);	
		
		return roomOrderVO;
	}
	
	public void deleteRoomOrder(String roomOrderId) {
		dao.delete(roomOrderId);
	}
	
	public RoomOrderVO getOneRoomOrder(String roomOrderId) {
		return dao.findByPrimaryKey(roomOrderId);
	}
	
	public List<RoomOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomOrderVO> getByMemId(String sellMemId) {
		return dao.getByMemId(sellMemId);
	}
	
	public List<RoomOrderVO> getBySellMemIdAndDate(String sellMemId, String selectedDate) {
		return dao.getBySellMemIdAndDate(sellMemId, selectedDate);
	}

}
