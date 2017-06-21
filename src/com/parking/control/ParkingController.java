package com.parking.control;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import org.apache.ibatis.annotations.Param;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dao.ParkingDAOMySQL;
import com.parking.validate.Public_parking_RestTemplateServiceImpl;
import com.parking.validate.Public_parking_coordinate_RestTemplateServiceImpl;
import com.parking.vo.Parking;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Context;

@Controller
public class ParkingController {
	private final Logger log = (Logger) LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ParkingDAOMySQL pdao;
	
	@RequestMapping(value = "/insert.do")
	public String http(Model model){
		Public_parking_RestTemplateServiceImpl rest = new Public_parking_RestTemplateServiceImpl();
		Public_parking_coordinate_RestTemplateServiceImpl coordRest = new Public_parking_coordinate_RestTemplateServiceImpl();
		
		try {
			List<Parking> public_parking = rest.getAddrList();
			List<Parking> public_parking_coord = coordRest.getAddrList();
			
			for(int i=0; i<public_parking.size(); i++){
				for(int j=0; j<public_parking_coord.size(); j++){
					if(public_parking.get(i).getParking_search_code().equals(public_parking_coord.get(j).getParking_search_code())){
						public_parking.get(i).setParking_latitude(public_parking_coord.get(j).getParking_latitude());
						public_parking.get(i).setParking_longitude(public_parking_coord.get(j).getParking_longitude());
						break;
					}
				}
			}
			
			pdao.insert(public_parking);
			
			model.addAttribute("result", public_parking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/test.jsp";
	}
	
	@RequestMapping(value = "/selectAll.do")
	public String httpPost(Model model){
		List<Parking> list = pdao.selectAll();
		model.addAttribute("result", list);
		return "/test.jsp";
	}
		
	//selectByLocation 시작
	//메인페이지에서 검색시 지역명으로 주차장 불러옴
	@RequestMapping(value = "/selectByLocation.do")
	public String selectByLocation(String location, /*Date reserveEntranceTime, Date reserveExitTime,*/ Model model) {
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String in = sdf.format(reserveEntranceTime);
		String out = sdf.format(reserveExitTime);
		System.out.println("location :"+location+"reserveEntranceTime :"+in+"reserveExitTime :"+out);*/
		
		//디폴트 지정 param으로 수정예정
		if("".equals(location)){
			location = "강남";
		}
		
		List<Parking> list = pdao.selectByLocation(location);
		model.addAttribute("list", list);
		return "/parkingList.jsp";
	}
	//selectByLocation 끝
	
	
	// start of androidSelectAll
	// 안드로이드에서 DB를 요청하면 오는 메소드
	@RequestMapping(value = "/androidSelectAll.do")
	@ResponseBody
	public JSONObject androidSelectAll() {
		System.out.println("androidSelectAll()");
		List<Parking> list = pdao.selectAll();
		JSONArray jArray = new JSONArray();
		for(int i = 0; i < list.size(); i++) {
			Parking p = list.get(i);
			JSONObject j = new JSONObject();
			j.put("parking_code", p.getParking_code());
			j.put("parking_p_id", p.getParking_p_id());
			j.put("parking_name", p.getParking_name());
			j.put("parking_phone_number", p.getParking_phone_number());
			j.put("parking_latitude", p.getParking_latitude());
			j.put("parking_longitude", p.getParking_longitude());
			j.put("parking_status", p.getParking_status());
			j.put("parking_operation", p.getParking_operation());
			j.put("parking_type", p.getParking_type());
			j.put("parking_is_mechan", p.isParking_is_mechan());
			j.put("parking_pay_type", p.getParking_pay_type());
			j.put("parking_capacity", p.getParking_capacity());
			j.put("parking_cur_seat", p.getParking_cur_seat());
			
			if(p.getParking_rates_time() / 60 >= 1) {
				p.setParking_rates(p.getParking_rates() * p.getParking_rates_time() / 60);
				//System.out.println("기본 요금 : " + p.getParking_rates());
			}
			else {
				if(p.getParking_rates_time() != 0)
					p.setParking_rates(p.getParking_rates() * 60 / p.getParking_rates_time());
				//System.out.println("기본 요금 : " + p.getParking_rates());
			}
			j.put("parking_rates", p.getParking_rates());
			j.put("parking_rates_time", p.getParking_rates_time());
			
			j.put("parking_add_rates", p.getParking_add_rates());
			j.put("parking_add_rates_time", p.getParking_add_rates_time());
			
			j.put("parking_day_rates", p.getParking_day_rates());
			j.put("parking_month_rates", p.getParking_month_rates());
			j.put("parking_weekdays_begin_time", p.getParking_weekdays_begin_time());
			j.put("parking_weekdays_end_time", p.getParking_weekdays_end_time());
			j.put("parking_sat_begin_time", p.getParking_sat_begin_time());
			j.put("parking_sat_end_time", p.getParking_sat_end_time());
			j.put("parking_holidays_begin_time", p.getParking_holidays_begin_time());
			j.put("parking_holidays_end_time", p.getParking_holidays_end_time());
			jArray.add(i, j);
			//System.out.println("기본 요금 시간 : " + p.getParking_rates_time());
		}		
		
		JSONObject json = new JSONObject();
		json.put("list", jArray);
		return json;
	} // end of androidSelectAll
}
