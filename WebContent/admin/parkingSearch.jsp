<%@page import="org.springframework.web.context.annotation.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<title>parkingSearch</title>
<script>
	// start of function
	$(function() {
		var $parentObj = $("article");
		if($parentObj.length == 0)
			$parentObj = $("body");
		
		// start of select click
		$("select[name=searchSort]").on("change", function() {
		//$("select[name=searchSort]").click(function() {
			var sortValue = $("select[name=searchSort]").val();
			console.log("sortValue : " + sortValue);
			$.ajax({url:'parkingList.do',
					method:'post',
					data:{'sortValue':sortValue},
					success:function(responseData) {
						if(responseData.trim() != "-1") {
							$parentObj.empty();
							$parentObj.html(responseData.trim());
						}
					}
			});
			return false;
		}); // end of select click
		
		// start of search click
		$("input[name=search]").click(function() {
			var searchValue = $("input[name=searchValue]").val();
			var option = $("select[name=searchItem]").val();
			
			$.ajax({url:'parkingSearch.do',
					method:'post',
					data:{'searchValue':searchValue, 'option':option},
					success:function(responseData) {
						if(responseData.trim() != "-1") {
							$parentObj.empty();
							$parentObj.html(responseData.trim());
						}
					}
			});
			return false;
		}); // end of search click
	}); // end of function
</script>
</head>
<body>
	<h5>정렬 
	<select name="searchSort">
		<%String[] sortList = {"코드순", "이름순", "주소순"};
		  Object obj1 = request.getAttribute("sortValue");
		  int n = 0;
		  if(obj1 != null) {
			  n = (int)obj1;
		  }%>
		<%for(int i = 0; i < 3; i++) { %>
			<option value="<%=i%>"
			<%if(n == i) {%>
				selected
			<%} %>><%=sortList[i]%></option>
		<%} %>
  	</select></h5> 
	<br>
	<select name = "searchItem">
		<%String[] searchList = {"코드", "이름"};
		  Object obj2 = request.getAttribute("option");
		  int o = 0;
		  if(obj2 != null) {
			  o = (int)obj2;
		  }%>
		<%for(int i = 0; i < 2; i++) {%>
			<option value="<%=i%>"
			<%if(o == i) { %>
				selected
			<%}%>><%=searchList[i]%></option>
		<%}%>
  	</select>
  	<input type="search" name="searchValue"> <input type="button" name="search" value="검색">
</body>
</html>