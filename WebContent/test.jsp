<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> 
<c:set var="list" value="${requestScope.result}"/>
<c:choose>
  <c:when test="${empty list}">
  <h3>err.</h3>
  </c:when>
  
  <c:otherwise>
  
      <table>
        <tr>
        	<td colspan="10"><h3 style="margin:center;">고객상세정보</h3></td>
        </tr>
        <tr> 
        	<td>name</td> 
        	<td>addr</td>
        	<td>phone</td> 
        	<td>pay</td> 
        	<td>capa</td> 
        	<td>curseat</td> 
        	<td>lat</td> 
        	<td>lng</td> 
        	<td>weekbegin</td> 
        	<td>weekend</td> 
        </tr>
        
        <c:forEach var="list2" items="${list}">
          <tr>
            <td>
                ${list2.parking_name}
            </td>
            <td>
                ${list2.parking_address}
            </td>
            <td>
                ${list2.parking_phone_number}
            </td>
            <td>
                ${list2.parking_pay_type}
            </td>
            <td>
                ${list2.parking_capacity}
            </td>
            <td>
                ${list2.parking_cur_seat}
            </td>
            
            <td>
                ${list2.parking_latitude}
            </td>
            <td>
                ${list2.parking_longitude}
            </td>
            <td>
                ${list2.parking_weekdays_begin_time}
            </td>
            <td>
                ${list2.parking_weekdays_end_time}
            </td>
          </tr>
       </c:forEach>
      </table>
      
  </c:otherwise>
</c:choose>
