package com.roomordereddate.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;


public interface RoomOrderedDateDAO_interface {
	
	public RoomOrderedDateVO insert(RoomOrderedDateVO roomOrderedDateVO);
	public void insertFromOrderDetail(RoomOrderedDateVO roomOrderedDateVO, Connection con);
	
    public void delete(String roomOrderedDateId);
    public List<RoomOrderedDateVO> getAll();
    public List<String> getByRoomId(String roomId);
	List<String> getBySellMemId(String sellMemId);
	

}
