package com.parking.control;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parking.service.AdminService;
import com.parking.service.CustomerService;
import com.parking.service.ParkingService;
import com.parking.vo.Admin;
import com.parking.vo.Customer;
import com.parking.vo.Parking;

@Controller
@RequestMapping(value="/admin/")
public class AdminController {
	
	// --건들지 마시오--
	@Autowired
	HttpSession session;
	@Autowired
	AdminService aService;
	@Autowired
	ParkingService pService;
	@Autowired
	CustomerService cService;
	
	/**
	 * @author yeahni
	 * @comment 로그인 정보 체크
	 * @param admin 로그인 정보
	 * @param session 로그인 확인 여부
	 * @return String url 주소
	 */
	@PostMapping("admin.do")
	public String adminLogin(String id, String pwd, Model model){
		System.out.println("adminLogin()");
		
		if(id != null && pwd != null){
			Admin admin = aService.login(new Admin(id, pwd));

			if(admin != null){
				if(admin.getA_id().equals(id) && admin.getA_password().equals(pwd)){
					session.setAttribute("admin", admin);
					model.addAttribute("msg", "1");
				}
				else {
					model.addAttribute("msg", "-1");
				}
			} 
		}
		return "/result.jsp";
	}
	
	/**
	 * @author yeahni
	 * @comment 전체 주차장 리스트
	 * @param session 로그인 확인 여부
	 * @param model 전체 주차장 리스트
	 * @return String url 주소
	 */
	// start of parkingList
	@RequestMapping("parkingList.do")
	public String parkingList(String num, String sortValue, Model model){
		System.out.println("parkingList()");
		
		Admin admin = (Admin)session.getAttribute("admin");
		String[] options = {"parking_code", "parking_name", "parking_address"};
		String sort;
		int sortFlag = 0;
		
		if(sortValue != null)
			sortFlag = Integer.parseInt(sortValue);
		sort = options[sortFlag];
		
		if(admin != null) {
			List<Parking> pAll = pService.selectAll(sort);
			
			model = pPaging(pAll, num, model);
			model.addAttribute("pAllSize", pAll.size());
			model.addAttribute("sortValue", sortFlag);
			model.addAttribute("flag", 0);
			return "/admin/parkingList.jsp";
		}
		return "/admin/admin.jsp";
	}
	// end of parkingList
	
	// start of parkingSearch
	@PostMapping("parkingSearch.do")
	public String parkingSearch(String searchValue, int option, String num, Model model) {
		System.out.println("parkingSearch()");
		
		Admin admin = (Admin)session.getAttribute("admin");
		String[] options = {"parking_code", "parking_name"};
		
		if (admin != null) {
				String searchItem = options[option];
				List<Parking> pAll = pService.pSearch(searchItem, searchValue);
				
				model = pPaging(pAll, num, model);
				model.addAttribute("pAllSizeSearch", pAll.size());
				model.addAttribute("searchValue", searchValue);
				model.addAttribute("option", option);
				model.addAttribute("flag", 1);
				
				return "/admin/parkingList.jsp";
		}
		
		return "/admin/admin.jsp";
	}
	
	// start of parkingPaging
	public Model pPaging(List<Parking> pAll, String num, Model model) {
		System.out.println("parkingPaging()");
		
		List<Parking> pList = new LinkedList<Parking>();
		
		int startPaging = 0;
		int endPaging = 15;
		int startPage = 1;
		int maxCount = 15;
		int pagingBlocks = 10;
		int n = 0;
		
		if(num != null) {
			n = Integer.parseInt(num);
			if(n % 10 == 0)
				n--;
			
			startPaging = Integer.parseInt(num) * maxCount - maxCount;
			endPaging = Integer.parseInt(num) * maxCount;
			startPage = n / pagingBlocks * pagingBlocks + 1;
		}
		System.out.println("startPaging : " + startPaging + " | endPaging : " + endPaging);
		
		if(endPaging > pAll.size())
			endPaging = pAll.size();
		int parkingSize = (pAll.size() % maxCount == 0) ? pAll.size() / maxCount : (pAll.size() / maxCount) + 1;
		 
		for(int i = startPaging; i < endPaging; i++)
			pList.add(pAll.get(i));
		
		model.addAttribute("pageNum", (startPaging / maxCount) + 1);
		model.addAttribute("pList", pList);
		model.addAttribute("parkingSize", parkingSize);
		model.addAttribute("startPage", startPage);
		
		return model;
	}
	// end of parkingPaging
	
