package com.roomordereddate.model;

import java.sql.Connection;
import java.util.List;


public interface RoomOrderedDateDAO_interface {
	
	public RoomOrderedDateVO insert(RoomOrderedDateVO roomOrderedDateVO);
	public void insertFromOrderDetail(RoomOrderedDateVO roomOrderedDateVO, Connection con);
	
    public void delete(String roomOrderedDateId);
    public List<RoomOrderedDateVO> getAll();
    public List<RoomOrderedDateVO> getByRoomId(String roomId);
	

}
