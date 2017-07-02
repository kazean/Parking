package com.parking.vo;

import java.util.Date;

public class Partner {
	
	String p_id;//`            VARCHAR(20)    NOT NULL     COMMENT '파트너 아이디 (신청시, 관리자가 제공)', 
	String p_password;//`      VARCHAR(16)    NOT NULL     COMMENT '파트너 비밀번호', 
	String p_name;//`          VARCHAR(20)    NOT NULL     COMMENT '파트너 이름', 
	String p_phone_number;//`  VARCHAR(11)    NOT NULL     COMMENT '파트너 전화번호 (-제외)', 
	String p_license;//`       VARCHAR(11)    NOT NULL     COMMENT '파트너 사업자번호(10/11)', 
	String p_bank;//`          VARCHAR(10)    NOT NULL     COMMENT '파트너 계좌은행 (은행제외)', 
	String p_account;//`       VARCHAR(15)    NOT NULL     COMMENT '파트너 계좌번호 (-제외)', 
    Date p_date;//`          DATETIME       NOT NULL     COMMENT '파트너 등록일자', 
    char p_status;//`        CHAR(1)        NOT NULL     COMMENT '파트너 제휴상태 (N: 제휴중 / D : 제휴탈퇴)', 
	String p_register_admin_id;
    
    
    
    public Partner(String p_id, String p_password, String p_name, String p_phone_number, String p_license,
			String p_bank, String p_account, Date p_date, char p_status,String p_register_admin_id) {
		super();
		this.p_id = p_id;
		this.p_password = p_password;
		this.p_name = p_name;
		this.p_phone_number = p_phone_number;
		this.p_license = p_license;
		this.p_bank = p_bank;
		this.p_account = p_account;
		this.p_date = p_date;
		this.p_status = p_status;
		this.p_register_admin_id = p_register_admin_id;

	}
    
    
	public String getP_register_admin_id() {
		return p_register_admin_id;
	}


	public void setP_register_admin_id(String p_register_admin_id) {
		this.p_register_admin_id = p_register_admin_id;
	}


	public Partner() {
		super();
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_password() {
		return p_password;
	}
	public void setP_password(String p_password) {
		this.p_password = p_password;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_phone_number() {
		return p_phone_number;
	}
	public void setP_phone_number(String p_phone_number) {
		this.p_phone_number = p_phone_number;
	}
	public String getP_license() {
		return p_license;
	}
	public void setP_license(String p_license) {
		this.p_license = p_license;
	}
	public String getP_bank() {
		return p_bank;
	}
	public void setP_bank(String p_bank) {
		this.p_bank = p_bank;
	}
	public String getP_account() {
		return p_account;
	}
	public void setP_account(String p_account) {
		this.p_account = p_account;
	}
	public Date getP_date() {
		return p_date;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	public char getP_status() {
		return p_status;
	}
	public void setP_status(char p_status) {
		this.p_status = p_status;
	}
	@Override
	public String toString() {
		return "Partner [p_id=" + p_id + ", p_password=" + p_password + ", p_name=" + p_name + ", p_phone_number="
				+ p_phone_number + ", p_license=" + p_license + ", p_bank=" + p_bank + ", p_account=" + p_account
				+ ", p_date=" + p_date + ", p_status=" + p_status + "]";
	}
	

    
    
    

}