	// start of parkingAdd
	@PostMapping("parkingAdd.do")
	public String parkingAdd(String jsonStr) {
		System.out.println("parkingAdd()");
		
		try {
			JSONParser parse = new JSONParser();
			Object o = parse.parse(jsonStr);
			JSONObject json = (JSONObject) o;
			Parking p = new Parking();
			
			p.toParking(json);
			int n = pService.parkingAdd(p);
			
			System.out.println("n : " + n);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "parkingList.do";
	}
	// end of parkingAdd
	
	// start of parkingDelete
	@PostMapping("parkingDelete.do")
	public String parkingDelete(String[] chklist, Model model) {
		System.out.println("parkingDelete()");
		
		int count = 0;
		
		for(String s : chklist)
			count += pService.parkingDelete(s);
		
		if(count != chklist.length) {
			model.addAttribute("msg", "-1");
			return "/result.jsp";
		}
		
		return "parkingList.do";
	}
	// end of parkingDelete
	
	/**
	 * @author yeahni
	 * @comment 해당 주차장 코드에 알맞는 정보
	 * @param parking_code 해당 주차장 코드
	 * @param session 로그인 확인 여부
	 * @param model 해당 주차장 정보
	 * @return
	 */
	@GetMapping("parkingDetail.do")
	public String parkingDetail(@RequestParam(required = false, defaultValue = "0")int parking_code, Model model){
		Admin admin = (Admin)session.getAttribute("admin");
		
		if(admin != null){
			if(parking_code != 0){
				Parking parking = pService.parkingDetail(parking_code);
				model.addAttribute(parking);
				
				return "/admin/parkingDetail.jsp";
			}
		}
		
		return "/admin/admin.jsp";
	}
	
	// start of parkingEdit
	@GetMapping("parkingEdit.do")
	public String parkingEdit(@RequestParam(required = false, defaultValue = "0")int parking_code, Model model){
		Admin admin = (Admin)session.getAttribute("admin");
		
		if(admin != null){
			if(parking_code != 0){
				Parking parking = pService.parkingDetail(parking_code);
				
				if(parking != null){
					model.addAttribute(parking);
				}
				
				return "/admin/parkingEdit.jsp";
			}
		}
		
		return "/admin/admin.jsp";
	}
	// end of parkingEdit
	
	// start of customerList
	@RequestMapping("customerList.do")
	public String customerList(String num, String sortValue, Model model) {
		System.out.println("customerList()");
		
		Admin admin = (Admin)session.getAttribute("admin");
		String[] options = {"c_id", "c_name", "c_date", "c_status_n", "c_status_d"};
		String sort;
		int sortFlag = 0;
		
		if(sortValue != null)
			sortFlag = Integer.parseInt(sortValue);
		sort = options[sortFlag];
		
		if(admin != null){
			List<Customer> cAll = cService.selectAll(sort);
			
			model = cPaging(cAll, num, model);
			model.addAttribute("cAllSize", cAll.size());
			model.addAttribute("sortValue", sortFlag);
			model.addAttribute("flag", 0);
			
			return "/admin/customerList.jsp";
		}
		
		return "/admin/admin.jsp";
		
	}
	// end of customerList
	
	// start of customerSearch.do
	@RequestMapping("customerSearch.do")
	public String customerSearch(String searchValue, int option, String num, Model model) {
		System.out.println("customerSearch()");
		
		Admin admin = (Admin)session.getAttribute("admin");
		String[] options = {"c_id", "c_name"};
		
		if(admin != null) {
			String searchItem = options[option];
			List<Customer> cAll = cService.cSearch(searchItem, searchValue);
			
			model = cPaging(cAll, num, model);
			model.addAttribute("cAllSizeSearch", cAll.size());
			model.addAttribute("searchValue", searchValue);
			model.addAttribute("option", option);
			model.addAttribute("flag", 1);
			
			return "admin/customerList.jsp";
		}
		
		return "/admin/admin.jsp";
	}
	
	// start of customerPaging
	public Model cPaging(List<Customer> cAll, String num, Model model) {
		System.out.println("customerpaging()");
		
		List<Customer> cList = new LinkedList<Customer>();
		int startPaging = 0;
		int endPaging = 15;
		int startPage = 1;
		int maxCount = 15;
		int pagingBlocks = 10;
		int n = 0;
		
		if(num != null) {
			n = Integer.parseInt(num);
			if(n % 10 == 0)
				n--;
			
			startPaging = Integer.parseInt(num) * maxCount - maxCount;
			endPaging = Integer.parseInt(num) * maxCount;
			startPage = n / pagingBlocks * pagingBlocks + 1;
		}
		System.out.println("startPaging : " + startPaging + " | endPaging : " + endPaging);
		
		if(endPaging > cAll.size())
			endPaging = cAll.size();
		int customerSize = (cAll.size() % maxCount == 0) ? cAll.size() / maxCount : (cAll.size() / maxCount) + 1;
		
		for(int i = startPaging; i < endPaging; i++)
			cList.add(cAll.get(i));
		
		model.addAttribute("pageNum", (startPaging / 15) + 1);
		model.addAttribute("cList", cList);
		model.addAttribute("customerSize", customerSize);
		model.addAttribute("startPage", startPage);
		
		return model;
	}
	
	// start of customerDetail
	@PostMapping("customerDetail.do")
	public String customerDetail(String c_id, Model model) {
		System.out.println("customerDetail()");
		Admin admin = (Admin)session.getAttribute("admin");
		
		if(admin != null) {
			Customer c = cService.selectById(c_id);
			
			model.addAttribute("customer", c);
			
			return "/admin/customerDetail.jsp";
		}
		return "/admin/admin.jsp";
		
	}
	
}
