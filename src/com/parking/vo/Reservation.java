package com.parking.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * '예약' 테이블
 * @author yunmeheo
 * @update yeahni/gunna
 * @comment int가 String으로 되어있어서 변경, char가 String으로 되어있어서 변경했습니다.
 */
public class Reservation implements Serializable{

	// 필드
	/* 
	 * pk: reserve_number
	 * fk: reserve_c_id (customer -> c_id), reserve_parking_code(parking -> parking_code)
	 */
	int reserve_number;			// INT NOT NULL  AUTO_INCREMENT COMMENT '예약 번호'
	String reserve_c_id;		// VARCHAR(60)    NOT NULL      COMMENT '예약한 고객 아이디'
	int reserve_parking_code;	// INT            NOT NULL      COMMENT '예약된 주차장 코드'
	String reserve_entrance_time;	// DATETIME       NOT NULL      COMMENT '예약한 입차시간 (날짜, 시, 분 포함)'
	String reserve_exit_time;		// DATETIME       NOT NULL      COMMENT '예약한 출차시간 (날짜, 시, 분 포함)'
	char reserve_status;		// CHAR           NOT NULL      COMMENT '예약 - 상태 (C:완료 / R:환불)'
	String reserve_time;			// DATETIME       NOT NULL      COMMENT '예약 시간 (1시간 선결제된 시간 기준)'
	String refund_time;			// DATETIME       NULL  DEFAULT 0000 COMMENT '환불 시간 (버튼 누른 기준)'
	 
	// 생성자
	public Reservation() {
	}

	public Reservation(int reserve_number, String reserve_c_id, int reserve_parking_code, String reserve_entrance_time,
			String reserve_exit_time, char reserve_status, String reserve_time, String refund_time) {
		super();
		this.reserve_number = reserve_number;
		this.reserve_c_id = reserve_c_id;
		this.reserve_parking_code = reserve_parking_code;
		this.reserve_entrance_time = reserve_entrance_time;
		this.reserve_exit_time = reserve_exit_time;
		this.reserve_status = reserve_status;
		this.reserve_time = reserve_time;
		this.refund_time = refund_time;
	}

	public int getReserve_number() {
		return reserve_number;
	}

	public void setReserve_number(int reserve_number) {
		this.reserve_number = reserve_number;
	}

	public String getReserve_c_id() {
		return reserve_c_id;
	}

	public void setReserve_c_id(String reserve_c_id) {
		this.reserve_c_id = reserve_c_id;
	}

	public int getReserve_parking_code() {
		return reserve_parking_code;
	}

	public void setReserve_parking_code(int reserve_parking_code) {
		this.reserve_parking_code = reserve_parking_code;
	}

	public String getReserve_entrance_time() {
		return reserve_entrance_time;
	}

	public void setReserve_entrance_time(String reserve_entrance_time) {
		this.reserve_entrance_time = reserve_entrance_time;
	}

	public String getReserve_exit_time() {
		return reserve_exit_time;
	}

	public void setReserve_exit_time(String reserve_exit_time) {
		this.reserve_exit_time = reserve_exit_time;
	}

	public char getReserve_status() {
		return reserve_status;
	}

	public void setReserve_status(char reserve_status) {
		this.reserve_status = reserve_status;
	}

	public String getReserve_time() {
		return reserve_time;
	}

	public void setReserve_time(String reserve_time) {
		this.reserve_time = reserve_time;
	}

	public String getRefund_time() {
		return refund_time;
	}

	public void setRefund_time(String refund_time) {
		this.refund_time = refund_time;
	}

	@Override
	public String toString() {
		return "Reservation [reserve_number=" + reserve_number + ", reserve_c_id=" + reserve_c_id
				+ ", reserve_parking_code=" + reserve_parking_code + ", reserve_entrance_time=" + reserve_entrance_time
				+ ", reserve_exit_time=" + reserve_exit_time + ", reserve_status=" + reserve_status + ", reserve_time="
				+ reserve_time + ", refund_time=" + refund_time + "]";
	}
	 	 
}