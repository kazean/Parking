<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>customerSearch</title>
<script>
	// start of function
	$(function() {
		var $parentObj = $("article");
		if($parentObj.length == 0)
			$parentObj = $("body");
		
		// start of select click
		$("select[name=searchSort]").click(function() {
			var searchSort = $("select[name=searchSort]").val();
			
			$.ajax({url:'customerList.do',
					method:'post',
					data:{'searchSort':searchSort},
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
			
			$.ajax({url:'customerSearch.do',
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
		});
	}); // end of function
</script>
</head>
<body>
<select name="searchSort">
	<%String[] sortList = {"아이디순", "이름순", "가입일순", "일반고객", "탈퇴한고객"};
	  Object obj1 = request.getAttribute("sortValue");
	  int n = 0;
	  if(obj1 != null) {
		  n = (int)obj1;
	  }%>
	<%for(int i = 0; i < 5; i++) { %>
		<option value="<%=i%>"
		<%if(n == i) { %>
			selected
		<%} %>><%=sortList[i]%></option>
	<%}%>
</select>
<br>
<select name="searchItem">
	<%String[] searchList = {"아이디", "이름"};
	  Object obj2 = request.getAttribute("option");
	  int o = 0;
	  if(obj2 != null) {
		  o = (int)obj2;
	  }%>
	<%for(int i = 0; i < 2; i++) { %>
		<option value="<%=i%>"
		<%if(o == i) { %>
			selected
		<%}%>><%=searchList[i]%></option>
	<%}%>
</select>
</body>
</html>