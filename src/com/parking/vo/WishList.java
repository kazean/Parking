package com.parking.vo;

import java.util.Date;

public class WishList {

	String wish_c_id;//`          VARCHAR(40)    NOT NULL     COMMENT '위시를 선택한 고객 아이디', 
	int wish_parking_code;//`  INT            NOT NULL     COMMENT '위시한 주차장 코드', 
	Date wish_date;//`		 DATETIME	NOT NULL     COMMENT '위시한 일자',
	
	
	 public WishList() {
		super();
	}
	 
	public WishList(String wish_c_id, int wish_parking_code, Date wish_date) {
		super();
		this.wish_c_id = wish_c_id;
		this.wish_parking_code = wish_parking_code;
		this.wish_date = wish_date;
	}
	public String getWish_c_id() {
		return wish_c_id;
	}
	public void setWish_c_id(String wish_c_id) {
		this.wish_c_id = wish_c_id;
	}
	public int getWish_parking_code() {
		return wish_parking_code;
	}
	public void setWish_parking_code(int wish_parking_code) {
		this.wish_parking_code = wish_parking_code;
	}
	public Date getWish_date() {
		return wish_date;
	}
	public void setWish_date(Date wish_date) {
		this.wish_date = wish_date;
	}

	@Override
	public String toString() {
		return "WishList [wish_c_id=" + wish_c_id + ", wish_parking_code=" + wish_parking_code + ", wish_date="
				+ wish_date + "]";
	}
	
	 
}