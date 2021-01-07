package com.roomorderdetail.model;

import java.io.Serializable;

public class RoomOrderDetailVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String room_order_id;
	private String room_id;
	private Integer room_cur_price;
	private String room_guest_name;
	private String room_guest_mail;
	private String room_guest_tel;
	
	
	public String getRoom_order_id() {
		return room_order_id;
	}
	public void setRoom_order_id(String room_order_id) {
		this.room_order_id = room_order_id;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public Integer getRoom_cur_price() {
		return room_cur_price;
	}
	public void setRoom_cur_price(Integer room_cur_price) {
		this.room_cur_price = room_cur_price;
	}
	public String getRoom_guest_name() {
		return room_guest_name;
	}
	public void setRoom_guest_name(String room_guest_name) {
		this.room_guest_name = room_guest_name;
	}
	public String getRoom_guest_mail() {
		return room_guest_mail;
	}
	public void setRoom_guest_mail(String room_guest_mail) {
		this.room_guest_mail = room_guest_mail;
	}
	public String getRoom_guest_tel() {
		return room_guest_tel;
	}
	public void setRoom_guest_tel(String room_guest_tel) {
		this.room_guest_tel = room_guest_tel;
	}
	
	
	
}
