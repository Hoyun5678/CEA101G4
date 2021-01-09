package com.roomphoto.model;

import java.util.List;

import com.room.model.RoomVO;

public class RoomPhotoService {
	private RoomPhotoDAO_interface dao;
	public RoomPhotoService() {
		dao = new RoomPhotoJDBCDAO();
	}
	
	public RoomPhotoVO addRoomPhoto(String roomPhotoId, String roomId, byte[] roomPhoto, String roomPhotoContent) {
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		
		roomPhotoVO.setRoomPhotoId(roomPhotoId);
		roomPhotoVO.setRoomId(roomId);
		roomPhotoVO.setRoomPhoto(roomPhoto);
		roomPhotoVO.setRoomPhotoContent(roomPhotoContent);
		dao.insert(roomPhotoVO);
		
		return roomPhotoVO;
	}

	public RoomPhotoVO updateRoomPhoto(String roomPhotoId, String roomId, byte[] roomPhoto, String roomPhotoContent) {
		RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
		
		roomPhotoVO.setRoomPhotoId(roomPhotoId);
		roomPhotoVO.setRoomId(roomId);
		roomPhotoVO.setRoomPhoto(roomPhoto);
		dao.update(roomPhotoVO);
		
		return roomPhotoVO;
	}
	
	public void deleteRoomPhoto(String roomPhotoId) {
		dao.delete(roomPhotoId);
	}

	public RoomPhotoVO getOneRoomPhoto(String roomPhotoId) {
		return dao.findByPrimaryKey(roomPhotoId);
	}

	public List<RoomPhotoVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomPhotoVO> getByRoomId(String roomId) {
		return dao.getByRoomId(roomId);
	}
	

}
