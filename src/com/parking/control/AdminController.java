package com.parking.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.imgscalr.Scalr;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@Resource(name="uploadPath")
	String uploadPath;

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
	public String parkingAdd(String jsonStr, Model model) {
		System.out.println("parkingAdd()");
			
		try {
			JSONParser parse = new JSONParser();
			Object o = parse.parse(jsonStr);
			JSONObject json = (JSONObject) o;
			Parking p = new Parking();

			p.toParking(json);
			int n = pService.parkingAdd(p);

			System.out.println("n : " + n);
			if(n != 0)
				return "parkingList.do";
			else
				model.addAttribute("msg", "-1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "result.jsp";
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
	@PostMapping("parkingDetail.do")
	public String parkingDetail(@RequestParam(required = false, defaultValue = "0")int parking_code, Model model){
		System.out.println("parkingDetail()");
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
	
	// start of parkingModify
	@GetMapping("parkingModify.do")
	public String parkingEdit(String jsonStr, Model model){
		Admin admin = (Admin)session.getAttribute("admin");
		
		if(admin != null) {
			try{
				JSONParser parse = new JSONParser();
				Object o = parse.parse(jsonStr);
				JSONObject json = (JSONObject) o;
				Parking p = new Parking();
				
				p.toParking(json);
				int n = pService.parkingModify(p);
				
				System.out.println("n : " + n);
				if(n != 0) {
					model.addAttribute("parking_code", p.getParking_code());
					return "parkingDetail.do";
				}
			} catch (ParseException e) {
				e.printStackTrace();
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
			) {
		
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
			if((String)session.getAttribute("searchValue") !=null){
				list = ptnService.selectAll(startPage,endPage,(String)session.getAttribute("searchItem"),(String)session.getAttribute("searchValue"));	
			}else{
				list = ptnService.selectAll(startPage,endPage,"",searchValue);
			}
		}else if(!"".equals(searchValue)){
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
			) {
		
		
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
		Partner partner=null;
		try {
			partner = ptnService.selectByP_id(p_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	
	
	
	
	
	
	//파트너 업로드 할때 기본이미지 넣기위한 메소드들 시작 
	
	//객체로 파일 업로드 1
	@RequestMapping(value="uploadAjax.do", method=RequestMethod.GET)
	public void uploadAjax(){
		System.out.println("IsMaterController , uploadAjax GET");
		// uploadAjax.jsp로 포워딩
	}

	//객체로 파일 업로드 2
	@ResponseBody
	@RequestMapping(value="uploadAjax.do", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		System.out.println("IsMaterController , uploadAjax POST");
		
		return new ResponseEntity<String>(uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}

	//파일 업로드 메서드 3
		public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
			System.out.println("IsMaterController , uploadFile");
			UUID uuid = UUID.randomUUID();
			String savedName = uuid.toString() + "_" + originalName;
			String savedPath = calcPath(uploadPath);
			File target = new File(uploadPath + savedPath, savedName);
			FileCopyUtils.copy(fileData, target);
			String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
			String uploadedFileName = null;
			if (getMediaType(formatName) != null) {
				uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
			} else {
				uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
			}
			return uploadedFileName;
		}
		
		
		// 날짜별 디렉토리 따로만들기
		private static String calcPath(String uploadPath) {
			System.out.println("IsMaterController , calcPath");
			Calendar cal = Calendar.getInstance();
			String yearPath = File.separator + cal.get(Calendar.YEAR);
			System.out.println(yearPath);
			String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
			System.out.println(monthPath);
			String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
			System.out.println(datePath);
			makeDir(uploadPath, yearPath, monthPath, datePath);
			return datePath;
		}
	
		// 저장소 생성
		private static void makeDir(String uploadPath, String... paths) {
			System.out.println("IsMaterController , makeDir");
			if (new File(paths[paths.length - 1]).exists()){
				System.out.println("저장소 있어서 안 만들게요");
				return;
			}
			for (String path : paths) {
				File dirPath = new File(uploadPath + path);
				if (!dirPath.exists()) {
					System.out.println("저장소없어서 만들었어요.");
					dirPath.mkdir(); //디렉토리 생성
				}
			}
		}    

		// 썸네일 생성
		private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
			System.out.println("IsMaterController , makeThumbnail");
			BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
			BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
			String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
			File newFile = new File(thumbnailName);
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			ImageIO.write(destImg, formatName.toUpperCase(), newFile);
			return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		}

		// 아이콘 생성
		private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
			System.out.println("IsMaterController , makeIcon");
			String iconName = uploadPath + path + File.separator + fileName;
			return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		}
		
		//타입확인
		private static Map<String, MediaType> mediaMap;
		static {
			mediaMap = new HashMap<String, MediaType>();
			mediaMap.put("JPG", MediaType.IMAGE_JPEG);
			mediaMap.put("GIF", MediaType.IMAGE_GIF);
			mediaMap.put("PNG", MediaType.IMAGE_PNG);
		}
		public static MediaType getMediaType(String type) {
			return mediaMap.get(type.toUpperCase());
		}
		
		
		//파트너 업로드 할때 기본이미지 넣기위한 메소드들 끝
		
		
}
