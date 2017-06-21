package com.parking.vo;

import java.io.Serializable;

/**
 * '관리자' 테이블
 * @author yeahni
 * @update 
 * @comment  
 */
public class Admin implements Serializable{

	// 필드
	/* 
	 * pk: a_id
	 */
	private String a_id;		// VARCHAR(7)  NOT NULL  COMMENT '관리자 아이디 (parking)'
	private String a_password;	// VARCHAR(7)  NOT NULL  COMMENT '관리자 비밀번호 (parking)'
	private String a_name;		// VARCHAR(7)  NOT NULL  COMMENT '관리자 이름 (parking)'
	
	// 생성자
	public Admin(){	
	}

	public Admin(String a_id, String a_password) {
		this.a_id = a_id;
		this.a_password = a_password;
	}
	
	public Admin(String a_id, String a_password, String a_name) {
		this(a_id, a_password);
		this.a_name = a_name;
	}
	
	// getter & setter
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}

	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}

	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	// override
	@Override
	public String toString() {
		return "Admin [a_id=" + a_id + ", a_password=" + a_password + ", a_name=" + a_name + "]";
	}	
	
}
