<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>partnerList</title>
<style>
.allPartner {border :1px solid}
.allPartner td{ border : 1px solid}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
$(function() {
	
	var $parentObj = $("article");
	if ($parentObj.length == 0)
		$parentObj = $("body");
	
	
	//추가하기 포워딩 시작
	$(".insert").click(function() {
		var url = $(this).attr("href");
		$.ajax({
			url : url,
			success : function(responseData) {
					$parentObj.empty();
					$parentObj.html(responseData.trim());
			}
		});
		return false;
	}); // 추가하기 포워딩 끝
	

	//페이징 버튼클릭시 액션
	$('.paging').click(function(){
		
	var pageno = $(this).attr('id');
	console.log(pageno);
	$.ajax({
		url:'partnerList.do',
        method: 'GET', 
        data:{
        	  'pageno': pageno,
        	  'pageClick': "pageClick"
		},
        success:function(responseData){
        	
        	$("article").empty();
            $("article").html(responseData.trim()); 
      }
      });  return false; 
  });  // 페이징 버튼클릭시 액션 끝
	
	
 
    
	//검색버튼을 클릭 했을 때
	 $(".btSearch").click(function(){
		 console.log("검색클릭");
		var searchItem = $('.searchItem').val();
		var searchValue = $('.searchValue').val();
		console.log("searchItem : "+searchItem+", searchValue : "+searchValue);
		
		$.ajax({ url:"partnerList.do",
				method:"GET",
				data:{'searchItem' : searchItem,
					  'searchValue' : searchValue,
					  'pageno': 1},
				success:function(responseData){
					
				var $parentObj = $("article");		
				if($("article").length == 0){
					$parentObj ==  $("body");
				}
				
				$parentObj.empty();
				$parentObj.html(responseData.trim());
				
				
			}
		});return false; 
	});   // 검색버튼 클릭햇을 때 끝
	
	
	
	
	//체크박스 '개별체크시' 액션 
	$("input:checkbox[name='checkbox']").click(function(){
		
		//중복 허용안함
		arrayParam = new Array(); 
	   	$("input:checkbox[name='checkbox']:checked").each(function(){
	       	arrayParam.push($(this).val());
	   	}); 
	   	if(arrayParam.length>1){
			$("input:checkbox[name='checkbox']").prop('checked', false);
			this.checked = true; 
	   }
	   	
	   	//체크된 행의 아이디값
	   	var checkedId = $(this).attr("value");
	     console.log(checkedId);

	     
	     //수정 시작
	     $('.modify').click (function(){
			console.log("수정클릭");
			
			$.ajax({
				   url: 'selectByP_id.do',
		           method: 'POST', 
		           data : {'p_id' : checkedId},
		           success:function(responseData){
		             $("article").empty();
		             $("article").html(responseData.trim()); 
		         }
		       });return false;
	      });
	   	
	   	
	     
	    //삭제 시작
	    $('.delete').click (function(){
		console.log("삭제클릭");
		
		 $.ajax({
			url : 'deletePartner.do',
			data : {'p_id' : checkedId},
			method : 'POST',
			success : function(responseData){
    			var result = responseData.trim();
    			if(result=="1"){
    				alert("성공");
    				
    				$.ajax({
    		      		   url : "partnerList.do",
    		      		   method: 'GET', 
    		      		   data:{'pageno':1},
    		      		   success : function(responseData){
    		      			   $("article").empty();
    		      			   $("article").html(responseData.trim()); 
    		      		   }
    		      	   }); return false;
    			}else{
    				alert("실패");
    			}
			}
		 }); return false;
				
	  });  // 삭제 끝	
	   	
	   	
	}); //체크박스 '개별체크시' 액션 끝
	
	
	
	//기본액션 삭제하기
	 $('.location a').click (function(){
		 event.preventDefault();
	 });
	
	
});




</script>
</head>

<body>
<br><br>
<h3>파트너 관리</h3>
<br><br><br>


<!-- 메뉴 시작-->
<div class="location">
<a class="insert" href="partnerAdd.jsp">추가하기</a>&nbsp;&nbsp;
<a class="modify" href="partnerModify.jsp">수정하기</a>&nbsp;&nbsp;
<a class="delete" href="deletePartner.do">삭제하기</a>&nbsp;&nbsp;
</div>
<!-- 메뉴 끝 -->

