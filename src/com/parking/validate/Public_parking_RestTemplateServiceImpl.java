package com.parking.validate;

import java.io.StringReader;
import java.net.URI;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.parking.vo.Parking;

import ch.qos.logback.classic.Logger;

public class Public_parking_RestTemplateServiceImpl {
	
	 private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(Public_parking_RestTemplateServiceImpl.class);    
	    
	    public String restTemplate(int start, int end) throws Exception{
	    	String serviceKey = "414b50776b6b617a313030574a5a4244";
	    	String responseStr = "";
	        
	        RestTemplate restTpl = new RestTemplate();
	        URI uri = new URI("http://openAPI.seoul.go.kr:8088/" + serviceKey + "/xml/GetParkInfo/" + start + "/" + end + "/");
	        
	        responseStr = restTpl.getForObject(uri, String.class);
	        
	        //log.info(responseStr);
	        
	        return responseStr;    
	    }
	    
	    public List<Parking> getAddrList() throws Exception {
	    	int start = 1;
	    	int end = 1000;
	    	int size = 1000;
	    	
	    	List<Parking> publicList = new ArrayList<>();
	    	
	    	while(size >= 1000){
	    		List<Parking> list = parseXmlStr(this.restTemplate(start, end));
	    		for(Parking p : list){
	    			publicList.add(p);
	    		}
	    		size = list.size();
	    		start += 1000;
	    		end += 1000;
	    	}
	    	return publicList;
	        //return parseXmlStr(this.restTemplate());
	    }
	 
	    private List<Parking> parseXmlStr(String xmlStr){
	        List<Parking> addrList = new ArrayList<Parking>();
	        StringReader sr = new StringReader(xmlStr);
	        InputSource is = new InputSource(sr);
	        NodeList result = null;
	        try {
	            Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	            Node node = (Node) doc.getDocumentElement();
	            
	            result = node.getChildNodes();
	            
	            for (int i = 0; i < result.getLength(); i++) {
	                Node tmpNode = result.item(i);
	                if ((tmpNode.getNodeName().equals("row"))) {
	                    NodeList addrNodeList = tmpNode.getChildNodes();
	                    Parking parking = new Parking();
	                    
	                    Map<String, String> addrMap = new HashMap<>();
	                    for (int j = 0; j < addrNodeList.getLength(); j++) {
	                    	addrMap.put(addrNodeList.item(j).getNodeName(), addrNodeList.item(j).getTextContent());
	                    }
	                    
	                    parking.setParking_search_code(addrMap.get("PARKING_CODE"));
	                    
	                    parking.setParking_name(addrMap.get("PARKING_NAME"));
	                    parking.setParking_address(addrMap.get("ADDR"));
	                    
	                    // 전화번호 정보가 제공이 될 경우
	                    if(!"".equals(addrMap.get("TEL").trim())){
	                    	if(addrMap.get("TEL").indexOf(")") != -1){
	                    		parking.setParking_phone_number(addrMap.get("TEL").replace(')', '-'));
	                    	} else {
	                    		parking.setParking_phone_number(addrMap.get("TEL"));
	                    	}
	                    } else {
	                    	parking.setParking_phone_number("0");
	                    }
	                    
	                    parking.setParking_status(1);
	                    
	                    parking.setParking_operation(1);
	                    
	                    // 노상
	                    if("노상 주차장".equals(addrMap.get("PARKING_TYPE_NM"))){
	                    	parking.setParking_type(1);
	                    }
	                    // 노외
	                    else if("노외 주차장".equals(addrMap.get("PARKING_TYPE_NM"))){
	                    	parking.setParking_type(2);
	                    }
	                    
	                    // 유료일 경우
	                    if("유료".equals(addrMap.get("PAY_NM"))){
	                    	parking.setParking_pay_type(1);
	                    }
	                    
	                    // 무료일 경우
	                    else if("무료".equals(addrMap.get("PAY_NM"))){
	                    	parking.setParking_pay_type(4);
	                    }
	                    
	                    //주차 자리
	                    parking.setParking_capacity(Integer.parseInt(addrMap.get("CAPACITY")));
	                    
	                    // 현재 좌석 제공 될 경우
	                    if("미연계중".equals(addrMap.get("QUE_STATUS_NM"))){
	                    	parking.setParking_cur_seat(-1);
	                    }else{
	                    	// 현재 좌석 제공 안 될 경우
	                    	parking.setParking_cur_seat(Integer.parseInt(addrMap.get("CUR_PARKING")));
	                    }
	                    
	                    // 기본 요금 & 기본 요금 시간이 제공 될 경우
	                    if(!"0".equals(addrMap.get("RATES")) && !"0".equals(addrMap.get("TIME_RATE"))){
	                    	 parking.setParking_rates(Integer.parseInt(addrMap.get("RATES")));
	                    	 parking.setParking_rates_time(Integer.parseInt(addrMap.get("TIME_RATE")));
	                    } else {
	                    	 parking.setParking_rates(0);
	                    	 parking.setParking_rates_time(0);
	                    }
	                   
	                    // 추가 요금 & 추가 요금 시간이 제공 될 경우
	                    if(!"0".equals(addrMap.get("ADD_RATES")) && !"".equals(addrMap.get("ADD_TIME_RATE"))){
	                    	 parking.setParking_add_rates(Integer.parseInt(addrMap.get("ADD_RATES")));
	                    	 parking.setParking_add_rates_time(Integer.parseInt(addrMap.get("ADD_TIME_RATE")));
	                    } else {
	                    	 parking.setParking_add_rates(0);
	                    	 parking.setParking_add_rates_time(0);
	                    }
	                    
	                    // 일 최대 요금
	                    if(!"0".equals(addrMap.get("DAY_MAXIMUM")) && !"".equals(addrMap.get("DAY_MAXIMUM")) ){
	                    	parking.setParking_day_rates(Integer.parseInt(addrMap.get("DAY_MAXIMUM")));
	                    }else{
	                    	parking.setParking_day_rates(0);
	                    }
	                    
	                    // 월 정기 요금
	                    if(!"0".equals(addrMap.get("FULLTIME_MONTHLY")) && !"".equals(addrMap.get("FULLTIME_MONTHLY"))){
	                    	parking.setParking_day_rates(Integer.parseInt(addrMap.get("FULLTIME_MONTHLY")));
	                    }else{
	                    	parking.setParking_day_rates(0);
	                    }
	                    
	                    // 평일 운영시간을 제공한다면
	                    SimpleDateFormat sdf = new SimpleDateFormat("HHMM");
	                    parking.setParking_weekdays_begin_time(new Time(sdf.parse(addrMap.get("WEEKDAY_BEGIN_TIME")).getTime()));
	                    parking.setParking_weekdays_end_time(new Time(sdf.parse(addrMap.get("WEEKDAY_END_TIME")).getTime()));
	                    
	                    // 토요일 운영시간을 제공한다면
	                    parking.setParking_sat_begin_time(new Time(sdf.parse(addrMap.get("WEEKEND_BEGIN_TIME")).getTime()));
	                    parking.setParking_sat_end_time(new Time(sdf.parse(addrMap.get("WEEKEND_END_TIME")).getTime()));

	                    // 공휴일 운영시간을 제공한다면
	                    parking.setParking_holidays_begin_time(new Time(sdf.parse(addrMap.get("HOLIDAY_BEGIN_TIME")).getTime()));
	                    parking.setParking_holidays_end_time(new Time(sdf.parse(addrMap.get("HOLIDAY_END_TIME")).getTime()));
	                    
	                    addrList.add(parking);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return addrList;
	    }    
	    
	    
	    
}
