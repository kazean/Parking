package com.parking.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.json.simple.JSONObject;

/**
 * '주차장' 테이블
 * @author yeahni
 * @update hawstrike
 * @comment 칼럼이 추가되었습니다.
 */
public class Parking implements Serializable{
	
	// 필드
	/* 
	 * pk: parking_code
	 * fk: parking_p_id (partner -> p_id)
	 */
	private String parking_search_code; // 위도, 경도를 찾기위해 필요한 임시값 (실제로 db에 들어갈 값x)
 
	private int parking_code;			// INT          NOT NULL AUTO_INCREMENT COMMENT '주차장 코드'
	private String parking_p_id;		// VARCHAR(20)  NULL     		 	COMMENT '제휴 주차장 파트너 아이디'
	private String parking_name;		// VARCHAR(150) NOT NULL 			COMMENT '주차장 이름'
	private String parking_address;		// VARCHAR(200) NOT NULL 		 	COMMENT '주차장 주소 (도로명 기준)'
	private String parking_phone_number;// VARCHAR(15)  NULL DEFAULT 0   	COMMENT '주차장 전화번호 (-포함)'
	
	private double parking_latitude;	// DOUBLE   	NULL DEFAULT 0.0 	COMMENT '주차장 위도'
	private double parking_longitude;	// DOUBLE    	NULL DEFAULT 0.0 	COMMENT '주차장 경도'
	
	private int parking_status;			// TINYINT(2)   NOT NULL DEFAULT 1  COMMENT '주차장 영업 상태 (1:영업 / 2:공사 / 3:폐업)'
	private int parking_operation;		// TINYINT(2)   NOT NULL     		COMMENT '주차장 구분 (1:공영 / 2:민영 / 3:개인)'
	private int parking_type;			// TINYINT(2)   NOT NULL     		COMMENT '주차장 유형 (1:노상 / 2:노외)'
	
	private boolean parking_is_resident; // 주차장 거주자 우선여부 (true&1: 거주자o / false&0: 거주자x)
	private boolean parking_is_manager; // 주차장 관리자여부 (true&1: 관리자o / false&0: 관리자x => *default true)
	private boolean parking_is_machine;	// 주차장 기계식 여부 (true:기계식 / false: 기계식아님 => *default false)
	private boolean parking_is_valet; // 주차장 발렛여부 (true&1: 발렛o / false&0: 발렛x => *default false)
	private boolean parking_is_cctv; // 주차장 cctv여부 (true&1: cctv o / false&0: cctv x => *default false)
	private boolean parking_is_motorcycle; // 주차장 cctv여부 (true&1: cctv o / false&0: cctv x => * default false)
	
	private String parking_impossible_car_type;	// VARCHAR(3) NULL DEFAULT 'ooo' COMMENT '주차장 반입 제한 여부  (대형수입소형 허용될경우 o, 허용안될 경우x)'
	private int parking_pay_type;		// INT          NULL DEFAULT 1 		COMMENT '주차장 결제 방법 (1:모두 / 2:현금 / 3:카드 / 4: 무료)'
	
	private int parking_floor; // 주차장 지상층 존재여부 (0: 존재x, 1 ~ n: 지상 n층까지 존재o => *default 1)
	private int parking_basement_floor; // 주차장 지하층 존재여부 (0: 존재x, -1 ~ -n: 지하 n층까지 존재o => *default 0)
	
	private int parking_capacity; 	// INT          NULL DEFAULT 0 		COMMENT '주차장 전체 좌석(0일 경우 좌석 정보 제공x)'
	private int parking_cur_seat;		// INT          NULL DEFAULT -1 	COMMENT '주차장 현재 주차중인 대수 (-1일 경우 실시간 정보 제공x)'
	
	private int parking_rates;			// INT          NULL DEFAULT 0 		COMMENT '주차장 기본 요금 (원 단위 기준)'
	private int parking_rates_time;		// INT          NULL DEFAULT 0 		COMMENT '주차장 기본 요금 시간 (분 단위 기준)'
	private int parking_add_rates;		// INT          NULL DEFAULT 0 		COMMENT '주차장 추가 요금 (원 단위 기준)'
	private int parking_add_rates_time; // INT          NULL DEFAULT 0 		COMMENT '주차장 추가 요금 시간 (분 단위 기준)'
	private int parking_day_rates;		// INT          NULL DEFAULT 0 		COMMENT '주차장 일 주차권 요금 (원 단위 기준)'
	private int parking_month_rates;	// INT          NULL DEFAULT 0 		COMMENT '주차장 월 정기 요금 (원 단위 기준)'
	 
