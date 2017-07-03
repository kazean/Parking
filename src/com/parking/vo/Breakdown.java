package com.parking.vo;

import java.io.Serializable;

public class Breakdown implements Serializable {
    String reserve_c_id;				// VARCHAR(60)    NOT NULL      COMMENT '예약한 고객 아이디'
    int reserve_parking_code;			// INT            NOT NULL      COMMENT '예약된 주차장 코드'
    String reserve_entrance_time;		// DATETIME       NOT NULL      COMMENT '예약한 입차시간 (날짜, 시, 분 포함)'
    String reserve_exit_time;			// DATETIME       NOT NULL      COMMENT '예약한 출차시간 (날짜, 시, 분 포함)'
    char reserve_status;					// CHAR           NOT NULL      COMMENT '예약 - 상태 (C:완료 / R:환불)'
    String reserve_time;				// DATETIME       NOT NULL      COMMENT '예약 시간 (1시간 선결제된 시간 기준)'
    double parking_rates;
    int parking_rates_time;				// 주차장 가격

    private String parking_name;		// 주차장 이름
    private String parking_address;		// 주차장 주소 (도로명 기준)

    boolean review_check;

    public Breakdown() {
    }

    public Breakdown(String reserve_c_id, int reserve_parking_code, String reserve_entrance_time, String reserve_exit_time, char reserve_status, String reserve_time, int parking_rates_time, String parking_name, String parking_address) {
        this.reserve_c_id = reserve_c_id;
        this.reserve_parking_code = reserve_parking_code;
        this.reserve_entrance_time = reserve_entrance_time;
        this.reserve_exit_time = reserve_exit_time;
        this.reserve_status = reserve_status;
        this.reserve_time = reserve_time;
        this.parking_rates_time = parking_rates_time;
        this.parking_name = parking_name;
        this.parking_address = parking_address;
    }

    public Breakdown(String reserve_c_id, int reserve_parking_code, String reserve_entrance_time, String reserve_exit_time, char reserve_status, String reserve_time, double parking_rates, int parking_rates_time, String parking_name, String parking_address, boolean review_check) {
        this.reserve_c_id = reserve_c_id;
        this.reserve_parking_code = reserve_parking_code;
        this.reserve_entrance_time = reserve_entrance_time;
        this.reserve_exit_time = reserve_exit_time;
        this.reserve_status = reserve_status;
        this.reserve_time = reserve_time;
        this.parking_rates = parking_rates;
        this.parking_rates_time = parking_rates_time;
        this.parking_name = parking_name;
        this.parking_address = parking_address;
        this.review_check = review_check;
    }

    public double getParking_rates() {
        return parking_rates;
    }

    public void setParking_rates(double parking_rates) {
        this.parking_rates = parking_rates;
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

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getParking_address() {
        return parking_address;
    }

    public void setParking_address(String parking_address) {
        this.parking_address = parking_address;
    }

    public int getParking_rates_time() {
        return parking_rates_time;
    }

    public void setParking_rates_time(int parking_rates_time) {
        this.parking_rates_time = parking_rates_time;
    }

    public boolean isReview_check() {
        return review_check;
    }

    public void setReview_check(boolean review_check) {
        this.review_check = review_check;
    }

    @Override
    public String toString() {
        return "Breakdown{" +
                "reserve_c_id='" + reserve_c_id + '\'' +
                ", reserve_parking_code=" + reserve_parking_code +
                ", reserve_entrance_time='" + reserve_entrance_time + '\'' +
                ", reserve_exit_time='" + reserve_exit_time + '\'' +
                ", reserve_status=" + reserve_status +
                ", reserve_time='" + reserve_time + '\'' +
                ", parking_rates=" + parking_rates +
                ", parking_rates_time=" + parking_rates_time +
                ", parking_name='" + parking_name + '\'' +
                ", parking_address='" + parking_address + '\'' +
                ", review_check=" + review_check +
                '}';
    }
	
}
