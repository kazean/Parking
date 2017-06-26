 package com.parking.control;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/")
public class ParkingController {
	
	// --건들지 마시오--
	@Autowired
	ParkingDAOMySQL pDao;

	//private final Logger log = (Logger) LoggerFactory.getLogger(ParkingController.class);

	/**
	 * @author yeahni
	 * @param model 임시 결과
	 * @return String url
	 */
	@RequestMapping(value = "/insert.do")
	public String add(Model model){
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
			
			pDao.insert(public_parking);
			
			model.addAttribute("result", public_parking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/test.jsp";
	}
	
	/**
	 * @author yeahni
	 * @comment 전체 주차장 리스트 반환
	 * @param model 결과 값 담는 공간
	 * @return String url
	 */
	@RequestMapping(value = "/selectAll.do")
	public String readList(Model model){
		List<Parking> list = pDao.selectAll();
		model.addAttribute("result", list);
		
		return "/test.jsp";
	}
	
	/**
	 * @author hawstrike
	 * @comment 메인페이지에서 검색시 지역명으로 주차장 불러옴
	 * @param location
	 * @param model
	 * @return String url
	 */
	//selectByLocation 시작
	@RequestMapping(value = "/selectByLocation.do")
	public String selectByLocation(String location, Date reserveEntranceTime, Date reserveExitTime, Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		String in = sdf.format(reserveEntranceTime);
		String out = sdf.format(reserveExitTime);
		System.out.println("location :"+location+"reserveEntranceTime :"+in+"reserveExitTime :"+out);
		
		//디폴트 지정 param으로 수정예정
		if("".equals(location)){
			location = "강남";
		}
		
		List<Parking> list = pDao.selectByLocation(location);
		model.addAttribute("list", list);
		return "/parkingList.jsp";
	}
	//selectByLocation 끝
	
	/**
	 * @author hawstrike
	 * @comment 안드로이드에서 DB를 요청하면 오는 메소드
	 * @return JSONObject
	 */
	// start of androidSelectAll
	
	//@RequestMapping(value ="androidSelectAll", headers="Accept=application/json")
	@GetMapping("aselectall")
	@ResponseBody
	public List<Parking> androidSelectAll() {
		System.out.println("androidSelectAll()");
		List<Parking> list = pDao.selectAll();
		List<Parking> plist = new LinkedList<Parking>();
		
		for(Parking p : list) {
			if(p.getParking_rates_time() / 60 >= 1)
				p.setParking_rates(p.getParking_rates() * p.getParking_rates_time() / 60);
			else
				if(p.getParking_rates_time() != 0)
					p.setParking_rates(p.getParking_rates() * 60 / p.getParking_rates_time());
			plist.add(p);
		}
		
		return plist;
	} // end of androidSelectAll
	
}
