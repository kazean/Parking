package com.parking.control;

import java.util.ArrayList;
import java.util.Date;
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
import com.parking.service.PartnerService;
import com.parking.vo.Admin;
import com.parking.vo.Customer;
import com.parking.vo.Parking;
import com.parking.vo.Partner;

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
	@Autowired
	PartnerService ptnService;
	
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
		model.addAttribute("msg","-1");
		return "/result.jsp";
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
		model.addAttribute("msg","-1");
		return "/result.jsp";
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
				model.addAttribute("parking", parking);
				
				return "/admin/parkingDetail.jsp";
			}
		}
		
		model.addAttribute("msg", "-1");
		return "/result.jsp";
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
		
		model.addAttribute("msg", "-1");
		return "/result.jsp";
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
		model.addAttribute("msg", "-1");
		return "/result.jsp";
		
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
		model.addAttribute("msg", "-1");
		return "/result.jsp";
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
		model.addAttribute("msg", "-1");
		return "/result.jsp";
		
	}
	
	
	//start for partnerList.do
	int listSize;
	@RequestMapping("partnerList.do")
	public String partnerList(Model model,
			int pageno,
			@RequestParam(required=false, defaultValue="all") String searchItem,
			@RequestParam(required=false, defaultValue="") String searchValue,
			String pageClick
			){
		
		System.out.println("partnerList()");
		
		//초기값 셋팅
		List <Partner> list = new ArrayList<>();
		int startPage = pageno*5-4;
		int endPage = pageno*5;
		
		//리스트사이즈 (검색시 한번만 불러올 것)
		int cnt=0;
		if(cnt == 0 && pageClick == null){
			cnt ++;
			listSize = ptnService.selectForListSize(searchItem,searchValue).size();
		}
		
		//페이지클릭 하지않았을 경우의 검색
		if(pageClick == null){
			session.removeAttribute("searchItem");//(, searchItem);
			session.removeAttribute("searchValue");//, searchValue);
		}
		
		//실제리스트
		if("".equals(searchValue)){
			System.out.println("(String)session.getAttribute(searchValue) :"+(String)session.getAttribute("searchValue") );
			if((String)session.getAttribute("searchValue") !=null){
				System.out.println("if의 세션 유");
				System.out.println("searchItem : "+(String)session.getAttribute("searchItem")+(String)session.getAttribute("searchValue"));
				list = ptnService.selectAll(startPage,endPage,(String)session.getAttribute("searchItem"),(String)session.getAttribute("searchValue"));	
				
			}else{
				System.out.println("if의 세션 무");
				list = ptnService.selectAll(startPage,endPage,"",searchValue);
			}
			
		}else if(!"".equals(searchValue)){
			
			System.out.println("if else 들어옴");
			
			session.setAttribute("searchItem", searchItem);
			session.setAttribute("searchValue", searchValue);
			
			list = ptnService.selectAll(startPage,endPage,searchItem,searchValue);	
			
		}
		
		
		model.addAttribute("list",list);
		model.addAttribute("listSize",listSize);

		return "/admin/partnerList.jsp";
		
	}
	//end for partnerList.do
	
	
	//start for addPartner.do
	@PostMapping("addPartner.do")
	public String addPartner(Model model,
			String p_id,
			String p_password,
			String p_name,
			String p_phone_number,
			String p_license,
			String p_bank,
			String p_account,
			String p_status,
			String p_register_admin_id
			){
		
		
		String msg = "-1";
		Partner partner = null;
		
		if(!"".equals(p_id)){
			partner = new Partner( p_id,  p_password,  p_name,  p_phone_number,  p_license,
					 p_bank,  p_account,  null,  'N', p_register_admin_id);
			ptnService.addPartner(partner);
			msg = "1";
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}
	//end for addPartner.do
	
	
	//start for selectByP_Id.do
	@PostMapping("selectByP_id.do")
	public String selectByP_Id(Model model,
							String p_id) {
		
		System.out.println("selectByP_Id()");
		Partner partner = ptnService.selectByP_id(p_id);
		model.addAttribute("partner",partner);
		return "/admin/partnerModify.jsp";
	}
	
	//start for deletePartner
	@PostMapping("deletePartner.do")
	public String deletePartner(Model model,
								String p_id) {
		System.out.println("deletePartner()");
		String msg = "-1";
		if(p_id!=null){
			ptnService.delete(p_id);
			msg = "1";
		}
		model.addAttribute("msg",msg);
		return "/result.jsp";
	}
	//end for deletePartner
	
}
