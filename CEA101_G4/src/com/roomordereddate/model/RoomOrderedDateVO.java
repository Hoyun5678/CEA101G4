package com.roomordereddate.model;

import java.sql.Date;

public class RoomOrderedDateVO {
	private String roomOrderedDateId;
	private String roomId;
	private String roomOrderId;
	private Date roomOrderDate;
	
	public String getRoomOrderedDateId() {
		return roomOrderedDateId;
	}
	public void setRoomOrderedDateId(String roomOrderedDateId) {
		this.roomOrderedDateId = roomOrderedDateId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomOrderId() {
		return roomOrderId;
	}
	public void setRoomOrderId(String roomOrderId) {
		this.roomOrderId = roomOrderId;
	}
	public Date getRoomOrderDate() {
		return roomOrderDate;
	}
	public void setRoomOrderDate(Date roomOrderDate) {
		this.roomOrderDate = roomOrderDate;
	}

}