	private Time parking_weekdays_begin_time; // TIME   NULL DEFAULT 0000 	COMMENT '주차장 평일 운영시간 -> 시작 (시, 분 포함)'
	private Time parking_weekdays_end_time;	  // TIME   NULL DEFAULT 0000 	COMMENT '주차장 평일 운영시간 -> 종료 (시, 분 포함)'
	private Time parking_sat_begin_time;	  // TIME   NULL DEFAULT 0000 	COMMENT '주차장 토요일 운영시간 -> 시작 (시, 분 포함)'
	private Time parking_sat_end_time;		  // TIME   NULL DEFAULT 0000 	COMMENT '주차장 토요일 운영시간 -> 종료 (시, 분 포함)'
	private Time parking_holidays_begin_time; // TIME   NULL DEFAULT 0000 	COMMENT '주차장 공휴일 운영시간 -> 시작 (시, 분 포함)'
	private Time parking_holidays_end_time;	  // TIME   NULL DEFAULT 0000 	COMMENT '주차장 공휴일 운영시간 -> 종료 (시, 분 포함)'
	
	private String parking_register_time; // 주차장 기본 정보의 등록 시간
	private String parking_update_time; // 주차장 기본 정보의 업데이트 시간
	private String parking_register_admin_id; // 주차장을 등록한 관리자 id => *default parking
	private String parking_update_admin_id; // 주차장을 업데이트한 관리자 id
	
	// 생성자
	public Parking() {
	}
	
	// start of Parking Full Constructor
	public Parking(int parking_code, String parking_p_id, String parking_name,
			String parking_address, String parking_phone_number, double parking_latitude, double parking_longitude,
			int parking_status, int parking_operation, int parking_type, boolean parking_is_resident,
			boolean parking_is_manager, boolean parking_is_machine, boolean parking_is_valet, boolean parking_is_cctv,
			boolean parking_is_motorcycle, String parking_impossible_car_type, int parking_pay_type, int parking_floor,
			int parking_basement_floor, int parking_capacity, int parking_cur_seat, int parking_rates,
			int parking_rates_time, int parking_add_rates, int parking_add_rates_time, int parking_day_rates,
			int parking_month_rates, Time parking_weekdays_begin_time, Time parking_weekdays_end_time,
			Time parking_sat_begin_time, Time parking_sat_end_time, Time parking_holidays_begin_time,
			Time parking_holidays_end_time, String parking_register_time, String parking_update_time,
			String parking_register_admin_id, String parking_update_admin_id) {
		super();
		this.parking_code = parking_code;
		this.parking_p_id = parking_p_id;
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_phone_number = parking_phone_number;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_is_resident = parking_is_resident;
		this.parking_is_manager = parking_is_manager;
		this.parking_is_machine = parking_is_machine;
		this.parking_is_valet = parking_is_valet;
		this.parking_is_cctv = parking_is_cctv;
		this.parking_is_motorcycle = parking_is_motorcycle;
		this.parking_impossible_car_type = parking_impossible_car_type;
		this.parking_pay_type = parking_pay_type;
		this.parking_floor = parking_floor;
		this.parking_basement_floor = parking_basement_floor;
		this.parking_capacity = parking_capacity;
		this.parking_cur_seat = parking_cur_seat;
		this.parking_rates = parking_rates;
		this.parking_rates_time = parking_rates_time;
		this.parking_add_rates = parking_add_rates;
		this.parking_add_rates_time = parking_add_rates_time;
		this.parking_day_rates = parking_day_rates;
		this.parking_month_rates = parking_month_rates;
		this.parking_weekdays_begin_time = parking_weekdays_begin_time;
		this.parking_weekdays_end_time = parking_weekdays_end_time;
		this.parking_sat_begin_time = parking_sat_begin_time;
		this.parking_sat_end_time = parking_sat_end_time;
		this.parking_holidays_begin_time = parking_holidays_begin_time;
		this.parking_holidays_end_time = parking_holidays_end_time;
		this.parking_register_time = parking_register_time;
		this.parking_update_time = parking_update_time;
		this.parking_register_admin_id = parking_register_admin_id;
		this.parking_update_admin_id = parking_update_admin_id;
	}
	// end of Parking full Constructor

