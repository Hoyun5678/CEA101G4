package com.roomorderdetail.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

public interface RoomOrderDetailDAO_interface {
	public void insert(RoomOrderDetailVO rodVO);
	public void update(RoomOrderDetailVO rodVO);
	public void delete(String room_order_id, String room_id);
	public RoomOrderDetailVO findByPrimaryKey(String room_order_id);
	public List<RoomOrderDetailVO> getAll();
	public void insertFromOrder(RoomOrderDetailVO rodVO, java.sql.Connection con, Date checkInDate, Date checkOutDate);

	
}
