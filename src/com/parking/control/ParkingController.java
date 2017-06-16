package com.parking.control;

import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	ParkingDAOMySQL dao;
	
	/*@RequestMapping(value = "/insert.do", method=RequestMethod.POST)
	public String httpPost(Model model){
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
			
			dao.insert(public_parking);
			
			model.addAttribute("result", public_parking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/test.jsp";
	}*/
	
	@RequestMapping(value = "/selectAll.do", method=RequestMethod.POST)
	public String httpPost(Model model){
		List<Parking> list = dao.selectAll();
		model.addAttribute("result", list);
		
		return "/test.jsp";
	}
	
}
