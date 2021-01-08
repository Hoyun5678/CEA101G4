package com.souvenir_product.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SouvenirProductVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sou_id;
	private String sou_type_id;
	private String sou_name ;
	private Integer sou_price;
	private Timestamp sou_on_date;
	private Timestamp sou_off_date;
	private Integer sou_like_count;
	private String sou_des;
	private Integer sou_status;
	private int quantity;
	public String getSou_id() {
		return sou_id;
	}
	public void setSou_id(String sou_id) {
		this.sou_id = sou_id;
	}
	public String getSou_type_id() {
		return sou_type_id;
	}
	public void setSou_type_id(String sou_type_id) {
		this.sou_type_id = sou_type_id;
	}
	public String getSou_name() {
		return sou_name;
	}
	public void setSou_name(String sou_name) {
		this.sou_name = sou_name;
	}
	public Integer getSou_price() {
		return sou_price;
	}
	public void setSou_price(Integer sou_price) {
		this.sou_price = sou_price;
	}
	public Timestamp getSou_on_date() {
		return sou_on_date;
	}
	public void setSou_on_date(Timestamp sou_on_date) {
		this.sou_on_date = sou_on_date;
	}
	public Timestamp getSou_off_date() {
		return sou_off_date;
	}
	public void setSou_off_date(Timestamp sou_off_date) {
		this.sou_off_date = sou_off_date;
	}
	public Integer getSou_like_count() {
		return sou_like_count;
	}
	public void setSou_like_count(Integer sou_like_count) {
		this.sou_like_count = sou_like_count;
	}
	public String getSou_des() {
		return sou_des;
	}
	public void setSou_des(String sou_des) {
		this.sou_des = sou_des;
	}
	public Integer getSou_status() {
		return sou_status;
	}
	public void setSou_status(Integer sou_status) {
		this.sou_status = sou_status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
}