	// start of Parking Constructor
	public Parking(int parking_code, String parking_p_id, String parking_name,
			String parking_address, String parking_phone_number, double parking_latitude, double parking_longitude,
			int parking_status, int parking_operation, int parking_type, boolean parking_is_resident,
			boolean parking_is_manager, boolean parking_is_machine, boolean parking_is_valet, boolean parking_is_cctv,
			boolean parking_is_motorcycle, String parking_impossible_car_type, int parking_pay_type, int parking_floor,
			int parking_basement_floor, int parking_capacity, int parking_cur_seat, int parking_rates,
			int parking_rates_time, int parking_add_rates, int parking_add_rates_time, int parking_day_rates,
			int parking_month_rates, Time parking_weekdays_begin_time, Time parking_weekdays_end_time,
			Time parking_sat_begin_time, Time parking_sat_end_time, Time parking_holidays_begin_time,
			Time parking_holidays_end_time) {
		super();
		this.parking_code = parking_code;
		this.parking_p_id = parking_p_id;
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_phone_number = parking_phone_number;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_is_resident = parking_is_resident;
		this.parking_is_manager = parking_is_manager;
		this.parking_is_machine = parking_is_machine;
		this.parking_is_valet = parking_is_valet;
		this.parking_is_cctv = parking_is_cctv;
		this.parking_is_motorcycle = parking_is_motorcycle;
		this.parking_impossible_car_type = parking_impossible_car_type;
		this.parking_pay_type = parking_pay_type;
		this.parking_floor = parking_floor;
		this.parking_basement_floor = parking_basement_floor;
		this.parking_capacity = parking_capacity;
		this.parking_cur_seat = parking_cur_seat;
		this.parking_rates = parking_rates;
		this.parking_rates_time = parking_rates_time;
		this.parking_add_rates = parking_add_rates;
		this.parking_add_rates_time = parking_add_rates_time;
		this.parking_day_rates = parking_day_rates;
		this.parking_month_rates = parking_month_rates;
		this.parking_weekdays_begin_time = parking_weekdays_begin_time;
		this.parking_weekdays_end_time = parking_weekdays_end_time;
		this.parking_sat_begin_time = parking_sat_begin_time;
		this.parking_sat_end_time = parking_sat_end_time;
		this.parking_holidays_begin_time = parking_holidays_begin_time;
		this.parking_holidays_end_time = parking_holidays_end_time;
	}
	// end of Parking Constructor

	// start of ParkingConstructor
	public Parking(String parking_name, String parking_address, String parking_phone_number, double parking_latitude,
			double parking_longitude, int parking_status, int parking_operation, int parking_type,
			boolean parking_is_resident, boolean parking_is_manager, boolean parking_is_machine,
			boolean parking_is_valet, boolean parking_is_cctv, boolean parking_is_motorcycle,
			String parking_impossible_car_type, int parking_pay_type, int parking_floor, int parking_basement_floor,
			int parking_capacity, int parking_rates, int parking_rates_time,
			int parking_add_rates, int parking_add_rates_time, int parking_day_rates, int parking_month_rates,
			Time parking_weekdays_begin_time, Time parking_weekdays_end_time, Time parking_sat_begin_time,
			Time parking_sat_end_time, Time parking_holidays_begin_time, Time parking_holidays_end_time) {
		super();
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_phone_number = parking_phone_number;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_is_resident = parking_is_resident;
		this.parking_is_manager = parking_is_manager;
		this.parking_is_machine = parking_is_machine;
		this.parking_is_valet = parking_is_valet;
		this.parking_is_cctv = parking_is_cctv;
		this.parking_is_motorcycle = parking_is_motorcycle;
		this.parking_impossible_car_type = parking_impossible_car_type;
		this.parking_pay_type = parking_pay_type;
		this.parking_floor = parking_floor;
		this.parking_basement_floor = parking_basement_floor;
		this.parking_capacity = parking_capacity;
		this.parking_rates = parking_rates;
		this.parking_rates_time = parking_rates_time;
		this.parking_add_rates = parking_add_rates;
		this.parking_add_rates_time = parking_add_rates_time;
		this.parking_day_rates = parking_day_rates;
		this.parking_month_rates = parking_month_rates;
		this.parking_weekdays_begin_time = parking_weekdays_begin_time;
		this.parking_weekdays_end_time = parking_weekdays_end_time;
		this.parking_sat_begin_time = parking_sat_begin_time;
		this.parking_sat_end_time = parking_sat_end_time;
		this.parking_holidays_begin_time = parking_holidays_begin_time;
		this.parking_holidays_end_time = parking_holidays_end_time;
	}
	// end of Parking Constructor
	
