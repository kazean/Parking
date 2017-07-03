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
		$("select[name=searchSort]").click(function() {
			var searchSort = $("select[name=searchSort]")[0].value;
			console.log("searchFlag : " + searchFlag);
			$.ajax({url:'parkingList.do',
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
		});
		$("input[name=search]").click(function() {
			var searchValue = $("input[name=searchValue]");
			var option = $("select[name=searchitem]")[0].value;
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
		});
	}); // end of function
</script>
</head>
<body>
	<select name = "searchSort">
	    <option value = "0">코드순</option>
	    <option value = "1">이름순</option>
	    <option value = "2">주소순</option>
  	</select> 
	<br>
	<select name = "searchItem">
	    <option value = "0">전체</option>
	    <option value = "1">코드</option>
	    <option value = "2">이름</option>
  	</select>    
  	<input type="search" name="searchValue"> <input type="button" name="search" value="검색">
</body>
</html>