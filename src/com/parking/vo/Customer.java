package com.parking.vo;

import java.util.Date;

/**
 * '고객' 테이블
 * @author yunmeheo
 * @update 
 * @comment  
 */
public class Customer {

	// 필드
	/* 
	 * pk: c_id
	 * fk: c_card_number
	 */
	private String c_id;			// VARCHAR(40)    NOT NULL     COMMENT '고객 아이디 (이메일)'
	private String c_password;		// VARCHAR(16)    NOT NULL     COMMENT '고객 비밀번호 (최소 8자, 최대 16자)'
	private String c_name;			// VARCHAR(20)    NOT NULL     COMMENT '고객 이름'
	private String c_phone_number;	// VARCHAR(15)    NOT NULL     COMMENT '고객 전화번호 (-포함)'
	private String c_car_number;	// VARCHAR(20)    NOT NULL     COMMENT '고객 차량번호 (서울00가0000 / 00가0000)'
	private String c_card_number;	// VARCHAR(16)    NULL         COMMENT '고객 카드번호 (-제외)' 
	private Date c_date;			// DATETIME       NOT NULL     COMMENT '고객 가입일자'
	private char c_sign_path;		// CHAR(1)		  NOT NULL   DEFAULT 'E' COMMENT '고객 가입경로 (E:이메일, K:카카오, F:페이스북)'
	private char c_status;			// CHAR(1)        NOT NULL     COMMENT '고객 상태 (N: 일반 / D: 탈퇴)'
	
	// 생성자
	public Customer(){
	}
	
	// 로그인시 필요 - 생성자
	public Customer(String c_id, String c_password){
		this.c_id = c_id;
		this.c_password = c_password;
	}

	// 회원가입시 필요 - 생성자
	public Customer(String c_id, String c_password, String c_name, 
			String c_phone_number, 
			String c_car_number,String c_card_number, 
			char c_sign_path) {
		this(c_id, c_password);
		this.c_name = c_name;
		this.c_phone_number = c_phone_number;
		this.c_car_number = c_car_number;
		this.c_card_number  = c_card_number;
		this.c_sign_path = c_sign_path;
	}
	
	// 정보수정시 필요 - 생성자
	public Customer(String c_id, String c_password, String c_name, 
			String c_phone_number, 
			String c_car_number,String c_card_number, 
			char c_sign_path,
			char c_status) {
		this(c_id, c_password, c_name, c_phone_number, c_car_number, c_card_number, c_sign_path);
		this.c_status = c_status;
	}
	
	public Customer(String c_id, String c_password, String c_name, 
			String c_phone_number, 
			String c_car_number,String c_card_number, 
			Date c_date,
			char c_sign_path,
			char c_status) {
		this(c_id, c_password, c_name, c_phone_number, c_car_number, c_card_number, c_sign_path, c_status);
		this.c_date = c_date;
	}
	
	// getter & setter
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_password() {
		return c_password;
	}
	public void setC_password(String c_password) {
		this.c_password = c_password;
	}

	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_phone_number() {
		return c_phone_number;
	}
	public void setC_phone_number(String c_phone_number) {
		this.c_phone_number = c_phone_number;
	}

	public String getC_car_number() {
		return c_car_number;
	}
	public void setC_car_number(String c_car_number) {
		this.c_car_number = c_car_number;
	}

	public String getC_card_number() {
		return c_card_number;
	}
	public void setC_card_number(String c_card_number) {
		this.c_card_number = c_card_number;
	}

	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}

	public char getC_sign_path() {
		return c_sign_path;
	}

	public void setC_sign_path(char c_sign_path) {
		this.c_sign_path = c_sign_path;
	}

	public char getC_status() {
		return c_status;
	}
	public void setC_status(char c_status) {
		this.c_status = c_status;
	}

	// override
	@Override
	public String toString() {
		return "Customer [c_id=" + c_id + ", c_password=" + c_password + ", c_name=" + c_name + ", c_phone_number="
				+ c_phone_number + ", c_car_number=" + c_car_number + ", c_card_number=" + c_card_number + ", c_date="
				+ c_date + ", c_sign_path=" + c_sign_path + ", c_status=" + c_status + "]";
	}
	
}