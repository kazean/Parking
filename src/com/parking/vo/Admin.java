package com.parking.vo;

import java.io.Serializable;

public class Admin implements Serializable{

	// 필드
	private String a_id;
	private String a_password;
	private String a_name;
	
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
