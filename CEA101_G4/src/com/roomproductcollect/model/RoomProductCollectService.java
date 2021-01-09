package com.roomproductcollect.model;

import java.util.ArrayList;
import java.util.List;

import com.roomorderdetail.model.RoomOrderDetailVO;

public class RoomProductCollectService {
	private RoomProductCollectDAO_interface dao;
	
	public RoomProductCollectService() {
		dao = new RoomProductCollectJDBCDAO();
	}
	
	public int addCollect(String mem_id, String room_id) {
		dao.insert(mem_id,room_id);
		return dao.countCollect(room_id);
	}
	
	public int deleteCollect(String mem_id, String room_id) {
		dao.delete(mem_id, room_id);
		return dao.countCollect(room_id);
	}
	public List<String> getOneByMemId(String mem_id){
		return dao.findByMemId(mem_id);
	}
	public List<String> getOneByRoomId(String room_id){
		return dao.findByRoomId(room_id);
	}
	public List<RoomProductCollectVO> getAll() {
		return dao.getAll();
	}
	public int getCountCollect(String room_id) {
		return dao.countCollect(room_id);
	}
	public boolean getCollect(String mem_id , String room_id){
		return dao.findByMemId(mem_id).contains(room_id);
	}
}