<!-- 검색시작 -->
<div class="search">

  <select name="select" class="searchItem">
    <option selected value="p_id">아이디</option>
    <option selected value="p_license">사업자번호</option>
    <option selected value="p_name">이름</option>
  </select> 
  
  <input name="textfield" type="text" class="searchValue"> 
  <a href=""><img src="image/searchBt.jpg" width="49" height="25" border="0"  class="btSearch"></a>
        

</div>
<!-- 검색끝 -->


<!-- 리스트시작 -->
<div class = "partnerList">
  <c:set var="partner"   value=""/>
  <table  class="allPartner">
    <tr>
      <td>
      <!-- <input type="checkbox" name="cbAll"> -->
      </td>
      <td>
      아이디
      </td>
      <td>
      비밀번호
      </td>
      <td>
      이름
      </td>
      <td>
      전화번호
      </td>
      <td>
      사업자번호
      </td>
      <td>
      은행
      </td>
       <td>
      계좌번호
      </td>
      <td>
      상태
      </td>
       <td>
      관리자 ID(admin)
      </td>
      
      
    </tr>
  
  
  
  <c:forEach  var="partner"    items="${requestScope.list}">
  
  
    <tr>
      <td>
      <input type="checkbox" name="checkbox" value="${partner.p_id}">
      </td>
      <td>
      ${partner.p_id}
      </td>
      <td>
       ${partner.p_password}
      </td>
      <td>
       ${partner.p_name}
      </td>
      <td>
       ${partner.p_phone_number}
      </td>
      <td>
        ${partner.p_license}
      </td>
      <td>
       ${partner.p_bank}
      </td>
       <td>
        ${partner.p_account}
      </td>
      <td>
        ${partner.p_status}
      </td>
       <td>
       ${partner.p_register_admin_id}
      </td>
      
      
    </tr>
  
  
  
  </c:forEach>
  
  
  <tr>
  </tr>
  
  </table>
</div>  
 <!-- 리스트끝 --> 
  
  
  <!-- 페이징시작 -->
<div>
   
  <%
      int pageno = Integer.parseInt(request.getParameter("pageno"));
     
      /* 전체리스트 길이 구해오기 시작 */
      Integer objTotal_record = (Integer)request.getAttribute("listSize");
      int total_record=objTotal_record;
      /* 전체리스트 길이 구해오기 끝 */
      
      int page_per_record_cnt = 5; 
      int group_per_page_cnt =5;     
      int record_end_no = pageno*page_per_record_cnt;       
      int record_start_no = record_end_no-(page_per_record_cnt-1);
      if(record_end_no>total_record){
        record_end_no = total_record;
      }
      int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt>0 ? 1 : 0);
      if(pageno>total_page){
        pageno = total_page;
      }
      int group_no = pageno/group_per_page_cnt+( pageno%5 >0 ? 1:0);
      int page_eno = group_no*group_per_page_cnt; 
      
      int page_sno = page_eno-(group_per_page_cnt-1); 
      if(page_eno>total_page){
        page_eno=total_page;
      }
      
      if(page_sno<0){
        page_sno = 1;
      }
      
      int prev_pageno = pageno-1;
      int next_pageno = pageno+1;
      if(prev_pageno<1){
        prev_pageno=1;
      }
      if(next_pageno>total_page){
        next_pageno=total_page/group_per_page_cnt*group_per_page_cnt+1;
        next_pageno=total_page;
      }
    %>
    
    
  <c:set var ="listSize" value="${requestScope.listSize}"/>
  
  <c:if test="${listSize > 5}">
    <a href="" id="1" class="paging"> ◀◀ </a>&nbsp;
    <a href="" id="<%=prev_pageno%>" class="paging"> ◀</a>&nbsp; 
  </c:if>

  <c:if test="${listSize > 5}"> 
    <%for(int i =page_sno;i<=page_eno;i++){%>
      <a href="" class="paging" id="<%=i%>">
        <%if(pageno == i){ %>
          [<%=i %>]
        <%}else{ %>
          <%=i %>
        <%} %>
      </a> 
    <%--  콤마   --%> 
      <%if(i<page_eno){ %>
        |
      <%} %>
    <%} %>
  </c:if>  
  
  <c:if test="${listSize <6 }"> 
  [1]
  </c:if>
     
  <c:if test="${listSize > 5}">         
      <a href="" id="<%=next_pageno%>" class="paging">▶</a>&nbsp;
      <a href="" id="<%=total_page%>" class="paging"> ▶▶</a>
  </c:if>
                        
</div>                  
  <!-- 페이징끝 -->
  


</body>
</html>