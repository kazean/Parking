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

public class Public_parking_coordinate_RestTemplateServiceImpl {
	
	 private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(Public_parking_coordinate_RestTemplateServiceImpl.class);    
	    
	    public String restTemplate(int start, int end) throws Exception{
	    	String serviceKey = "414b50776b6b617a313030574a5a4244";
	    	String responseStr = "";
	        
	        RestTemplate restTpl = new RestTemplate();
	        URI uri = new URI("http://openapi.seoul.go.kr:8088/" + serviceKey + "/xml/SearchParkingInfo/" + start + "/" + end + "/");
//	        http://openapi.seoul.go.kr:8088/414b50776b6b617a313030574a5a4244/xml/SearchParkingInfo/1/1/
	        responseStr = restTpl.getForObject(uri, String.class);
	        
	        log.info(responseStr);
	        
	        return responseStr;    
	    }
	    
	    public List<Parking> getAddrList() throws Exception {
	    	int start = 1;
	    	int end = 1000;
	    	int size = 1000;
	    	
	    	List<Parking> publicList = new ArrayList<>();
	    	int i=1;
	    	while(size >= 1000){
	    		List<Parking> list = parseXmlStr(this.restTemplate(start, end));
	    		for(Parking p : list){
	    			System.out.println(i++);
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
	                    
	                    if(!"".equals(addrMap.get("LAT"))){
	                    	parking.setParking_latitude(Double.parseDouble(addrMap.get("LAT")));
	                    } 
	                    
	                    if(!"".equals(addrMap.get("LNG"))){
	                    	parking.setParking_longitude(Double.parseDouble(addrMap.get("LNG")));
	                    }
	                    
	                    addrList.add(parking);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return addrList;
	    }    
	    
	    
	    
}
