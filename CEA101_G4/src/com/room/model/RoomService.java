package com.room.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RoomService {
	
	private RoomDAO_interface dao;
	
	public RoomService() {
		dao = new RoomDAO();
	}

	public RoomVO addRoom(/*String roomId,*/ String sellMemId, String roomName,
			Integer roomPrice, Integer roomCapacity, /*Timestamp roomOnTime,*/
			String roomDes, /*Integer roomCollect,*/ Integer roomStatus) {

		RoomVO roomVO = new RoomVO();

//		roomVO.setRoomId(roomId);
		roomVO.setSellMemId(sellMemId);
		roomVO.setRoomName(roomName);
		roomVO.setRoomPrice(roomPrice);
		roomVO.setRoomCapacity(roomCapacity);
//		roomVO.setRoomOnTime(roomOnTime);
		roomVO.setRoomDes(roomDes);
//		roomVO.setRoomCollect(roomCollect);
		roomVO.setRoomStatus(roomStatus);
		dao.insert(roomVO);

		return roomVO;
	}

	public RoomVO updateRoom(String roomId, String sellMemId, String roomName,
			Integer roomPrice, Integer roomCapacity, Timestamp roomOnTime,
			String roomDes, Integer roomCollect, Integer roomStatus) {

		RoomVO roomVO = new RoomVO();

		roomVO.setRoomId(roomId);
		roomVO.setSellMemId(sellMemId);
		roomVO.setRoomName(roomName);
		roomVO.setRoomPrice(roomPrice);
		roomVO.setRoomCapacity(roomCapacity);
		roomVO.setRoomOnTime(roomOnTime);
		roomVO.setRoomDes(roomDes);
		roomVO.setRoomCollect(roomCollect);
		roomVO.setRoomStatus(roomStatus);
		dao.update(roomVO);

		return roomVO;
	}
	
	public RoomVO updateRoom(String roomId, String sellMemId, String roomName,
			Integer roomPrice, Integer roomCapacity,
			String roomDes, Integer roomStatus) {

		RoomVO roomVO = new RoomVO();

		roomVO.setRoomId(roomId);
		roomVO.setRoomName(roomName);
		roomVO.setRoomPrice(roomPrice);
		roomVO.setRoomCapacity(roomCapacity);
		roomVO.setRoomDes(roomDes);
		roomVO.setRoomStatus(roomStatus);
		dao.updateRoomBySell(roomVO);

		return roomVO;
	}

	public void deleteRoom(String roomId) {
		dao.delete(roomId);
	}

	public RoomVO getOneRoom(String roomId) {
		return dao.findByPrimaryKey(roomId);
	}

	public List<RoomVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<RoomVO> getMemIdRoomList(String sellMemId) {
		return dao.getByMemId(sellMemId);
	}
	
	public List<RoomVO> getByDateRange(Date from, Date to) {
		return dao.getByDateRange(from, to);
	}
	

}
