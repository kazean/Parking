package com.parking.vo;

import java.io.Serializable;
import java.sql.Date;

public class Car implements Serializable{
	private String car_c_id;
	private String car_number;
	private Date car_date;
	
	public Car(){
		
	}
	
	public Car(String car_c_id, String car_number) {
		super();
		this.car_c_id = car_c_id;
		this.car_number = car_number;
	}
	
	public Car(String car_c_id, String car_number, Date car_date) {
		super();
		this.car_c_id = car_c_id;
		this.car_number = car_number;
		this.car_date = car_date;
	}




	public String getCar_c_id() {
		return car_c_id;
	}
	public void setCar_c_id(String car_c_id) {
		this.car_c_id = car_c_id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public Date getCar_date() {
		return car_date;
	}
	public void setCar_date(Date car_date) {
		this.car_date = car_date;
	}
	
}
