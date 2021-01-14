package com.roomordereddate.model;

import java.sql.Date;
import java.util.List;


public class RoomOrderedDateService {
	
	private RoomOrderedDateDAO_interface dao;
	public RoomOrderedDateService() {
		dao = new RoomOrderedDateDAO();
	}
	
	public RoomOrderedDateVO addRoomOrderedDate(String roomOrderedDateId, 
			String roomId, String roomOrderId, Date roomOrderDate) {
		RoomOrderedDateVO roomOrderedDateVO = new RoomOrderedDateVO();
		
		roomOrderedDateVO.setRoomOrderedDateId(roomOrderedDateId);
		roomOrderedDateVO.setRoomId(roomId);
		roomOrderedDateVO.setRoomOrderId(roomOrderId);
		roomOrderedDateVO.setRoomOrderDate(roomOrderDate);
		
		dao.insert(roomOrderedDateVO);
		return roomOrderedDateVO;
	}
	
	public void deleteRoomOrderedDate(String roomOrderedDateId) {
		dao.delete(roomOrderedDateId);
	}
	
	public List<RoomOrderedDateVO> getAll() {

		return dao.getAll();
	}
	
	public List<String> getOrderedDateByRoomId(String roomId) {
		return dao.getByRoomId(roomId);
	}

}
