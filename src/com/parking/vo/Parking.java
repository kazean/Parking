<<<<<<< HEAD
package com.parking.vo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Parking implements Serializable{
	// 필드 = 테이블의 '칼럼'
	/* [알림] default 써져있는 필드는 실제로 db에서 default 설정이 되어있습니다.
	1-(1). default 값 = '0' or '-1'인 경우 -> API에서 '해당 정보를 제공하지 않는다.' 는 뜻입니다.
	1-(2). default 값 = 'null'인 경우	   -> '해당 정보가 제휴되지 않은 주차장이다.'는 뜻입니다.
	
	2. 요일의 end_time = '0000'일 경우     -> '해당 요일에는 운영하지 않는다'.	     는 뜻입니다. */
	
	
	// 위도, 경도를 찾기위해 필요한 임시값 (실제로 db에 들어갈 값x)
	private String parking_search_code; 
	
	private String parking_p_id;		// 제휴 주차장 파트너 아이디 (=> *default null)
	private String parking_name;		// 주차장 이름
	private String parking_address;		// 주차장 주소 (도로명 기준)
	private String parking_phone_number;// 주차장 전화번호 (-제외 => *default 0)
	
	private double parking_latitude;	// 주차장 위도 (=> *default 0.0)
	private double parking_longitude;	// 주차장 경도 (=> *default 0.0)
	
	private int parking_status;			// 주차장 영업 상태 (1:영업 / 2:공사 / 3:폐업 => *default 1)
	private int parking_operation;		// 주차장 구분 (1:공영 / 2:민영 / 3:개인 => 전국 데이터는 2, 서울시 데이터는 1)
	private int parking_type;			// 주차장 유형 (1:노상 / 2:노외)
	private boolean parking_is_mechan;	// 주차장 기계식 여부 (true:기계식 / false: 기계식아님 => *default false)
	private String parking_impossible_car_type;	// 주차장 반입 제한 여부  (대형수입소형 허용될경우 o, 허용안될 경우x => *default 'ooo')
	private int parking_pay_type;		// 주차장 결제 방법 (1:모두 / 2:현금 / 3:카드 / 4: 무료 => *default 1)
	
	private String parking_capacity; 	// 주차장 전체 좌석 (=> *default 0 = 0일 경우 좌석 정보 제공x) 
	private int parking_cur_seat;		// 주차장 현재 주차중인 대수 (=> *default -1 = -1일 경우 실시간 정보 제공x)
	
	private int parking_rates;			// 주차장 기본 요금 (원 단위 기준 => *default 0)
	private int parking_rates_time;		// 주차장 기본 요금 시간 (분 단위 기준 => *default 0)
	private int parking_add_rates;		// 주차장 추가 요금 (원 단위 기준 => *default 0)
	private int parking_add_rates_time; // 주차장 추가 요금 시간 (분 단위 기준 => *default 0)
	private int parking_day_rates;		// 주차장 일 주차권 요금 (원 단위 기준 => *default 0)
	private int parking_month_rates;	// 주차장 월 정기 요금 (원 단위 기준 => *default 0)
	 
	private Time parking_weekdays_begin_time; // 주차장 평일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_weekdays_end_time;	  // 주차장 평일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	private Time parking_sat_begin_time;	  // 주차장 토요일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_sat_end_time;		  // 주차장 토요일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	private Time parking_holidays_begin_time; // 주차장 공휴일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_holidays_end_time;	  // 주차장 공휴일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	
	// 생성자
	public Parking() {
		super();
	}
	
	public Parking(String parking_name, String parking_address, double parking_latitude, double parking_longitude,
			int parking_status, int parking_operation, int parking_type, String parking_impossible_car_type) {
		super();
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_impossible_car_type = parking_impossible_car_type;
	}

	public Parking(String parking_p_id, 
			String parking_name, String parking_address, String parking_phone_number,
			double parking_latitude, double parking_longitude, 
			int parking_status, int parking_operation, int parking_type, 
			boolean parking_is_mechan, String parking_impossible_car_type, int parking_pay_type,
			String parking_capacity, int parking_cur_seat, 
			int parking_rates, int parking_rates_time,
			int parking_add_rates, int parking_add_rates_time, 
			int parking_day_rates, int parking_month_rates,
			Time parking_weekdays_begin_time, Time parking_weekdays_end_time,
			Time parking_sat_begin_time, Time parking_sat_end_time, 
			Time parking_holidays_begin_time, Time parking_holidays_end_time) {
		super();
		this.parking_p_id = parking_p_id;
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_phone_number = parking_phone_number;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_is_mechan = parking_is_mechan;
		this.parking_impossible_car_type = parking_impossible_car_type;
		this.parking_pay_type = parking_pay_type;
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

	// getter & setter
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

	public boolean isParking_is_mechan() {
		return parking_is_mechan;
	}
	public void setParking_is_mechan(boolean parking_is_mechan) {
		this.parking_is_mechan = parking_is_mechan;
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

	public String getParking_capacity() {
		return parking_capacity;
	}
	public void setParking_capacity(String parking_capacity) {
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

	// override
	@Override
	public String toString() {
		return "Parking [parking_search_code=" + parking_search_code + ", "
				+ "parking_p_id=" + parking_p_id
				+ ", parking_name=" + parking_name 
				+ ", parking_address=" + parking_address
				+ ", parking_latitude=" + parking_latitude 
				+ ", parking_longitude=" + parking_longitude 
				+ ", parking_operation=" + parking_operation
				+ ", parking_capacity=" + parking_capacity 
				+ ", parking_cur_seat=" + parking_cur_seat
				+ ", parking_weekdays_begin_time=" + parking_weekdays_begin_time 
				+ ", parking_weekdays_end_time=" + parking_weekdays_end_time + "]";
	}

}
=======
package com.parking.vo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Parking implements Serializable{
	
	// 필드 = 테이블의 '칼럼'
	/* [알림] default 써져있는 필드는 실제로 db에서 default 설정이 되어있습니다.
	1-(1). default 값 = '0' or '-1'인 경우 -> API에서 '해당 정보를 제공하지 않는다.' 는 뜻입니다.
	1-(2). default 값 = 'null'인 경우	   -> '해당 정보가 제휴되지 않은 주차장이다.'는 뜻입니다.
	
	2. 요일의 end_time = '0000'일 경우     -> '해당 요일에는 운영하지 않는다'.	     는 뜻입니다. */
	
	
	// 위도, 경도를 찾기위해 필요한 임시값 (실제로 db에 들어갈 값x)
	private String parking_search_code; 
	//
	private int parking_code;		// 주차장 전용 코드
	private String parking_p_id;		// 제휴 주차장 파트너 아이디 (=> *default null)
	private String parking_name;		// 주차장 이름
	private String parking_address;		// 주차장 주소 (도로명 기준)
	private String parking_phone_number;// 주차장 전화번호 (-제외 => *default 0)
	
	private double parking_latitude;	// 주차장 위도 (=> *default 0.0)
	private double parking_longitude;	// 주차장 경도 (=> *default 0.0)
	
	private int parking_status;			// 주차장 영업 상태 (1:영업 / 2:공사 / 3:폐업 => *default 1)
	private int parking_operation;		// 주차장 구분 (1:공영 / 2:민영 / 3:개인 => 전국 데이터는 2, 서울시 데이터는 1)
	private int parking_type;			// 주차장 유형 (1:노상 / 2:노외)
	private boolean parking_is_mechan;	// 주차장 기계식 여부 (true:기계식 / false: 기계식아님 => *default false)
	private String parking_impossible_car_type;	// 주차장 반입 제한 여부  (대형수입소형 허용될경우 o, 허용안될 경우x => *default 'ooo')
	private int parking_pay_type;		// 주차장 결제 방법 (1:모두 / 2:현금 / 3:카드 / 4: 무료 => *default 1)
	
	private String parking_capacity; 	// 주차장 전체 좌석 (=> *default 0 = 0일 경우 좌석 정보 제공x) 
	private int parking_cur_seat;		// 주차장 현재 주차중인 대수 (=> *default -1 = -1일 경우 실시간 정보 제공x)
	
	private int parking_rates;			// 주차장 기본 요금 (원 단위 기준 => *default 0)
	private int parking_rates_time;		// 주차장 기본 요금 시간 (분 단위 기준 => *default 0)
	private int parking_add_rates;		// 주차장 추가 요금 (원 단위 기준 => *default 0)
	private int parking_add_rates_time; // 주차장 추가 요금 시간 (분 단위 기준 => *default 0)
	private int parking_day_rates;		// 주차장 일 주차권 요금 (원 단위 기준 => *default 0)
	private int parking_month_rates;	// 주차장 월 정기 요금 (원 단위 기준 => *default 0)
	 
	private Time parking_weekdays_begin_time; // 주차장 평일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_weekdays_end_time;	  // 주차장 평일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	private Time parking_sat_begin_time;	  // 주차장 토요일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_sat_end_time;		  // 주차장 토요일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	private Time parking_holidays_begin_time; // 주차장 공휴일 운영시간 -> 시작 (시, 분 포함 => *default 0000)
	private Time parking_holidays_end_time;	  // 주차장 공휴일 운영시간 -> 종료 (시, 분 포함 => *default 0000)
	
	// 생성자
	public Parking() {
		super();
	}
	
	public Parking(int parking_code, String parking_name, String parking_address, double parking_latitude, double parking_longitude,
			int parking_status, int parking_operation, int parking_type, String parking_impossible_car_type) {
		super();
		this.parking_code = parking_code;
		this.parking_name = parking_name;
		this.parking_address = parking_address;
		this.parking_latitude = parking_latitude;
		this.parking_longitude = parking_longitude;
		this.parking_status = parking_status;
		this.parking_operation = parking_operation;
		this.parking_type = parking_type;
		this.parking_impossible_car_type = parking_impossible_car_type;
	}

	public Parking(int parking_code, String parking_p_id, 
			String parking_name, String parking_address, String parking_phone_number,
			double parking_latitude, double parking_longitude, 
			int parking_status, int parking_operation, int parking_type, 
			boolean parking_is_mechan, String parking_impossible_car_type, int parking_pay_type,
			String parking_capacity, int parking_cur_seat, 
			int parking_rates, int parking_rates_time,
			int parking_add_rates, int parking_add_rates_time, 
			int parking_day_rates, int parking_month_rates,
			Time parking_weekdays_begin_time, Time parking_weekdays_end_time,
			Time parking_sat_begin_time, Time parking_sat_end_time, 
			Time parking_holidays_begin_time, Time parking_holidays_end_time) {
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
		this.parking_is_mechan = parking_is_mechan;
		this.parking_impossible_car_type = parking_impossible_car_type;
		this.parking_pay_type = parking_pay_type;
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
	
	// getter and setter
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

	public boolean isParking_is_mechan() {
		return parking_is_mechan;
	}
	public void setParking_is_mechan(boolean parking_is_mechan) {
		this.parking_is_mechan = parking_is_mechan;
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

	public String getParking_capacity() {
		return parking_capacity;
	}
	public void setParking_capacity(String parking_capacity) {
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

	// override
	@Override
	public String toString() {
		return "Parking [parking_search_code=" + parking_search_code + ", "
				+ "parking_p_id=" + parking_p_id
				+ ", parking_name=" + parking_name 
				+ ", parking_address=" + parking_address
				+ ", parking_latitude=" + parking_latitude 
				+ ", parking_longitude=" + parking_longitude 
				+ ", parking_operation=" + parking_operation
				+ ", parking_capacity=" + parking_capacity 
				+ ", parking_cur_seat=" + parking_cur_seat
				+ ", parking_weekdays_begin_time=" + parking_weekdays_begin_time 
				+ ", parking_weekdays_end_time=" + parking_weekdays_end_time + "]";
	}

}
>>>>>>> fb9098ff34e4d400290dd99a75d68db51a2cd4b6