	// getter & setter
	public int getParking_code() {
		return parking_code;
	}
	public void setParking_code(int parking_code) {
		this.parking_code = parking_code;
	}
	
	public String getParking_p_id() {
		return parking_p_id;
	}
	public void setParking_p_id(String parking_p_id) {
		this.parking_p_id = parking_p_id;
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
	
	public String getParking_phone_number() {
		return parking_phone_number;
	}
	public void setParking_phone_number(String parking_phone_number) {
		this.parking_phone_number = parking_phone_number;
	}
	
	public double getParking_latitude() {
		return parking_latitude;
	}
	public void setParking_latitude(double parking_latitude) {
		this.parking_latitude = parking_latitude;
	}

	public double getParking_longitude() {
		return parking_longitude;
	}
	public void setParking_longitude(double parking_longitude) {
		this.parking_longitude = parking_longitude;
	}
	
	public int getParking_status() {
		return parking_status;
	}
	public void setParking_status(int parking_status) {
		this.parking_status = parking_status;
	}
	
	public int getParking_operation() {
		return parking_operation;
	}
	public void setParking_operation(int parking_operation) {
		this.parking_operation = parking_operation;
	}

	public int getParking_type() {
		return parking_type;
	}
	public void setParking_type(int parking_type) {
		this.parking_type = parking_type;
	}
	
	public boolean isParking_is_resident() {
		return parking_is_resident;
	}

	public void setParking_is_resident(boolean parking_is_resident) {
		this.parking_is_resident = parking_is_resident;
	}

	public boolean isParking_is_manager() {
		return parking_is_manager;
	}

	public void setParking_is_manager(boolean parking_is_manager) {
		this.parking_is_manager = parking_is_manager;
	}

	public boolean isParking_is_valet() {
		return parking_is_valet;
	}

	public void setParking_is_valet(boolean parking_is_valet) {
		this.parking_is_valet = parking_is_valet;
	}

	public boolean isParking_is_cctv() {
		return parking_is_cctv;
	}

	public void setParking_is_cctv(boolean parking_is_cctv) {
		this.parking_is_cctv = parking_is_cctv;
	}

	public boolean isParking_is_motorcycle() {
		return parking_is_motorcycle;
	}

	public void setParking_is_motorcycle(boolean parking_is_motorcycle) {
		this.parking_is_motorcycle = parking_is_motorcycle;
	}

	public boolean isParking_is_machine() {
		return parking_is_machine;
	}
	public void setParking_is_machine(boolean parking_is_machine) {
		this.parking_is_machine = parking_is_machine;
	}

	public String getParking_impossible_car_type() {
		return parking_impossible_car_type;
	}
	public void setParking_impossible_car_type(String parking_impossible_car_type) {
		this.parking_impossible_car_type = parking_impossible_car_type;
	}

	public int getParking_pay_type() {
		return parking_pay_type;
	}
	public void setParking_pay_type(int parking_pay_type) {
		this.parking_pay_type = parking_pay_type;
	}
	
	public int getParking_floor() {
		return parking_floor;
	}

	public void setParking_floor(int parking_floor) {
		this.parking_floor = parking_floor;
	}

	public int getParking_basement_floor() {
		return parking_basement_floor;
	}

	public void setParking_basement_floor(int parking_basement_floor) {
		this.parking_basement_floor = parking_basement_floor;
	}

	public int getParking_capacity() {
		return parking_capacity;
	}
	public void setParking_capacity(int parking_capacity) {
		this.parking_capacity = parking_capacity;
	}

	public int getParking_cur_seat() {
		return parking_cur_seat;
	}
	public void setParking_cur_seat(int parking_cur_seat) {
		this.parking_cur_seat = parking_cur_seat;
	}

	public int getParking_rates() {
		return parking_rates;
	}
	public void setParking_rates(int parking_rates) {
		this.parking_rates = parking_rates;
	}

	public int getParking_rates_time() {
		return parking_rates_time;
	}
	public void setParking_rates_time(int parking_rates_time) {
		this.parking_rates_time = parking_rates_time;
	}

	public int getParking_add_rates() {
		return parking_add_rates;
	}
	public void setParking_add_rates(int parking_add_rates) {
		this.parking_add_rates = parking_add_rates;
	}

	public int getParking_add_rates_time() {
		return parking_add_rates_time;
	}
	public void setParking_add_rates_time(int parking_add_rates_time) {
		this.parking_add_rates_time = parking_add_rates_time;
	}

	public int getParking_day_rates() {
		return parking_day_rates;
	}
	public void setParking_day_rates(int parking_day_rates) {
		this.parking_day_rates = parking_day_rates;
	}

	public int getParking_month_rates() {
		return parking_month_rates;
	}
	public void setParking_month_rates(int parking_month_rates) {
		this.parking_month_rates = parking_month_rates;
	}

	
	public Time getParking_weekdays_begin_time() {
		return parking_weekdays_begin_time;
	}
	public void setParking_weekdays_begin_time(Time parking_weekdays_begin_time) {
		this.parking_weekdays_begin_time = parking_weekdays_begin_time;
	}

	public Time getParking_weekdays_end_time() {
		return parking_weekdays_end_time;
	}
	public void setParking_weekdays_end_time(Time parking_weekdays_end_time) {
		this.parking_weekdays_end_time = parking_weekdays_end_time;
	}

	
	public Time getParking_sat_begin_time() {
		return parking_sat_begin_time;
	}
	public void setParking_sat_begin_time(Time parking_sat_begin_time) {
		this.parking_sat_begin_time = parking_sat_begin_time;
	}

	public Time getParking_sat_end_time() {
		return parking_sat_end_time;
	}
	public void setParking_sat_end_time(Time parking_sat_end_time) {
		this.parking_sat_end_time = parking_sat_end_time;
	}

	public Time getParking_holidays_begin_time() {
		return parking_holidays_begin_time;
	}
	public void setParking_holidays_begin_time(Time parking_holidays_begin_time) {
		this.parking_holidays_begin_time = parking_holidays_begin_time;
	}

	public Time getParking_holidays_end_time() {
		return parking_holidays_end_time;
	}
	public void setParking_holidays_end_time(Time parking_holidays_end_time) {
		this.parking_holidays_end_time = parking_holidays_end_time;
	}

	public String getParking_search_code() {
		return parking_search_code;
	}

	public void setParking_search_code(String parking_search_code) {
		this.parking_search_code = parking_search_code;
	}
	public String getParking_register_time() {
		return parking_register_time;
	}

	public void setParking_register_time(String parking_register_time) {
		this.parking_register_time = parking_register_time;
	}

	public String getParking_update_time() {
		return parking_update_time;
	}

	public void setParking_update_time(String parking_update_time) {
		this.parking_update_time = parking_update_time;
	}

	public String getParking_register_admin_id() {
		return parking_register_admin_id;
	}

	public void setParking_register_admin_id(String parking_register_admin_id) {
		this.parking_register_admin_id = parking_register_admin_id;
	}

	public String getParking_update_admin_id() {
		return parking_update_admin_id;
	}

	public void setParking_update_admin_id(String parking_update_admin_id) {
		this.parking_update_admin_id = parking_update_admin_id;
	}
	
	public void toParking(JSONObject j) {
		try {
			SimpleDateFormat sdf;
			
			//setParking_code((j.get("parking_p_id") != null) ? (int)j.get("parking_p") : 0);
			setParking_p_id(j.get("parking_p_id").toString());
			setParking_name(j.get("parking_name").toString());
			setParking_address(j.get("parking_address").toString());
			setParking_phone_number(j.get("parking_phone_number").toString());
			setParking_latitude(Double.parseDouble(j.get("parking_latitude").toString()));
			setParking_longitude(Double.parseDouble(j.get("parking_longitude").toString()));
			setParking_operation(Integer.parseInt(j.get("parking_operation").toString()));
			setParking_type(Integer.parseInt(j.get("parking_type").toString()));
			setParking_is_resident(Boolean.parseBoolean(j.get("parking_is_resident").toString()));
			setParking_is_manager(Boolean.parseBoolean(j.get("parking_is_manager").toString()));
			setParking_is_machine(Boolean.parseBoolean(j.get("parking_is_machine").toString()));
			setParking_is_valet(Boolean.parseBoolean(j.get("parking_is_valet").toString()));
			setParking_is_cctv(Boolean.parseBoolean(j.get("parking_is_cctv").toString()));
			setParking_is_motorcycle(Boolean.parseBoolean(j.get("parking_is_motorcycle").toString()));
			setParking_impossible_car_type(j.get("parking_impossible_car_type").toString());
			setParking_pay_type(Integer.parseInt(j.get("parking_pay_type").toString()));
			setParking_floor(Integer.parseInt(j.get("parking_floor").toString()));
			setParking_basement_floor(Integer.parseInt(j.get("parking_basement_floor").toString()));
			setParking_capacity(Integer.parseInt(j.get("parking_capacity").toString()));
			setParking_rates(Integer.parseInt(j.get("parking_rates").toString()));
			setParking_rates_time(Integer.parseInt(j.get("parking_rates_time").toString()));
			setParking_add_rates(Integer.parseInt(j.get("parking_add_rates").toString()));
			setParking_add_rates_time(Integer.parseInt(j.get("parking_add_rates_time").toString()));
			setParking_day_rates(Integer.parseInt(j.get("parking_day_rates").toString()));
			setParking_month_rates(Integer.parseInt(j.get("parking_month_rates").toString()));
			sdf = new SimpleDateFormat("HH:MM");
			setParking_weekdays_begin_time(new Time(sdf.parse(j.get("parking_weekdays_begin_time").toString()).getTime()));
			setParking_weekdays_end_time(new Time(sdf.parse(j.get("parking_weekdays_end_time").toString()).getTime()));
			setParking_sat_begin_time(new Time(sdf.parse(j.get("parking_sat_begin_time").toString()).getTime()));
			setParking_sat_end_time(new Time(sdf.parse(j.get("parking_sat_end_time").toString()).getTime()));
			setParking_holidays_begin_time(new Time(sdf.parse(j.get("parking_holidays_begin_time").toString()).getTime()));
			setParking_holidays_end_time(new Time(sdf.parse(j.get("parking_holidays_end_time").toString()).getTime()));
			//sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			//setParking_register_time(new Time(sdf.parse(j.getString("parking_register_time")).getTime()));
			//setParking_update_time(j.get("parking_update_time").toString());
			//setParking_register_admin_id(j.getString("parking_register_admin_id"));
			//setParking_update_admin_id(j.getString("parking_update_admin_id"));

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Parking [parking_p_id=" + parking_p_id + ", parking_name=" + parking_name + ", parking_address="
				+ parking_address + ", parking_phone_number=" + parking_phone_number + ", parking_latitude="
				+ parking_latitude + ", parking_longitude=" + parking_longitude + ", parking_operation="
				+ parking_operation + ", parking_type=" + parking_type + ", parking_is_resident=" + parking_is_resident
				+ ", parking_is_manager=" + parking_is_manager + ", parking_is_machine=" + parking_is_machine
				+ ", parking_is_valet=" + parking_is_valet + ", parking_is_cctv=" + parking_is_cctv
				+ ", parking_is_motorcycle=" + parking_is_motorcycle + ", parking_impossible_car_type="
				+ parking_impossible_car_type + ", parking_pay_type=" + parking_pay_type + ", parking_floor="
				+ parking_floor + ", parking_basement_floor=" + parking_basement_floor + ", parking_capacity="
				+ parking_capacity + ", parking_rates=" + parking_rates + ", parking_rates_time=" + parking_rates_time
				+ ", parking_add_rates=" + parking_add_rates + ", parking_add_rates_time=" + parking_add_rates_time
				+ ", parking_day_rates=" + parking_day_rates + ", parking_month_rates=" + parking_month_rates
				+ ", parking_weekdays_begin_time=" + parking_weekdays_begin_time + ", parking_weekdays_end_time="
				+ parking_weekdays_end_time + ", parking_sat_begin_time=" + parking_sat_begin_time
				+ ", parking_sat_end_time=" + parking_sat_end_time + ", parking_holidays_begin_time="
				+ parking_holidays_begin_time + ", parking_holidays_end_time=" + parking_holidays_end_time + "]";
	}

	
}