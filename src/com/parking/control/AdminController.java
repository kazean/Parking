package com.parking.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parking.service.AdminService;
import com.parking.service.ParkingService;
import com.parking.vo.Admin;
import com.parking.vo.Parking;

@Controller
@RequestMapping(value="/admin/")
public class AdminController {
	
	// --건들지 마시오--
	@Autowired
	AdminService aService;
	
	@Autowired
	ParkingService pService;
	
	/**
	 * @author yeahni
	 * @comment 로그인 정보 체크
	 * @param admin 로그인 정보
	 * @param session 로그인 확인 여부
	 * @return String url 주소
	 */
	@PostMapping("admin.do")
	public String check(@RequestBody Admin admin, HttpSession session){
		if(admin != null){
			Admin responseAdmin = aService.login(admin);

			if(responseAdmin != null){
				System.out.println(responseAdmin);
				if(responseAdmin.getA_id().equals(admin.getA_id()) && responseAdmin.getA_password().equals(admin.getA_password())){
					session.setAttribute("responseAdmin", responseAdmin);
					return "/admin/main.jsp";
				}	
			} 
		}
		return "/admin/default.jsp";
	}
	
	/**
	 * @author yeahni
	 * @comment 전체 주차장 리스트
	 * @param session 로그인 확인 여부
	 * @param model 전체 주차장 리스트
	 * @return String url 주소
	 */
	@GetMapping("parkingList")
	public String parkingList(HttpSession session, Model model){		
		Admin admin = (Admin)session.getAttribute("responseAdmin");
		
		if(admin != null){
			List<Parking> parkingList = (List<Parking>) pService.readParkingList();	
			model.addAttribute(parkingList);
			
			return "/admin/parkingList.jsp";
		}
		
		return "/admin/default.jsp";
	}
	
	/**
	 * @author yeahni
	 * @comment 해당 주차장 코드에 알맞는 정보
	 * @param parking_code 해당 주차장 코드
	 * @param session 로그인 확인 여부
	 * @param model 해당 주차장 정보
	 * @return
	 */
	@GetMapping("parkingDetail")
	public String parkingDetail(@RequestParam(required = false, defaultValue = "0")int parking_code ,HttpSession session, Model model){
		Admin admin = (Admin)session.getAttribute("responseAdmin");
		
		if(admin != null){
			if(parking_code != 0){
				Parking parking = pService.readParkingDetail(parking_code);
				model.addAttribute(parking);
				
				return "/admin/parkingDetail.jsp";
			}
		}
		
		return "/admin/default.jsp";
	}	
	
	@GetMapping("parkingEdit")
	public String parkingEdit(@RequestParam(required = false, defaultValue = "0")int parking_code, HttpSession session, Model model){
		Admin admin = (Admin)session.getAttribute("responseAdmin");
		
		if(admin != null){
			if(parking_code != 0){
				Parking parking = pService.readParkingDetail(parking_code);
				
				if(parking != null){
					model.addAttribute(parking);
				}
				
				return "/admin/parkingEdit.jsp";
			}
		}
		
		return "/admin/default.jsp";
	}
	
}
